package `fun`.gladkikh.app.fastpallet6.ui.base


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

open class BaseViewModel: ViewModel() {

    val message = SingleLiveEvent<String>()
    val messageError = SingleLiveEvent<String>()
    val showProgress = MutableLiveData<Boolean>()

    protected val command = SingleLiveEvent<Command>()
    fun getCommand(): LiveData<Command> = command

    val disposables = CompositeDisposable()
    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }
}