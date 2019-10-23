package `fun`.gladkikh.app.fastpallet6.ui.fragment.documents

import `fun`.gladkikh.app.fastpallet6.domain.entity.screens.documents.DocumentsItem
import `fun`.gladkikh.app.fastpallet6.domain.usecase.testdata.AddTestDataUseCase
import `fun`.gladkikh.app.fastpallet6.network.ApiFactory
import `fun`.gladkikh.app.fastpallet6.repository.DocumentsRepository
import `fun`.gladkikh.app.fastpallet6.repository.SettingsRepository
import `fun`.gladkikh.app.fastpallet6.ui.base.BaseViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

class DocumentsViewModel(val documentsRepository: DocumentsRepository,
                         val addTestDataUseCase: AddTestDataUseCase,
                         val settingsRepository: SettingsRepository,
                         val apiFactory: ApiFactory
) : BaseViewModel() {
    private val viewStateLiveData = MutableLiveData<DocumentsViewState>()
    private val repositoryLiveData = documentsRepository.getDocumentsLiveData()

    private val documentsObserver = Observer<List<DocumentsItem>> {
        viewStateLiveData.value =
            DocumentsViewState(
                list = it
            )
    }

    fun getViewSate(): LiveData<DocumentsViewState> = viewStateLiveData

    override fun onCleared() {
        super.onCleared()
        repositoryLiveData.removeObserver(documentsObserver)
    }


    init {
        viewStateLiveData.value =
            DocumentsViewState()
        repositoryLiveData.observeForever(documentsObserver)
    }

    fun saveTestData(){
        addTestDataUseCase.save()
    }

    fun loadDocs() {
        loadDocuments()
    }


}