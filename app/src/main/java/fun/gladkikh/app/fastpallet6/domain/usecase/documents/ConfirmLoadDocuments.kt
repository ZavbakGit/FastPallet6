package `fun`.gladkikh.app.fastpallet6.domain.usecase.documents

import `fun`.gladkikh.app.fastpallet6.domain.entity.*
import `fun`.gladkikh.app.fastpallet6.network.ApiFactory
import `fun`.gladkikh.app.fastpallet6.network.intity.ConfirmDocumentsLoadRequest
import `fun`.gladkikh.app.fastpallet6.network.intity.ConfirmResponse
import `fun`.gladkikh.app.fastpallet6.network.intity.DocConfirm
import io.reactivex.Single


fun confirmLoadDocuments(
    listDocuments: List<Document>
    , settingsPref: SettingsPref
    , apiFactory: ApiFactory
): Single<List<Document>> {


    val list = listDocuments.map {
        when (it) {
            is CreatePallet -> {
                DocConfirm(it.guidServer!!, Type.CREATE_PALLET.nameServer)
            }
        }
    }


    return apiFactory.request(
        command = "command_confirm_doc",
        objRequest = ConfirmDocumentsLoadRequest(
            settingsPref.code ?: "",
            list = list
        ),
        classResponse = ConfirmResponse::class.java
    )
        .map {
            it as ConfirmResponse
        }
        .map { confirm ->
            if (list.map { it.guid }.sortedBy { it } !=
                confirm.listConfirm.map { it.guid }.sortedBy { it }) {
                throw Throwable("Не верное подтверждение!")
            }

            //Проставим статус из подтверждения
            return@map listDocuments.map {
                it.setNewStatus(confirm)
            }
        }
}

fun Document.setNewStatus(confirmResponse: ConfirmResponse): Document {
    return when (this) {
        is CreatePallet -> {
            this.apply {
                status =

                    Status.getStatusByString(
                        confirmResponse.listConfirm.find { it.guid == this.guidServer }?.status!!
                    ).id
            }
        }
    }
}