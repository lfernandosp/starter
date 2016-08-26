package com.fernandopereira.starterapp.inject.component;

import com.fernandopereira.starterapp.inject.module.DetailViewModule;

import dagger.Subcomponent;

@Subcomponent(
        modules = {
                DetailViewModule.class,
        }
)
public interface DetailViewComponent {

}
