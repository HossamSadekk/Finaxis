package core.base.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import core.network.utils.ApiResponse
import io.github.aakira.napier.Napier
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

open class BaseViewModel : ViewModel() {
    protected fun <R> executeUseCase(
        block: suspend () -> ApiResponse<R>,
        onStart: () -> Unit,
        onError: (String) -> Unit,
        onSuccess: (value: R) -> Unit,
    ) {
        safeLaunch {
            onStart()
            try {
                when (val result = block()) {
                    is ApiResponse.Success -> onSuccess(result.data)
                    is ApiResponse.Error -> onError(result.message)
                }
            } catch (e: Exception) {
                val errorMessage = e.message ?: "Unknown error"
                onError(errorMessage)
            }
        }
    }

    private val handler = CoroutineExceptionHandler { _, exception ->
        Napier.e(message = exception.message.orEmpty())
    }

    protected fun safeLaunch(block: suspend CoroutineScope.() -> Unit) {
        viewModelScope.launch(handler, block = block)
    }
}