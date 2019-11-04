package `fun`.gladkikh.app.fastpallet6.di

import `fun`.gladkikh.app.fastpallet6.db.AppDatabase
import `fun`.gladkikh.app.fastpallet6.db.dao.DocumentsQueryDao
import `fun`.gladkikh.app.fastpallet6.db.dao.createpallet.CreatePalletUpdateDao
import `fun`.gladkikh.app.fastpallet6.db.dao.createpallet.screen.box.BoxScreenCreatePalletDao
import `fun`.gladkikh.app.fastpallet6.db.dao.createpallet.screen.doc.DocCreatePalletQueryDao
import `fun`.gladkikh.app.fastpallet6.db.dao.createpallet.screen.pallet.PalletScreenCreatePalletDao
import `fun`.gladkikh.app.fastpallet6.db.dao.createpallet.screen.product.ProductScreenCreatePalletDao
import `fun`.gladkikh.app.fastpallet6.domain.usecase.testdata.AddTestDataUseCase
import `fun`.gladkikh.app.fastpallet6.network.ApiFactory
import `fun`.gladkikh.app.fastpallet6.repository.DocumentsRepository
import `fun`.gladkikh.app.fastpallet6.repository.SettingsRepository
import `fun`.gladkikh.app.fastpallet6.repository.createpallet.CreatePalletRepositoryUpdate
import `fun`.gladkikh.app.fastpallet6.repository.createpallet.DocCreatePalletRepository
import `fun`.gladkikh.app.fastpallet6.repository.createpallet.screen.box.BoxScreenCreatePalletRepository
import `fun`.gladkikh.app.fastpallet6.repository.createpallet.screen.pallet.PalletScreenCreatePalletRepository
import `fun`.gladkikh.app.fastpallet6.repository.createpallet.screen.product.ProductScreenCreatePalletRepository
import `fun`.gladkikh.app.fastpallet6.ui.fragment.createpallet.box.BoxCreatePalletViewModel
import `fun`.gladkikh.app.fastpallet6.ui.fragment.createpallet.doc.DocCreatePalletViewModel
import `fun`.gladkikh.app.fastpallet6.ui.fragment.createpallet.pallet.PalletCreatePalletViewModel
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

        //Добавляем  DAO
        //*****************************************************************************************
        //Documents
        single { getDocumentsQueryDao(get()) }

        //CreatePallet
        //Screen
        single { getBoxScreenCreatePalletDao(get()) }
        single { getPalletScreenCreatePalletDao(get()) }
        single { getProductScreenCreatePalletDao(get()) }


        single { getCreatePalletUpdateDao(get()) }
        single { getDocCreatePalletQueryDao(get()) }

        //Добавляем  repository
        //****************************************************************************************
        //Documents
        single { DocumentsRepository(get(), get()) }

        //CreatePallet
        //Screen
        single { BoxScreenCreatePalletRepository(get(),get()) }
        single { PalletScreenCreatePalletRepository(get(),get()) }
        single { ProductScreenCreatePalletRepository(get(),get()) }

        single { CreatePalletRepositoryUpdate(get()) }
        single { DocCreatePalletRepository(get()) }




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



        //Screen
        viewModel { BoxCreatePalletViewModel(get()) }
        viewModel { PalletCreatePalletViewModel(get()) }
        viewModel { ProductCreatePalletViewModel(get()) }


        single { AddTestDataUseCase(get()) }

    }

    private fun getDataBase(context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "mydatabase")
            .allowMainThreadQueries()
            .build()
    }


    //DAO

    //SCREEN
    private fun getBoxScreenCreatePalletDao(database: AppDatabase): BoxScreenCreatePalletDao {
        return database.getBoxScreenCreatePalletDao()
    }

    private fun getPalletScreenCreatePalletDao(database: AppDatabase): PalletScreenCreatePalletDao {
        return database.getPalletScreenCreatePalletDao()
    }

    private fun getProductScreenCreatePalletDao(database: AppDatabase): ProductScreenCreatePalletDao {
        return database.getProductScreenCreatePalletDao()
    }




    private fun getDocumentsQueryDao(database: AppDatabase): DocumentsQueryDao {
        return database.getDocumentsQueryDao()
    }
    private fun getCreatePalletUpdateDao(database: AppDatabase): CreatePalletUpdateDao {
        return database.getCreatePalletUpdateDao()
    }

    private fun getDocCreatePalletQueryDao(database: AppDatabase): DocCreatePalletQueryDao {
        return database.getDocCreatePalletQueryDao()
    }






}