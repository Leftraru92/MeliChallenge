package com.leftraru.melichallenge.repository.database.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "items")
public class ItemEntity {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public String searchKey;


    public ItemEntity() {
    }
}
