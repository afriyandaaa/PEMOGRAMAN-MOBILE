package com.example.themooviedb.ui.viewPlayer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import com.example.themooviedb.R
import com.example.themooviedb.databinding.ActivityViewPlayerBinding
import com.example.themooviedb.models.Result
import com.example.themooviedb.util.Constants.Companion.API_KEY_YOUTUBE
import com.example.themooviedb.util.Features
import com.example.themooviedb.util.NetworkResult
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ViewPlayerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityViewPlayerBinding

    private lateinit var features: Features

    var result: Result? = null

    private val viewPlayerViewModel: ViewPlayerViewModel by viewModels()

    var alertDialogBuilderLoading: MaterialAlertDialogBuilder? = null
    var customLayoutLoading: View? = null
    var alertDialogLoading: AlertDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityViewPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        super.onCreate(savedInstanceState)

        features = Features()

        val intent = intent
        result = intent.getSerializableExtra("result") as Result

        supportActionBar!!.title = result!!.title

        initAlertDialog()

        result!!.id?.let { viewPlayerViewModel.getVideo(it) }

        viewPlayerViewModel.video.observe(this@ViewPlayerActivity, { response ->

            when(response) {

                is NetworkResult.Success -> {

                    val onInitializedListener = object : YouTubePlayer.OnInitializedListener {
                        override fun onInitializationSuccess(provider: YouTubePlayer.Provider, youTubePlayer: YouTubePlayer, b: Boolean) {
                            youTubePlayer.loadVideo(response.data?.results?.get(0)?.key)
                            youTubePlayer.play()
                        }

                        override fun onInitializationFailure(provider: YouTubePlayer.Provider, youTubeInitializationResult: YouTubeInitializationResult) {
                            Toast.makeText(this@ViewPlayerActivity, "No se pudo iniciar la reproducion.", Toast.LENGTH_LONG).show()
                        }
                    }

                    binding.youtubePlayerView.initialize(API_KEY_YOUTUBE, onInitializedListener)

                    alertDialogLoading!!.dismiss()
                }

                is NetworkResult.Error -> {
                    Toast.makeText(this@ViewPlayerActivity,response.message, Toast.LENGTH_LONG).show()
                    alertDialogLoading!!.dismiss()
                }

                is NetworkResult.Loading -> {
                    alertDialogLoading!!.show()
                }

            }
        })


    }

    fun initAlertDialog(){

        //memuat
        alertDialogBuilderLoading = MaterialAlertDialogBuilder(this@ViewPlayerActivity)
        customLayoutLoading = layoutInflater.inflate(R.layout.dialog_loading, null);
        alertDialogBuilderLoading!!.setCancelable(false)
        alertDialogBuilderLoading!!.setView(customLayoutLoading)
        alertDialogLoading = alertDialogBuilderLoading!!.create()

    }

}