package com.shahriar.task8.ui.home

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.shahriar.task8.R
import com.shahriar.task8.data.Place


class PlaceAdapter(
    private var placeList: List<Place>,
    private val onItemClick: (Place) -> Unit
) :
    RecyclerView.Adapter<PlaceAdapter.PlaceViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.places, parent, false)
        return PlaceViewHolder(view)
    }

    override fun onBindViewHolder(holder: PlaceViewHolder, position: Int) {
        val place = placeList[position]
        holder.bind(place)
        holder.itemView.setOnClickListener { onItemClick(place) }
    }

    override fun getItemCount() = placeList.size

    inner class PlaceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        @SuppressLint("ResourceType")
        fun bind(place: Place) {
            // Bind data based on view type
            val userImage = itemView.findViewById<ImageView>(R.id.image)
            userImage.load(place.imageURL) {
                crossfade(true)
            }
            itemView.findViewById<TextView>(R.id.name).text = place.name
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun onPlaceChange(newPlaceList: List<Place>) {
        placeList = newPlaceList
        notifyDataSetChanged()
    }

}