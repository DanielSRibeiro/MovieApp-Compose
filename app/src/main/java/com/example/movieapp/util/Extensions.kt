package com.example.movieapp.util

import com.example.movieapp.BuildConfig

fun String?.toPostURL() = "${BuildConfig.BASE_URL_IMAGE}$this"

fun String?.toBackdropURL() = "${BuildConfig.BASE_URL_IMAGE}$this"