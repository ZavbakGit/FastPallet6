package `fun`.gladkikh.app.fastpallet6.ui.fragment.createpallet.pallet

import `fun`.gladkikh.app.fastpallet6.common.getWeightByBarcode
import `fun`.gladkikh.app.fastpallet6.domain.checkEditDocByStatus
import `fun`.gladkikh.app.fastpallet6.domain.entity.Box
import `fun`.gladkikh.app.fastpallet6.repository.createpallet.screen.pallet.PalletScreenCreatePalletRepository
import `fun`.gladkikh.app.fastpallet6.ui.base.BaseViewModel
import `fun`.gladkikh.app.fastpallet6.ui.base.Command.OpenForm
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import java.util.*

class PalletCreatePalletViewModel(val repository: PalletScreenCreatePalletRepository) :
    BaseViewModel() {

    var guid: String? = null
        private set

    private var viewStateLiveData = MutableLiveData<PalletScreenCreatePalletViewState>()

    private val loadHandler: PalletScreenCreatePalletLoadDataHandler

    fun getViewSate(): LiveData<PalletScreenCreatePalletViewState> = viewStateLiveData

    init {
        //viewStateLiveData.value = PalletScreenCreatePalletViewState()
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



    fun addBox(barcode: String) {
        if (!checkEditDocByStatus(viewStateLiveData.value!!.data!!.docStatus)){
            messageError.postValue("Нельзя менять документ с этим статусом!")
            return
        }

        val weight = getWeightByBarcode(
            barcode = barcode,
            start = viewStateLiveData.value?.data?.prodWeightStartProduct ?: 0,
            finish = viewStateLiveData.value?.data?.prodWeightEndProduct ?: 0,
            coff = viewStateLiveData.value?.data?.prodWeightCoffProduct ?: 0f
        )

        if (weight == 0f) {
            messageError.value = "Ошибка в считывания веса! \n $barcode"
            return
        }

        val box = Box(
            guid = UUID.randomUUID().toString(),
            guidPallet = viewStateLiveData.value?.data?.palGuid!!,
            barcode = barcode,
            countBox = 1,
            weight = weight,
            data = Date()
        )

        repository.saveBox(box)
        command.postValue(OpenForm(box.guid))
    }

}