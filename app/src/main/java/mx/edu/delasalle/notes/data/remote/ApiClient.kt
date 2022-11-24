package mx.edu.delasalle.notes.data.remote

import com.google.gson.GsonBuilder
import mx.edu.delasalle.notes.app.Constants
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit

object ApiClient {

    val service by lazy {
        Retrofit.Builder()
            .baseUrl(Constants.BaseUrl)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(ApiService::class.java)
    }

}
