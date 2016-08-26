package com.fernandopereira.starterapp.inject.component;

import com.fernandopereira.starterapp.inject.module.RestServiceModule;
import com.fernandopereira.starterapp.inject.module.StorageModule;
import com.fernandopereira.starterapp.inject.module.ViewModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        RestServiceModule.class,
        StorageModule.class
})

public interface ApplicationComponent {

    ViewComponent addSubmodule(ViewModule viewModule);

}
