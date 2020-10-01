//package com.dicoding.capstoneproject.core.ui
//
//import android.content.Context
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.ViewModelProvider
//import com.dicoding.capstoneproject.core.di.Injection
//import com.dicoding.capstoneproject.core.domain.usecase.KamusUseCase
//import com.dicoding.capstoneproject.view.detail.DetailKamusViewModel
//import com.dicoding.capstoneproject.view.favorite.FavoriteViewModel
//import com.dicoding.capstoneproject.view.main.MainMenuViewModel
//
//
//class ViewModelFactory private constructor(private val kamusUseCase: KamusUseCase) :
//    ViewModelProvider.NewInstanceFactory() {
//
//    companion object {
//        @Volatile
//        private var instance: ViewModelFactory? = null
//
//        fun getInstance(context: Context): ViewModelFactory =
//            instance ?: synchronized(this) {
//                instance ?: ViewModelFactory(
//                    Injection.provideKamusUseCase(context)
//                )
//            }
//    }
//
//    @Suppress("UNCHECKED_CAST")
//    override fun <T : ViewModel> create(modelClass: Class<T>): T =
//        when {
//            modelClass.isAssignableFrom(MainMenuViewModel::class.java) -> {
//                MainMenuViewModel(kamusUseCase) as T
//            }
//            modelClass.isAssignableFrom(FavoriteViewModel::class.java) -> {
//                FavoriteViewModel(kamusUseCase) as T
//            }
//            modelClass.isAssignableFrom(DetailKamusViewModel::class.java) -> {
//                DetailKamusViewModel(kamusUseCase) as T
//            }
//            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
//        }
//}