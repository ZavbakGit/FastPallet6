package `fun`.gladkikh.app.fastpallet6.ui.fragment.createpallet.product

import `fun`.gladkikh.app.fastpallet6.domain.entity.screens.createpallet.screen.product.PalletItemCreatePallet
import `fun`.gladkikh.app.fastpallet6.repository.createpallet.ProductCreatePalletRepository
import `fun`.gladkikh.app.fastpallet6.ui.base.BaseViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

class ProductCreatePalletViewModel(private val productCreatePalletRepository: ProductCreatePalletRepository) :
    BaseViewModel() {
    private val viewStateLiveData = MutableLiveData<ProductCreatePalletViewState>()
    var listLiveData: LiveData<List<PalletItemCreatePallet>>? = null

    private val businessDelegate = ProductCreatPalletDelegate(
        viewModel = this,
        productCreatePalletRepository = productCreatePalletRepository,
        viewStateLiveData = viewStateLiveData
    )

    private val listObserver = Observer<List<PalletItemCreatePallet>> {
        viewStateLiveData.value =
            ProductCreatePalletViewState(
                list = it
            )
    }

    init {
        viewStateLiveData.value = ProductCreatePalletViewState()
    }

    fun getViewSate(): LiveData<ProductCreatePalletViewState> = viewStateLiveData

    fun addPallet(barcode:String){
        businessDelegate.addPallet(barcode)
    }

    fun setGuid(guid: String) {
        listLiveData = productCreatePalletRepository.getListPalletItemCreatePalletLiveData(guid)
        listLiveData?.observeForever(listObserver)
    }

    override fun onCleared() {
        super.onCleared()
        listLiveData?.removeObserver(listObserver)
    }


}