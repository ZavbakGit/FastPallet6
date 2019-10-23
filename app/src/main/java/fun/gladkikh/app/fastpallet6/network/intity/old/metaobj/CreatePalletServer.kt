package `fun`.gladkikh.app.fastpallet6.network.intity.old.metaobj

import `fun`.gladkikh.app.fastpallet6.domain.entity.Status
import `fun`.gladkikh.app.fastpallet6.domain.entity.Type
import `fun`.gladkikh.app.fastpallet6.network.intity.old.MetaObjServer
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*


class CreatePalletServer(
    var guid: String? = null,
    var guidServer: String? = null,
    var type: Type = Type.CREATE_PALLET,
    var typeFromServer: String? = null,
    var status: Status = Status.NEW,
    var number: String? = null,
    var date: Date? = null,
    var dataChanged: Date? = null,
    var isWasLoadedLastTime: Boolean? = false,
    var description: String? = null,
    var barcode: String? = null,

    @SerializedName("stringProducts")
    @Expose
    var listProduct: List<ProductServer> = listOf()
) : MetaObjServer()