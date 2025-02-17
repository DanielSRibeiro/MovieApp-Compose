package com.example.movieapp.framework.data.network.mapper

import com.example.core.domain.model.MovieSearch
import com.example.movieapp.framework.data.network.response.SearchResultResponse
import com.example.movieapp.util.toPostURL


fun SearchResultResponse.toMovieSearch(): MovieSearch {
    return MovieSearch(
        id = this.id,
        imageUrl = this.posterPath.toPostURL(),
        voteAverage = this.voteAverage,
    )
}