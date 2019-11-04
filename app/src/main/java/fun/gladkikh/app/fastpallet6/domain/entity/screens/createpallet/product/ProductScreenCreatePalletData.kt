package `fun`.gladkikh.app.fastpallet6.domain.entity.screens.createpallet.product

import `fun`.gladkikh.app.fastpallet6.domain.entity.Status
import java.util.*

data class ProductScreenCreatePalletData(
    val docGuid:String?,
    val docNumber:String?,
    val docDate:Date?,
    val docStatus:Status?,
    val docGuidServer:String?,
    val docChanged:Date?,
    val docIsWasLoadedLastTime:Boolean?,
    val docDescription:String?,
    val docBarcode:String?,
    val prodGuid:String?,
    val prodGuidDoc:String?,
    val prodNumber:String?,
    val prodBarcode:String?,
    val prodGuidProduct:String?,
    val prodNameProduct:String?,
    val prodCodeProduct:String?,
    val prodEd:String?,
    val prodWeightBarcode:String?,
    val prodWeightStartProduct:Int?,
    val prodWeightEndProduct:Int?,
    val prodWeightCoffProduct:Float?,
    val prodEdCoff:Float?,
    val prodCount:Int?,
    val prodCountBox:Int?,
    val prodCountPallet:Int?,
    val prodDataChanged:Date?,
    val prodIsWasLoadedLastTime:Boolean?,


    val totalProdCountBox:Int?,
    val totalProdRow:Int?,
    val totalProdWeight:Float?,
    val totalProdCountPallet:Int?
)