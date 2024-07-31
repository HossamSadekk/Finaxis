package core.network.utils

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.Composable

sealed class RequestState<out T> {
    object Idle : RequestState<Nothing>()
    object Loading : RequestState<Nothing>()
    data class Success<T>(val data: T) : RequestState<T>()
    data class Error(val message: String) : RequestState<Nothing>()

    fun isLoading() = this is Loading
    fun isSuccess() = this is Success
    fun isError() = this is Error

    fun getSuccessData() = (this as Success).data
    fun getSuccessDataOrNull(): T? = (this as? Success)?.data

    fun getErrorMessage() = (this as Error).message
    fun getErrorMessageOrNull(): String? = (this as? Error)?.message

    @Composable
    fun DisplayResult(
        onIdle: (@Composable () -> Unit)? = null,
        onLoading: @Composable () -> Unit,
        onSuccess: @Composable (T) -> Unit,
        onError: @Composable (String) -> Unit,
    ) {
        AnimatedContent(
            targetState = this,
            transitionSpec = {
                fadeIn(tween(durationMillis = 300)) togetherWith
                    fadeOut(tween(durationMillis = 300))
            },
            label = "Content Animation"
        ) { state ->
            when (state) {
                is Idle -> onIdle?.invoke()
                is Loading -> onLoading()
                is Success -> onSuccess(state.getSuccessData())
                is Error -> onError(state.getErrorMessage())
            }
        }
    }
}
