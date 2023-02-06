package com.taranovegor91.choosingahomescreenwallpaper.presentation.fragments

import android.location.GnssAntennaInfo
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.taranovegor91.choosingahomescreenwallpaper.R
import com.taranovegor91.choosingahomescreenwallpaper.databinding.FragmentTopicSelectionBinding
import com.taranovegor91.choosingahomescreenwallpaper.presentation.vievModels.TopicSelectionViewModel
import com.taranovegor91.choosingahomescreenwallpaper.utils.onTopicClick

class TopicSelectionFragment : Fragment(R.layout.fragment_topic_selection), View.OnClickListener {
    lateinit var binding: FragmentTopicSelectionBinding
    private val vm: TopicSelectionViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentTopicSelectionBinding.bind(view)
        binding.buttonSea.setOnClickListener(this)
        binding.buttonSun.setOnClickListener(this)
        binding.buttonAnimals.setOnClickListener(this)
        binding.buttonNature.setOnClickListener(this)
        binding.buttonBalloon.setOnClickListener(this)
        binding.buttonCats.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
//        var b=bundleOf(ImagesListFragment.KEY_THEME to "sea")
//        when(v?.id){
//            R.id.buttonSea->{ b= bundleOf(ImagesListFragment.KEY_THEME to "sea")}
//            R.id.buttonSun->{ b= bundleOf(ImagesListFragment.KEY_THEME to "sun")}
//            R.id.buttonNature->{ b= bundleOf(ImagesListFragment.KEY_THEME to "nature")}
//            R.id.buttonAnimals->{ b= bundleOf(ImagesListFragment.KEY_THEME to "animals")}
//            R.id.buttonBalloon->{ b= bundleOf(ImagesListFragment.KEY_THEME to "balloon")}
//            R.id.buttonCats->{ b= bundleOf(ImagesListFragment.KEY_THEME to "cats")}
//        }
        findNavController().navigate(R.id.action_topicSelectionFragment_to_imagesListFragment, requireContext().onTopicClick(v!!.id))
    }
}