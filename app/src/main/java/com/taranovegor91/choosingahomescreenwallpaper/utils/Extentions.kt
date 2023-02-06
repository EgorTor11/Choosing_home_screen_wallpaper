package com.taranovegor91.choosingahomescreenwallpaper.utils

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.core.os.bundleOf
import com.taranovegor91.choosingahomescreenwallpaper.R
import com.taranovegor91.choosingahomescreenwallpaper.presentation.fragments.ImagesListFragment

fun Context.onTopicClick(viewId:Int):Bundle{
    var b= bundleOf(ImagesListFragment.KEY_THEME to "sea")
    when(viewId){
        R.id.buttonSea->{ b= bundleOf(ImagesListFragment.KEY_THEME to getString(R.string.sea))
        }
        R.id.buttonSun->{ b= bundleOf(ImagesListFragment.KEY_THEME to getString(R.string.sun))
        }
        R.id.buttonNature->{ b= bundleOf(ImagesListFragment.KEY_THEME to getString(R.string.nature))
        }
        R.id.buttonAnimals->{ b= bundleOf(ImagesListFragment.KEY_THEME to getString(R.string.animals))
        }
        R.id.buttonBalloon->{ b= bundleOf(ImagesListFragment.KEY_THEME to getString(R.string.balloon))
        }
        R.id.buttonCats->{ b= bundleOf(ImagesListFragment.KEY_THEME to getString(R.string.cats))
        }
    }
return b
}
fun Context.showToastLong(text: String) = Toast.makeText(this, text, Toast.LENGTH_LONG).show()