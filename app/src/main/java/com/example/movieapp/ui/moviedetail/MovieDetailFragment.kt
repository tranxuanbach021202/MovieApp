package com.example.movieapp.ui.moviedetail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.databinding.FragmentMovieDetailBinding
import com.example.movieapp.domain.model.Cast
import com.example.movieapp.ui.common.etx.VideoExt
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MovieDetailFragment : Fragment() {

    val viewModel : MovieDetailViewModel by viewModels()
    lateinit var binding: FragmentMovieDetailBinding

    private val _casts = emptyList<Cast>()
    private val safeArgs : MovieDetailFragmentArgs by navArgs()
    private val _movieId= MutableLiveData<Int>()
    private val castMovieAdapter = CastOfMovieAdapter()
    private val movieId : LiveData<Int> get() = _movieId

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _movieId.value = safeArgs.movieId
        Log.d(this.javaClass.name, "movieId" + movieId.toString())
        setupToolBar()

        movieId.observe(viewLifecycleOwner, {
            fetchData(it)
        })

        initObservers()
    }

    private fun initView() {
        // setup adapter cast
        binding.rvCasts.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvCasts.adapter = castMovieAdapter
    }

    private fun fetchData(id : Int) {
        viewModel.fetchMovieDetail(id)
        viewModel.getCastOfMovie(id)
        viewModel.getKeyVideoTrailer(id)
    }

    private fun initObservers() {
        viewModel.movieDetal.observe(viewLifecycleOwner, {
            with(binding) {
                item = it
            }
        })

        viewModel.listCast.observe(viewLifecycleOwner, {
            it?.let {
                Log.d("ListCast", it.toString())
                castMovieAdapter.setData(it)
            }
        })

        viewModel.keyVideo.observe(viewLifecycleOwner, {
            Log.d("keyVideoOb", it.toString())
            loadVideo(it)
        })
    }
    private fun setupToolBar() {
        binding.btnBack.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }


    private fun loadVideo(videoKey : String) {
        val _video = VideoExt(videoKey)
        Log.d("VideoKey", _video.getVideoHtml())
        binding.playVideo.settings.javaScriptEnabled = true
        binding.playVideo.webChromeClient = WebChromeClient()
        binding.playVideo.loadData(_video.getVideoHtml(), "text/html", "utf-8")
    }

}