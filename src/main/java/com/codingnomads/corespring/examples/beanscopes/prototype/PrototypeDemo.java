/* CodingNomads (C)2024 */
package com.codingnomads.corespring.examples.beanscopes.prototype;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class PrototypeDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(PrototypeDemoConfig.class);
        ctx.refresh();

        SpringBean springBean1 = ctx.getBean(SpringBean.class);
        System.out.println("Hash code: " + springBean1.hashCode());

        SpringBean springBean2 = ctx.getBean(SpringBean.class);
        System.out.println("Hash code: " + springBean2.hashCode());


        MyBean myBean1 = ctx.getBean(MyBean.class);
        MyBean myBean2 = ctx.getBean(MyBean.class);
        ctx.close();
    }
}
