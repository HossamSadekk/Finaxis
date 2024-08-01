package domain.repository

interface UserManagerRepository {
    suspend fun saveUserLoggedIn(isLoggedIn: Boolean)
    suspend fun isUserLoggedIn(): Boolean
    suspend fun saveUserToken(token: String)
    suspend fun getUserToken(): String
}