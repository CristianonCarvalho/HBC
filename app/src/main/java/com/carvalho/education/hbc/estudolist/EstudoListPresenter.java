package com.carvalho.education.hbc.estudolist;

import com.carvalho.education.hbc.entities.Estudo;
import com.carvalho.education.hbc.estudolist.events.EstudoListEvent;
import com.carvalho.education.hbc.estudolist.ui.EstudoListView;

/**
 * Created by 06094547659 on 12/07/2016.
 */
public interface EstudoListPresenter  {
    void onCreate();
    void onDestroy();

    void getEstudos();
    void removeEstudo(Estudo estudo);
    void onEventMainThread(EstudoListEvent event);

    EstudoListView getView();

}
