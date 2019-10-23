package `fun`.gladkikh.app.fastpallet6.di

import `fun`.gladkikh.app.fastpallet6.db.AppDatabase
import `fun`.gladkikh.app.fastpallet6.db.dao.createpallet.CreatePalletUpdateDao
import `fun`.gladkikh.app.fastpallet6.db.dao.DocumentsQueryDao
import `fun`.gladkikh.app.fastpallet6.db.dao.createpallet.screen.doc.DocCreatePalletQueryDao
import `fun`.gladkikh.app.fastpallet6.db.dao.createpallet.screen.pallet.PalletCreatePalletQueryDao
import `fun`.gladkikh.app.fastpallet6.db.dao.createpallet.screen.product.ProductCreatePalletQueryDao
import `fun`.gladkikh.app.fastpallet6.domain.usecase.testdata.AddTestDataUseCase
import `fun`.gladkikh.app.fastpallet6.network.ApiFactory
import `fun`.gladkikh.app.fastpallet6.repository.createpallet.CreatePalletRepositoryUpdate
import `fun`.gladkikh.app.fastpallet6.repository.createpallet.DocCreatePalletRepository
import `fun`.gladkikh.app.fastpallet6.repository.DocumentsRepository
import `fun`.gladkikh.app.fastpallet6.repository.SettingsRepository
import `fun`.gladkikh.app.fastpallet6.repository.createpallet.ProductCreatePalletRepository
import `fun`.gladkikh.app.fastpallet6.ui.fragment.createpallet.doc.DocCreatePalletViewModel
import `fun`.gladkikh.app.fastpallet6.ui.fragment.createpallet.product.ProductCreatePalletViewModel
import `fun`.gladkikh.app.fastpallet6.ui.fragment.documents.DocumentsViewModel
import android.content.Context
import androidx.room.Room
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


object DependencyModule {

    val appModule = module {
        single { getDataBase(androidContext()) }

        //Добавляем  Dao
        //*****************************************************************************************
        //Documents
        single { getDocumentsQueryDao(get()) }

        //CreatePallet
        single { getCreatePalletUpdateDao(get()) }
        single { getDocCreatePalletQueryDao(get()) }
        single { getProductCreatePalletQueryDao(get()) }
        single { getPalletCreatePalletQueryDao(get()) }

        //Добавляем  repository
        //****************************************************************************************
        //Documents
        single { DocumentsRepository(get(), get()) }

        //CreatePallet
        single { CreatePalletRepositoryUpdate(get()) }
        single { DocCreatePalletRepository(get()) }
        single { ProductCreatePalletRepository(get()) }


        //Настройки
        single { SettingsRepository(get()) }

        //Сеть
        single { ApiFactory(get()) }

        //Добавляем ViewModel
        //*****************************************************************************************
        //Documents
        viewModel { DocumentsViewModel(get(), get(), get(), get()) }

        //CreatePallet
        viewModel { DocCreatePalletViewModel(get()) }
        viewModel { ProductCreatePalletViewModel(get()) }


        single { AddTestDataUseCase(get()) }

    }

    private fun getDataBase(context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "mydatabase")
            .allowMainThreadQueries()
            .build()
    }


    //DAO
    private fun getDocumentsQueryDao(database: AppDatabase): DocumentsQueryDao {
        return database.getDocumentsQueryDao()
    }
    private fun getCreatePalletUpdateDao(database: AppDatabase): CreatePalletUpdateDao {
        return database.getCreatePalletUpdateDao()
    }

    private fun getDocCreatePalletQueryDao(database: AppDatabase): DocCreatePalletQueryDao {
        return database.getDocCreatePalletQueryDao()
    }

    private fun getProductCreatePalletQueryDao(database: AppDatabase): ProductCreatePalletQueryDao {
        return database.getProductCreatePalletQueryDao()
    }

    private fun getPalletCreatePalletQueryDao(database: AppDatabase): PalletCreatePalletQueryDao {
        return database.getPalletCreatePalletQueryDao()
    }

}