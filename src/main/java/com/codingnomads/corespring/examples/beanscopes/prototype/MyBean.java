package com.codingnomads.corespring.examples.beanscopes.prototype;

public class MyBean {
    public MyBean() {System.out.println("I'm a custom bean with hashcode: " + this.hashCode());}
}
