package com.earlynetworks.modernandroid

import com.earlynetworks.modernandroid.mask.repository.MaskService
import org.junit.Test

import org.junit.Assert.*
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun retrofitTest(){
       var retrofit = Retrofit.Builder()
           .baseUrl(MaskService.BASE_URL)
           .addConverterFactory(MoshiConverterFactory.create())
           .build()

        var service = retrofit.create(MaskService::class.java)

        var storeInfoCall = service.fetchStoreInfo()

        var storeInfo = storeInfoCall.execute().body()

        assertEquals(222, storeInfo!!.count)
        assertEquals(222, storeInfo.stores.size)
    }
}