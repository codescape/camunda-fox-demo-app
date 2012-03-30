package de.codescape.process;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.DependencyResolvers;
import org.jboss.shrinkwrap.resolver.api.maven.MavenDependencyResolver;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

import static de.codescape.process.TwitterPosting.PROCESS_DEFINITION_FILE;
import static de.codescape.process.TwitterPosting.PROCESS_DEFINITION_KEY;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(Arquillian.class)
public class TwitterPostingProcessTest {

    private static WebArchive createProcessArchive(String archiveName) {
        MavenDependencyResolver resolver = DependencyResolvers.use(MavenDependencyResolver.class)
                .loadMetadataFromPom("pom.xml");
        return ShrinkWrap.create(WebArchive.class, archiveName)
                .addAsManifestResource("ARQUILLIAN-MANIFEST-JBOSS7.MF", "MANIFEST.MF")
                .addAsWebResource("META-INF/processes.xml", "WEB-INF/classes/META-INF/processes.xml")
                .addAsLibraries(resolver.artifact("com.camunda.fox:fox-platform-client").resolveAsFiles());
    }

    @Deployment
    public static Archive<?> deployProcessArchive() {
        return createProcessArchive("twitter-posting-process.war")
                .addAsResource("META-INF/beans.xml")
                .addClass(PostTweetDelegate.class)
                .addAsResource(PROCESS_DEFINITION_FILE);
    }

    @Inject
    public RuntimeService runtimeService;

    @Inject
    public RepositoryService repositoryService;

    @Test
    public void shouldBeAbleToDeployProcessToProcessEngine() {
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                .processDefinitionKey(PROCESS_DEFINITION_KEY)
                .singleResult();
        assertNotNull(processDefinition);
    }

    @Test
    public void shouldBeAbleToStartProcessInstanceWithParameters() {
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("message", "Hello World");
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(PROCESS_DEFINITION_KEY, variables);
        assertNotNull(processInstance.getId());
        assertTrue(processInstance.isEnded());
    }

}
