package `fun`.gladkikh.app.fastpallet6.mapping.createpallet

import `fun`.gladkikh.app.fastpallet6.db.entity.creatpallet.screen.box.old.BoxScreenCreatePalletDb
import `fun`.gladkikh.app.fastpallet6.domain.entity.Status
import `fun`.gladkikh.app.fastpallet6.domain.entity.screens.createpallet.screen.box.old.BoxScreenCreatePallet
import java.util.*

fun BoxScreenCreatePalletDb.toObject(): BoxScreenCreatePallet {
    return BoxScreenCreatePallet(
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
        docStatus = Status.getStatusById(docStatus!!),
        palTotalCountBox = palTotalCountBox,
        palTotalRowsCount = palTotalRowsCount,
        palTotalWeight = palTotalWeight

    )
}

