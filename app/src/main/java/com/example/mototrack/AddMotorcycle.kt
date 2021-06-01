package com.example.mototrack

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import com.example.mototrack.databinding.ActivityAddMotorcycleBinding
import com.example.mototrack.databinding.ActivityMainScreenBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class AddMotorcycle : AppCompatActivity() {

    private lateinit var binding : ActivityAddMotorcycleBinding
    private lateinit var database : DatabaseReference
    private val fAuth : FirebaseAuth = FirebaseAuth.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddMotorcycleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.addMotoBtn.setOnClickListener{
            val motoBrand = binding.motoBrand.text.toString()
            val motoModel = binding.motoModel.text.toString()
            val mileage = binding.mileage.text.toString()
            val vin = binding.vin.text.toString()

            if(TextUtils.isEmpty(motoBrand))
            {
                Toast.makeText(this, "Error: Motorcycle Brand is required", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if(TextUtils.isEmpty(motoModel))
            {
                Toast.makeText(this, "Error: Motorcycle Model is required", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if(TextUtils.isEmpty(mileage))
            {
                Toast.makeText(this, "Error: Motorcycle Mileage is required", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if(TextUtils.isEmpty(vin))
            {
                Toast.makeText(this, "Error: Motorcycle VIN is required", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            database = FirebaseDatabase.getInstance().getReference(fAuth.uid.toString())
            val motorcycle = Motorcycle(motoBrand, motoModel, mileage, vin)
            database.child(vin).setValue(motorcycle).addOnSuccessListener {
                binding.motoBrand.text.clear()
                binding.motoModel.text.clear()
                binding.mileage.text.clear()
                binding.vin.text.clear()

                Toast.makeText(this, "Succesfully Added", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener{
                Toast.makeText(this, "ERROR: Failed To Add (Check for empty new lines)", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun backToDashboard(view: View)
    {
        finish()
    }
}

