package com.example.movieapp.ui.moviedetail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.databinding.FragmentMovieDetailBinding
import com.example.movieapp.data.models.Cast
import com.example.movieapp.ui.common.etx.video
import com.example.movieapp.ui.moviedetail.castofmovie.CastMovieAdapter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MovieDetailFragment : Fragment() {

    val viewModel : MovieDetailViewModel by viewModels()
    lateinit var binding: FragmentMovieDetailBinding

    private val _casts = emptyList<Cast>()
    private val castsMovieAdapter = CastMovieAdapter()
    private val safeArgs : MovieDetailFragmentArgs by navArgs()
    private var movieId : Int = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("MovieFragment", "adddd")
        binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        movieId = safeArgs.movieId
        Log.d("MovieId", movieId.toString())
        viewModel.setMovieId(movieId)
        setupToolBar()
        binding.rvCasts.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvCasts.adapter = castsMovieAdapter
        getMovieDetail()

    }


    private fun setupToolBar() {
        binding.btnBack.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    private fun getMovieDetail() {
        viewModel.movieResponse.observe(viewLifecycleOwner) {
            Log.d("Movieeee", it.toString())
        }
        viewModel.movieDetail.observe(viewLifecycleOwner){
            Log.d("Movieeee", it.toString())
        }
        viewModel.casts.observe(viewLifecycleOwner) {
            it?.let { it1 -> castsMovieAdapter.setData(it1) }
            Log.d("Castt", it.toString())
        }

        viewModel.videoDetail.observe(viewLifecycleOwner) {
            if (it != null) {
                val videoKey = viewModel.getKeyVideo()
                if (videoKey != null) {
                    loadVideo(videoKey)
                }
            }
        }
    }

    private fun loadVideo(videoKey : String) {
        binding.playVideo.settings.javaScriptEnabled = true
        binding.playVideo.webChromeClient = WebChromeClient()
        binding.playVideo.loadData(video, "text/html", "utf-8")
    }

}