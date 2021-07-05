package com.baghira.omdbmovieapp.di
import android.content.Context
import androidx.room.Room
import com.baghira.omdbmovieapp.data.room.FavoriteMovieDB
import com.baghira.omdbmovieapp.data.room.dao.FavoriteMovieDAO
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule {

    @Singleton
    @Provides
    fun provideDb(context: Context): FavoriteMovieDB {
        return Room.databaseBuilder(
            context.applicationContext,
            FavoriteMovieDB::class.java,
            "FavoriteMovieDB"
        ).build()
    }

    @Singleton
    @Provides
    fun providesFavMovieDao(favoriteMovieDB: FavoriteMovieDB): FavoriteMovieDAO {
        return favoriteMovieDB.favoriteMovieDao
    }

} 