package `fun`.gladkikh.app.fastpallet6.ui.fragment.createpallet.doc

import `fun`.gladkikh.app.fastpallet6.repository.createpallet.screen.doc.DocScreenCreatePalletRepository
import androidx.lifecycle.MutableLiveData
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject

class DocScreenCreatePalletLoadDataHandler(
    private val viewStateLiveData: MutableLiveData<DocScreenCreatePalletViewState>,
    compositeDisposable: CompositeDisposable,
    private val repository: DocScreenCreatePalletRepository
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
                val viewState = DocScreenCreatePalletViewState(
                    data = repository.getData(guid),
                    progress = true,
                    list = repository.getListItem(guid)

                )

                viewStateLiveData.postValue(viewState)
                return@map viewState
            }
            .observeOn(Schedulers.io())
            .doOnNext { viewState ->
                viewState.progress = false
                viewState.list = repository.getListItemTotal(viewState.data!!.guid)
                viewStateLiveData.postValue(viewState)
            }

    }

    fun loadData(guid: String) {
        publishSubject.onNext(guid)
    }
}