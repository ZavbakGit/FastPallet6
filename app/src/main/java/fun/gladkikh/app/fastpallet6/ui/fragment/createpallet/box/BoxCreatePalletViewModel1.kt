package `fun`.gladkikh.app.fastpallet6.ui.fragment.createpallet.box

import `fun`.gladkikh.app.fastpallet6.common.getWeightByBarcode
import `fun`.gladkikh.app.fastpallet6.domain.entity.Box
import `fun`.gladkikh.app.fastpallet6.domain.entity.screens.createpallet.screen.box.BoxScreenCreatePallet
import `fun`.gladkikh.app.fastpallet6.repository.createpallet.BoxCreatePalletRepository
import `fun`.gladkikh.app.fastpallet6.repository.createpallet.CreatePalletRepositoryUpdate
import `fun`.gladkikh.app.fastpallet6.ui.base.BaseViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import io.reactivex.BackpressureStrategy
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import java.util.*
import java.util.concurrent.TimeUnit

class BoxCreatePalletViewModel1(
    private val repository: BoxCreatePalletRepository) :
    BaseViewModel() {
    private var viewStateLiveData = MutableLiveData<BoxScreenViewState>()
    private var repositoryLiveData: LiveData<BoxScreenCreatePallet>? = null

    var changedGuid = false


    private val saveBufferBoxPublishSubject = PublishSubject.create<List<Box>>()
    private val bufferBoxList: MutableList<Box> = mutableListOf()

    private val dataObserver = Observer<BoxScreenCreatePallet> {
        viewStateLiveData.value =
            BoxScreenViewState(
                it
            )
    }

    init {
        viewStateLiveData.value = BoxScreenViewState()

        disposables.add(
            saveBufferBoxPublishSubject.toFlowable(BackpressureStrategy.BUFFER)
                .doOnNext {
                    //Отображаем минимально необходимое без пересчетов
                    val data = viewStateLiveData.value!!.data!!
                        .copy(
                            boxWeight = it.last().weight,
                            boxGuid = it.last().guid,
                            boxCountBox = it.last().countBox,
                            boxDate = it.last().data
                        )

                    //Отсылаем на отображение
                    viewStateLiveData.postValue(
                        BoxScreenViewState(
                            data = data,
                            sizeBuffer = bufferBoxList.size
                        )
                    )
                }
                .debounce(2000, TimeUnit.MILLISECONDS)
                .map { it1 ->
                    val list = it1.map {
                        it.copy()
                    }

                    bufferBoxList.clear()
                    repository.saveListBox(list)
                    return@map list.last().guid

                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    setGuid(it) //Подписываемся на последний
                    changedGuid = true //Это чтобы если повернули, то guid не брала из arguments
                }
        )
    }

    fun getViewSate(): LiveData<BoxScreenViewState> = viewStateLiveData

    fun setGuid(guid: String) {
        //repositoryLiveData = repository.getBoxScreenCreatePalletLiveData(guid)
        repositoryLiveData?.observeForever(dataObserver)
    }

    override fun onCleared() {
        super.onCleared()
        repositoryLiveData?.removeObserver(dataObserver)
    }

    fun add(barcode: String) {
        //ToDo Проверка на доступы
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

        bufferBoxList.add(box)
        saveBufferBoxPublishSubject.onNext(bufferBoxList)

        //Удалим наблюдателя
        repositoryLiveData?.removeObserver(dataObserver)
    }

}