package com.d121201076.tudu

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TugasAdapter(
    val tugasClickDeleteInterface: TugasClickDeleteInterface,
    val tugasClickDoneInterface: TugasClickDoneInterface
    ) :
        RecyclerView.Adapter<TugasAdapter.ViewHolder>() {

        // on below line we are creating a
        // variable for our all notes list.
        private val semuaTugas = ArrayList<Tugas>()

        // on below line we are creating a view holder class.
        inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            // on below line we are creating an initializing all our
            // variables which we have added in layout file.
            val judulTugas = itemView.findViewById<TextView>(R.id.judulTugas)
            val kategoriTugas = itemView.findViewById<TextView>(R.id.kategoriTugas)
            val deleteBtn = itemView.findViewById<ImageButton>(R.id.deleteButton)
            val doneBtn = itemView.findViewById<ImageButton>(R.id.doneButton)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            // inflating our layout file for each item of recycler view.
            val itemView = LayoutInflater.from(parent.context).inflate(
                R.layout.tugas_rv_item,
                parent, false
            )
            return ViewHolder(itemView)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            // on below line we are setting data to item of recycler view.
            holder.judulTugas.setText(semuaTugas.get(position).judul)
            holder.kategoriTugas.setText(semuaTugas.get(position).kategori)
            // on below line we are adding click listener to our delete image view icon.
            holder.deleteBtn.setOnClickListener {
                // on below line we are calling a note click
                // interface and we are passing a position to it.
                tugasClickDeleteInterface.onDeleteIconClick(semuaTugas.get(position))
            }
            holder.doneBtn.setOnClickListener {
                tugasClickDoneInterface.onDoneIconClick(semuaTugas.get(position).id)
            }

        }

        override fun getItemCount(): Int {
            return semuaTugas.size
        }
        fun updateList(newList: List<Tugas>) {
            semuaTugas.clear()
            semuaTugas.addAll(newList)
            notifyDataSetChanged()
        }
    interface TugasClickDeleteInterface {
        fun onDeleteIconClick(tugas: Tugas)
    }

    interface TugasClickDoneInterface {
        fun onDoneIconClick(id: Int)
    }
}


