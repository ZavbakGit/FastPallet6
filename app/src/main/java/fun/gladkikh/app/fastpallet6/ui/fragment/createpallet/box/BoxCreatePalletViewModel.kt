package `fun`.gladkikh.app.fastpallet6.ui.fragment.createpallet.box

import `fun`.gladkikh.app.fastpallet6.common.getWeightByBarcode
import `fun`.gladkikh.app.fastpallet6.domain.checkEditDocByStatus
import `fun`.gladkikh.app.fastpallet6.domain.entity.Box
import `fun`.gladkikh.app.fastpallet6.repository.createpallet.screen.box.BoxScreenCreatePalletRepository
import `fun`.gladkikh.app.fastpallet6.ui.base.BaseViewModel
import `fun`.gladkikh.app.fastpallet6.ui.base.Command.*

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import java.util.*

class BoxCreatePalletViewModel(private val repository: BoxScreenCreatePalletRepository) :
    BaseViewModel() {

    var guid: String? = null
        private set

    private var viewStateLiveData = MutableLiveData<BoxScreenCreatePalletViewState>()

    private val loadHandler: BoxScreenCreatePalletLoadDataHandler
    private val addBoxHandler: AddBoxScreenHandler

    fun getViewSate(): LiveData<BoxScreenCreatePalletViewState> = viewStateLiveData

    private fun getViewStateData() = viewStateLiveData.value?.data

    init {
        loadHandler = BoxScreenCreatePalletLoadDataHandler(
            viewStateLiveData = viewStateLiveData,
            compositeDisposable = disposables,
            repository = repository
        )

        addBoxHandler = AddBoxScreenHandler(
            compositeDisposable = disposables,
            repository = repository,
            //Берем данные из нового бокса
            beforeAddFun = { box, buffer ->
                viewStateLiveData.postValue(
                    getViewStateByBoxAndBuffer(box, buffer)
                )
            },
            //После сохранения всего списка
            afterSaveFun = { box, buffer ->
                viewStateLiveData.postValue(
                    getViewStateByBoxAndBuffer(box, buffer)
                )
                setGuid(box.guid)
            }
        )
    }

    fun setGuid(guidParam: String) {
        loadHandler.loadData(guidParam)
        this.guid = guidParam
    }

    private fun getViewStateByBoxAndBuffer(box: Box, buffer: Int): BoxScreenCreatePalletViewState {
        val data = getViewStateData()!!.copy(
            boxWeight = box.weight,
            boxBarcode = box.barcode,
            boxData = box.data,
            boxGuid = box.guid,
            boxCountBox = box.countBox
        )

        return BoxScreenCreatePalletViewState(
            data = data,
            sizeBuffer = buffer,
            progress = viewStateLiveData.value!!.progress

        )
    }

    fun scanBarcode(barcode: String) {
        if (!checkEditDocByStatus(getViewStateData()?.docStatus)) {
            messageError.postValue("Нельзя менять документ с этим статусом!")
            return
        }

        val weight = getWeightByBarcode(
            barcode = barcode,
            start = getViewStateData()!!.prodWeightStartProduct ?: 0,
            finish = getViewStateData()!!.prodWeightEndProduct ?: 0,
            coff = getViewStateData()!!.prodWeightCoffProduct ?: 0f
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

        addBoxHandler.addBox(box)
    }

    fun dell() {
        val box = repository.getBox(getViewStateData()!!.boxGuid!!)
        repository.deleteBox(box)
        command.value = Close
    }

    fun startDell() {
        if (!checkEditDocByStatus(getViewStateData()!!.docStatus)) {
            messageError.postValue("Нельзя менять документ с этим статусом!")
            return
        }

        command.value = StartConfirmDialog("Удалить?")
    }

    fun editPlace(place: Int) {
        getViewStateData()?.let {
            val box = Box(
                guid = it.boxGuid!!,
                guidPallet = it.palGuid!!,
                barcode = it.boxBarcode,
                countBox = place,
                weight = it.boxWeight,
                data = Date()
            )
            repository.saveBox(box)
            setGuid(it.boxGuid)
        }
    }

    fun editWeight(weight: Float) {
        getViewStateData()?.let {
            val box = Box(
                guid = it.boxGuid!!,
                guidPallet = it.palGuid!!,
                barcode = it.boxBarcode,
                countBox = it.boxCountBox,
                weight = weight,
                data = Date()
            )
            repository.saveBox(box)
            setGuid(it.boxGuid)
        }
    }

    fun startEditPlace() {
        if (!checkEditDocByStatus(getViewStateData()!!.docStatus)) {
            messageError.postValue("Нельзя менять документ с этим статусом!")
            return
        }
        getViewStateData()?.let {
            command.value = StartEditNumberDialog(
                title = "Мест",
                number = it.boxCountBox
            )
        }
    }

    fun startEditWeight() {
        if (!checkEditDocByStatus(getViewStateData()!!.docStatus)) {
            messageError.postValue("Нельзя менять документ с этим статусом!")
            return
        }

        getViewStateData()?.let {
            command.value = StartEditDecimalDialog(
                title = "Вес",
                number = it.boxWeight
            )
        }
    }

    fun startAddBox() {
        if (!checkEditDocByStatus(getViewStateData()!!.docStatus)) {
            messageError.postValue("Нельзя менять документ с этим статусом!")
            return
        }

        getViewStateData()?.let {
            val box = Box(
                guid = UUID.randomUUID().toString(),
                guidPallet = viewStateLiveData.value?.data?.palGuid!!,
                barcode = null,
                countBox = 0,
                weight = 0f,
                data = Date()
            )

            repository.saveBox(box)
            setGuid(box.guid)
        }
    }

}