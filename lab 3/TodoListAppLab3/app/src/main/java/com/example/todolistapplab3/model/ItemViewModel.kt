package com.example.todolistapplab3.model

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel

class ItemViewModel: ViewModel() {
    var itemList = mutableStateListOf<Item>()

    fun add(newItem: Item){
        itemList.add(newItem)
    }

    fun delete(item: Item){
        itemList.remove(item)
    }
}