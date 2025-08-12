/* CodingNomads (C)2024 */
package com.codingnomads.corespring.lab.mylab;

import com.codingnomads.corespring.lab.mylab.deploy.CoreLabConfigDeploy;
import com.codingnomads.corespring.lab.mylab.test.CoreLabConfigTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

public class CoreLabApplication {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        String profileString = Arrays.asList(ctx.getEnvironment().getActiveProfiles()).contains("deploy") ? "deploy" : "test";
        Class config = profileString.equals("deploy") ? CoreLabConfigDeploy.class : CoreLabConfigTest.class;
        System.out.println("Using profile: " + profileString);
        ctx.register(config);
        ctx.refresh();


        Chair chair = ctx.getBean(Chair.class);
        System.out.println("\nSingleton Chair name: " + chair.getName() + " with Hash code: " + chair.hashCode());
        Chair secondChair = ctx.getBean(Chair.class);
        System.out.println("Second (singleton) chair name: " + chair.getName() + " with Hash code: " + secondChair.hashCode());
        System.out.println("Chairs are ready\n");


        String monitorString = profileString.equals("deploy") ? "deployMonitor" : "testMonitor";
        Monitor profileMonitor = ctx.getBean(monitorString, Monitor.class);
        System.out.println("Monitor constructed with name: " + profileMonitor.getName()
                + " with size: " + profileMonitor.getSizeInches() + " and with Hash Code: " + profileMonitor.hashCode());
        System.out.println("Monitors are ready\n");

        ctx.close();

        System.out.println("App is done!");

    }

}
