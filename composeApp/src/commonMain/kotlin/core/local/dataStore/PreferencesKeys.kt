package core.local.dataStore

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

object PreferencesKeys {
    val USER_LOGGED_IN = booleanPreferencesKey("user_logged_in")
    val TOKEN_KEY = stringPreferencesKey("token_key")
}