package `fun`.gladkikh.app.fastpallet6.mapping.createpallet

import `fun`.gladkikh.app.fastpallet6.db.entity.creatpallet.screen.pallet.old.BoxItemCreatePalletQueryDb
import `fun`.gladkikh.app.fastpallet6.db.entity.creatpallet.screen.pallet.old.PalletTotalInfoCreatePalletQueryDb
import `fun`.gladkikh.app.fastpallet6.domain.entity.screens.createpallet.screen.pallet.old.BoxItemCreatePallet
import `fun`.gladkikh.app.fastpallet6.domain.entity.screens.createpallet.screen.pallet.old.TotalInfoPalletCreatePallet

fun BoxItemCreatePalletQueryDb.toObject(): BoxItemCreatePallet {
    return BoxItemCreatePallet(
        docGuid = docGuid,
        boxCount = boxCount,
        boxGuid = boxGuid,
        boxWeight = boxWeight,
        palGuid = palGuid,
        prodGuid = prodGuid
    )
}

fun PalletTotalInfoCreatePalletQueryDb.toObject(): TotalInfoPalletCreatePallet {
    return TotalInfoPalletCreatePallet(
        docGuid = docGuid,
        prodGuid = prodGuid,
        palGuid = palGuid,
        boxWeight = boxWeight,
        boxCount = boxCount,
        palNumber = palNumber,
        boxRow = boxRow,
        palCount = palCount
    )
}