package com.revolut.data

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.experimental.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by yatsinar on 17/03/2018.
 * Revolut
 * All rights reserved
 */
class Network {

    private val retrofit by lazy {
        Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .baseUrl("https://bittrex.com/api/v1.1/public/")
                .build()
    }

    val bittrexApi by lazy {retrofit.create(BittrexApi::class.java) }

}