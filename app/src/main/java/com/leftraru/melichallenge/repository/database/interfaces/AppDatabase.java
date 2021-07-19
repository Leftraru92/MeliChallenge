package com.leftraru.melichallenge.repository.database.interfaces;

import com.leftraru.melichallenge.repository.database.interfaces.ItemDao;
import com.leftraru.melichallenge.repository.database.model.ItemEntity;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {ItemEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ItemDao itemDao();
}
