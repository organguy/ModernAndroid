package com.earlynetworks.modernandroid.mask_kotlin

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.earlynetworks.modernandroid.mask_kotlin.model.Store
import com.earlynetworks.modernandroid.mask_kotlin.repository.MaskService
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MainViewModel : ViewModel(){
    val itemLiveData = MutableLiveData<List<Store>>()
    val loadingLiveData = MutableLiveData<Boolean>()

    private val service: MaskService

    init {
        val retorofit = Retrofit.Builder()
            .baseUrl(MaskService.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        service = retorofit.create(MaskService::class.java)

        fetchStoreInfo()
    }

    fun fetchStoreInfo() {
        loadingLiveData.value = true

        viewModelScope.launch {
            val storeInfo = service.fetchStoreInfo(137.188078, 127.043002)
            itemLiveData.value = storeInfo.stores

            loadingLiveData.value = false
        }
    }
}