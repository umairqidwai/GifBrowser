package com.mobimeo.gifbrowser.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.mobimeo.gifbrowser.R
import com.mobimeo.gifbrowser.databinding.FragmentGifDetailBinding
import com.mobimeo.gifbrowser.presentation.utils.loadImage


/**
 * A simple [Fragment] subclass.
 * Use the [GifDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class GifDetailFragment : Fragment() {
    private lateinit var binding: FragmentGifDetailBinding
    val args: GifDetailFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentGifDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpView()
    }

    private fun setUpView(){
        binding.gifDetailImageView.loadImage(args.gifItem.images.original.url)
        binding.titleTv.text = String.format(getString(R.string.gif_detailview_title), args.gifItem.title)
        args.gifItem.user?.let { user ->
            binding.userTv.text = String.format(getString(R.string.gif_detailview_user), user.display_name)
        }
        binding.datetimeTv.text = String.format(getString(R.string.gif_detailview_datetime), args.gifItem.import_datetime)
        binding.typeTv.text = String.format(getString(R.string.gif_detailview_type), args.gifItem.type)
        binding.ratingTv.text = String.format(getString(R.string.gif_detailview_rating), args.gifItem.rating)
    }

}