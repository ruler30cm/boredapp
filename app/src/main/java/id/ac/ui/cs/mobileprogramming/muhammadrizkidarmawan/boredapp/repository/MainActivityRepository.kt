package id.ac.ui.cs.mobileprogramming.muhammadrizkidarmawan.boredapp.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import id.ac.ui.cs.mobileprogramming.muhammadrizkidarmawan.boredapp.data.BoredResponse
import id.ac.ui.cs.mobileprogramming.muhammadrizkidarmawan.boredapp.network.BoredApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object MainActivityRepository {
    val boredResponse = MutableLiveData<BoredResponse>()

    fun getServicesApiCall() : MutableLiveData<BoredResponse> {
        val call = BoredApi.retrofitService.getRandomActivity()

        call.enqueue(object : Callback<BoredResponse> {
            override fun onFailure(call: Call<BoredResponse>, t: Throwable) {
                Log.v("DEBUG : ", t.message.toString())
            }

            override fun onResponse(call: Call<BoredResponse>, response: Response<BoredResponse>) {
                Log.v("DEBUG : ", response.body().toString())
                val data = response.body()

                boredResponse.value = BoredResponse(data!!.activity, data!!.accessibility, data!!.price, data!!.link, data!!.type, data!!.key, data!!.participants)
            }
        })
        return boredResponse
    }
}
