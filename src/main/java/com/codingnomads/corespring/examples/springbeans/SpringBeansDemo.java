/* CodingNomads (C)2024 */
package com.codingnomads.corespring.examples.springbeans;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringBeansDemo {

    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringBeansDemoConfig.class);
        SpringDeveloper springDeveloper = ctx.getBean(SpringDeveloper.class);
        System.out.println(
                "Spring Developer Address: " + springDeveloper.getAddress().getStreetNumber() + " "
                        + springDeveloper.getAddress().getStreet());
        System.out.println(
                "Spring Developer Computer: " + springDeveloper.getComputer().getName() + " "
                    + springDeveloper.getComputer().getProcessor()
        );
    }
}
