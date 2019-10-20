package `fun`.gladkikh.app.fastpallet6.repository

import `fun`.gladkikh.app.fastpallet6.db.dao.DocumentsQueryDao
import `fun`.gladkikh.app.fastpallet6.domain.entity.ItemDocument
import `fun`.gladkikh.app.fastpallet6.mapping.documents.toObject
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations

class DocumentsRepository(private val documentsQueryDao: DocumentsQueryDao) {
    fun getDocumentsLiveData(): LiveData<List<ItemDocument>> = Transformations.map(
        documentsQueryDao.getDocuments()
    ) { list ->
       return@map list.map {
            it.toObject()
        }
    }
}