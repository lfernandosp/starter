package com.fernandopereira.starterapp.inject;

import com.fernandopereira.starterapp.inject.component.ApplicationComponent;
import com.fernandopereira.starterapp.inject.component.DaggerApplicationComponent;


public class AppDependencyInjector {

    private static ApplicationComponent sApplicationComponent;

    public synchronized static void init() {
        sApplicationComponent = DaggerApplicationComponent.builder()
                .build();
    }

    public static ApplicationComponent getApplicationComponent() {
        return sApplicationComponent;
    }
}
