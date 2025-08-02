package com.codingnomads.ioc.examples.dependencylookup;

public class MyGreetingProvider implements GreetingProvider{

    @Override
    public String getGreeting() {
        return "This is my custom greeting";
    }
}
