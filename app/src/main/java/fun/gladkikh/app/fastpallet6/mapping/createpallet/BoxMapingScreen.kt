package `fun`.gladkikh.app.fastpallet6.mapping.createpallet

import `fun`.gladkikh.app.fastpallet6.db.entity.creatpallet.screen.box.BoxTotalInfoCreatePalletQueryDb
import `fun`.gladkikh.app.fastpallet6.db.entity.creatpallet.screen.pallet.BoxItemCreatePalletQueryDb
import `fun`.gladkikh.app.fastpallet6.db.entity.creatpallet.screen.pallet.PalletTotalInfoCreatePalletQueryDb
import `fun`.gladkikh.app.fastpallet6.domain.entity.screens.createpallet.screen.box.BoxTotalInfoCreatePallet
import `fun`.gladkikh.app.fastpallet6.domain.entity.screens.createpallet.screen.pallet.BoxItemCreatePallet
import `fun`.gladkikh.app.fastpallet6.domain.entity.screens.createpallet.screen.pallet.TotalInfoPalletCreatePallet
import java.util.*

fun BoxTotalInfoCreatePalletQueryDb.toObject(): BoxTotalInfoCreatePallet {
    return BoxTotalInfoCreatePallet(
        boxCountBox = boxCountBox,
        palNumber = palNumber,
        boxWeight = boxWeight,
        palGuid = palGuid,
        boxGuid = boxGuid,
        prodName = prodName,
        boxDate = boxDate?.let { Date(it) },
        prodCoeff = prodCoeff,
        prodEnd = prodEnd,
        prodStart = prodStart,
        boxBarcode = boxBarcode,
        docDescription = docDescription,
        docGuid = docGuid,
        docGuidServer = docGuidServer,
        docNumber = docNumber,
        palTotalCountBox = palTotalCountBox,
        palTotalRowsCount = palTotalRowsCount,
        palTotalWeight = palTotalWeight
    )
}

