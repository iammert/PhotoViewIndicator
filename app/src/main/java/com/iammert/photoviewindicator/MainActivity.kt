package com.iammert.photoviewindicator

import android.os.Bundle

import com.github.chrisbanes.photoview.PhotoView
import com.iammert.photoviewindicatorlib.PhotoViewIndicator
import com.squareup.picasso.Picasso

import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val photoView = findViewById<PhotoView>(R.id.photoView)
        val indicator = findViewById<PhotoViewIndicator>(R.id.indicator)

        indicator.setPhotoView(photoView)

        Picasso.with(this)
                .load("https://images.unsplash.com/photo-1547908345-4260f706aa20?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=3278&q=80")
                .into(photoView)

    }
}
