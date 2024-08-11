package domain.repository

interface UserManagerRepository {
    suspend fun saveUserLoggedIn(isLoggedIn: Boolean)
    suspend fun isUserLoggedIn(): Boolean
    suspend fun saveUserToken(token: String)
    suspend fun getUserToken(): String
    suspend fun userHasAccount(isAccountCreated: Boolean)
    suspend fun isUserHasAccount(): Boolean
    suspend fun saveUserPasscode(passcode: String)
    suspend fun getUserPasscode(): String
    suspend fun saveUsername(username: String)
    suspend fun getUsername(): String
}