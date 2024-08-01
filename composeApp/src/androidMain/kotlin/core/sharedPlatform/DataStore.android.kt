package core.sharedPlatform

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import core.local.dataStore.UserManager
import core.local.dataStore.dataStoreFileName
import org.finaxis.app.MyApplication

actual fun createDataStore(): DataStore<Preferences> {
    val context = MyApplication.instance
    return UserManager.getDataStore(
        producePath = {
            context!!.filesDir
                .resolve(dataStoreFileName)
                .absolutePath
        }
    )
}