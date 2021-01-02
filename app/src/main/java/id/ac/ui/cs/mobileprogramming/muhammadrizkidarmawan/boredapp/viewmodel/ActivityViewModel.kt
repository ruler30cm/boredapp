package id.ac.ui.cs.mobileprogramming.muhammadrizkidarmawan.boredapp.viewmodel

import androidx.lifecycle.*
import id.ac.ui.cs.mobileprogramming.muhammadrizkidarmawan.boredapp.database.ActivityRepository
import id.ac.ui.cs.mobileprogramming.muhammadrizkidarmawan.boredapp.database.BoredResponse
import kotlinx.coroutines.launch

class ActivityViewModel(private val repository: ActivityRepository) : ViewModel() {

    val allActivity : LiveData<List<BoredResponse>> = repository.allActivity.asLiveData()

    fun insert(activity: BoredResponse) = viewModelScope.launch {
        repository.insert(activity)
    }

}

class ActivityViewModelFactory(private val repository: ActivityRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ActivityViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ActivityViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
