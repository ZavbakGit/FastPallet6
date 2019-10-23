package `fun`.gladkikh.app.fastpallet6.ui.fragment.createpallet.pallet

import `fun`.gladkikh.app.fastpallet6.domain.entity.screens.createpallet.screen.pallet.BoxItemCreatePallet
import `fun`.gladkikh.app.fastpallet6.repository.createpallet.PalletCreatePalletRepository
import `fun`.gladkikh.app.fastpallet6.ui.base.BaseViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

class PalletCreatePalletViewModel(private val repository: PalletCreatePalletRepository) :
    BaseViewModel() {
    private val viewStateLiveData = MutableLiveData<PalletCreatePalletViewState>()
    var listLiveData: LiveData<List<BoxItemCreatePallet>>? = null

    private val listObserver = Observer<List<BoxItemCreatePallet>> {
        viewStateLiveData.value =
            PalletCreatePalletViewState(
                list = it
            )
    }

    init {
        viewStateLiveData.value = PalletCreatePalletViewState()
    }

    fun getViewSate(): LiveData<PalletCreatePalletViewState> = viewStateLiveData

    fun setGuid(guid: String) {
        listLiveData = repository.getListBoxItemCreatePalletLiveData(guid)
        listLiveData?.observeForever(listObserver)
    }

    override fun onCleared() {
        super.onCleared()
        listLiveData?.removeObserver(listObserver)
    }
}