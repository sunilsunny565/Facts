package com.assignment.facts.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.assignment.facts.database.constants.Tables
import com.assignment.facts.model.Facts

interface FactsDao {

    @Query("SELECT * FROM ${Tables.TABLE_FACTS}")
    fun getAllFacts(): LiveData<MutableList<Facts>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(callData: List<Facts>)

    @Query("DELETE FROM ${Tables.TABLE_FACTS}")
    fun clearAllData() {
    }
}