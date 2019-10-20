package `fun`.gladkikh.app.fastpallet6.ui.fragment.documents

import `fun`.gladkikh.app.fastpallet6.domain.entity.ItemDocument
import `fun`.gladkikh.app.fastpallet6.domain.usecase.testdata.AddTestDataUseCase
import `fun`.gladkikh.app.fastpallet6.repository.DocumentsRepository
import `fun`.gladkikh.app.fastpallet6.ui.base.BaseViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

class DocumentsViewModel(private val documentsRepository: DocumentsRepository,
                         private val addTestDataUseCase: AddTestDataUseCase) : BaseViewModel() {
    private val documentsViewState = MutableLiveData<DocumentsViewState>()
    private val documentsLiveData = documentsRepository.getDocumentsLiveData()

    private val documentsObserver = Observer<List<ItemDocument>> {
        documentsViewState.value =
            DocumentsViewState(
                list = it
            )
    }

    fun getViewSate(): LiveData<DocumentsViewState> = documentsViewState

    override fun onCleared() {
        super.onCleared()
        documentsLiveData.removeObserver(documentsObserver)
    }


    init {
        documentsViewState.value =
            DocumentsViewState()
        documentsLiveData.observeForever(documentsObserver)
    }

    fun saveTestData(){
        addTestDataUseCase.save()
    }


}