
package com.d121201076.tudu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), TugasAdapter.TugasClickDeleteInterface, TugasAdapter.TugasClickDoneInterface {
    lateinit var viewModal: TugasViewModel
    lateinit var tugasRV: RecyclerView
    lateinit var tambahBtn: FloatingActionButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tambahBtn = findViewById(R.id.tambahBtn)
        tugasRV = findViewById(R.id.tugasRV)
        tugasRV.layoutManager = LinearLayoutManager(this)

        val noteRVAdapter = TugasAdapter( this, this)

        tugasRV.adapter = noteRVAdapter

        viewModal = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(TugasViewModel::class.java)

        viewModal.semuaTugas.observe(this, Observer { list ->
            list?.let {
                noteRVAdapter.updateList(it)
            }
        })
        tambahBtn.setOnClickListener {
            val intent = Intent(applicationContext, ActivityTambahTugas::class.java)
            startActivity(intent)
            this.finish()
        }
    }

    override fun onDeleteIconClick(tugas: Tugas) {
        lifecycleScope.launch {
            viewModal.delete(tugas)
        }
        Toast.makeText(this, "${tugas.judul} Deleted", Toast.LENGTH_LONG).show()
    }

    override fun onDoneIconClick(id: Int) {
        lifecycleScope.launch {
            viewModal.tugasSelesai(id)
        }
        Toast.makeText(this, "Tugas Selesai", Toast.LENGTH_LONG).show()
    }
}