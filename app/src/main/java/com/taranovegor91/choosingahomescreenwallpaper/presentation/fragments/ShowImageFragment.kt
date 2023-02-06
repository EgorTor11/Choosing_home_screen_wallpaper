package com.taranovegor91.choosingahomescreenwallpaper.presentation.fragments

import android.app.WallpaperManager
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.drawToBitmap
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.taranovegor91.choosingahomescreenwallpaper.R
import com.taranovegor91.choosingahomescreenwallpaper.databinding.FragmentShowImageBinding
import com.taranovegor91.choosingahomescreenwallpaper.presentation.vievModels.ShowImageViewModel
import java.io.IOException


class ShowImageFragment : Fragment(R.layout.fragment_show_image) {
    companion object {
        const val KEY_SHOW_IMAGE = "KEY_SHOW_IMAGE"
    }
    private val vm: ShowImageViewModel by viewModels()
    lateinit var binding: FragmentShowImageBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentShowImageBinding.bind(view)
        Glide.with(requireContext()).load(requireArguments().getString(KEY_SHOW_IMAGE)).centerCrop()
            .into(binding.imShowImage)
        val manager =
            WallpaperManager.getInstance(requireContext())
        binding.btnSetAsWallpaper.setOnClickListener {
            try {
                manager.setBitmap(binding.imShowImage.drawToBitmap())
            } catch (e: IOException) {
                Toast.makeText(requireContext(), "не вышло не фортануло", Toast.LENGTH_LONG).show()
            }
        }
    }
}