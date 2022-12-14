package com.d121201076.tudu

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Tugas(
    var judul: String,
    var detail: String,
    var kategori: String,
    var selesai: Int = 0,
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
)
