package `fun`.gladkikh.app.fastpallet6.ui.fragment.createpallet.box

import `fun`.gladkikh.app.fastpallet6.common.getWeightByBarcode
import `fun`.gladkikh.app.fastpallet6.domain.entity.Box
import `fun`.gladkikh.app.fastpallet6.domain.entity.screens.createpallet.screen.box.BoxTotalInfoCreatePallet
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

class BoxCreatePalletViewModel(
    private val repository: BoxCreatePalletRepository,
    private val createPalletRepositoryUpdate: CreatePalletRepositoryUpdate
) :
    BaseViewModel() {
    private var viewStateLiveData = MutableLiveData<BoxTotalInfoCreatePalletViewState>()
    var repositoryLiveData: LiveData<BoxTotalInfoCreatePallet>? = null

    var changedGuid = false
        private set

    private val saveBufferBoxPublishSubject = PublishSubject.create<List<Box>>()
    private val bufferBoxList: MutableList<Box> = mutableListOf()

    private val dataObserver = Observer<BoxTotalInfoCreatePallet> {
        viewStateLiveData.value =
            BoxTotalInfoCreatePalletViewState(
                it
            )
    }

    init {
        viewStateLiveData.value = BoxTotalInfoCreatePalletViewState()

        disposables.add(
            saveBufferBoxPublishSubject.toFlowable(BackpressureStrategy.BUFFER)
                .doOnNext {
                    //Отображаем минимально необходимое без пересчетов
                    val data = viewStateLiveData!!.value!!.data!!
                        .copy(
                            boxWeight = it.last().weight,
                            boxGuid = it.last().guid,
                            boxCountBox = it.last().countBox,
                            boxDate = it.last().data
                        )

                    //Отсылаем на отображение
                    viewStateLiveData.postValue(
                        BoxTotalInfoCreatePalletViewState(
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
                    createPalletRepositoryUpdate.saveListBox(list)
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

    fun getViewSate(): LiveData<BoxTotalInfoCreatePalletViewState> = viewStateLiveData

    fun setGuid(guid: String) {
        repositoryLiveData = repository.getBoxTotalInfoCreatePallet(guid)
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