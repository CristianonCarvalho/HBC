package com.carvalho.education.hbc.estudolist.di;

import com.carvalho.education.hbc.estudolist.EstudoListPresenter;
import com.carvalho.education.hbc.estudolist.ui.adapters.EstudoAdapter;
import com.carvalho.education.hbc.lib.di.LibsModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by ykro.
 */
@Singleton
@Component(modules = {EstudoListModule.class, LibsModule.class})
public interface EstudoListComponent {
    //void inject(RecipeMainActivity activity);
    //ImageLoader getImageLoader();
    EstudoListPresenter getPresenter();

    EstudoAdapter getAdapter();
}
