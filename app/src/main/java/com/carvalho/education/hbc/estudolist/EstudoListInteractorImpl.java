package com.carvalho.education.hbc.estudolist;

/**
 * Created by Cristiano on 14/07/2016.
 */
public class EstudoListInteractorImpl implements EstudoListInteractor {
    private EstudoListRepository repository;

    public EstudoListInteractorImpl(EstudoListRepository repository) {
        this.repository = repository;
    }


    @Override
    public void execute() {
        repository.getSavedEstudo();
    }
}
