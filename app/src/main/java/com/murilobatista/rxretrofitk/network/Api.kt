package com.murilobatista.rxretrofitk.network
import com.murilobatista.rxretrofitk.model.Post
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface Api {
    companion object {
        private const val BASE_URL: String = "https://jsonplaceholder.typicode.com/"
        fun create(): Api {
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(Api::class.java)
        }
    }

    // GetPosts
    @GET("posts")
    fun getPost() : Observable<List<Post>>
}