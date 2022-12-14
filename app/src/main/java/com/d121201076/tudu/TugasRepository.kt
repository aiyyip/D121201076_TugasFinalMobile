package com.d121201076.tudu

import androidx.lifecycle.LiveData

class TugasRepository(private val dao: Dao) {
    val semuaTugas: LiveData<List<Tugas>> = dao.getAll()

    suspend fun insert(tugas: Tugas) {
        dao.insert(tugas)
    }

    suspend fun delete(tugas: Tugas) {
        dao.delete(tugas)
    }

    suspend fun tugasSelesai(id: Int){
        dao.tugasSelesai(id)
    }
}