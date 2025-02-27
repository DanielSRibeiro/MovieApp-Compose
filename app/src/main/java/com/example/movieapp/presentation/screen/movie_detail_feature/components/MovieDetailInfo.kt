package com.example.movieapp.presentation.screen.movie_detail_feature.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.core.domain.model.MovieDetail
import com.example.movieapp.R

@Composable
fun MovieInfoContent(
    movieDetail: MovieDetail?,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = modifier
    ) {
        MovieInfo(
            name = stringResource(id = R.string.vote_average),
            value = movieDetail?.voteAverage.toString()
        )
        MovieInfo(
            name = stringResource(id = R.string.duration),
            value = stringResource(
                R.string.duration_minutes,
                movieDetail?.duration.toString()
            )
        )
        MovieInfo(
            name = stringResource(id = R.string.release_date),
            value = movieDetail?.releaseDate.toString()
        )
    }
}

@Composable
fun MovieInfo(
    name: String, value: String
) {
    Column {
        Text(
            text = name,
            style = MaterialTheme.typography.labelMedium.copy(
                fontSize = 13.sp,
                letterSpacing = 1.sp
            ),
            color = Color.DarkGray,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Text(
            text = value,
            style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.SemiBold),
            color = Color.DarkGray,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 4.dp)
        )
    }
}

@Preview
@Composable
private fun MovieFieldsPreview() {
    MovieInfoContent(
        modifier = Modifier.fillMaxWidth(),
        movieDetail = MovieDetail(
            id = 1,
            title = "Filme",
            genres = listOf("Aventura", "Comedia"),
            overview = null,
            backdropPath = null,
            releaseDate = null,
            voteAverage = 7.5,
            duration = 90,
            voteCount = 100
        )
    )
}