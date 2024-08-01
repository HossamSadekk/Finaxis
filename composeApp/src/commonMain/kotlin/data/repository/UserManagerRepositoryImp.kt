package data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import core.local.dataStore.PreferencesKeys.TOKEN_KEY
import core.local.dataStore.PreferencesKeys.USER_LOGGED_IN
import domain.repository.UserManagerRepository
import kotlinx.coroutines.flow.first

class UserManagerRepositoryImp(private val dataStore: DataStore<Preferences>) : UserManagerRepository {
    override suspend fun saveUserLoggedIn(isLoggedIn: Boolean) {
        dataStore.edit { preferences ->
            preferences[USER_LOGGED_IN] = isLoggedIn
        }
    }

    override suspend fun isUserLoggedIn(): Boolean {
        val preferences = dataStore.data.first()
        return preferences[USER_LOGGED_IN] ?: false
    }

    override suspend fun saveUserToken(token: String) {
        dataStore.edit { preferences ->
            preferences[TOKEN_KEY] = token
        }
    }

    override suspend fun getUserToken(): String {
        val preferences = dataStore.data.first()
        return preferences[TOKEN_KEY].orEmpty()
    }
}