package `fun`.gladkikh.app.fastpallet6.db.entity.creatpallet.screen.pallet.old

data class BoxItemCreatePalletQueryDb(
    val docGuid:String?,
    val prodGuid:String?,
    val palGuid:String?,
    val boxGuid:String?,
    val boxCount:Int?,
    val boxWeight:Float?
)