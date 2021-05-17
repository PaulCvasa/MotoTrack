package com.example.mototrack

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.mototrack.databinding.ActivityAddMotorcycleBinding
import com.example.mototrack.databinding.ActivityMainScreenBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class AddMotorcycle : AppCompatActivity() {

    private lateinit var binding : ActivityAddMotorcycleBinding
    private lateinit var database : DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddMotorcycleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.addMotoBtn.setOnClickListener{
            val motoBrand = binding.motoBrand.text.toString()
            val motoModel = binding.motoModel.text.toString()
            val mileage = binding.mileage.text.toString()
            val vin = binding.vin.text.toString()

            database = FirebaseDatabase.getInstance().getReference("mototrack")
            val Motorcycle = Motorcycle(motoBrand, motoModel, mileage, vin)
            database.child(vin).setValue(Motorcycle).addOnSuccessListener {
                binding.motoBrand.text.clear()
                binding.motoModel.text.clear()
                binding.mileage.text.clear()
                binding.vin.text.clear()

                Toast.makeText(this, "Succesfully Added", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener{
                Toast.makeText(this, "Failed To Add", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
