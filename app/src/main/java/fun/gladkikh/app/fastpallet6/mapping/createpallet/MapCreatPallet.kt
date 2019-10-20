package `fun`.gladkikh.app.fastpallet6.mapping.createpallet


import `fun`.gladkikh.app.fastpallet6.db.entity.BoxCreatePalletDb
import `fun`.gladkikh.app.fastpallet6.db.entity.CreatePalletDb
import `fun`.gladkikh.app.fastpallet6.db.entity.PalletCreatePalletDb
import `fun`.gladkikh.app.fastpallet6.db.entity.ProductCreatePalletDb
import `fun`.gladkikh.app.fastpallet6.domain.entity.Box
import `fun`.gladkikh.app.fastpallet6.domain.entity.CreatePallet
import `fun`.gladkikh.app.fastpallet6.domain.entity.Pallet
import `fun`.gladkikh.app.fastpallet6.domain.entity.Product
import java.util.*

fun CreatePalletDb.toObject(): CreatePallet {
    return CreatePallet(
        guid = this.guid,
        description = this.description,
        guidServer = this.guidServer,
        isWasLoadedLastTime = this.isWasLoadedLastTime,
        dataChanged = this.dataChanged?.let { Date(it) },
        barcode = this.barcode,
        number = this.number,
        date = this.date?.let { Date(it) },
        status = this.status
    )
}

fun CreatePallet.toDb(): CreatePalletDb {

    return CreatePalletDb(
        guid = this.guid,
        status = this.status,
        number = this.number,
        date = this.date?.time,
        barcode = this.barcode,
        dataChanged = this.dataChanged?.time,
        isWasLoadedLastTime = this.isWasLoadedLastTime,
        guidServer = this.guidServer,
        description = this.description
    )
}

fun ProductCreatePalletDb.toObject(): Product {
    return Product(
        guid = this.guid,
        guidDoc = this.guidDoc,
        number = this.number,
        barcode = this.barcode,
        dataChanged = this.dataChanged?.let { Date(it) },
        isWasLoadedLastTime = this.isWasLoadedLastTime,
        countBox = this.countBox,
        nameProduct = this.nameProduct,
        count = this.count,
        weightStartProduct = this.weightStartProduct,
        weightEndProduct = this.weightEndProduct,
        weightCoffProduct = this.weightCoffProduct,
        weightBarcode = this.weightBarcode,
        edCoff = this.edCoff,
        ed = this.ed,
        codeProduct = this.codeProduct,
        countPallet = this.countPallet,
        guidProduct = this.guidProduct
    )
}

fun Product.toDb(): ProductCreatePalletDb {
    return ProductCreatePalletDb(
        guid = this.guid,
        guidDoc = this.guidDoc,
        countPallet = this.countPallet,
        codeProduct = this.codeProduct,
        ed = this.ed,
        edCoff = this.edCoff,
        weightBarcode = this.weightBarcode,
        weightCoffProduct = this.weightCoffProduct,
        weightEndProduct = this.weightEndProduct,
        weightStartProduct = this.weightStartProduct,
        guidProduct = this.guidProduct,
        count = this.count,
        nameProduct = this.nameProduct,
        countBox = this.countBox,
        isWasLoadedLastTime = this.isWasLoadedLastTime,
        dataChanged = this.dataChanged?.time,
        barcode = this.barcode,
        number = this.number
    )
}

fun PalletCreatePalletDb.toObject(): Pallet {
    return Pallet(
        guid = this.guid,
        guidProduct = this.guidProduct,
        number = this.number,
        barcode = this.barcode,
        dataChanged = this.dataChanged?.let { Date(it) },
        countBox = this.countBox,
        nameProduct = this.nameProduct,
        count = this.count,
        state = this.state,
        sclad = this.sclad
    )
}

fun Pallet.toDb(): PalletCreatePalletDb {
    return PalletCreatePalletDb(
        guid = this.guid,
        guidProduct = this.guidProduct,
        sclad = this.sclad,
        state = this.state,
        count = this.count,
        nameProduct = this.nameProduct,
        countBox = this.countBox,
        dataChanged = this.dataChanged?.time,
        barcode = this.barcode,
        number = this.number
    )
}

fun BoxCreatePalletDb.toObject(): Box {
    return Box(
        guid = this.guid,
        guidPallet = this.guidPallet,
        barcode = this.barcode,
        countBox = this.countBox,
        data = this.data?.let { Date(it) },
        weight = this.weight
    )
}

fun Box.toDb(): BoxCreatePalletDb {
    return BoxCreatePalletDb(
        guid = this.guid,
        guidPallet = this.guidPallet,
        weight = this.weight,
        data = this.data?.time,
        countBox = this.countBox,
        barcode = this.barcode
    )
}