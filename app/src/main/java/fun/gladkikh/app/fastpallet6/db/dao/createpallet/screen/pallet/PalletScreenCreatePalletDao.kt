package `fun`.gladkikh.app.fastpallet6.db.dao.createpallet.screen.pallet

import `fun`.gladkikh.app.fastpallet6.db.entity.creatpallet.screen.pallet.ItemPalletScreenDataDb
import `fun`.gladkikh.app.fastpallet6.db.entity.creatpallet.screen.pallet.PalletScreenDataDb
import androidx.room.Dao
import androidx.room.Query

@Dao
interface PalletScreenCreatePalletDao {
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
            "       Pal.sclad AS palSclad " +
            "  FROM BoxCreatePalletDb Box " +
            "       JOIN " +
            "       PalletCreatePalletDb Pal ON Pal.guid = Box.guidPallet " +
            "       JOIN " +
            "       ProductCreatePalletDb Prod ON Prod.guid = Pal.guidProduct " +
            "       JOIN " +
            "       CreatePalletDb Doc ON Doc.guid = Prod.guidDoc " +
            " WHERE Pal.guid =:guidPallet " +
            " GROUP BY Pal.guid, " +
            "          Prod.guid, " +
            "          Doc.guid;")
    fun getData(guidPallet:String): PalletScreenDataDb

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
            " WHERE Pal.guid = :guidPallet " +
            " GROUP BY Pal.guid, " +
            "          Prod.guid, " +
            "          Doc.guid;")
    fun getTotalData(guidPallet:String):PalletScreenDataDb


    @Query("SELECT Box.guid AS boxGuid,  " +
            "Box.guidPallet AS guidPallet,  " +
            "Box.barcode AS boxBarcode, " +
            "Box.weight AS boxWeight, " +
            "Box.countBox AS boxCountBox, " +
            "Box.data AS boxData " +
            "FROM  BoxCreatePalletDb Box WHERE Box.guidPallet =:guidBox ")
    fun getListItem(guidBox:String):List<ItemPalletScreenDataDb>
}