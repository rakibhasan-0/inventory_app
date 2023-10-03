package com.example.database_testing

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.database_testing.databinding.ItemBinding

class ItemAdapter(var itemsData: List<Item>) : RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    interface OnItemDeleteListener {
        fun onDeleteItem(item: Item)
    }

    var deleteListener: OnItemDeleteListener? = null

    class ViewHolder(private val binding: ItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val itemName: TextView = binding.itemName
        val itemPrice: TextView = binding.itemPrice
        val itemQuantity: TextView = binding.itemQuantity
        val itemDelete: ImageView = binding.deleteIcon
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = itemsData[position]
        holder.itemName.text = item.itemName
        holder.itemPrice.text = item.itemPrice.toString()
        holder.itemQuantity.text = item.quantityInStock.toString()

        holder.itemDelete.setOnClickListener {
            deleteListener?.onDeleteItem(item)
        }
    }

    override fun getItemCount(): Int {
        return itemsData.size
    }
}
