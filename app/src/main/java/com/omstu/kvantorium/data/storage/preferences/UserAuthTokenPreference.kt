package com.omstu.kvantorium.data.storage.preferences

import android.content.SharedPreferences
import com.omstu.kvantorium.data.storage.preferences.base.StringPreference

class UserAuthTokenPreference(sharedPreferences: SharedPreferences) :
    StringPreference(sharedPreferences, "user_token")