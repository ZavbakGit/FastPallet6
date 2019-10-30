package `fun`.gladkikh.app.fastpallet6.db.entity.creatpallet.screen.box.old

data class BoxScreenCreatePalletDb(
    val docGuid: String?,
    val docGuidServer: String?,
    val docNumber: String?,
    val docDescription: String?,
    val docStatus: Int?,

    val palTotalWeight: Float?,
    val palTotalRowsCount: Int?,
    val palTotalCountBox: Int?,
    val palGuid: String?,
    val palNumber: String?,

    val prodName: String?,
    val prodStart: Int?,
    val prodEnd: Int?,
    val prodCoeff: Float?,

    val boxGuid: String?,
    val boxBarcode: String,
    val boxDate: Long?,
    val boxCountBox: Int?,
    val boxWeight: Float?

)
