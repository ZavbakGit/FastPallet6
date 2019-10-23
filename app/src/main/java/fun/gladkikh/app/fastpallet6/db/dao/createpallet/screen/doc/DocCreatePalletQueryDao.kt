package `fun`.gladkikh.app.fastpallet6.db.dao.createpallet.screen.doc


import `fun`.gladkikh.app.fastpallet6.db.entity.creatpallet.CreatePalletDb
import `fun`.gladkikh.app.fastpallet6.db.entity.creatpallet.screen.doc.ProductItemCreatePalletQueryDb
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query

@Dao
interface DocCreatePalletQueryDao {

    @Query("SELECT * FROM CreatePalletDb WHERE guid =:guid")
    fun getDocCreatePalletByGuidLiveData(guid: String): LiveData<CreatePalletDb>

    @Query(
        "SELECT  SUM(BoxCountBOX) AS boxCount " +
                "       ,SUM(RoundedWieght) AS boxWeight " +
                "       ,SUM(BoxCountRows) AS boxRow " +
                "       ,SUM(PalCount) AS palCount " +
                "       ,ProdGuid AS prodGuid " +
                "       ,ProdName AS prodName " +
                "FROM ( " +
                "SELECT SUM(Box.countBox) AS BoxCountBOX " +
                "       ,SUM(Round(Box.weight * 1000) ) / 1000 AS RoundedWieght " +
                "       ,COUNT(Box.guid) AS BoxCountRows " +
                "       ,COUNT(DISTINCT Pal.guid) AS PalCount " +
                "       ,Prod.guid AS ProdGuid " +
                "       ,MAX(Prod.guidProduct) ProdProductGuid " +
                "       ,MAX(Prod.nameProduct) AS ProdName " +
                "FROM BoxCreatePalletDb Box " +
                "       LEFT JOIN " +
                "       PalletCreatePalletDb Pal ON Pal.guid = Box.guidPallet " +
                "       LEFT JOIN " +
                "       ProductCreatePalletDb Prod ON Prod.guid = Pal.guidProduct " +
                "WHERE Prod.guidDoc = :guidDoc " +
                "GROUP BY Prod.guid " +
                "UNION ALL " +
                "SELECT 0,0,0,0, Prod.guid  " +
                "       ,Prod.guidProduct  " +
                "       ,Prod.nameProduct " +
                "FROM ProductCreatePalletDb Prod WHERE Prod.guidDoc =:guidDoc) " +
                "GROUP BY ProdName " +
                "       ,ProdProductGuid " +
                "       ,ProdGuid " +
                "ORDER BY ProdName " +
                "       ,ProdProductGuid " +
                "       ,ProdGuid"
    )
    fun getListProductCreatePalletLiveData(guidDoc: String): LiveData<List<ProductItemCreatePalletQueryDb>>
}