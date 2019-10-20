package `fun`.gladkikh.app.fastpallet6.di

import `fun`.gladkikh.app.fastpallet6.db.AppDatabase
import `fun`.gladkikh.app.fastpallet6.db.dao.CreatePalletUpdateDao
import `fun`.gladkikh.app.fastpallet6.db.dao.DocumentsQueryDao
import `fun`.gladkikh.app.fastpallet6.domain.usecase.testdata.AddTestDataUseCase
import `fun`.gladkikh.app.fastpallet6.repository.CreatePalletRepositoryUpdate
import `fun`.gladkikh.app.fastpallet6.repository.DocumentsRepository
import `fun`.gladkikh.app.fastpallet6.ui.fragment.DocumentsViewModel
import android.content.Context
import androidx.room.Room
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


object DependencyModule {

    val appModule = module {
        single { getDataBase(androidContext()) }
        single { getCreatePalletUpdateDao(get()) }
        single { getDocumentsQueryDao(get()) }
        single { CreatePalletRepositoryUpdate(get()) }
        single { DocumentsRepository(get()) }
        single { AddTestDataUseCase(get()) }
        viewModel { DocumentsViewModel(get(),get()) }
    }

    private fun getDataBase(context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "mydatabase")
            .allowMainThreadQueries()
            .build()
    }

    fun getCreatePalletUpdateDao(database:AppDatabase): CreatePalletUpdateDao {
        return database.getCreatePalletUpdateDao()
    }

    fun getDocumentsQueryDao(database:AppDatabase): DocumentsQueryDao {
        return database.getDocumentsQueryDao()
    }
}