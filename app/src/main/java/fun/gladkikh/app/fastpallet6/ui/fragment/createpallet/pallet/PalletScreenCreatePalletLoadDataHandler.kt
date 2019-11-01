package `fun`.gladkikh.app.fastpallet6.ui.fragment.createpallet.pallet

import `fun`.gladkikh.app.fastpallet6.repository.createpallet.screen.pallet.PalletScreenCreatePalletRepository
import androidx.lifecycle.MutableLiveData
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject

class PalletScreenCreatePalletLoadDataHandler(
    private val viewStateLiveData: MutableLiveData<PalletScreenCreatePalletViewState>,
    compositeDisposable: CompositeDisposable,
    private val repository: PalletScreenCreatePalletRepository
) {
    private val publishSubject = PublishSubject.create<String>()

    init {
        compositeDisposable.add(
        getLoadDataFlowable()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
        )
    }

    private fun getLoadDataFlowable(): Flowable<*> {
        return publishSubject.toFlowable(BackpressureStrategy.BUFFER)
            .map { guid ->
                val viewState = PalletScreenCreatePalletViewState(
                    data = repository.getData(guid),
                    progress = true,
                    list = repository.getListItem(guid).map {
                        ItemBox(
                            boxGuid = it.boxGuid,
                            boxWeight = it.boxWeight,
                            boxCountBox = it.boxCountBox,
                            boxBarcode = it.boxBarcode,
                            boxData = it.boxData
                        )
                    }
                )

                viewStateLiveData.postValue(viewState)
                return@map viewState
            }
            .observeOn(Schedulers.io())
            .doOnNext { viewState ->
                viewState.data = repository.getTotalData(viewState.data!!.palGuid!!)
                viewState.progress = false
                viewStateLiveData.postValue(viewState)
            }

    }

    fun loadData(guid: String) {
        publishSubject.onNext(guid)
    }
}