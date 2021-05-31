package com.rusnoto.core.di

import android.content.Context
import androidx.room.Room
import com.rusnoto.core.data.source.local.room.GameDao
import com.rusnoto.core.data.source.local.room.GameDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Singleton
    @Provides
    fun getDatabase(@ApplicationContext context: Context): GameDatabase {
        val passphrase: ByteArray = SQLiteDatabase.getBytes("rusnoto".toCharArray())
        val factory = SupportFactory(passphrase)
        return Room.databaseBuilder(
            context,
            GameDatabase::class.java, "MainGame.db"
        ).fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }

    @Provides
    fun getGameDao(database: GameDatabase): GameDao = database.gameDao()
}