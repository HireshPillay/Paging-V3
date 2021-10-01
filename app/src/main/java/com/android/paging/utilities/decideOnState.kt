package com.android.paging.utilities

import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView

inline fun <T : Any, U : RecyclerView.ViewHolder> CombinedLoadStates.decideOnState(
    adapter: PagingDataAdapter<T, U>,
    showLoading: (Boolean) -> Unit,
    showEmptyState: (Boolean) -> Unit,
    showError: (String) -> Unit
) {
    showLoading(refresh is LoadState.Loading)

    showEmptyState(source.append is LoadState.NotLoading && source.append.endOfPaginationReached && adapter.itemCount == 0)

    val errorState = source.append as? LoadState.Error
        ?: source.prepend as? LoadState.Error
        ?: source.refresh as? LoadState.Error
        ?: append as? LoadState.Error
        ?: prepend as? LoadState.Error
        ?: refresh as? LoadState.Error

    errorState?.let { showError(it.error.toString()) }
}
