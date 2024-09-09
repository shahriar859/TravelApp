package com.shahriar.task8.ui.details

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.shahriar.task8.R
import com.shahriar.task8.data.Place

class Details : AppCompatActivity() {
    private lateinit var image: ImageView
    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_details)

        image = findViewById(R.id.imageDetail)
        textView = findViewById(R.id.textView)

        val place = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("item", Place::class.java)
        } else {
            intent.getParcelableExtra<Place>("item")
        }
        Log.d("PostDetails", place.toString())

        place?.let {
            // Update UI with post details
            textView.text = it.name
            image.load(it.imageURL) {
                crossfade(true)
            }

        }

    }
}