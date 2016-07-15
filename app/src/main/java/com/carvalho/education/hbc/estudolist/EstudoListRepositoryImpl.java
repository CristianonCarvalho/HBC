package com.carvalho.education.hbc.estudolist;

import com.carvalho.education.hbc.entities.Estudo;
import com.carvalho.education.hbc.estudolist.events.EstudoListEvent;
import com.carvalho.education.hbc.lib.base.EventBus;

import java.util.List;

/**
 * Created by Cristiano on 14/07/2016.
 */
public class EstudoListRepositoryImpl implements EstudoListRepository {
    private EventBus eventBus;

    @Override
    public void getSavedEstudo() {

    }

    @Override
    public void updateEstudo(Estudo estudo) {

    }

    @Override
    public void removeEstudo(Estudo estudo) {

    }

    private void post(int type, List<Estudo> estudoList){
        EstudoListEvent event = new EstudoListEvent();
        event.setEstudos(estudoList);
        event.setType(type);
        eventBus.post(event);
    }
    private void post(int type) {
        post(EstudoListEvent.UPDATE_EVENT, null);
    }
}
