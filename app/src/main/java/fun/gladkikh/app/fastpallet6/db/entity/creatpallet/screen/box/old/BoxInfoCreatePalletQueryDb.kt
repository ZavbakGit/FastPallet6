package `fun`.gladkikh.app.fastpallet6.db.entity.creatpallet.screen.box.old

data class BoxInfoCreatePalletQueryDb(
    val boxCountBox: Int?,
    val boxWeight: Float?,
    val palTotalWeight: Float?,
    val palTotalRowsCount: Int?,
    val palTotalCountBox: Int?,
    val palGuid: String?,
    val palNumber: String?,
    val docGuid: String?,
    val docGuidServer: String?,
    val docNumber: String?,
    val docDescription: String?,
    val prodName: String?,
    val prodStart: Int?,
    val prodEnd: Int?,
    val prodCoeff: Float?,
    val boxBarcode: String,
    val boxGuid: String?,
    val boxDate: Long?
)
