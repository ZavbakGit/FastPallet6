package `fun`.gladkikh.app.fastpallet6.domain.entity.screens.createpallet.screen.box

import java.util.*

data class BoxScreenCreatePallet(
    val boxCountBox: Int? = null,
    val boxWeight: Float? = null,
    val palTotalWeight: Float? = null,
    val palTotalRowsCount: Int? = null,
    val palTotalCountBox: Int? = null,
    val palGuid: String? = null,
    val palNumber: String? = null,
    val docGuid: String? = null,
    val docGuidServer: String? = null,
    val docNumber: String? = null,
    val docDescription: String? = null,
    val prodName: String? = null,
    val prodStart: Int? = null,
    val prodEnd: Int? = null,
    val prodCoeff: Float? = null,
    val boxBarcode: String? = null,
    val boxGuid: String? = null,
    val boxDate: Date?= null
)
