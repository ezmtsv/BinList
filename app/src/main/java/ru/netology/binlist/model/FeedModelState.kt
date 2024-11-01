package ru.netology.binlist.model

data class FeedModelState(
    val loading: Boolean = false,
    val error: Boolean = false,
    val error403: Boolean = false,
    val error415: Boolean = false,
    val error400: Boolean = false,
    val error404: Boolean = false,
    val errorNetWork: Boolean = false,
    val refreshing: Boolean = false,
)