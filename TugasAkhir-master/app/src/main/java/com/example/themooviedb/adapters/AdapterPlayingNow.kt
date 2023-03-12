package com.example.themooviedb.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatRatingBar
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.themooviedb.R
import com.example.themooviedb.models.PlayingNowResult
import com.example.themooviedb.util.Constants
import com.google.android.material.textview.MaterialTextView

class AdapterPlayingNow (private val context: Context, private var mValues: MutableList<PlayingNowResult>): RecyclerView.Adapter<AdapterPlayingNow.ViewHolder>() {

    lateinit var playingNowClicklistener: PlayingNowClicklistener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_row_grid_layout, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mValues.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.mItem = mValues[position]

        Glide.with(context)
            .load("${Constants.BASE_URL_IMAGES}${holder.mItem!!.poster_path}")
            .dontAnimate()
            .error(R.drawable.logomovie)
            .centerCrop()
            .into(holder.imageViewMovieSrc!!)
//untuk memanggil item
        holder.ratingBarRatingVoteAverage!!.rating = (holder.mItem!!.vote_average?.toFloat() ?: 0.0) as Float

        holder.materialTextViewTitle!!.text = "${holder.mItem!!.original_title}"
        holder.materialTextViewDate!!.text = "${holder.mItem!!.release_date}"
        holder.materialTextViewOverView!!.text = "${holder.mItem!!.overview}"

        holder.itemView.setOnClickListener{
            playingNowClicklistener.onPlayingNowClick(holder.mItem!!, holder.itemView)
        }

    }

    fun setData(result: MutableList<PlayingNowResult>){
        mValues = result
        notifyDataSetChanged()
    }

    inner class ViewHolder(mView: View): RecyclerView.ViewHolder(mView){

        var imageViewMovieSrc: ImageView? = null
        var ratingBarRatingVoteAverage: AppCompatRatingBar? = null
        var materialTextViewTitle: MaterialTextView? = null
        var materialTextViewDate: MaterialTextView? = null
        var materialTextViewOverView:MaterialTextView?=null
        var mItem: PlayingNowResult? = null
        //tampilkan
        init {
            imageViewMovieSrc = mView.findViewById(R.id.imageViewMovieSrc)
            ratingBarRatingVoteAverage = mView.findViewById(R.id.ratingBarRatingVoteAverage)
            materialTextViewTitle = mView.findViewById(R.id.materialTextViewTitle)
            materialTextViewDate = mView.findViewById(R.id.materialTextViewDate)
            materialTextViewOverView = mView.findViewById(R.id.materialTextViewOverView)
        }

    }

    interface PlayingNowClicklistener{
        fun onPlayingNowClick(movie: PlayingNowResult, movieCard: View)
    }

}