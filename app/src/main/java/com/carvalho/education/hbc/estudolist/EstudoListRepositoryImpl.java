package com.carvalho.education.hbc.estudolist;

import com.carvalho.education.hbc.entities.Estudo;
import com.carvalho.education.hbc.estudolist.events.EstudoListEvent;
import com.carvalho.education.hbc.lib.base.EventBus;
import com.raizlabs.android.dbflow.sql.language.Select;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Cristiano on 14/07/2016.
 */
public class EstudoListRepositoryImpl implements EstudoListRepository {
    private EventBus eventBus;

    public EstudoListRepositoryImpl(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    @Override
    public void getSavedEstudo() {
        //FlowCursorList<Estudo> storedEstudo = new FlowCursorList<Estudo>(false, new Select().from(Estudo.class));
        List<Estudo> storedEstudo = new Select().from(Estudo.class).queryList();
        post(EstudoListEvent.READ_EVENT, storedEstudo);
    }

    @Override
    public void updateEstudo(Estudo estudo) {
        estudo.update();
        post();
    }

    @Override
    public void removeEstudo(Estudo estudo) {
        estudo.delete();
        post(EstudoListEvent.DELETE_EVENT, Arrays.asList(estudo));
    }

    private void post(int type, List<Estudo> estudoList){
        EstudoListEvent event = new EstudoListEvent();
        event.setEstudos(estudoList);
        event.setType(type);
        eventBus.post(event);
    }

    private void post() {
        post(EstudoListEvent.UPDATE_EVENT, null);
    }
}
