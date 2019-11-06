package `fun`.gladkikh.app.fastpallet6.di

import `fun`.gladkikh.app.fastpallet6.db.AppDatabase
import `fun`.gladkikh.app.fastpallet6.db.dao.documents.old.DocumentsQueryDao
import `fun`.gladkikh.app.fastpallet6.db.dao.createpallet.CreatePalletUpdateDao
import `fun`.gladkikh.app.fastpallet6.db.dao.createpallet.screen.box.BoxScreenCreatePalletDao
import `fun`.gladkikh.app.fastpallet6.db.dao.createpallet.screen.doc.DocScreenCreatePalletDao
import `fun`.gladkikh.app.fastpallet6.db.dao.createpallet.screen.pallet.PalletScreenCreatePalletDao
import `fun`.gladkikh.app.fastpallet6.db.dao.createpallet.screen.product.ProductScreenCreatePalletDao
import `fun`.gladkikh.app.fastpallet6.db.dao.documents.DocumentsScreenDao
import `fun`.gladkikh.app.fastpallet6.domain.usecase.testdata.AddTestDataUseCase
import `fun`.gladkikh.app.fastpallet6.network.ApiFactory
import `fun`.gladkikh.app.fastpallet6.repository.documents.old.DocumentsRepository
import `fun`.gladkikh.app.fastpallet6.repository.SettingsRepository
import `fun`.gladkikh.app.fastpallet6.repository.createpallet.CreatePalletRepositoryUpdate
import `fun`.gladkikh.app.fastpallet6.repository.createpallet.screen.box.BoxScreenCreatePalletRepository
import `fun`.gladkikh.app.fastpallet6.repository.createpallet.screen.doc.DocScreenCreatePalletRepository
import `fun`.gladkikh.app.fastpallet6.repository.createpallet.screen.pallet.PalletScreenCreatePalletRepository
import `fun`.gladkikh.app.fastpallet6.repository.createpallet.screen.product.ProductScreenCreatePalletRepository
import `fun`.gladkikh.app.fastpallet6.repository.documents.DocumentsScreenRepository
import `fun`.gladkikh.app.fastpallet6.ui.fragment.createpallet.box.BoxCreatePalletViewModel
import `fun`.gladkikh.app.fastpallet6.ui.fragment.createpallet.doc.DocCreatePalletViewModel
import `fun`.gladkikh.app.fastpallet6.ui.fragment.createpallet.pallet.PalletCreatePalletViewModel
import `fun`.gladkikh.app.fastpallet6.ui.fragment.createpallet.product.ProductCreatePalletViewModel
import `fun`.gladkikh.app.fastpallet6.ui.fragment.documents.DocumentsViewModel
import `fun`.gladkikh.app.fastpallet6.ui.fragment.documents.old.DocumentsViewModel1
import android.content.Context
import androidx.room.Room
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


object DependencyModule {

    val appModule = module {
        single { getDataBase(androidContext()) }

        //****************************************************************************************
        //DAO

        //DOCUMENTS
        single { getDocumentsQueryDao(get()) }
        single { getDocumentsScreenDao(get()) }

        //CREATE PALLET
        //Screen
        single { getBoxScreenCreatePalletDao(get()) }
        single { getPalletScreenCreatePalletDao(get()) }
        single { getProductScreenCreatePalletDao(get()) }
        single { getDocScreenCreatePalletDao(get()) }


        single { getCreatePalletUpdateDao(get()) }


        //Documents
        single {
            DocumentsRepository(
                get(),
                get()
            )
        }

        //****************************************************************************************
        //REPOSITORY

        //DOCUMENTS
        single { DocumentsScreenRepository(get(),get()) }

        //CREATE PALLET
        single { BoxScreenCreatePalletRepository(get(),get()) }
        single { PalletScreenCreatePalletRepository(get(),get()) }
        single { ProductScreenCreatePalletRepository(get(),get()) }
        single { DocScreenCreatePalletRepository(get(),get()) }

        single { CreatePalletRepositoryUpdate(get()) }


        //SETTINGS
        single { SettingsRepository(get()) }

        //NET
        single { ApiFactory(get()) }

        //****************************************************************************************
        //VIEW MODEL

        //DOCUMENTS
        viewModel { DocumentsViewModel(get()) }

        viewModel {
            DocumentsViewModel1(
                get(),
                get(),
                get(),
                get()
            )
        }

        //CREATE PALLET
        viewModel { BoxCreatePalletViewModel(get()) }
        viewModel { PalletCreatePalletViewModel(get()) }
        viewModel { ProductCreatePalletViewModel(get()) }
        viewModel { DocCreatePalletViewModel(get()) }

        //****************************************************************************************
        //USE CASE
        single { AddTestDataUseCase(get()) }

    }

    private fun getDataBase(context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "mydatabase")
            .allowMainThreadQueries()
            .build()
    }


    //DAO

    //SCREEN

    //DOCUMENTS
    private fun getDocumentsScreenDao(database: AppDatabase): DocumentsScreenDao {
        return database.getDocumentsDao()
    }

    //CREATE PALLET
    private fun getBoxScreenCreatePalletDao(database: AppDatabase): BoxScreenCreatePalletDao {
        return database.getBoxScreenCreatePalletDao()
    }

    private fun getPalletScreenCreatePalletDao(database: AppDatabase): PalletScreenCreatePalletDao {
        return database.getPalletScreenCreatePalletDao()
    }

    private fun getProductScreenCreatePalletDao(database: AppDatabase): ProductScreenCreatePalletDao {
        return database.getProductScreenCreatePalletDao()
    }

    private fun getDocScreenCreatePalletDao(database: AppDatabase): DocScreenCreatePalletDao {
        return database.getDocScreenCreatePalletDao()
    }




    private fun getDocumentsQueryDao(database: AppDatabase): DocumentsQueryDao {
        return database.getDocumentsQueryDao()
    }
    private fun getCreatePalletUpdateDao(database: AppDatabase): CreatePalletUpdateDao {
        return database.getCreatePalletUpdateDao()
    }

}