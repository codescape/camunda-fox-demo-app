package de.codescape.process.book;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

import javax.inject.Named;

@Named
public class PrintChapterDelegate implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        System.out.println(execution.getVariable("loopCounter") + ": " + execution.getVariable("chapter"));
    }

}
