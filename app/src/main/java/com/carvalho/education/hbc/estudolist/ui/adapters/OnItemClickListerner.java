package com.carvalho.education.hbc.estudolist.ui.adapters;

import com.carvalho.education.hbc.entities.Estudo;

/**
 * Created by 06094547659 on 12/07/2016.
 */
public interface OnItemClickListerner {
    void onDeleteClick(Estudo estudo);
    void onItemClick(Estudo estudo);
}
