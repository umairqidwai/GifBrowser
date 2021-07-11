package com.mobimeo.gifbrowser.presentation.di

import android.content.Context
import com.mobimeo.gifbrowser.presentation.fragments.GifBrowserFragment
import com.mobimeo.gifbrowser.presentation.MainActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(DataModule::class)])
interface AppComponent {

    @Component.Factory
    interface Factory {
        // With @BindsInstance, the Context passed in will be available in the graph
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun inject(mainActivity: MainActivity)
    fun inject(fragment: GifBrowserFragment)


}