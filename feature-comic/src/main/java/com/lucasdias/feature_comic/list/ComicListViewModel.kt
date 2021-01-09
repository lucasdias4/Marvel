package com.lucasdias.feature_comic.list

import com.lucasdias.base.presentation.BaseViewModel
import com.lucasdias.core.resource.Resource
import com.lucasdias.domain.model.Comic
import com.lucasdias.domain.usecase.FetchComicList
import kotlinx.coroutines.CoroutineDispatcher

class ComicListViewModel(
    private val fetchComicList: FetchComicList,
    coroutineDispatcher: CoroutineDispatcher
) : BaseViewModel<List<Comic>>(coroutineDispatcher) {

    override suspend fun request(): Resource<List<Comic>> {
        return fetchComicList()
    }

    fun requestNextPage() {
        if (isNotLoading()) {
            executeRequest()
        }
    }
}
