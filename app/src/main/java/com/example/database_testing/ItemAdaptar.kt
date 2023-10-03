package com.example.database_testing

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.database_testing.databinding.ItemBinding


class ItemAdapter(var itemsData: List<Item>):
    RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    class ViewHolder(private val binding: ItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val itemName: TextView = binding.itemName
        val itemPrice: TextView = binding.itemPrice
        val itemQuantity: TextView = binding.itemQuantity
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemAdapter.ViewHolder {
        val binding = ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemAdapter.ViewHolder, position: Int) {
        val item = itemsData[position]
        holder.itemName.text = item.itemName
        holder.itemPrice.text = item.itemPrice.toString()
        holder.itemQuantity.text = item.quantityInStock.toString()
    }

    override fun getItemCount(): Int {
        return itemsData.size
    }

}
