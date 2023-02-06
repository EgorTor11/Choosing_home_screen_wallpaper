package com.taranovegor91.choosingahomescreenwallpaper.domain.useCases

import com.taranovegor91.choosingahomescreenwallpaper.domain.Repository
import com.taranovegor91.choosingahomescreenwallpaper.domain.models.Image

class GetImageListUseCase(private val repository: Repository) {
    suspend fun execute(q: String): MutableList<Image> {
        return repository.getImageList(q)
    }
}