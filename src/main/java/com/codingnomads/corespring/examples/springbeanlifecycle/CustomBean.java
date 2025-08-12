package com.codingnomads.corespring.examples.springbeanlifecycle;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class CustomBean implements BeanNameAware {

    @Override
    public void setBeanName(@NonNull String name) {
        System.out.println("My Bean name is Carl");
    }
}
