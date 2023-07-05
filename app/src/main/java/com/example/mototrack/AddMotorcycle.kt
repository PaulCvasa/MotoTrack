package com.example.mototrack

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import com.amplifyframework.core.Amplify
import com.amplifyframework.datastore.generated.model.MotorcycleDB
import com.example.mototrack.databinding.ActivityAddMotorcycleBinding
import com.amplifyframework.auth.cognito.AWSCognitoAuthSession
import com.amplifyframework.auth.result.AuthSessionResult

class AddMotorcycle : AppCompatActivity() {

    private lateinit var binding: ActivityAddMotorcycleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddMotorcycleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.addMotoBtn.setOnClickListener {
            val motoBrand = binding.motoBrand.text.toString()
            val motoModel = binding.motoModel.text.toString()
            val mileage = binding.mileage.text.toString().toInt()
            val vin = binding.vin.text.toString()

            if (TextUtils.isEmpty(motoBrand)) {
                Toast.makeText(this, "Error: Motorcycle Brand is required", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }

            if (TextUtils.isEmpty(motoModel)) {
                Toast.makeText(this, "Error: Motorcycle Model is required", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }

            if (TextUtils.isEmpty(mileage.toString())) {
                Toast.makeText(this, "Error: Motorcycle Mileage is required", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }

            if (TextUtils.isEmpty(vin)) {
                Toast.makeText(this, "Error: Motorcycle VIN is required", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Amplify DB
            Amplify.Auth.fetchAuthSession(
                {
                    val session = it as AWSCognitoAuthSession
                    when (session.identityIdResult.type) {
                        AuthSessionResult.Type.SUCCESS -> {
                            Log.i("AuthQuickStart", "IdentityId = ${session.identityIdResult.value}")
                            val item: MotorcycleDB = MotorcycleDB.builder()
                                .vin(vin)
                                .mileage(mileage)
                                .motoBrand(motoBrand)
                                .motoModel(motoModel)
                                .owner(session.identityIdResult.value.toString())
                                .build()
                            Amplify.DataStore.save(
                                item,
                                { success ->
                                    Log.i("Amplify", "Saved item: " + success.item().vin)
                                    runOnUiThread {
                                    Toast.makeText(this, "Succesfully Added", Toast.LENGTH_SHORT).show()
                                    }
                                    finish()

                                },
                                { error -> Log.e("Amplify", "Could not save item to DataStore", error) }
                            )
                        }

                        AuthSessionResult.Type.FAILURE ->
                            Log.w("AuthQuickStart", "IdentityId not found", session.identityIdResult.error)
                    }
                },
                { Log.e("AuthQuickStart", "Failed to fetch session", it) }
            )

        }
    }
}

