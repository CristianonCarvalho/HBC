package com.carvalho.education.hbc.estudolist.ui.adapters;

import com.carvalho.education.hbc.entities.Estudo;
import com.h6ah4i.android.widget.advrecyclerview.swipeable.SwipeableItemConstants;

/**
 * Created by 06094547659 on 12/07/2016.
 */
public interface OnItemClickListerner extends SwipeableItemConstants {
    void onDeleteClick(Estudo estudo);
    void onItemClick(Estudo estudo);
}
