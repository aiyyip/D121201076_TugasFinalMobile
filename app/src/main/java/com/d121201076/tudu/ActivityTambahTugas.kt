package com.d121201076.tudu

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

class ActivityTambahTugas: AppCompatActivity() {
    lateinit var tambahJudulTugas: EditText
    lateinit var tambahDetailTugas: EditText
    lateinit var saveBtn: Button
    lateinit var spinnerKategori: Spinner


    lateinit var viewModal: TugasViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tambah_tugas)
        viewModal = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(TugasViewModel::class.java)
        tambahJudulTugas = findViewById(R.id.tambahJudulTugas)
        tambahDetailTugas = findViewById(R.id.tambahDetailTugas)
        spinnerKategori = findViewById(R.id.spinnerKategori)
        saveBtn = findViewById(R.id.idBtn)
        val adapter = ArrayAdapter.createFromResource(this, R.array.kategoriTugas, android.R
            .layout.simple_list_item_1)
        spinnerKategori.adapter = adapter

        saveBtn.setOnClickListener {
            val judulTugas = tambahJudulTugas.text.toString()
            val detailTugas = tambahDetailTugas.text.toString()
            val kategoriTugas = spinnerKategori.selectedItem.toString()
            if (judulTugas.isNotEmpty() && detailTugas.isNotEmpty()) {
                viewModal.insert(Tugas(judulTugas, detailTugas, kategoriTugas))
                Toast.makeText(this, "$judulTugas Ditambahkan", Toast.LENGTH_LONG).show()
            }
        }
        startActivity(Intent(applicationContext, MainActivity::class.java))
        this.finish()
    }
}