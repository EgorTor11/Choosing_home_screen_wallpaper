package com.taranovegor91.choosingahomescreenwallpaper.data

import android.util.Log
import androidx.core.net.toUri
import com.taranovegor91.choosingahomescreenwallpaper.domain.Repository
import com.taranovegor91.choosingahomescreenwallpaper.domain.models.Image
import kotlinx.coroutines.suspendCancellableCoroutine
import okhttp3.*
import org.json.JSONObject
import java.io.IOException
import java.util.*
import kotlin.coroutines.resume

class RepositoryImpl : Repository {
    companion object {
        val BASE_URL = "https://pixabay.com/api/"
        val CATEGORY_PARAM_NAME = "category"
        val ORIENTATION_PARAM_NAME = "orientation"
        val KEY_PARAM_NAME = "key"
        val Q_PARAM_NAME = "q"
        val IMAGE_TYPE_PARAM_NAME = "image_type"
        val image_type = "photo"
        val API_KEY = "33106230-b104905cd7ff74ed17e2229af"
        val ORIENTATION = "vertical"
        val CATEGORY = "backgrounds"

    }

    override suspend fun getImageList(q: String): MutableList<Image> {
        var imageList = mutableListOf<Image>()
        val uri = BASE_URL.toUri().buildUpon()
            .appendQueryParameter(KEY_PARAM_NAME, API_KEY)
            .appendQueryParameter(CATEGORY_PARAM_NAME, CATEGORY)
            .appendQueryParameter(ORIENTATION_PARAM_NAME, ORIENTATION)
            .appendQueryParameter(Q_PARAM_NAME, q)
            .appendQueryParameter(IMAGE_TYPE_PARAM_NAME, image_type)
            .appendQueryParameter("per_page", 21.toString())
            .build()
        suspendCancellableCoroutine { continuation ->
            val request = Request.Builder()
                .url(uri.toString()) //  "https://pixabay.com/api/?key=33106230-b104905cd7ff74ed17e2229af&q=flowers&image_type=photo"
                .get().build()
            val client = OkHttpClient.Builder().build()
            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    e.printStackTrace()
                    continuation.resume(imageList)
                }

                override fun onResponse(call: Call, response: Response) {
                    response.body.use { responseBody ->
                        if (response.isSuccessful) {
                            val strRespons = responseBody!!.string()
                            val jsonObject = JSONObject(strRespons)
                            val imageArr = jsonObject.getJSONArray("hits")
                            for (i in 0 until imageArr.length()) {
                                imageList.add(i,
                                    Image(imageArr.getJSONObject(i).getInt("id"),
                                        imageArr.getJSONObject(i).getString("largeImageURL")))
                            }
                        }
                    }
                    continuation.resume(imageList)
                }
            })
        }
        return imageList
    }
}