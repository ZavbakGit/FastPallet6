package `fun`.gladkikh.app.fastpallet6.ui.fragment.createpallet.doc

import `fun`.gladkikh.app.fastpallet6.domain.entity.CreatePallet
import `fun`.gladkikh.app.fastpallet6.domain.entity.screens.createpallet.screen.doc.ProductItemCreatePallet
import `fun`.gladkikh.app.fastpallet6.repository.createpallet.DocCreatePalletRepository
import `fun`.gladkikh.app.fastpallet6.ui.base.BaseViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

class DocCreatePalletViewModel(private val docCreatePalletRepository: DocCreatePalletRepository) :
    BaseViewModel() {

    private val listViewStateLiveData = MutableLiveData<DocListCreatePalletViewState>()
    private val entityViewStateLiveData = MutableLiveData<DocEntityCreatePalletViewState>()

    private var listLiveData: LiveData<List<ProductItemCreatePallet>>? = null
    private var entityLiveData: LiveData<CreatePallet>? = null

    private val listObserver = Observer<List<ProductItemCreatePallet>> {
        listViewStateLiveData.value =
            DocListCreatePalletViewState(
                list = it
            )
    }

    private val entityObserver = Observer<CreatePallet> {
        entityViewStateLiveData.value = DocEntityCreatePalletViewState(
            entity = it
        )
    }


    init {
        listViewStateLiveData.value =
            DocListCreatePalletViewState()

        entityViewStateLiveData.value = DocEntityCreatePalletViewState()
    }

    fun getListViewSate(): LiveData<DocListCreatePalletViewState> = listViewStateLiveData
    fun getEntityViewSate(): LiveData<DocEntityCreatePalletViewState> = entityViewStateLiveData

    fun setGuid(guid: String) {
        entityLiveData = docCreatePalletRepository.getDocCreatePalletByGuidLiveData(guid)
        entityLiveData?.observeForever(entityObserver)

        listLiveData = docCreatePalletRepository.getListProductCreatePalletLiveData(guid)
        listLiveData?.observeForever(listObserver)
    }

    override fun onCleared() {
        super.onCleared()
        entityLiveData?.removeObserver(entityObserver)
        listLiveData?.removeObserver(listObserver)
    }
}