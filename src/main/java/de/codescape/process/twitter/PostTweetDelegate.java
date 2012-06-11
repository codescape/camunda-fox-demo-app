package de.codescape.process.twitter;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

import javax.inject.Named;
import java.util.logging.Logger;

@Named
public class PostTweetDelegate implements JavaDelegate {

    private static final Logger log = Logger.getLogger(PostTweetDelegate.class.getName());

    public void execute(DelegateExecution execution) throws Exception {
        log.info("Sending new tweet: " + execution.getVariable("message").toString());
    }

}
