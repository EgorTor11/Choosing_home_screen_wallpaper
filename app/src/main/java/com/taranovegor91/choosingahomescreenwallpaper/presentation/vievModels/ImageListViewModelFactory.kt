package com.taranovegor91.choosingahomescreenwallpaper.presentation.vievModels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.taranovegor91.choosingahomescreenwallpaper.data.RepositoryImpl
import com.taranovegor91.choosingahomescreenwallpaper.domain.useCases.GetImageListUseCase

class ImageListViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    val repository = RepositoryImpl()
    val getImageListUseCase=GetImageListUseCase(repository)
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ImageListViewModel(getImageListUseCase) as T
    }
}