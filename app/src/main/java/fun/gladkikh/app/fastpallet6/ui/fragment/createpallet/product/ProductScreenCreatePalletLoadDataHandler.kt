package `fun`.gladkikh.app.fastpallet6.ui.fragment.createpallet.product

import `fun`.gladkikh.app.fastpallet6.repository.createpallet.screen.product.ProductScreenCreatePalletRepository
import androidx.lifecycle.MutableLiveData
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject

class ProductScreenCreatePalletLoadDataHandler(
    private val viewStateLiveData: MutableLiveData<ProductScreenCreatePalletViewState>,
    compositeDisposable: CompositeDisposable,
    private val repository: ProductScreenCreatePalletRepository
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
                val viewState = ProductScreenCreatePalletViewState(
                    data = repository.getData(guid),
                    progress = true,
                    list = repository.getListItem(guid)

                )

                viewStateLiveData.postValue(viewState)
                return@map viewState
            }
            .observeOn(Schedulers.io())
            .doOnNext { viewState ->
                viewState.data = repository.getTotalData(viewState.data!!.prodGuid!!)
                viewState.progress = false
                viewState.list = repository.getListItemTotal(viewState.data!!.prodGuid!!)
                viewStateLiveData.postValue(viewState)
            }

    }

    fun loadData(guid: String) {
        publishSubject.onNext(guid)
    }
}