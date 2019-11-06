package `fun`.gladkikh.app.fastpallet6.ui.fragment.documents.old

import `fun`.gladkikh.app.fastpallet6.domain.usecase.documents.getListDocumentsDbFromServer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

fun DocumentsViewModel1.loadDocuments() {
    disposables.add(
        getListDocumentsDbFromServer(
            documentRepository = documentsRepository,
            apiFactory = apiFactory,
            settingsRepository = settingsRepository
        )
            .doOnSubscribe {
                showProgress.postValue(true)
            }
            .doFinally {
                showProgress.postValue(false)
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                message.value = "Загрузили ${it.size}"
            }, {
                message.value = it.message
            })
    )
}