package com.carvalho.education.hbc.entities;

import com.carvalho.education.hbc.db.EstudoDatabase;
import com.google.gson.annotations.SerializedName;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.util.Date;

/**
 * Created by 06094547659 on 11/07/2016.
 */
@Table(database = EstudoDatabase.class)
public class Estudo extends BaseModel{
    @SerializedName("estudo_id")
    @PrimaryKey private String estudoID;

    @Column private Date date;
    @Column
    private String time;
    @Column private String comment;

    private boolean pinned;

    public String getEstudoID() {
        return estudoID;
    }

    public void setEstudoID(String estudoID) {
        this.estudoID = estudoID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public boolean isPinned() {
        return pinned;
    }

    public void setPinned(boolean pinned) {
        this.pinned = pinned;
    }
    @Override
    public boolean equals(Object obj){
        boolean equal = false;
        if (obj instanceof Estudo){
            Estudo estudo = (Estudo)obj;
            equal = this.estudoID.equals(estudo.getEstudoID());
        }
        return equal;
    }
}
