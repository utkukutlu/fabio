package com.utkukutlu.fabio

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.utkukutlu.library.fabio.Fabio
import com.utkukutlu.library.fabio.FabioFabSize
import com.utkukutlu.library.fabio.FabioItem
import com.utkukutlu.library.fabio.OnFabSelectedListener


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fabio = findViewById<Fabio>(R.id.fabio)
        fabio.setOnActionSelectedListener(object : OnFabSelectedListener {
            override fun onSelected(item: FabioItem) {

            }
        })

        fabio.setSubFabs(
            arrayListOf(
                FabioItem(
                    this,
                    R.color.colorPrimaryDark,
                    R.drawable.ic_android_white_24dp,
                    FabioFabSize.MINI
                ),
                FabioItem(
                    this,
                    R.color.colorPrimary,
                    R.drawable.ic_close_white_24dp,
                    FabioFabSize.MINI
                )
            )
        )

    }
}
