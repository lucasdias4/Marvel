package com.lucasdias.data.comic.remote.mapper

import com.lucasdias.data.comic.remote.model.ComicResponse
import com.lucasdias.data.comic.remote.model.ComicThumbnailResponse
import com.lucasdias.domain.model.Comic
import com.lucasdias.domain.model.ComicThumbnail

fun List<ComicResponse>.toDomain(): List<Comic> {
    val comicList = mutableListOf<Comic>()

    this.forEach {
        comicList.addJustValidComic(it)
    }

    return comicList
}

fun ComicThumbnailResponse.toDomain(): ComicThumbnail {
    return ComicThumbnail(
        path = this.path,
        extension = this.extension
    )
}

private fun MutableList<Comic>.addJustValidComic(comicResponse: ComicResponse) {
    comicResponse.also {
        if (
            it.id != null &&
            it.title != null &&
            it.thumbnail != null &&
            it.thumbnail.path?.contains("image_not_available") != true
        ) {
            val comic = Comic(
                id = it.id,
                title = it.title,
                thumbnail = it.thumbnail.toDomain()
            )
            this.add(comic)
        }
    }
}
