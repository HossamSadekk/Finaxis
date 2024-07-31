package core.network.utils

sealed interface DataError {
    val description: String

    sealed class Network(override val description: String) : DataError {
        object REQUEST_TIMEOUT : Network("Request timed out")
        object TOO_MANY_REQUESTS : Network("Too many requests")
        object NO_INTERNET : Network("No internet connection")
        object PAYLOAD_TOO_LARGE : Network("Payload too large")
        object SERVER_ERROR : Network("Server error")
        object SERIALIZATION : Network("Serialization error")
        object UNKNOWN : Network("Unknown network error")
    }

    sealed class Local(override val description: String) : DataError {
        object DISK_FULL : Local("Disk space is full")
    }
}
