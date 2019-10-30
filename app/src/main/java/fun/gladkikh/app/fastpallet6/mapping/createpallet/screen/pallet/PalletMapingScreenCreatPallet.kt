package `fun`.gladkikh.app.fastpallet6.mapping.createpallet.screen.pallet

import `fun`.gladkikh.app.fastpallet6.db.entity.creatpallet.screen.pallet.ItemPalletScreenDataDb
import `fun`.gladkikh.app.fastpallet6.db.entity.creatpallet.screen.pallet.PalletScreenDataDb
import `fun`.gladkikh.app.fastpallet6.domain.entity.Status
import `fun`.gladkikh.app.fastpallet6.domain.entity.screens.createpallet.screen.pallet.ItemPalletScreenData
import `fun`.gladkikh.app.fastpallet6.domain.entity.screens.createpallet.screen.pallet.PalletScreenCreatePalletData
import java.util.*

fun PalletScreenDataDb.toObject(): PalletScreenCreatePalletData {
    return PalletScreenCreatePalletData(
        docGuid = docGuid,
        docStatus = Status.getStatusById(docStatus?:0),
        docBarcode = docBarcode,
        docChanged = Date(docChanged?:0L),
        docDate = Date(docDate?:0L),
        docDescription = docDescription,
        docGuidServer = docGuidServer,
        docIsWasLoadedLastTime = docIsWasLoadedLastTime,
        docNumber = docNumber,
        prodGuid = prodGuid,
        prodBarcode = prodBarcode,
        prodCodeProduct = prodCodeProduct,
        prodCount = prodCount,
        prodCountBox = prodCountBox,
        prodCountPallet = prodCountPallet,
        prodDataChanged = Date(prodDataChanged?:0L),
        prodEd = prodEd,
        prodEdCoff = prodEdCoff,
        prodGuidDoc = prodGuidDoc,
        prodGuidProduct = prodGuidProduct,
        prodIsWasLoadedLastTime = prodIsWasLoadedLastTime,
        prodNameProduct = prodNameProduct,
        prodNumber = prodNumber,
        prodWeightBarcode = prodWeightBarcode,
        prodWeightStartProduct = prodWeightStartProduct,
        prodWeightEndProduct = prodWeightEndProduct,
        prodWeightCoffProduct = prodWeightCoffProduct,
        palGuid = palGuid,
        palBarcode = palBarcode,
        palCount = palCount,
        palCountBox = palCountBox,
        palDataChanged = Date(palDataChanged?:0L),
        palGuidProduct = palGuidProduct,
        palNameProduct = palNameProduct,
        palNumber = palNumber,
        palSclad = palSclad,
        palState = palState,
        totalProdCountBox = totalProdCountBox,
        totalProdCountPallet = totalProdCountPallet,
        totalProdRow = totalProdRow,
        totalPalCountBox = totalPalCountBox,
        totalPalRow = totalPalRow,
        totalPalWeight = totalPalWeight,
        totalProdWeight = totalProdWeight
    )
}

fun ItemPalletScreenDataDb.toObject(): ItemPalletScreenData {
   return ItemPalletScreenData(
       boxGuid = boxGuid,
       boxData = Date(boxData?:0L),
       boxBarcode = boxBarcode,
       boxCountBox = boxCountBox,
       boxWeight = boxWeight
   )
}