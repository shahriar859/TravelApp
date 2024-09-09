package com.shahriar.task8.ui.home

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.carousel.CarouselLayoutManager
import com.shahriar.task8.R
import com.shahriar.task8.data.Place
import com.shahriar.task8.ui.details.Details

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var recyclerView: RecyclerView
    private lateinit var mostViewBtn: Button
    private lateinit var nearbyBtn: Button
    private lateinit var latestBtn: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    @SuppressLint("ResourceAsColor")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        val mostViewList = List(6) { index ->
            Place(
                id = index + 1,
                type = "most_viewed",
                name = "Nilgiri",
                imageURL = "https://picsum.photos/1500/1500?random=$index",
                city = "Bandarban",
                country = "Bangladesh",
                price = index+10,
                hour = index+2,
                temperature = 27,
                description = "Hello, \n Good morning to you all, we have passed two weeks together. Hope for a great journey",
                rating = 4.5,
            )
        }

        val nearbyList = List(8) { index ->
            Place(
                id = index + 1,
                type = "nearby",
                name = "Sundarban",
                imageURL = "https://picsum.photos/1600/1600?random=$index",
                city = "Khulna",
                country = "Bangladesh",
                price = index+10,
                hour = index+2,
                temperature = 27,
                description = "Hello, \n Good morning to you all, we have passed two weeks together. Hope for a great journey",
                rating = 4.5,
            )
        }

        val latestList = List(4) { index ->
            Place(
                id = index + 1,
                type = "nearby",
                name = "Saint Martin",
                imageURL = "https://picsum.photos/1800/1800?random=$index",
                city = "Cox's Bazar",
                country = "Bangladesh",
                price = index+10,
                hour = index+2,
                temperature = 27,
                description = "Hello, \n Good morning to you all, we have passed two weeks together. Hope for a great journey",
                rating = 4.5,
            )
        }

        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.setLayoutManager(CarouselLayoutManager())

        mostViewBtn = view.findViewById(R.id.mostView)
        nearbyBtn = view.findViewById(R.id.nearby)
        latestBtn = view.findViewById(R.id.latest)

        fun resetButtons() {
            mostViewBtn.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.defaultBtn))
            mostViewBtn.setTextColor(ContextCompat.getColor(requireContext(), R.color.defaultBtnText))

            nearbyBtn.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.defaultBtn))
            nearbyBtn.setTextColor(ContextCompat.getColor(requireContext(), R.color.defaultBtnText))

            latestBtn.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.defaultBtn))
            latestBtn.setTextColor(ContextCompat.getColor(requireContext(), R.color.defaultBtnText))
        }

        val placeAdapter = PlaceAdapter(mostViewList)
        { place ->
            val intent = Intent(requireContext(), Details::class.java)
            intent.putExtra("item", place)
            startActivity(intent)
        }
        recyclerView.adapter = placeAdapter
        recyclerView.addItemDecoration(ItemSpacingDecoration(horizontal = 4, vertical = 4))
        recyclerView.setPadding(0, 0, 0, 0)

        mostViewBtn.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.black))
        mostViewBtn.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
        mostViewBtn.setOnClickListener {
            resetButtons()
            mostViewBtn.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.black))
            mostViewBtn.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
            placeAdapter.onPlaceChange(mostViewList)
        }

        nearbyBtn.setOnClickListener {
            resetButtons()
            nearbyBtn.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.black))
            nearbyBtn.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
            placeAdapter.onPlaceChange(nearbyList)
        }

        latestBtn.setOnClickListener {
            resetButtons()
            latestBtn.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.black))
            latestBtn.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
            placeAdapter.onPlaceChange(latestList)
        }

        return view
    }
}