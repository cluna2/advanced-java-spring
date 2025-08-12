/* CodingNomads (C)2024 */
package com.codingnomads.corespring.examples.scopeannotaion;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ScopeAnnotationDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(ScopeAnnotationDemoConfig.class);
        ctx.refresh();
        SingletonBean singletonBean1 = ctx.getBean(SingletonBean.class);
        SingletonBean singletonBean2 = ctx.getBean(SingletonBean.class);

        System.out.println("-----Hashcode of SingletonBean-----");
        System.out.println(singletonBean1.hashCode());
        System.out.println(singletonBean2.hashCode());

        final PrototypeBean prototypeBean1 = ctx.getBean(PrototypeBean.class);
        final PrototypeBean prototypeBean2 = ctx.getBean(PrototypeBean.class);

        System.out.println("-----Hashcode of PrototypeBean-----");
        System.out.println(prototypeBean1.hashCode());
        System.out.println(prototypeBean2.hashCode());
        System.out.println();

        SillyClass1 singletonSilly1 = ctx.getBean(SillyClass1.class);
        SillyClass1 singletonSilly2 = ctx.getBean(SillyClass1.class);

        System.out.println("Hashcode of Singleton Sillys");
        System.out.println(singletonSilly1.hashCode());
        System.out.println(singletonSilly2.hashCode());

        final SillyClass2 prototypeSilly1 = ctx.getBean(SillyClass2.class);
        final SillyClass2 prototypeSilly2 = ctx.getBean(SillyClass2.class);

        System.out.println("Hashcode of Prototype Sillys");
        System.out.println(prototypeSilly1.hashCode());
        System.out.println(prototypeSilly2.hashCode());
        ctx.close();
    }
}
