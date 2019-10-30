package `fun`.gladkikh.app.fastpallet6.ui.fragment.createpallet.box

import `fun`.gladkikh.app.fastpallet6.repository.createpallet.screen.box.BoxScreenCreatePalletRepository
import androidx.lifecycle.MutableLiveData
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit

class BoxScreenLoadDataHandler(
    private val viewStateLiveData: MutableLiveData<BoxScreenViewState>,
    compositeDisposable: CompositeDisposable,
    private val repository: BoxScreenCreatePalletRepository
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
            .doOnNext {
                viewStateLiveData.postValue(
                    BoxScreenViewState(
                        data = repository.getData(it),
                        progress = true,
                        sizeBuffer = viewStateLiveData.value!!.sizeBuffer
                    )
                )
            }
            .debounce(2000, TimeUnit.MILLISECONDS)
            .doOnNext {
                viewStateLiveData.postValue(
                    BoxScreenViewState(
                        data = repository.getTotalData(it),
                        progress = false,
                        sizeBuffer = viewStateLiveData.value!!.sizeBuffer
                    )
                )
            }
    }

    fun loadData(guid:String){
        publishSubject.onNext(guid)
    }
}