package `fun`.gladkikh.app.fastpallet6.mapping.createpallet

import `fun`.gladkikh.app.fastpallet6.db.entity.creatpallet.screen.doc.old.ProductItemCreatePalletQueryDb
import `fun`.gladkikh.app.fastpallet6.domain.entity.screens.createpallet.doc.old.ProductItemCreatePallet

fun ProductItemCreatePalletQueryDb.toObject(): ProductItemCreatePallet {
   return ProductItemCreatePallet(
       guid = this.prodGuid,
       boxCount = this.boxCount,
       boxWeight = this.boxWeight,
       name = this.prodName,
       palCount = this.palCount
   )
}