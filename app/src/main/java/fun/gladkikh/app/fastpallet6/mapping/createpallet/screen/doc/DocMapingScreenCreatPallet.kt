package `fun`.gladkikh.app.fastpallet6.mapping.createpallet.screen.doc

import `fun`.gladkikh.app.fastpallet6.db.entity.creatpallet.screen.doc.ItemDocScreenDataDb
import `fun`.gladkikh.app.fastpallet6.domain.entity.Status
import `fun`.gladkikh.app.fastpallet6.domain.entity.screens.createpallet.doc.ItemDocScreenCreatePalletData
import java.util.*

fun ItemDocScreenDataDb.toObject(): ItemDocScreenCreatePalletData {
   return ItemDocScreenCreatePalletData(
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
       totalProdWeight = totalProdWeight,
       totalProdRow = totalProdRow,
       totalProdCountPallet = totalProdCountPallet,
       totalProdCountBox = totalProdCountBox
   )
}