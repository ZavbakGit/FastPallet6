package `fun`.gladkikh.app.fastpallet6.db.dao.documents.old


import `fun`.gladkikh.app.fastpallet6.db.entity.documents.old.DocumentItemQueryDb
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query

@Dao
interface DocumentsQueryDao {
    @Query("SELECT guid, status, number, date, description,1 as type, dataChanged  FROM CreatePalletDb")
    fun getDocuments(): LiveData<List<DocumentItemQueryDb>>
}