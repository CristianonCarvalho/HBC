package com.carvalho.education.hbc.estudolist.di;

import com.carvalho.education.hbc.entities.Estudo;
import com.carvalho.education.hbc.estudolist.EstudoListInteractor;
import com.carvalho.education.hbc.estudolist.EstudoListInteractorImpl;
import com.carvalho.education.hbc.estudolist.EstudoListPresenter;
import com.carvalho.education.hbc.estudolist.EstudoListPresenterImpl;
import com.carvalho.education.hbc.estudolist.EstudoListRepository;
import com.carvalho.education.hbc.estudolist.EstudoListRepositoryImpl;
import com.carvalho.education.hbc.estudolist.StoredEstudoInteractor;
import com.carvalho.education.hbc.estudolist.StoredEstudoInteractorImpl;
import com.carvalho.education.hbc.estudolist.ui.EstudoListView;
import com.carvalho.education.hbc.estudolist.ui.adapters.EstudoAdapter;
import com.carvalho.education.hbc.estudolist.ui.adapters.OnItemClickListerner;
import com.carvalho.education.hbc.lib.base.EventBus;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ykro.
 */
@Module
public class EstudoListModule {
    EstudoListView view;
    OnItemClickListerner clickListerner;

    public EstudoListModule(EstudoListView view, OnItemClickListerner clickListerner) {
        this.view = view;
        this.clickListerner = clickListerner;
    }

    @Provides
    @Singleton
    EstudoListView provideEstudoListView() {
        return this.view;
    }

    @Provides
    @Singleton
    EstudoListPresenter provideEstudoListPresenter(EventBus eventBus, EstudoListView view, EstudoListInteractor listInteractor, StoredEstudoInteractor storedInteractor) {
        return new EstudoListPresenterImpl(eventBus, view, listInteractor, storedInteractor);
    }

    @Provides
    @Singleton
    StoredEstudoInteractor provideStoredEstudoInteractor(EstudoListRepository repository) {
        return new StoredEstudoInteractorImpl(repository);
    }

    @Provides
    @Singleton
    EstudoListInteractor provideEstudoListInteractor(EstudoListRepository repository) {
        return new EstudoListInteractorImpl(repository);
    }

    @Provides
    @Singleton
    EstudoListRepository provideEstudoListRepository(EventBus eventBus) {
        return new EstudoListRepositoryImpl(eventBus);
    }

    @Provides
    @Singleton
    EstudoAdapter provideEstudoAdapter(estudoList, OnItemClickListerner onItemClickListerner) {
        return new EstudoAdapter(estudoList, onItemClickListerner);
    }

    @Provides
    @Singleton
    OnItemClickListerner provideOnItemClickListerner() {
        return this.clickListerner;
    }

    @Provides
    @Singleton
    List<Estudo> provideEmptyList() {
        return new ArrayList<Estudo>();
    }
}
