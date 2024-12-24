package com.example.abhinavitsoultionspvtltd.di

import android.content.Context
import androidx.room.Room
import com.example.abhinavitsoultionspvtltd.data.room.dao.MemberDao
import com.example.abhinavitsoultionspvtltd.data.room.database.MemberDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): MemberDatabase {
        return Room.databaseBuilder(
            context,
            MemberDatabase::class.java,
            "user_database"
        ).build()
    }

    @Provides
    fun provideUserDao(database: MemberDatabase): MemberDao {
        return database.memberDao()
    }

    @Provides
    @Singleton
    fun provideContext(@ApplicationContext context: Context): Context {
        return context
    }
}