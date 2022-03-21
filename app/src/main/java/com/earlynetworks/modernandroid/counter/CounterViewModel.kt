package com.earlynetworks.modernandroid.counter

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

class CounterViewModel(private val handle: SavedStateHandle) : ViewModel(){
    private var count = handle.get<Int>("count") ?: 0
        set(value) {
            countLiveData.value = value
            field = value
            handle.set("count", value)
        }

    val countLiveData = MutableLiveData<Int>()

    fun increaseCount(){
        count++
    }

    fun decreaseCount(){
        count--
    }
}