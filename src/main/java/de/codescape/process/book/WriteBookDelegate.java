package de.codescape.process.book;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named
public class WriteBookDelegate implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        List<String> chapters = new ArrayList<String>();

        // add 5 chapters
        chapters.add("Lorem ipsum dolor sit amet, consetetur tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua.");
        chapters.add("Duis autem vel eum iriure dolor in hendrerit in vulputate facilisis at vero eros et accumsan et iusto odio.");
        chapters.add("Ut wisi enim ad minim veniam, quis nostrud exerci tation ullamcorper suscipit lobortis nisl ut aliquip ex ea commodo consequat.");
        chapters.add("Nam liber tempor cum soluta nobis eleifend option congue nihil imperdiet doming id quod mazim placerat facer possim assum.");
        chapters.add("Duis autem vel eum iriure dolor in hendrerit in vulputate velit esse molestie consequat, vel illum dolore eu feugiat nulla facilisis.");

        execution.setVariable("book", chapters);
    }

}
