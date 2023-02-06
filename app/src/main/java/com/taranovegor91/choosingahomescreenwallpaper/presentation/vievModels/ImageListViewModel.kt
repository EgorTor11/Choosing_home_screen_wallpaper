package com.taranovegor91.choosingahomescreenwallpaper.presentation.vievModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.taranovegor91.choosingahomescreenwallpaper.domain.models.Image
import com.taranovegor91.choosingahomescreenwallpaper.domain.useCases.GetImageListUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ImageListViewModel(private val getImageListUseCase: GetImageListUseCase) : ViewModel() {
    var imageListLiveData = MutableLiveData<MutableList<Image>>()
    fun getImageList(q: String) {
        viewModelScope.launch(Dispatchers.IO) {
            imageListLiveData.postValue(getImageListUseCase.execute(q))
        }
    }
}