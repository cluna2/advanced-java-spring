package com.codingnomads.corespring.examples.application_context.customeventlistener;

import org.springframework.context.ApplicationListener;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class UserRegistrationCompletedEventCustomListener implements ApplicationListener<UserRegistrationCompletedEvent> {

    @Override
    public void onApplicationEvent(@NonNull UserRegistrationCompletedEvent event) {
        System.out.println("I'm a second listener!");
    }
}
