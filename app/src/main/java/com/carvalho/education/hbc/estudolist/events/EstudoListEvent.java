package com.carvalho.education.hbc.estudolist.events;

import com.carvalho.education.hbc.entities.Estudo;

import java.util.List;

/**
 * Created by 06094547659 on 12/07/2016.
 */
public class EstudoListEvent {
    private int type;
    private List<Estudo> estudos;

    public final static int READ_EVENT = 0;
    public final static int UPDATE_EVENT = 1;
    public final static int DELETE_EVENT = 2;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<Estudo> getEstudos() {
        return estudos;
    }

    public void setEstudos(List<Estudo> estudos) {
        this.estudos = estudos;
    }
}
