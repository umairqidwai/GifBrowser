package com.mobimeo.gifbrowser.presentation.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobimeo.gifbrowser.BuildConfig
import com.mobimeo.gifbrowser.R
import com.mobimeo.gifbrowser.databinding.FragmentGifBrowserBinding
import com.mobimeo.gifbrowser.domain.model.Data
import com.mobimeo.gifbrowser.presentation.MainActivity
import com.mobimeo.gifbrowser.presentation.adapter.GifAdapter
import com.mobimeo.gifbrowser.presentation.viewmodel.GifBrowserViewModel
import javax.inject.Inject


class GifBrowserFragment : Fragment() {
    private lateinit var binding: FragmentGifBrowserBinding

    @Inject
    lateinit var viewModel: GifBrowserViewModel

    var count: Int = 0
    var offset: Int = 0
    var totalCount: Int = 0
    var searchInput: String = ""

    var gifListAdapter = GifAdapter(arrayListOf())

    companion object {
        fun newInstance() = GifBrowserFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGifBrowserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupSearchView()
        viewModel.gifList.observe(viewLifecycleOwner, Observer { gitSearch ->
            gitSearch?.let {
                count = it.pagination.count
                offset = count
                totalCount = it.pagination.total_count
                gifListAdapter.updateGifs(it.data)
                gifListAdapter.onItemClick = { gifItem ->
                    navigateToGifDetail(gifItem)
                }
            }
        })
        viewModel.gifMoreItemList.observe(viewLifecycleOwner, Observer { gitSearch ->
            gitSearch?.let {
                count = it.pagination.count
                offset += count
                totalCount = it.pagination.total_count
                gifListAdapter.loadMoreGifs(it.data)
                gifListAdapter.onItemClick = { moreGifItems ->
                    navigateToGifDetail(moreGifItems)
                }
            }
        })

        viewModel.gifSearchError.observe(viewLifecycleOwner, Observer { isError ->
            if (isError != null) {
                Toast.makeText(context, isError, Toast.LENGTH_SHORT).show()
            }
        })

        viewModel.loading.observe(viewLifecycleOwner, Observer { loading ->
            if(loading){
                binding.progressLoading.visibility = View.VISIBLE
            }else{
                binding.progressLoading.visibility = View.GONE
            }
        })

    }

    private fun setupSearchView() {
        binding.gifSearchRv.apply {
            layoutManager = GridLayoutManager(context, 3)
            val verticalDecorator = DividerItemDecoration(activity, DividerItemDecoration.VERTICAL)
            val horizontalDecorator = DividerItemDecoration(activity, DividerItemDecoration.HORIZONTAL)
            val grid_divider = AppCompatResources.getDrawable(context, R.drawable.grid_divider)
            if (grid_divider != null) {
                verticalDecorator.setDrawable(grid_divider)
            }
            if (grid_divider != null) {
                horizontalDecorator.setDrawable(grid_divider)
            }
            adapter = gifListAdapter
        }
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            binding.gifSearchRv.setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
                if (isLastItemVisible()) {
                    if (shouldLoadMoreItems()) {
                        viewModel.loadMoreGif(
                            BuildConfig.API_KEY,
                            this.searchInput,
                            this.offset
                        )
                    }
                }
            }
        }
        binding.gifSearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    viewModel.job1?.cancel()
                    searchInput = newText
                    viewModel.searchGif(BuildConfig.API_KEY, newText)
                }
                return true
            }

        })
    }

    private fun shouldLoadMoreItems(): Boolean {
        return gifListAdapter.itemCount < totalCount
    }


    private fun isLastItemVisible(): Boolean {
        val layoutManager = binding.gifSearchRv.getLayoutManager() as LinearLayoutManager
        val pos = layoutManager.findLastCompletelyVisibleItemPosition()
        val numItems: Int = gifListAdapter.itemCount
        return pos >= numItems - 1
    }

    private fun navigateToGifDetail(gifItem: Data) {
        val direction =
            GifBrowserFragmentDirections.navigateToGifDetailFragment(gifItem)
        findNavController().navigate(direction)

    }

    override fun onAttach(context: Context) {
        (requireActivity() as MainActivity).appComponent.inject(this)
        super.onAttach(context)
    }

}