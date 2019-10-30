package `fun`.gladkikh.app.fastpallet6.ui.fragment.createpallet.pallet

import `fun`.gladkikh.app.fastpallet6.repository.createpallet.screen.pallet.PalletScreenCreatePalletRepository
import androidx.lifecycle.MutableLiveData
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit

class PalletScreenCreatePalletLoadDataHandler(
    private val viewStateLiveData: MutableLiveData<PalletScreenCreatePalletViewState>,
    compositeDisposable: CompositeDisposable,
    private val repository: PalletScreenCreatePalletRepository
) {
    private val publishSubject = PublishSubject.create<String>()

    init {
        compositeDisposable.add(
            getLoadDataFlowable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()
        )
    }

    private fun getLoadDataFlowable(): Flowable<String> {
        return publishSubject.toFlowable(BackpressureStrategy.BUFFER)
            .map {
                viewStateLiveData.postValue(
                    PalletScreenCreatePalletViewState(
                        data = repository.getData(it),
                        progress = true
                    )
                )

                return@map it
            }
            .debounce(100, TimeUnit.MILLISECONDS)
            .doOnNext {
                viewStateLiveData.postValue(
                    PalletScreenCreatePalletViewState(
                        data = repository.getTotalData(it),
                        progress = false
                    )
                )
            }
    }

    fun loadData(guid:String){
        publishSubject.onNext(guid)
    }
}