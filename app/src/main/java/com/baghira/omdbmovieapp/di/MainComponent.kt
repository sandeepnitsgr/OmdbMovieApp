package com.baghira.omdbmovieapp.di

import android.content.Context
import com.baghira.omdbmovieapp.data.room.dao.FavoriteMovieDAO
import com.baghira.omdbmovieapp.presentation.ui.activity.MainActivity
import com.baghira.omdbmovieapp.presentation.ui.fragment.BaseFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [HttpModule::class,
        MainAppModule::class,
        RoomModule::class,
        RepositoryModule::class,
        ViewModelModule::class]
)
@Singleton
interface MainComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(baseFragment: BaseFragment)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun activityContext(context: Context): Builder
        fun build(): MainComponent
    }
}