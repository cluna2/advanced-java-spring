/* CodingNomads (C)2024 */
package com.codingnomads.corespring.examples.beanannotation;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BeanAnnotationDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(BeanAnnotationConfig.class);
        ctx.refresh();
        SampleBean sampleBean = ctx.getBean("friendly_bean_name", SampleBean.class);
        SampleBean myBean = ctx.getBean("different_friendly_name", SampleBean.class);
        ctx.close();
    }
}
