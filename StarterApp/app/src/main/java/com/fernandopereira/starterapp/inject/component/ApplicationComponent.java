package com.fernandopereira.starterapp.inject.component;

import com.fernandopereira.starterapp.inject.module.DetailViewModule;
import com.fernandopereira.starterapp.inject.module.PaginatedViewModule;
import com.fernandopereira.starterapp.inject.module.RestServiceModule;
import com.fernandopereira.starterapp.inject.module.StorageModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        RestServiceModule.class,
        StorageModule.class
})

public interface ApplicationComponent {

    PaginatedViewComponent addSubmodule(PaginatedViewModule paginatedViewModule);

    DetailViewComponent addSubmodule(DetailViewModule detailViewModule);

}
