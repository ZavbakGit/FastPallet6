package `fun`.gladkikh.app.fastpallet6.db.dao.createpallet.screen.doc

import `fun`.gladkikh.app.fastpallet6.db.entity.creatpallet.CreatePalletDb
import `fun`.gladkikh.app.fastpallet6.db.entity.creatpallet.screen.doc.ItemDocScreenDataDb
import androidx.room.Dao
import androidx.room.Query

@Dao
interface DocScreenCreatePalletDao {
    @Query("SELECT * FROM CreatePalletDb WHERE guid =:guidDoc ")
    fun getData(guidDoc:String): CreatePalletDb


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
            "       Prod.isWasLoadedLastTime AS prodIsWasLoadedLastTime " +
            "  FROM BoxCreatePalletDb Box " +
            "       JOIN " +
            "       PalletCreatePalletDb Pal ON Pal.guid = Box.guidPallet " +
            "       JOIN " +
            "       ProductCreatePalletDb Prod ON Prod.guid = Pal.guidProduct " +
            "       JOIN " +
            "       CreatePalletDb Doc ON Doc.guid = Prod.guidDoc " +
            " WHERE Doc.guid =:guidDoc " +
            " GROUP BY Prod.guid, " +
            "          Doc.guid;")
    fun getListItem(guidDoc:String):List<ItemDocScreenDataDb>

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
            "       AS totalProdCountPallet " +
            "  FROM BoxCreatePalletDb Box " +
            "       JOIN " +
            "       PalletCreatePalletDb Pal ON Pal.guid = Box.guidPallet " +
            "       JOIN " +
            "       ProductCreatePalletDb Prod ON Prod.guid = Pal.guidProduct " +
            "       JOIN " +
            "       CreatePalletDb Doc ON Doc.guid = Prod.guidDoc " +
            " WHERE Doc.guid =:guidDoc " +
            " GROUP BY Prod.guid, " +
            "          Doc.guid; ")
    fun getListItemTotal(guidDoc:String):List<ItemDocScreenDataDb>

}