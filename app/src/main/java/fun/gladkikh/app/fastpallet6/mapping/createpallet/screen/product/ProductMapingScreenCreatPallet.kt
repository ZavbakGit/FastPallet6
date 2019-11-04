package `fun`.gladkikh.app.fastpallet6.mapping.createpallet.screen.product

import `fun`.gladkikh.app.fastpallet6.db.entity.creatpallet.screen.product.ItemProductScreenDataDb
import `fun`.gladkikh.app.fastpallet6.db.entity.creatpallet.screen.product.ProductScreenDataDb
import `fun`.gladkikh.app.fastpallet6.domain.entity.Status
import `fun`.gladkikh.app.fastpallet6.domain.entity.screens.createpallet.product.ItemProductScreenCreatePalletData
import `fun`.gladkikh.app.fastpallet6.domain.entity.screens.createpallet.product.ProductScreenCreatePalletData
import java.util.*

fun ProductScreenDataDb.toObject(): ProductScreenCreatePalletData {
    return ProductScreenCreatePalletData(
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
        totalProdCountBox = totalProdCountBox,
        totalProdCountPallet = totalProdCountPallet,
        totalProdRow = totalProdRow,
        totalProdWeight = totalProdWeight
    )
}

fun ItemProductScreenDataDb.toObject(): ItemProductScreenCreatePalletData {
   return ItemProductScreenCreatePalletData(
       docGuid = docGuid,
       docNumber = docNumber,
       docIsWasLoadedLastTime = docIsWasLoadedLastTime,
       docGuidServer = docGuidServer,
       docDescription = docDescription,
       docDate = Date(docDate?:0L),
       docChanged = Date(docChanged?:0L),
       docBarcode = docBarcode,
       docStatus = Status.getStatusById(docStatus?:0),
       prodWeightCoffProduct = prodWeightCoffProduct,
       prodWeightEndProduct = prodWeightEndProduct,
       prodWeightStartProduct = prodWeightStartProduct,
       prodWeightBarcode = prodWeightBarcode,
       prodNumber = prodNumber,
       prodNameProduct = prodNameProduct,
       prodIsWasLoadedLastTime = prodIsWasLoadedLastTime,
       prodGuidProduct = prodGuidProduct,
       prodGuidDoc = prodGuidDoc,
       prodEdCoff = prodEdCoff,
       prodEd = prodEd,
       prodDataChanged = Date(prodDataChanged?:0L),
       prodCountPallet = prodCountPallet,
       prodCountBox = prodCountBox,
       prodCount = prodCount,
       prodCodeProduct = prodCodeProduct,
       prodBarcode = prodBarcode,
       prodGuid = prodGuid,
       palState = palState,
       palSclad = palSclad,
       palNumber = palNumber,
       palNameProduct = palNameProduct,
       palGuidProduct = palGuidProduct,
       palDataChanged = Date(palDataChanged?:0L),
       palCountBox = palCountBox,
       palCount = palCount,
       palBarcode = palBarcode,
       palGuid = palGuid,
       totalProdWeight = totalProdWeight,
       totalPalWeight = totalPalWeight,
       totalPalRow = totalPalRow,
       totalPalCountBox = totalPalCountBox,
       totalProdRow = totalProdRow,
       totalProdCountPallet = totalProdCountPallet,
       totalProdCountBox = totalProdCountBox
   )
}