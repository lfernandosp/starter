package com.fernandopereira.starterapp.inject.component;

import com.fernandopereira.starterapp.inject.module.ViewModule;
import com.fernandopereira.starterapp.view.android.MainActivityFragment;

import dagger.Subcomponent;

@Subcomponent(
        modules = {
                ViewModule.class,
        }
)
public interface ViewComponent {

    void inject(MainActivityFragment mainActivityFragment);

}
