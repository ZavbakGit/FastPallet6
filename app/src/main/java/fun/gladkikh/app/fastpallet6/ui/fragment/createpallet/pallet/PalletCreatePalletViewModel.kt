package `fun`.gladkikh.app.fastpallet6.ui.fragment.createpallet.pallet

import `fun`.gladkikh.app.fastpallet6.repository.createpallet.screen.pallet.PalletScreenCreatePalletRepository
import `fun`.gladkikh.app.fastpallet6.ui.base.BaseViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class PalletCreatePalletViewModel(repository: PalletScreenCreatePalletRepository) :
    BaseViewModel() {

    var guid: String? = null
        private set

    private var viewStateLiveData = MutableLiveData<PalletScreenCreatePalletViewState>()

    private val loadHandler: PalletScreenCreatePalletLoadDataHandler

    fun getViewSate(): LiveData<PalletScreenCreatePalletViewState> = viewStateLiveData

    init {
        viewStateLiveData.value = PalletScreenCreatePalletViewState()
        loadHandler = PalletScreenCreatePalletLoadDataHandler(
            viewStateLiveData = viewStateLiveData,
            compositeDisposable = disposables,
            repository = repository
        )

        //ToDo Без этого не срабатывает onNext
        Thread.sleep(200)
    }

    fun setGuid(guidParam: String) {
        loadHandler.loadData(guidParam)
        this.guid = guidParam
    }

}