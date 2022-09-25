package Adapter

import Interface.CardListener
import Model.Hewan
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.uc.week2visprog_0706012110003.HomeActivity
import com.uc.week2visprog_0706012110003.R
import com.uc.week2visprog_0706012110003.RegisterActivity
import com.uc.week2visprog_0706012110003.databinding.ListCardBinding

class ListHewanRVAdapter(val listHewan: ArrayList<Hewan>, val cardListener: CardListener):
    RecyclerView.Adapter<ListHewanRVAdapter.viewHolder>() {

    override fun onBindViewHolder(holder: ListHewanRVAdapter.viewHolder, position: Int) {
        holder.setData(listHewan[position])
    }

    class viewHolder(itemView: View, val cardListener1: CardListener): RecyclerView.ViewHolder(itemView) {
        val binding = ListCardBinding.bind(itemView)

        fun setData(data:Hewan){
            binding.namahewanTextViewListCard.text = data.nama
            binding.jenishewanTextViewListCard.text = data.jenis
            binding.usiahewanTextViewListCard.text = data.usia

            if (!data.imageUri!!.isEmpty()){
                binding.fotoHewanImageView.setImageURI(Uri.parse(data.imageUri))
            }
            binding.editButtonListCard.setOnClickListener {
                cardListener1.onEditClicked(adapterPosition)
            }
            binding.deleteButtonListCard.setOnClickListener {
                cardListener1.onDeleteClicked(adapterPosition)
            }
            binding.imageButton.setOnClickListener {
                Toast.makeText(itemView.context, data.hewansuara, Toast.LENGTH_LONG).show()
            }
            binding.imageButton2.setOnClickListener {
                Toast.makeText(itemView.context, data.hewanamakan, Toast.LENGTH_LONG).show()
            }
        }
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListHewanRVAdapter.viewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.list_card, parent, false)
        return viewHolder(view, cardListener)
    }


    override fun getItemCount(): Int {
        return listHewan.size
    }
}