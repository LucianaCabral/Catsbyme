package com.lcabral.catsbyme.features.search.presentation

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.lcabral.catsbyme.features.breeds.R
import com.lcabral.catsbyme.features.breeds.databinding.FragmentSearchBinding
import com.lcabral.catsbyme.features.search.domain.model.Search
import com.lcabral.catsbyme.features.search.presentation.adapter.SearchAdapter
import com.lcabral.catsbyme.features.search.presentation.viewmodel.ActionView
import com.lcabral.catsbyme.features.search.presentation.viewmodel.SearchViewModel
import com.lcabral.catsbyme.libs.arch.extensions.hideKeyboard
import com.lcabral.catsbyme.libs.dstools.extensions.showError
import dagger.hilt.android.AndroidEntryPoint

const val LAST_SEARCH_ID = "last_search_query"
const val DEFAULT_QUERY = ""

@AndroidEntryPoint
internal class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SearchViewModel by viewModels()
    private val searchAdapter: SearchAdapter by lazy {
        SearchAdapter { itemClicked -> itemClicked }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState:
        Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = savedInstanceState?.getString(LAST_SEARCH_ID) ?: DEFAULT_QUERY

        setupRecycler()
        searchInit(id)
        setupObservables()
    }

    private fun setupObservables() {
        viewModel.stateView.observe(viewLifecycleOwner) { state ->
            updateList(state.searchBreed)
            loadingState(state.shouldShowLoading)
        }

        viewModel.viewAction.observe(viewLifecycleOwner) { action ->
            when (action) {
                ActionView.HideKeyboard -> view?.hideKeyboard()
                ActionView.ShowError -> showError()
            }
        }
    }

    private fun loadingState(isVisible: Boolean) {
        binding.progressbarSearch.isVisible = isVisible
    }

    private  fun navigateToHome() {
       findNavController().popBackStack(R.id.breedFragment, false)
    }

    private fun updateList(searchLists: List<Search>?) {
        searchAdapter.submitList(searchLists)
    }

    private fun searchInit(query: String) = with(binding) {
        inputEditTextSearch.setText(query)
        inputEditTextSearch.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == android.view.inputmethod.EditorInfo.IME_ACTION_SEARCH) {
                updateSearchList()
                true
            } else {
                false
            }
        }

        inputEditTextSearch.setOnKeyListener { _, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                updateSearchList()
                true
            } else {
                false
            }
        }
    }

    private fun updateSearchList() = with(binding) {
        inputEditTextSearch.editableText.trim().let {
            if (it.isNotEmpty()) {
                searchId(it.toString())
            }
        }
    }

    private fun searchId(query: String) {
        viewModel.submittedSearch(query)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(
            LAST_SEARCH_ID,
            binding.inputEditTextSearch.editableText.trim().toString()
        )
    }

    private fun setupRecycler() = with(binding) {
        binding.recyclerSearch.apply {
            setHasFixedSize(true)
            adapter = searchAdapter
        }
    }
}