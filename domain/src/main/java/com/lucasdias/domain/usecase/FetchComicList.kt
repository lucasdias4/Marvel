package com.lucasdias.domain.usecase

import com.lucasdias.core.resource.Resource
import com.lucasdias.domain.BuildConfig
import com.lucasdias.domain.model.ComicSummary
import com.lucasdias.domain.repository.ComicListRepository

class FetchComicList(
    private val getHash: GetHash,
    private val comicListRepository: ComicListRepository
) {

    suspend operator fun invoke(): Resource<List<ComicSummary>> {
        val timesMap = System.currentTimeMillis().toString()
        val apiPublicKey = BuildConfig.MARVEL_API_PUBLIC_KEY
        val apiPrivateKey = BuildConfig.MARVEL_API_PRIVATE_KEY

        val hash = getHash(timesMap, apiPrivateKey, apiPublicKey)

        return comicListRepository.fetch(apiPublicKey, timesMap, hash)
    }
}
