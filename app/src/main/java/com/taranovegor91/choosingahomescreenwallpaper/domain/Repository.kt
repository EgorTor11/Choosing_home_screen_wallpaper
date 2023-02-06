package com.taranovegor91.choosingahomescreenwallpaper.domain

import com.taranovegor91.choosingahomescreenwallpaper.domain.models.Image

interface Repository {
    suspend   fun getImageList(q: String): MutableList<Image>
}