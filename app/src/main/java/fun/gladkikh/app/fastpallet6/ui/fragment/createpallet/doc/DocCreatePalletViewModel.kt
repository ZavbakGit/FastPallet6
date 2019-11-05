package `fun`.gladkikh.app.fastpallet6.ui.fragment.createpallet.doc

import `fun`.gladkikh.app.fastpallet6.repository.createpallet.screen.doc.DocScreenCreatePalletRepository
import `fun`.gladkikh.app.fastpallet6.ui.base.BaseViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class DocCreatePalletViewModel(private val repository: DocScreenCreatePalletRepository) :
    BaseViewModel() {

    var guid: String? = null
        private set

    private var viewStateLiveData = MutableLiveData<DocScreenCreatePalletViewState>()

    private val loadHandler: DocScreenCreatePalletLoadDataHandler

    fun getViewSate(): LiveData<DocScreenCreatePalletViewState> = viewStateLiveData

    init {
        loadHandler = DocScreenCreatePalletLoadDataHandler(
            viewStateLiveData = viewStateLiveData,
            compositeDisposable = disposables,
            repository = repository
        )
    }

    private fun getViewStateData() = viewStateLiveData.value?.data

    fun setGuid(guidParam: String) {
        loadHandler.loadData(guidParam)
        this.guid = guidParam
    }
}