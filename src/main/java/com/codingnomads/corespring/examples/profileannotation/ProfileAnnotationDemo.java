/* CodingNomads (C)2024 */
package com.codingnomads.corespring.examples.profileannotation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class ProfileAnnotationDemo {
    public static void main(String[] args) {
        final ConfigurableApplicationContext ctx = SpringApplication.run(ProfileAnnotationDemo.class);
        final SpringDeveloper springDeveloper = ctx.getBean(SpringDeveloper.class);
        final MyPOJO pojo = ctx.getBean(MyPOJO.class);
        System.out.println("Spring Developer app version: " + springDeveloper.getVersion());
        System.out.println("MyPOJO app version: " + pojo.getVersion());
        ctx.close();
    }
}
