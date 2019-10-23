package `fun`.gladkikh.app.fastpallet6.mapping.createpallet

import `fun`.gladkikh.app.fastpallet6.db.entity.creatpallet.screen.pallet.TotalInfoPalletCreatePalletQueryDb
import `fun`.gladkikh.app.fastpallet6.db.entity.creatpallet.screen.product.PalletItemCreatePalletQueryDb
import `fun`.gladkikh.app.fastpallet6.domain.entity.screens.createpallet.screen.pallet.TotalInfoPalletCreatePallet
import `fun`.gladkikh.app.fastpallet6.domain.entity.screens.createpallet.screen.product.PalletItemCreatePallet

fun PalletItemCreatePalletQueryDb.toObject(): PalletItemCreatePallet {
    return PalletItemCreatePallet(
        guid = this.palGuid,
        boxCount = this.boxCount,
        boxWeight = this.boxWeight,
        prodName = this.prodName,
        boxRow = this.boxRow,
        palNumber = this.palNumber
    )
}

