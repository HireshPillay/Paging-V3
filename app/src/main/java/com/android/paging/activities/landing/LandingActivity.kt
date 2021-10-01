package com.android.paging.activities.landing

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.paging.PagingData
import com.android.paging.R
import com.android.paging.activities.landing.posts.PostsAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.experimental.dsl.viewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.dsl.module

class LandingActivity : AppCompatActivity() {

    private val activityViewModel: LandingActivityViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = PostsAdapter()
        posts_rv.adapter = adapter

        CoroutineScope(Dispatchers.IO).launch {
            activityViewModel.getPosts.collectLatest {
                adapter.submitData(it)
            }
        }
    }
}

val landingActivityViewModelModule = module {
    viewModel<LandingActivityViewModel>()
}