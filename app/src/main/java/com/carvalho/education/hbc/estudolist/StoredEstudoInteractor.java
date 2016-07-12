package com.carvalho.education.hbc.estudolist;

import com.carvalho.education.hbc.entities.Estudo;

/**
 * Created by 06094547659 on 12/07/2016.
 */
public interface StoredEstudoInteractor {
    void executeUpdate(Estudo estudo);
    void executeDelete(Estudo estudo);
}
