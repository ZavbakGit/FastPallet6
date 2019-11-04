package `fun`.gladkikh.app.fastpallet6.ui.fragment.createpallet.product

import `fun`.gladkikh.app.fastpallet6.common.getNumberDocByBarCode
import `fun`.gladkikh.app.fastpallet6.common.isPallet
import `fun`.gladkikh.app.fastpallet6.domain.checkEditDocByStatus
import `fun`.gladkikh.app.fastpallet6.domain.entity.Pallet
import `fun`.gladkikh.app.fastpallet6.repository.createpallet.screen.product.ProductScreenCreatePalletRepository
import `fun`.gladkikh.app.fastpallet6.ui.base.BaseViewModel
import `fun`.gladkikh.app.fastpallet6.ui.base.Command
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import java.util.*

class ProductCreatePalletViewModel(private val repository: ProductScreenCreatePalletRepository) :
    BaseViewModel() {

    var guid: String? = null
        private set

    private var viewStateLiveData = MutableLiveData<ProductScreenCreatePalletViewState>()

    private val loadHandler: ProductScreenCreatePalletLoadDataHandler

    fun getViewSate(): LiveData<ProductScreenCreatePalletViewState> = viewStateLiveData

    init {
        loadHandler = ProductScreenCreatePalletLoadDataHandler(
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



    fun scanBarcode(barcode: String) {
        if (!checkEditDocByStatus(getViewStateData()?.docStatus)){
            messageError.postValue("Нельзя менять документ с этим статусом!")
            return
        }

        if (!isPallet(barcode)){
            messageError.postValue("Это не паллета!")
            return
        }

        val number = getNumberDocByBarCode(barcode)

        if (repository.getPalletByNumber(number)!= null){
            messageError.postValue("Паллета уже используется!")
            return
        }

        val pallet = Pallet(
            guid = UUID.randomUUID().toString(),
            guidProduct = getViewStateData()!!.prodGuid!!,
            number = getNumberDocByBarCode(barcode),
            barcode = barcode,
            dataChanged = Date()
        )

        repository.savePallet(pallet)
        setGuid(getViewStateData()!!.prodGuid!!)
    }

    fun startDell(position: Int) {
        if (!checkEditDocByStatus(getViewStateData()!!.docStatus)) {
            messageError.postValue("Нельзя менять документ с этим статусом!")
            return
        }

        command.value = Command.StartConfirmDialog(message ="Удалить?", data = position)
    }



    fun dellPallet(position: Int) {
        val guid = viewStateLiveData.value!!.list[position].palGuid
        val pallet = repository.getPallet(guid!!)
        repository.deletePallet(pallet)
        setGuid(getViewStateData()!!.prodGuid!!)
    }

}