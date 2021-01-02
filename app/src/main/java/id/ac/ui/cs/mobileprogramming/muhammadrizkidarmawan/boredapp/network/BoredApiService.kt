package id.ac.ui.cs.mobileprogramming.muhammadrizkidarmawan.boredapp.network

import id.ac.ui.cs.mobileprogramming.muhammadrizkidarmawan.boredapp.data.BoredResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://boredapi.com/api/"

private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()

interface BoredApiService {
    @GET("activity")
    fun getRandomActivity(): Call<BoredResponse>

//    @GET("activity")
//    fun getActivityByKey(@Query("key") key : String): Call<List<BoredResponse>>
//
//    @GET("activity")
//    fun getRandomActivityByType(@Query("type") type : String): Call<List<BoredResponse>>
//
//    @GET("activity")
//    fun getRandomActivityByParticipants(@Query("participants") participants : String): Call<List<BoredResponse>>
}

object BoredApi {
    val retrofitService : BoredApiService by lazy {
        retrofit.create(BoredApiService::class.java) }
}

