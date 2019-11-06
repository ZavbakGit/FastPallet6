package `fun`.gladkikh.app.fastpallet6.domain.usecase.documents

import `fun`.gladkikh.app.fastpallet6.domain.entity.CreatePallet
import `fun`.gladkikh.app.fastpallet6.domain.entity.Status
import `fun`.gladkikh.app.fastpallet6.mapping.createpallet.toCreatPalletOld
import `fun`.gladkikh.app.fastpallet6.network.ApiFactory
import `fun`.gladkikh.app.fastpallet6.network.intity.SendDocumentsReqest
import `fun`.gladkikh.app.fastpallet6.network.intity.SendDocumentsResponse
import `fun`.gladkikh.app.fastpallet6.repository.documents.old.DocumentsRepository
import `fun`.gladkikh.app.fastpallet6.repository.SettingsRepository
import io.reactivex.Completable


fun sendCreatePalletToServer(
    createPallet: CreatePallet,
    documentsRepository: DocumentsRepository,
    settingsRepository: SettingsRepository,
    apiFactory: ApiFactory
): Completable {

    val settingsPref = settingsRepository.getSettings()


    val objReqest = SendDocumentsReqest(
        settingsPref.code ?: "",
        list = listOf(createPallet.toCreatPalletOld())
    )


    return apiFactory.request(
        command = "command_send_doc",
        objRequest = objReqest,
        classResponse = SendDocumentsResponse::class.java
    )
        .map {
            it as SendDocumentsResponse
        }
        .doOnSuccess { response ->
            response.listConfirm.forEach {
                val doc =
                    (documentsRepository.getDocumentByGuid<CreatePallet>(it.guid) as CreatePallet)
                        .apply {
                            this.status = Status.getStatusByString(it.status).id
                        }
                documentsRepository.saveDocument(doc)
            }
        }
        .ignoreElement()
}



