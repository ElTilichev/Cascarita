package com.cascarita.app.core.common

// Game constants
object GameConstants {
    const val DEFAULT_TARGET_SCORE = 7
    const val OVERTIME_SCORE = 3
    const val OVERTIME_ADDITIONAL_POINTS = 2
    const val MIN_TEAMS_TO_START = 2
}

// Result wrapper for operations
sealed class Result<out T> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val exception: Throwable) : Result<Nothing>()
    object Loading : Result<Nothing>()
}

// Resource wrapper for UI state
data class Resource<out T>(
    val status: Status,
    val data: T? = null,
    val message: String? = null
) {
    companion object {
        fun <T> success(data: T): Resource<T> = Resource(Status.SUCCESS, data)
        fun <T> error(message: String, data: T? = null): Resource<T> = Resource(Status.ERROR, data, message)
        fun <T> loading(data: T? = null): Resource<T> = Resource(Status.LOADING, data)
    }
}

enum class Status {
    SUCCESS,
    ERROR,
    LOADING
}
