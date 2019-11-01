package `fun`.gladkikh.app.fastpallet6.ui.fragment.createpallet.pallet

import `fun`.gladkikh.app.fastpallet6.common.getWeightByBarcode
import `fun`.gladkikh.app.fastpallet6.domain.checkEditDocByStatus
import `fun`.gladkikh.app.fastpallet6.domain.entity.Box
import `fun`.gladkikh.app.fastpallet6.repository.createpallet.screen.pallet.PalletScreenCreatePalletRepository
import `fun`.gladkikh.app.fastpallet6.ui.base.BaseViewModel
import `fun`.gladkikh.app.fastpallet6.ui.base.Command
import `fun`.gladkikh.app.fastpallet6.ui.base.Command.OpenForm
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import java.util.*

class PalletCreatePalletViewModel(private val repository: PalletScreenCreatePalletRepository) :
    BaseViewModel() {

    var guid: String? = null
        private set

    private var viewStateLiveData = MutableLiveData<PalletScreenCreatePalletViewState>()

    private val loadHandler: PalletScreenCreatePalletLoadDataHandler

    fun getViewSate(): LiveData<PalletScreenCreatePalletViewState> = viewStateLiveData

    init {
        loadHandler = PalletScreenCreatePalletLoadDataHandler(
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

        val weight = getWeightByBarcode(
            barcode = barcode,
            start = getViewStateData()?.prodWeightStartProduct?:0,
            finish = getViewStateData()?.prodWeightEndProduct ?: 0,
            coff = getViewStateData()?.prodWeightCoffProduct ?: 0f
        )

        if (weight == 0f) {
            messageError.value = "Ошибка в считывания веса! \n $barcode"
            return
        }

        val box = Box(
            guid = UUID.randomUUID().toString(),
            guidPallet = getViewStateData()!!.palGuid!!,
            barcode = barcode,
            countBox = 1,
            weight = weight,
            data = Date()
        )

        repository.saveBox(box)
        command.postValue(OpenForm(box.guid))
    }

    fun startDell(position: Int) {
        if (!checkEditDocByStatus(getViewStateData()!!.docStatus)) {
            messageError.postValue("Нельзя менять документ с этим статусом!")
            return
        }

        command.value = Command.StartConfirmDialog(message ="Удалить?", data = position)
    }

    fun startAddBox() {
        if (!checkEditDocByStatus(getViewStateData()?.docStatus)){
            messageError.postValue("Нельзя менять документ с этим статусом!")
            return
        }

        val box = Box(
            guid = UUID.randomUUID().toString(),
            guidPallet = getViewStateData()!!.palGuid!!,
            barcode = null,
            countBox = 0,
            weight = 0f,
            data = Date()
        )

        repository.saveBox(box)
        command.postValue(OpenForm(box.guid))
    }

    fun dellBox(position: Int) {
        val guidBox = viewStateLiveData.value!!.list[position].boxGuid
        val box = repository.getBox(guidBox!!)
        repository.deleteBox(box)
        setGuid(getViewStateData()!!.palGuid!!)
    }

}