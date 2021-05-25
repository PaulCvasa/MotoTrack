package com.example.mototrack


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.getValue


class VirtualGarage: AppCompatActivity() {
    private var mRecyclerView: RecyclerView? = null
    private lateinit var mAdapter: ExampleAdapter
    private var mLayoutManager: RecyclerView.LayoutManager? = null
    private val mMotorcycleList: ArrayList<ExampleItem> = ArrayList()




    /*fun createList() {
        mMotorcycleList.add(ExampleItem(R.drawable.ic_oldtimer_motorcycle_mechanic, "Kawasaki", "ER-6N"))
        mMotorcycleList.add(ExampleItem(R.drawable.ic_oldtimer_motorcycle_mechanic, "BMW", "R 1200 R"))
        mMotorcycleList.add(ExampleItem(R.drawable.ic_oldtimer_motorcycle_mechanic, "Line 5", "Line 6"))
    }*/

    fun buildRecyclerView() {
        mRecyclerView = findViewById(R.id.recyclerView)
        mLayoutManager = LinearLayoutManager(this)
        mAdapter = ExampleAdapter(mMotorcycleList)
        mRecyclerView?.layoutManager = mLayoutManager
        mRecyclerView?.adapter = mAdapter

        mAdapter.setOnItemClickListener(object : ExampleAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                //changeItem(position, "Clicked")
                Toast.makeText(this@VirtualGarage, "Clicked item at position " + position, Toast.LENGTH_SHORT )
            }
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_virtual_garage)

        //createList()
        buildRecyclerView()

        val rootRef = FirebaseDatabase.getInstance().reference
        val mototrackRef = rootRef.child("mototrack")
        mototrackRef.get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                for (ds in task.result?.children!!) {
                    val motoBrand = ds.child("motoBrand").getValue(String::class.java)
                    Log.d("TAG", motoBrand!!)
                    //item.setText1(motoBrand)
                    val motoModel = ds.child("motoModel").getValue(String::class.java)
                    Log.d("TAG", motoModel!!)
                    //item.setText2(motoModel)
                    val mileage = ds.child("mileage").getValue(String::class.java)
                    Log.d("TAG", mileage!!)
                    //item.setText3(mileage)
                    val vin = ds.child("vin").getValue(String::class.java)
                    Log.d("TAG", vin!!)
                    //item.setText4(vin)
                    val item = ExampleItem(motoBrand,motoModel,mileage,vin)
                    mMotorcycleList.add(item)
                }
            } else {
                Log.d("TAG", task.exception?.message!!) // potential errors!
            }
            mAdapter.notifyDataSetChanged()
        }

        val buttonRemove: Button = findViewById(R.id.button_remove)
        val editTextRemove: EditText = findViewById(R.id.edittext_remove)


        fun removeItem(position: Int) {
            mototrackRef.child(mMotorcycleList[position-1].getText4().toString()).removeValue()
            mMotorcycleList.removeAt(position-1)
            mAdapter!!.notifyItemRemoved(position-1)
        }


        buttonRemove.setOnClickListener {
            val position = editTextRemove.text.toString().toInt()
            removeItem(position)
        }

    }
    fun addMotorcycle(view: View)
    {
        val intent3 = Intent(this, AddMotorcycle::class.java)
        startActivity(intent3)
        finish()
    }
}