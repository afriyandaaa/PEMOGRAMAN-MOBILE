package com.example.themooviedb.ui.playingNow

import android.content.Intent
import android.os.Bundle
import android.util.DisplayMetrics
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.themooviedb.R
import com.example.themooviedb.adapters.AdapterPlayingNow
import com.example.themooviedb.databinding.FragmentPlayingNowBinding
import com.example.themooviedb.models.PlayingNowResult
import com.example.themooviedb.ui.DetailsMovieActivity
import com.example.themooviedb.util.NetworkResult
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.ExperimentalCoroutinesApi
import com.example.themooviedb.models.Result

@ExperimentalCoroutinesApi
class PlayingNowFragment : Fragment() {

    private var _binding: FragmentPlayingNowBinding? = null
    private val binding get() = _binding!!

    private lateinit var playingNowViewModel: PlayingNowViewModel

    private val adapterPlayingNowFragment by lazy { AdapterPlayingNow(requireContext(), arrayListOf()) }

    var alertDialogBuilderLoading: MaterialAlertDialogBuilder? = null
    var customLayoutLoading: View? = null
    var alertDialogLoading: AlertDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        playingNowViewModel = ViewModelProvider(requireActivity()).get(PlayingNowViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Mengembang tataletak untuk fragmen ini
        _binding = FragmentPlayingNowBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAlertDialog()

        val displayMetrics = DisplayMetrics()
        requireActivity().windowManager.defaultDisplay.getMetrics(displayMetrics)

        if(displayMetrics.widthPixels < 1500){
            binding.recyclerViewPlayingNow.layoutManager = GridLayoutManager(requireContext(), 2)
        }else{
            binding.recyclerViewPlayingNow.layoutManager = GridLayoutManager(requireContext(), 3)
        }
        binding.recyclerViewPlayingNow.setHasFixedSize(true)

        adapterPlayingNowFragment.playingNowClicklistener = object : AdapterPlayingNow.PlayingNowClicklistener {
            override fun onPlayingNowClick(movie: PlayingNowResult, movieCard: View) {

                val intent = Intent(requireContext(), DetailsMovieActivity::class.java)
                intent.putExtra("result", Result(
                    movie.overview,
                    movie.original_language,
                    movie.original_title,
                    movie.video,
                    movie.title,
                    movie.poster_path,
                    movie.backdrop_path,
                    movie.release_date,
                    movie.popularity,
                    movie.vote_average,
                    movie.id,
                    movie.adult,
                    movie.vote_count
                ))
                startActivity(intent)

            }
        }

        binding.recyclerViewPlayingNow.adapter = adapterPlayingNowFragment

        playingNowViewModel.getPlayingNow()

        playingNowViewModel.playingNow.observe(viewLifecycleOwner, { response ->

            when(response) {

                is NetworkResult.Success -> {
                    response.data?.let{
                        if(it.isNotEmpty()){
                            adapterPlayingNowFragment.setData(it as MutableList<PlayingNowResult>)
                        }else{
                            Toast.makeText(requireContext(), "No tienes una copia de respaldo.", Toast.LENGTH_LONG).show()
                        }
                        alertDialogLoading!!.dismiss()
                    }
                }

                is NetworkResult.Error -> {
                    response.data?.let{
                        if(it.isNotEmpty()){
                            Toast.makeText(requireContext(), "No tienes acceso a internet", Toast.LENGTH_LONG).show()
                            adapterPlayingNowFragment.setData(it as MutableList<PlayingNowResult>)
                        }else{
                            Toast.makeText(requireContext(), "No tienes una copia de respaldo.", Toast.LENGTH_LONG).show()
                        }
                        alertDialogLoading!!.dismiss()
                    }
                }

                is NetworkResult.Loading -> {
                    alertDialogLoading!!.show()
                }

            }
        })

    }

    fun initAlertDialog(){

        //memuat
        alertDialogBuilderLoading = MaterialAlertDialogBuilder(requireContext())
        customLayoutLoading = layoutInflater.inflate(R.layout.dialog_loading, null);
        alertDialogBuilderLoading!!.setCancelable(false)
        alertDialogBuilderLoading!!.setView(customLayoutLoading)
        alertDialogLoading = alertDialogBuilderLoading!!.create()

    }

}