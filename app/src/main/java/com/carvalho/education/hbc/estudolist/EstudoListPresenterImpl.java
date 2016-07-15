package com.carvalho.education.hbc.estudolist;

import com.carvalho.education.hbc.entities.Estudo;
import com.carvalho.education.hbc.estudolist.events.EstudoListEvent;
import com.carvalho.education.hbc.estudolist.ui.EstudoListView;
import com.carvalho.education.hbc.lib.base.EventBus;

import org.greenrobot.eventbus.Subscribe;


/**
 * Created by Cristiano on 14/07/2016.
 */
public class EstudoListPresenterImpl implements EstudoListPresenter {
    private EventBus eventBus;
    private EstudoListView view;
    private EstudoListInteractor listInteractor;
    private StoredEstudoInteractor storedInteractor;

    public EstudoListPresenterImpl(EventBus eventBus, EstudoListView view, EstudoListInteractor listInteractor, StoredEstudoInteractor storedInteractor) {
        this.eventBus = eventBus;
        this.view = view;
        this.listInteractor = listInteractor;
        this.storedInteractor = storedInteractor;
    }

    @Override
    public void onCreate() {
        eventBus.register(this);
    }

    @Override
    public void onDestroy() {
        eventBus.unregister(this);
        view = null;
    }

    @Override
    public void getEstudos() {
        listInteractor.execute();
    }

    @Override
    public void removeEstudo(Estudo estudo) {
        storedInteractor.executeDelete(estudo);
    }

    @Override
    @Subscribe
    public void onEventMainThread(EstudoListEvent event) {
        if (this.view != null) {
            switch (event.getType()){
                case EstudoListEvent.READ_EVENT:
                    view.setEstudos(event.getEstudos());
                    break;
                case EstudoListEvent.UPDATE_EVENT:
                    view.estudoUpdate();
                    break;
                case EstudoListEvent.DELETE_EVENT:
                    Estudo estudo = event.getEstudos().get(0);
                    view.estudoDeleted(estudo);
                    break;

            }
        }
    }

    @Override
    public EstudoListView getView() {
        return this.view;
    }
}
