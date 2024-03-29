package com.example.mototrack

import android.util.Log
import com.amplifyframework.api.aws.AWSApiPlugin
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin
import com.amplifyframework.core.Amplify
import com.amplifyframework.datastore.AWSDataStorePlugin

class MyAmplifyApp : android.app.Application() {

    override fun onCreate() {
        super.onCreate()

        // Include the Auth plugin.
        Amplify.addPlugin(AWSCognitoAuthPlugin())
        Amplify.addPlugin(AWSDataStorePlugin())
        Amplify.addPlugin(AWSApiPlugin())
        Amplify.configure(applicationContext)

        Amplify.Auth.fetchAuthSession(
            { Log.i("AmplifyQuickstart", "Auth session = $it") },
            { error -> Log.e("AmplifyQuickstart", "Failed to fetch auth session", error) }
        )
    }
}