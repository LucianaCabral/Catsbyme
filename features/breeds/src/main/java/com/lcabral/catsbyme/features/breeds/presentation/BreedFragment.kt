package com.lcabral.catsbyme.features.breeds.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.PagingData
import com.lcabral.catsbyme.core.domain.model.model.Breed
import com.lcabral.catsbyme.features.breeds.databinding.FragmentBreedBinding
import com.lcabral.catsbyme.features.breeds.presentation.adapter.BreedAdapter
import com.lcabral.catsbyme.features.breeds.presentation.viewmodel.ActionView
import com.lcabral.catsbyme.features.breeds.presentation.viewmodel.BreedViewModel
import com.lcabral.catsbyme.libs.arch.extensions.showToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class BreedFragment : Fragment() {

    private var _binding: FragmentBreedBinding? = null
    private val binding get() = _binding!!
    private val viewModel: BreedViewModel by viewModels()
    private val adapter: BreedAdapter by lazy {
        BreedAdapter { itemClicked ->
            viewModel.onAdapterItemClicked(itemClicked)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBreedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView()
        setupObservers()
    }

    private fun setupObservers() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.value.breeds.collect { state ->
                    updateList(state)
                    adapter.addLoadStateListener {
                        onSuccessMoviesLoading(isStateSaved)
                    }
                }
            }

            viewModel.action.collect { action ->
                when (action) {
                    is ActionView.GoToDetails -> showToast("NOT IMPLEMENT")
                }
            }
        }
    }

    private fun updateList(breed: PagingData<Breed>) {
        lifecycleScope.launch {
            adapter.submitData(breed)
        }
    }

    private fun onSuccessMoviesLoading(isVisible: Boolean) {
        binding.progressCircular.isVisible = isVisible
    }

    private fun setupView() {
        setupRecyclerView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupRecyclerView() {
        binding.recyclerBreed.adapter = adapter
    }
}