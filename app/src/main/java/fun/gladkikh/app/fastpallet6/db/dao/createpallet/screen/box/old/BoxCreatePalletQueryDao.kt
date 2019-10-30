package `fun`.gladkikh.app.fastpallet6.db.dao.createpallet.screen.box.old

import `fun`.gladkikh.app.fastpallet6.db.entity.creatpallet.screen.box.old.BoxScreenCreatePalletDb
import androidx.room.Dao
import androidx.room.Query

@Dao
interface BoxCreatePalletQueryDao {
    @Query("SELECT Sum(Box.countBox) AS boxCountBox,  " +
            "       Sum(Round(Box.weight * 1000) ) / 1000 AS boxWeight,  " +
            "       (  " +
            "           SELECT Sum(Round(BPT.weight * 1000) ) / 1000  " +
            "             FROM BoxCreatePalletDb BPT  " +
            "            WHERE BPT.guidPallet = Box.guidPallet  " +
            "       )  " +
            "       AS palTotalWeight,  " +
            "       (  " +
            "           SELECT Count( * )   " +
            "             FROM BoxCreatePalletDb BPT  " +
            "            WHERE BPT.guidPallet = Box.guidPallet  " +
            "       )  " +
            "       AS palTotalRowsCount,  " +
            "       (  " +
            "           SELECT Sum(BPT.countBox)   " +
            "             FROM BoxCreatePalletDb BPT  " +
            "            WHERE BPT.guidPallet = Box.guidPallet  " +
            "       )  " +
            "       AS palTotalCountBox,  " +
            "       pal.guid AS palGuid,  " +
            "       MAX(Pal.number) AS palNumber,  " +
            "       Doc.guid AS docGuid,  " +
            "       Doc.guidServer AS docGuidServer,  " +
            "       Doc.number AS docNumber,  " +
            "       Doc.description As docDescription,  " +
            "       Prod.nameProduct As prodName,  " +
            "       Prod.weightStartProduct As prodStart,  " +
            "       Prod.weightEndProduct As prodEnd,  " +
            "       Prod.weightCoffProduct As prodCoeff,  " +
            "       Box.barcode As boxBarcode,  " +
            "       Box.guid As  boxGuid,  " +
            "       Box.data As boxDate  " +
            "  FROM BoxCreatePalletDb Box  " +
            "       LEFT JOIN  " +
            "       PalletCreatePalletDb Pal ON Pal.guid = Box.guidPallet  " +
            "       LEFT JOIN  " +
            "       ProductCreatePalletDb Prod ON Prod.guid = Pal.guidProduct  " +
            "       LEFT JOIN  " +
            "       CreatePalletDb Doc ON Doc.guid = Prod.guidDoc  " +
            " WHERE Box.guid =:guidBox   " +
            " GROUP BY Pal.guid,  " +
            "          Prod.guid,  " +
            "          Doc.guid;")
    fun getBoxScreenCreatePalletTotalDb(guidBox:String): BoxScreenCreatePalletDb

    //ToDo Загрузить статус
    @Query("SELECT guid AS boxGuid,  barcode AS boxBarcode,data AS boxDate,countBox AS boxCountBox, weight AS boxWeight FROM BoxCreatePalletDb WHERE guid =:guid")
    fun getBoxScreenCreatePalletDb(guid:String): BoxScreenCreatePalletDb
}