package `fun`.gladkikh.app.fastpallet6.repository

import `fun`.gladkikh.app.fastpallet6.db.dao.DocumentsQueryDao
import `fun`.gladkikh.app.fastpallet6.domain.entity.screens.documents.DocumentsItem
import `fun`.gladkikh.app.fastpallet6.mapping.documents.toObject
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations

class DocumentsRepository(private val documentsQueryDao: DocumentsQueryDao) {
    fun getDocumentsLiveData(): LiveData<List<DocumentsItem>> = Transformations.map(
        documentsQueryDao.getDocuments()
    ) { list ->
       return@map list.map {
            it.toObject()
        }
    }
}