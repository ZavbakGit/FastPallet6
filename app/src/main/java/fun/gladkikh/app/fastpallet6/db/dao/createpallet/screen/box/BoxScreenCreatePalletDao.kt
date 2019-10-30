package `fun`.gladkikh.app.fastpallet6.db.dao.createpallet.screen.box

import `fun`.gladkikh.app.fastpallet6.db.entity.creatpallet.screen.box.BoxScreenDataDb
import androidx.room.Dao
import androidx.room.Query

@Dao
interface BoxScreenCreatePalletDao {
    @Query("SELECT Doc.guid AS docGuid, " +
            "       Doc.number AS docNumber, " +
            "       Doc.date AS docDate, " +
            "       Doc.status AS docStatus, " +
            "       Doc.guidServer AS docGuidServer, " +
            "       Doc.dataChanged AS docChanged, " +
            "       Doc.isWasLoadedLastTime AS docIsWasLoadedLastTime, " +
            "       Doc.description AS docDescription, " +
            "       Doc.barcode AS docBarcode, " +
            "       Prod.guid AS prodGuid, " +
            "       Prod.guidDoc AS prodGuidDoc, " +
            "       Prod.number AS prodNumber, " +
            "       Prod.barcode AS prodBarcode, " +
            "       Prod.guidProduct AS prodGuidProduct, " +
            "       Prod.nameProduct AS prodNameProduct, " +
            "       Prod.codeProduct AS prodCodeProduct, " +
            "       Prod.ed AS prodEd, " +
            "       Prod.weightBarcode AS prodWeightBarcode, " +
            "       Prod.weightStartProduct AS prodWeightStartProduct, " +
            "       Prod.weightEndProduct AS prodWeightEndProduct, " +
            "       Prod.weightCoffProduct AS prodWeightCoffProduct, " +
            "       Prod.edCoff AS prodEdCoff, " +
            "       Prod.count AS prodCount, " +
            "       Prod.countBox AS prodCountBox, " +
            "       Prod.countPallet AS prodCountPallet, " +
            "       Prod.dataChanged AS prodDataChanged, " +
            "       Prod.isWasLoadedLastTime AS prodIsWasLoadedLastTime, " +
            "       Pal.guid AS palGuid, " +
            "       Pal.guidProduct AS palGuidProduct, " +
            "       Pal.number AS palNumber, " +
            "       Pal.barcode AS palBarcode, " +
            "       Pal.dataChanged AS palDataChanged, " +
            "       Pal.count AS palCount, " +
            "       Pal.countBox AS palCountBox, " +
            "       Pal.nameProduct AS palNameProduct, " +
            "       Pal.state AS palState, " +
            "       Pal.sclad AS palSclad, " +
            "       Box.barcode AS boxBarcode, " +
            "       Box.weight AS boxWeight, " +
            "       Box.countBox AS boxCountBox, " +
            "       Box.data AS boxData, " +
            "       Box.guid AS boxGuid " +
            "  FROM BoxCreatePalletDb Box " +
            "       JOIN " +
            "       PalletCreatePalletDb Pal ON Pal.guid = Box.guidPallet " +
            "       JOIN " +
            "       ProductCreatePalletDb Prod ON Prod.guid = Pal.guidProduct " +
            "       JOIN " +
            "       CreatePalletDb Doc ON Doc.guid = Prod.guidDoc " +
            " WHERE Box.guid = :guidBox " +
            " GROUP BY Pal.guid, " +
            "          Prod.guid, " +
            "          Doc.guid;")
    fun getData(guidBox:String): BoxScreenDataDb

    @Query("SELECT Doc.guid AS docGuid, " +
            "       Doc.number AS docNumber, " +
            "       Doc.date AS docDate, " +
            "       Doc.status AS docStatus, " +
            "       Doc.guidServer AS docGuidServer, " +
            "       Doc.dataChanged AS docChanged, " +
            "       Doc.isWasLoadedLastTime AS docIsWasLoadedLastTime, " +
            "       Doc.description AS docDescription, " +
            "       Doc.barcode AS docBarcode, " +
            "       Prod.guid AS prodGuid, " +
            "       Prod.guidDoc AS prodGuidDoc, " +
            "       Prod.number AS prodNumber, " +
            "       Prod.barcode AS prodBarcode, " +
            "       Prod.guidProduct AS prodGuidProduct, " +
            "       Prod.nameProduct AS prodNameProduct, " +
            "       Prod.codeProduct AS prodCodeProduct, " +
            "       Prod.ed AS prodEd, " +
            "       Prod.weightBarcode AS prodWeightBarcode, " +
            "       Prod.weightStartProduct AS prodWeightStartProduct, " +
            "       Prod.weightEndProduct AS prodWeightEndProduct, " +
            "       Prod.weightCoffProduct AS prodWeightCoffProduct, " +
            "       Prod.edCoff AS prodEdCoff, " +
            "       Prod.count AS prodCount, " +
            "       Prod.countBox AS prodCountBox, " +
            "       Prod.countPallet AS prodCountPallet, " +
            "       Prod.dataChanged AS prodDataChanged, " +
            "       Prod.isWasLoadedLastTime AS prodIsWasLoadedLastTime, " +
            "       Pal.guid AS palGuid, " +
            "       Pal.guidProduct AS palGuidProduct, " +
            "       Pal.number AS palNumber, " +
            "       Pal.barcode AS palBarcode, " +
            "       Pal.dataChanged AS palDataChanged, " +
            "       Pal.count AS palCount, " +
            "       Pal.countBox AS palCountBox, " +
            "       Pal.nameProduct AS palNameProduct, " +
            "       Pal.state AS palState, " +
            "       Pal.sclad AS palSclad, " +
            "       Box.barcode AS boxBarcode, " +
            "       Box.weight AS boxWeight, " +
            "       Box.countBox AS boxCountBox, " +
            "       Box.data AS boxData, " +
            "       Box.guid AS boxGuid, " +
            "       ( " +
            "           SELECT Sum(Round(BPT.weight * 1000) ) / 1000 " +
            "             FROM BoxCreatePalletDb BPT " +
            "            WHERE BPT.guidPallet IN ( " +
            "                      SELECT guid " +
            "                        FROM PalletCreatePalletDb palb " +
            "                       WHERE palb.guidProduct = Prod.guid " +
            "                  ) " +
            "       ) " +
            "       AS totalProdWeight, " +
            "       ( " +
            "           SELECT Count( * ) " +
            "             FROM BoxCreatePalletDb BPT " +
            "            WHERE BPT.guidPallet IN ( " +
            "                      SELECT guid " +
            "                        FROM PalletCreatePalletDb palb " +
            "                       WHERE palb.guidProduct = Prod.guid " +
            "                  ) " +
            "       ) " +
            "       AS totalProdRow, " +
            "       ( " +
            "           SELECT Sum(BPT.countBox) " +
            "             FROM BoxCreatePalletDb BPT " +
            "            WHERE BPT.guidPallet IN ( " +
            "                      SELECT guid " +
            "                        FROM PalletCreatePalletDb palb " +
            "                       WHERE palb.guidProduct = Prod.guid " +
            "                  ) " +
            "       ) " +
            "       AS totalProdCountBox, " +
            "       ( " +
            "           SELECT Count(DISTINCT guid) " +
            "             FROM PalletCreatePalletDb palb " +
            "            WHERE palb.guidProduct = Prod.guid " +
            "       ) " +
            "       AS totalProdCountPallet, " +
            "        " +
            "       ( " +
            "           SELECT Sum(Round(BPT.weight * 1000) ) / 1000 " +
            "             FROM BoxCreatePalletDb BPT " +
            "            WHERE BPT.guidPallet = Box.guidPallet " +
            "       ) " +
            "       AS totalPalWeight, " +
            "       ( " +
            "           SELECT Count( * ) " +
            "             FROM BoxCreatePalletDb BPT " +
            "            WHERE BPT.guidPallet = Box.guidPallet " +
            "       ) " +
            "       AS totalPalRow, " +
            "       ( " +
            "           SELECT Sum(BPT.countBox) " +
            "             FROM BoxCreatePalletDb BPT " +
            "            WHERE BPT.guidPallet = Box.guidPallet " +
            "       ) " +
            "       AS totalPalCountBox " +
            "  FROM BoxCreatePalletDb Box " +
            "       JOIN " +
            "       PalletCreatePalletDb Pal ON Pal.guid = Box.guidPallet " +
            "       JOIN " +
            "       ProductCreatePalletDb Prod ON Prod.guid = Pal.guidProduct " +
            "       JOIN " +
            "       CreatePalletDb Doc ON Doc.guid = Prod.guidDoc " +
            " WHERE Box.guid =:guidBox " +
            " GROUP BY Pal.guid, " +
            "          Prod.guid, " +
            "          Doc.guid;")
    fun getTotalData(guidBox:String):BoxScreenDataDb

}