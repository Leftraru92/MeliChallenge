package com.leftraru.melichallenge.repository.database.interfaces;

import com.leftraru.melichallenge.repository.database.model.ItemEntity;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface ItemDao {

    @Query("SELECT * FROM items ORDER BY id DESC")
    List<ItemEntity> getAll();

    @Insert
    void insertAll(ItemEntity... items);

    @Delete
    void delete(ItemEntity item);

}
