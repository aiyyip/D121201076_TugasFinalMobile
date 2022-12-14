package com.d121201076.tudu

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TugasViewModel(application: Application): AndroidViewModel(application) {
    val semuaTugas: LiveData<List<Tugas>>
    val repository: TugasRepository
    init {
        val dao = TugasDatabase.getDatabase(application).Dao()
        repository = TugasRepository(dao)
        semuaTugas = repository.semuaTugas
    }

    fun insert(tugas: Tugas) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(tugas)
        }
    }

    fun delete(tugas: Tugas){
        viewModelScope.launch(Dispatchers.IO) {
            repository.delete(tugas)
        }
    }

    fun tugasSelesai(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.tugasSelesai(id)
        }
    }
}