package com.taranovegor91.choosingahomescreenwallpaper.presentation.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import com.taranovegor91.choosingahomescreenwallpaper.R
import com.taranovegor91.choosingahomescreenwallpaper.databinding.FragmentImagesListBinding
import com.taranovegor91.choosingahomescreenwallpaper.domain.models.Image
import com.taranovegor91.choosingahomescreenwallpaper.presentation.ImageAdapter
import com.taranovegor91.choosingahomescreenwallpaper.presentation.vievModels.ImageListViewModel
import com.taranovegor91.choosingahomescreenwallpaper.presentation.vievModels.ImageListViewModelFactory


class ImagesListFragment : Fragment(R.layout.fragment_images_list) {
    companion object {
        const val KEY_THEME = "KEY_THEME"
    }

    lateinit var binding: FragmentImagesListBinding
    lateinit var vm: ImageListViewModel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentImagesListBinding.bind(view)
        vm = ViewModelProvider(this, ImageListViewModelFactory(requireActivity())).get(
            ImageListViewModel::class.java)
        val adapter = ImageAdapter(object : ImageAdapter.Listener {
            override fun onImageClick(image: Image) {
                findNavController().navigate(R.id.action_imagesListFragment_to_showImageFragment,
                    bundleOf(ShowImageFragment.KEY_SHOW_IMAGE to image.imageUrl))
            }
        })

        binding.rcImage.layoutManager = GridLayoutManager(requireContext(), 3)
        (binding.rcImage.itemAnimator as? DefaultItemAnimator)?.supportsChangeAnimations = false
        binding.rcImage.adapter = adapter
        vm.getImageList(requireArguments().getString(KEY_THEME)!!)
        vm.imageListLiveData.observe(viewLifecycleOwner) {
            binding.progressBar2.visibility = View.GONE
            if (it.isEmpty()){
                Toast.makeText(requireContext(),"Oops, something went wrong. Please check your internet connection and try again.",Toast.LENGTH_LONG).show()
            }
            adapter.submitList(it)
        }
        binding.btnRestart.setOnClickListener{
            vm.getImageList(requireArguments().getString(KEY_THEME)!!)
            binding.progressBar2.visibility = View.VISIBLE
        }
    }
}