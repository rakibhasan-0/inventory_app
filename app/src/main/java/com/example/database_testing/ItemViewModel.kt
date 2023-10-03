package com.example.database_testing

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.database_testing.ItemDao
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ItemViewModel(private val itemDao: ItemDao) : ViewModel() {

    private var _itemsData = MutableStateFlow<List<Item>>(emptyList())
    val itemsData: StateFlow<List<Item>> = _itemsData

    init {
        fetchItems()
    }

    fun addNewItem(itemName: String, itemPrice: String, itemCount: String) {
        val newItem = getNewItemEntry(itemName, itemPrice, itemCount)
        if (newItem != null) {
            insertItem(newItem)
        }
    }

    private fun insertItem(item: Item) {
        viewModelScope.launch {
            try {
                itemDao.insertItem(item)
                // Directly update in-memory list after a successful insert
                val currentList = _itemsData.value.toMutableList()
                currentList.add(item)
                _itemsData.value = currentList
            } catch (e: Exception) {
                // Handle insertion error
                // For example, show a message to the user or log the error
                null
            }
        }
    }

    private fun getNewItemEntry(itemName: String, itemPrice: String, itemCount: String): Item? {
        return try {
            Item(
                itemName = itemName,
                itemPrice = itemPrice.toDouble(),
                quantityInStock = itemCount.toInt()
            )
        } catch (e: Exception) {
            null
        }
    }

    private fun fetchItems() {
        viewModelScope.launch {
            itemDao.getItems().collect { items ->
                _itemsData.value = items
            }
        }
    }


}
