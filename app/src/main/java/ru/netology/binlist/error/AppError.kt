package ru.netology.binlist.error

import android.database.SQLException
import java.io.IOException

sealed class AppError(var code: String) : RuntimeException() {

    companion object {
        fun from(e: Throwable): AppError = when (e) {
            is AppError -> e
            is SQLException -> DbError
            is IOException -> NetworkError
            else -> UnknownErrors
        }
    }
}

class ApiError(val status: Int, code: String) : AppError(code)
class ApiError403(code: String) : AppError(code)
class ApiError404(code: String) : AppError(code)


data object NetworkError : AppError("error_network")
data object DbError : AppError("error_db")
data object UnknownErrors : AppError("error_unknown")

