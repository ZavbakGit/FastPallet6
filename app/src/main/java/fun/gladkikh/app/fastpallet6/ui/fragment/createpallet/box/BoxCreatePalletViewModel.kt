package `fun`.gladkikh.app.fastpallet6.ui.fragment.createpallet.box

import `fun`.gladkikh.app.fastpallet6.common.getWeightByBarcode
import `fun`.gladkikh.app.fastpallet6.domain.checkEditDocByStatus
import `fun`.gladkikh.app.fastpallet6.domain.entity.Box
import `fun`.gladkikh.app.fastpallet6.repository.createpallet.screen.box.BoxScreenCreatePalletRepository
import `fun`.gladkikh.app.fastpallet6.ui.base.BaseViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import java.util.*

class BoxCreatePalletViewModel(repository: BoxScreenCreatePalletRepository) :
    BaseViewModel() {

    var guid: String? = null
        private set

    private var viewStateLiveData = MutableLiveData<BoxScreenCreatePalletViewState>()

    private val loadHandler: BoxScreenCreatePalletLoadDataHandler
    private val addBoxHandler: AddBoxScreenHandler

    fun getViewSate(): LiveData<BoxScreenCreatePalletViewState> = viewStateLiveData

    init {
        viewStateLiveData.value = BoxScreenCreatePalletViewState()
        loadHandler = BoxScreenCreatePalletLoadDataHandler(
            viewStateLiveData = viewStateLiveData,
            compositeDisposable = disposables,
            repository = repository
        )

        addBoxHandler = AddBoxScreenHandler(
            compositeDisposable = disposables,
            repository = repository,
            //Берем данные из нового бокса
            beforeAddFun = {box, buffer->
                viewStateLiveData.postValue(
                    getViewStateByBoxAndBuffer(box,buffer)
                )
            },
            //После сохранения всего списка
            afterSaveFun = {box, buffer->
                viewStateLiveData.postValue(
                    getViewStateByBoxAndBuffer(box,buffer)
                )
                setGuid(box.guid)
            }
        )

    }

    fun setGuid(guidParam: String) {
        loadHandler.loadData(guidParam)
        this.guid = guidParam
    }


    fun getViewStateByBoxAndBuffer(box: Box,buffer:Int): BoxScreenCreatePalletViewState {
        val data = viewStateLiveData.value!!.data!!.copy(
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

        addBoxHandler.addBox(box)
    }
}