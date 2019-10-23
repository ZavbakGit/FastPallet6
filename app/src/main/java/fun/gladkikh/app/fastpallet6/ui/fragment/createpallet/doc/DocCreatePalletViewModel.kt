package `fun`.gladkikh.app.fastpallet6.ui.fragment.createpallet.doc

import `fun`.gladkikh.app.fastpallet6.domain.entity.CreatePallet
import `fun`.gladkikh.app.fastpallet6.domain.entity.screens.createpallet.screen.doc.ProductItemCreatePallet
import `fun`.gladkikh.app.fastpallet6.repository.createpallet.DocCreatePalletRepository
import `fun`.gladkikh.app.fastpallet6.ui.base.BaseViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import io.reactivex.BackpressureStrategy
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit

class DocCreatePalletViewModel(private val docCreatePalletRepository: DocCreatePalletRepository) :
    BaseViewModel() {

    private val listViewStateLiveData = MutableLiveData<DocListCreatePalletViewState>()
    private val entityViewStateLiveData = MutableLiveData<DocEntityCreatePalletViewState>()

    private var listLiveData: LiveData<List<ProductItemCreatePallet>>? = null
    private var entityLiveData: LiveData<CreatePallet>? = null

    private val guidPublishSubject = PublishSubject.create<String>()
    private val listObserver = Observer<List<ProductItemCreatePallet>> {
        listViewStateLiveData.value =
            DocListCreatePalletViewState(
                list = it
            )
    }

    var showProgressBarFrag = MutableLiveData<Boolean>()

    private val entityObserver = Observer<CreatePallet> {
        entityViewStateLiveData.value = DocEntityCreatePalletViewState(
            entity = it
        )
    }

    init {
        showProgressBarFrag.postValue(false)

        disposables.add(
            guidPublishSubject.toFlowable(BackpressureStrategy.BUFFER)
                .doOnNext {
                    showProgressBarFrag.postValue(true)
                }
                .debounce(30, TimeUnit.MILLISECONDS)
                .map {
                    docCreatePalletRepository.getListProductCreatePallet(it)
                }
                .doOnNext {
                    listViewStateLiveData.postValue(
                        DocListCreatePalletViewState(
                            list = it
                        )
                    )
                }
                .subscribe {
                    showProgressBarFrag.postValue(false)
                }
        )


        listViewStateLiveData.value =
            DocListCreatePalletViewState()

        entityViewStateLiveData.value = DocEntityCreatePalletViewState()


    }

    fun getListViewSate(): LiveData<DocListCreatePalletViewState> = listViewStateLiveData
    fun getEntityViewSate(): LiveData<DocEntityCreatePalletViewState> = entityViewStateLiveData

    fun setGuid(guid: String) {



        entityLiveData = docCreatePalletRepository.getDocCreatePalletByGuidLiveData(guid)
        entityLiveData?.observeForever(entityObserver)

        //listLiveData = docCreatePalletRepository.getListProductCreatePalletLiveData(guid)
        listLiveData = docCreatePalletRepository.getSimpleListProductCreatePalletLiveData(guid)
        listLiveData?.observeForever(listObserver)


        guidPublishSubject.onNext(guid)

    }

    override fun onCleared() {
        super.onCleared()
        entityLiveData?.removeObserver(entityObserver)
        listLiveData?.removeObserver(listObserver)
    }
}