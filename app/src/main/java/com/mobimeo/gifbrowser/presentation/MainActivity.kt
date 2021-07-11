package com.mobimeo.gifbrowser.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mobimeo.gifbrowser.R
import com.mobimeo.gifbrowser.presentation.di.AppComponent
import com.mobimeo.gifbrowser.presentation.di.DaggerAppComponent
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(applicationContext)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

}