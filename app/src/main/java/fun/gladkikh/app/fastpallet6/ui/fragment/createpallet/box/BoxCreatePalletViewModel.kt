package `fun`.gladkikh.app.fastpallet6.ui.fragment.createpallet.box

import `fun`.gladkikh.app.fastpallet6.common.getWeightByBarcode
import `fun`.gladkikh.app.fastpallet6.domain.entity.Box
import `fun`.gladkikh.app.fastpallet6.domain.entity.screens.createpallet.screen.box.BoxScreenCreatePallet
import `fun`.gladkikh.app.fastpallet6.repository.createpallet.BoxCreatePalletRepository
import `fun`.gladkikh.app.fastpallet6.ui.base.BaseViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import java.util.*

class BoxCreatePalletViewModel(repository: BoxCreatePalletRepository) :
    BaseViewModel() {

    var guid: String? = null
        private set

    private var viewStateLiveData = MutableLiveData<BoxScreenViewState>()

    private val loadHandler: BoxScreenLoadDataHandler
    private val addBoxHandler: AddBoxScreenHandler

    fun getViewSate(): LiveData<BoxScreenViewState> = viewStateLiveData

    init {
        viewStateLiveData.value = BoxScreenViewState()
        loadHandler = BoxScreenLoadDataHandler(
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


    fun getViewStateByBoxAndBuffer(box: Box,buffer:Int): BoxScreenViewState {
        val data = viewStateLiveData.value!!.data!!.copy(
            boxWeight = box.weight,
            boxBarcode = box.barcode,
            boxDate = box.data,
            boxGuid = box.guid,
            boxCountBox = box.countBox

        )

        return BoxScreenViewState(
            data = data,
            sizeBuffer = buffer,
            progress = viewStateLiveData.value!!.progress

        )
    }

    fun addBox(barcode: String) {
        val weight = getWeightByBarcode(
            barcode = barcode,
            start = viewStateLiveData.value?.data?.prodStart ?: 0,
            finish = viewStateLiveData.value?.data?.prodEnd ?: 0,
            coff = viewStateLiveData.value?.data?.prodCoeff ?: 0f
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