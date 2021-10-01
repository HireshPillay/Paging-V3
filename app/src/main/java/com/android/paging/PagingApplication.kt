package com.android.paging

import android.app.Application
import com.android.paging.activities.landing.landingActivityViewModelModule
import com.android.paging.networking.networkModule
import com.android.paging.networking.pagingRepositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.fragment.koin.fragmentFactory
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class PagingApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@PagingApplication)
            androidFileProperties()
            fragmentFactory()
            modules(networkModule, pagingRepositoryModule, landingActivityViewModelModule)
        }
    }
}