package id.ac.ui.cs.mobileprogramming.muhammadrizkidarmawan.boredapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.ac.ui.cs.mobileprogramming.muhammadrizkidarmawan.boredapp.data.BoredResponse
import id.ac.ui.cs.mobileprogramming.muhammadrizkidarmawan.boredapp.repository.MainActivityRepository

class FindViewModel : ViewModel() {

    var boredLiveData: MutableLiveData<BoredResponse>? = null

    fun getActivity() : LiveData<BoredResponse>? {
        boredLiveData = MainActivityRepository.getServicesApiCall()
        return boredLiveData
    }

}