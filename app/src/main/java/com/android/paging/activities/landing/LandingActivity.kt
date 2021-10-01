package com.android.paging.activities.landing

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.PagingData
import com.android.paging.R
import com.android.paging.activities.landing.posts.PostsAdapter
import com.android.paging.utilities.decideOnState
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

        adapter.addLoadStateListener {
            it.decideOnState(
                adapter = adapter,
                showLoading = { visible -> progress_bar.isVisible = visible },
                showEmptyState = { visible ->
                    empty_animation?.isVisible = visible
                    posts_rv?.isVisible = !visible
                },
                showError = { message ->
                    empty_animation.isVisible = true
                    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                }
            )

        }
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