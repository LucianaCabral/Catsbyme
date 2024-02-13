package com.lcabral.catsbyme.features.images.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.lcabral.catsbyme.features.images.databinding.FragmentPhotoBinding
import com.lcabral.catsbyme.features.images.domain.model.Photo
import com.lcabral.catsbyme.features.images.presentation.adapter.PhotoAdapter
import com.lcabral.catsbyme.features.images.presentation.viewmodel.ActionView
import com.lcabral.catsbyme.features.images.presentation.viewmodel.PhotoViewModel
import com.lcabral.catsbyme.libs.arch.extensions.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
internal class PhotoFragment : Fragment() {

    private var _binding: FragmentPhotoBinding? = null
    private val binding get() = _binding!!
    private val viewModel: PhotoViewModel by viewModels()
    private val photoAdapter: PhotoAdapter = PhotoAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPhotoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecycler()
        setupObservables()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onStart() {
        super.onStart()
        viewModel.getPhotosImages()
    }

    private fun setupObservables() {
        viewModel.stateView.observe(viewLifecycleOwner) { state ->
            updateList(state.getPhotos)
            println("<L> photo fragment = ${state.getPhotos}")
            println("<L> photo fragment size = ${state.getPhotos.size}")
            showToast("success")
        }

        viewModel.actionView.observe(viewLifecycleOwner) { action ->
            when (action) {
                is ActionView.NextFact -> viewModel.onNextClicked(action.photo)
            }
        }
    }

    private fun updateList(photos: List<Photo>) {
            photoAdapter.submitList(photos)
    }

    private fun setupRecycler() {
      binding.recyclerPhoto.adapter = photoAdapter
    }
}
