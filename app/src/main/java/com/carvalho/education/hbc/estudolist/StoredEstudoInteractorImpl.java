package com.carvalho.education.hbc.estudolist;

import com.carvalho.education.hbc.entities.Estudo;

/**
 * Created by Cristiano on 14/07/2016.
 */
public class StoredEstudoInteractorImpl implements StoredEstudoInteractor {
    private EstudoListRepository repository;

    public StoredEstudoInteractorImpl(EstudoListRepository repository) {
        this.repository = repository;
    }

    @Override
    public void executeUpdate(Estudo estudo) {
        repository.updateEstudo(estudo);
    }

    @Override
    public void executeDelete(Estudo estudo) {
        repository.removeEstudo(estudo);
    }
}
