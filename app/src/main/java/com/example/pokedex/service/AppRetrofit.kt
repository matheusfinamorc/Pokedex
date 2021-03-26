package com.example.pokedex.service

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL =  "https://api.pokemontcg.io/"

class AppRetrofit {
    val client = OkHttpClient.Builder()
        .addInterceptor(AppInterceptor())
        .build()

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }
    val pokemonService: API by lazy {
        retrofit.create(API::class.java)
    }

}