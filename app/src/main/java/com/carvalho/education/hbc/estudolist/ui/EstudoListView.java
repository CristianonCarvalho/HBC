package com.carvalho.education.hbc.estudolist.ui;

import com.carvalho.education.hbc.entities.Estudo;

import java.util.List;

/**
 * Created by 06094547659 on 12/07/2016.
 */
public interface EstudoListView {
    void setEstudos(List<Estudo> data);
    void estudoUpdate();
    void estudoDeleted(Estudo estudo);
}
