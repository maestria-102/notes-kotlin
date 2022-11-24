package mx.edu.delasalle.notes.ui.notes.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import mx.edu.delasalle.notes.R
import mx.edu.delasalle.notes.data.model.Note
import mx.edu.delasalle.notes.databinding.ItemNoteBinding
import com.squareup.picasso.Picasso

class NotesAdapter (private val notes: List<Note>, private val listener: (Note) -> Unit) : RecyclerView.Adapter<NotesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val note = notes[position]
        holder.itemView.setOnClickListener {
            listener(note)
        }
        Picasso.get().load(note.image).into(holder.binding.imgNota)
    }

    override fun getItemCount(): Int = notes.size

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val binding = ItemNoteBinding.bind(v);

    }



}



