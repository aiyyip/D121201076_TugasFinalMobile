package com.d121201076.tudu

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.Dao

@Dao
interface Dao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(tugas: Tugas)

    @Delete
    suspend fun delete(tugas: Tugas)

    @Query("Select * from Tugas where selesai == 0")
    fun getAll(): LiveData<List<Tugas>>

    @Query("Update Tugas set selesai = 1 where id=:id")
    suspend fun tugasSelesai(id: Int)

}