package com.example.themooviedb.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.themooviedb.R
import com.example.themooviedb.databinding.ActivityDetailsMovieBinding
import com.example.themooviedb.models.Result
import com.example.themooviedb.ui.viewPlayer.ViewPlayerActivity
import com.example.themooviedb.util.Constants
import com.example.themooviedb.util.Features
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsMovieActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityDetailsMovieBinding

    private lateinit var features: Features

    var result: Result? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityDetailsMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)
        super.onCreate(savedInstanceState)

        features = Features()

        val intent = intent
        result = intent.getSerializableExtra("result") as Result

        supportActionBar!!.title = result!!.title

        Glide.with(this@DetailsMovieActivity)
            .load("${Constants.BASE_URL_IMAGES}${result!!.poster_path}")
            .dontAnimate()
            .error(R.drawable.logomovie)
            .centerCrop()
            .into(binding.imageViewSrc)

        binding.ratingBarRatingVoteAverage.rating = (result!!.vote_average?.toFloat() ?: 0.0) as Float

        binding.materialTextViewOriginalTitle.text = "${result!!.original_title}"

        binding.materialTextViewDate.text = "${result!!.release_date}"

        binding.materialTextViewOverView.text = "${result!!.overview}"

        binding.buttonVideo.setOnClickListener(this@DetailsMovieActivity)

    }

    override fun onClick(v: View?) {

        when(v!!.id) {

            R.id.buttonVideo -> {

                if(features.isConnected(this@DetailsMovieActivity)){

                    val intent = Intent(this@DetailsMovieActivity, ViewPlayerActivity::class.java)
                    intent.putExtra("result", result)
                    startActivity(intent)

                }else{

                    Toast.makeText(this@DetailsMovieActivity, "Necesitas internet para acceder al video.", Toast.LENGTH_LONG).show()

                }

            }

        }

    }

        }