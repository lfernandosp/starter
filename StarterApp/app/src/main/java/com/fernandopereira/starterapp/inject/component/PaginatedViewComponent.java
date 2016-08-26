package com.fernandopereira.starterapp.inject.component;

import com.fernandopereira.starterapp.inject.module.PaginatedViewModule;
import com.fernandopereira.starterapp.view.android.MainActivityFragment;

import dagger.Subcomponent;

@Subcomponent(
        modules = {
                PaginatedViewModule.class,
        }
)
public interface PaginatedViewComponent {

    void inject(MainActivityFragment mainActivityFragment);

}
