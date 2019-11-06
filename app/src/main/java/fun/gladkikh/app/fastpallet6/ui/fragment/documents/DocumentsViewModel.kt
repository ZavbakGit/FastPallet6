package `fun`.gladkikh.app.fastpallet6.ui.fragment.documents

import `fun`.gladkikh.app.fastpallet6.domain.entity.screens.documents.ItemDocumentsScreenData
import `fun`.gladkikh.app.fastpallet6.repository.documents.DocumentsScreenRepository
import `fun`.gladkikh.app.fastpallet6.ui.base.BaseViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

class DocumentsViewModel(private val repository: DocumentsScreenRepository) :
    BaseViewModel() {

    private var viewStateLiveData = MutableLiveData<DocumentsScreenViewState>()
    private val repositoryLiveData = repository.getDocumentsLiveData()

    private val documentsObserver = Observer<List<ItemDocumentsScreenData>> {
        viewStateLiveData.value =
            DocumentsScreenViewState(
                list = it
            )
    }

    init {
        repositoryLiveData.observeForever(documentsObserver)
    }

    override fun onCleared() {
        super.onCleared()
        repositoryLiveData.removeObserver(documentsObserver)
    }

    fun getViewSate(): LiveData<DocumentsScreenViewState> = viewStateLiveData

}