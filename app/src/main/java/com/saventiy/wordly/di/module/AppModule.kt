package com.saventiy.wordly.di.module

import android.app.Application
import android.content.Context
import androidx.room.Room
import androidx.work.WorkManager
import com.saventiy.wordly.data.repository.CollectionRepository
import com.saventiy.wordly.data.repository.CollectionRepositoryImpl
import com.saventiy.wordly.data.source.local.Database
import com.saventiy.wordly.data.source.local.dao.CollectionDao
import com.saventiy.wordly.screens.widget.WordWidget
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
internal open class AppModule {

    @Provides
    @Singleton
    fun provideDatabase(app: Application) =
        Room.databaseBuilder(
            app.applicationContext,
            Database::class.java,
            "wordly.db"
//            BuildConfig.DATABASE_NAME
        ).fallbackToDestructiveMigration()
            .build()

    @Provides
    fun provideCollectionDao(database: Database): CollectionDao = database.collectionDao()

    @Provides
    @Singleton
    fun provideCollection(collectionDao: CollectionDao): CollectionRepository =
        CollectionRepositoryImpl(collectionDao)

    @Provides
    fun provideWorkManager(@ApplicationContext context: Context): WorkManager =
        WorkManager.getInstance(context)

//
//    @Provides
//    fun provideWordWidget(): WordWidget = WordWidget

}