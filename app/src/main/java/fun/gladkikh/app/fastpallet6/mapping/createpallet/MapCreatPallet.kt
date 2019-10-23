package `fun`.gladkikh.app.fastpallet6.mapping.createpallet


import `fun`.gladkikh.app.fastpallet6.common.getFloatByParseStr
import `fun`.gladkikh.app.fastpallet6.common.getIntByParseStr
import `fun`.gladkikh.app.fastpallet6.db.entity.creatpallet.BoxCreatePalletDb
import `fun`.gladkikh.app.fastpallet6.db.entity.creatpallet.CreatePalletDb
import `fun`.gladkikh.app.fastpallet6.db.entity.creatpallet.PalletCreatePalletDb
import `fun`.gladkikh.app.fastpallet6.db.entity.creatpallet.ProductCreatePalletDb
import `fun`.gladkikh.app.fastpallet6.domain.entity.*
import `fun`.gladkikh.app.fastpallet6.network.intity.DocResponse
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

fun DocResponse.toCreatePallet(): CreatePallet {

    val guidDoc = UUID.randomUUID().toString()

    val listProd = this.listStringsProduct?.map { stringProd ->
        Product(
            guid = UUID.randomUUID().toString(),
            guidDoc = guidDoc,
            nameProduct = stringProd.nameProduct,
            isWasLoadedLastTime = true,
            dataChanged = java.util.Date(),
            barcode = null,
            number = stringProd.number,
            countBox = stringProd.countBox?.getIntByParseStr(),
            count = stringProd.count?.getFloatByParseStr(),
            countPallet = null,
            boxes = null,
            codeProduct = stringProd.codeProduct,
            ed = stringProd.ed,
            edCoff = stringProd.edCoff?.getFloatByParseStr(),
            guidProduct = stringProd.guidProduct,
            weightBarcode = stringProd.barcode,
            weightCoffProduct = stringProd.weightCoffProduct?.getFloatByParseStr(),
            weightEndProduct = stringProd.weightEndProduct?.getIntByParseStr(),
            weightStartProduct = stringProd.weightStartProduct?.getIntByParseStr()
        )
    } ?: kotlin.collections.listOf()


    return CreatePallet(
        guid = guidDoc,
        date = this.date,
        number = this.number,
        status = Status.getStatusByString(this.status).id,
        barcode = null,
        dataChanged = null,
        description = this.description,
        guidServer = this.guid,
        isWasLoadedLastTime = null,
        listProduct = listProd
    )
}