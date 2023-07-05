package com.example.mototrack


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.amplifyframework.auth.cognito.AWSCognitoAuthSession
import com.amplifyframework.auth.result.AuthSessionResult
import com.amplifyframework.core.Amplify
import com.amplifyframework.core.model.query.Where
import com.amplifyframework.datastore.generated.model.MotorcycleDB


class VirtualGarage: AppCompatActivity() {
    private var mRecyclerView: RecyclerView? = null
    private lateinit var mAdapter: MotorcycleAdapter
    private var mLayoutManager: RecyclerView.LayoutManager? = null
    private val mMotorcycleList: ArrayList<MotorcycleAWS> = ArrayList()

    fun buildRecyclerView() {
        mRecyclerView = findViewById(R.id.recyclerView)
        mLayoutManager = LinearLayoutManager(this)
        mAdapter = MotorcycleAdapter(mMotorcycleList)
        mRecyclerView?.layoutManager = mLayoutManager
        mRecyclerView?.adapter = mAdapter

        mAdapter.setOnItemClickListener(object : MotorcycleAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                Toast.makeText(
                    this@VirtualGarage,
                    "Clicked item at position " + position,
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_virtual_garage)
        buildRecyclerView()

        // AWS Database
        Amplify.Auth.fetchAuthSession(
            {
                val session = it as AWSCognitoAuthSession
                when (session.identityIdResult.type) {
                    AuthSessionResult.Type.SUCCESS -> {
                        Log.i("AuthQuickStart", "IdentityId = ${session.identityIdResult.value}")
                        Amplify.DataStore.query(
                            MotorcycleDB::class.java,
                            Where.matches(MotorcycleDB.OWNER.eq(session.identityIdResult.value.toString())),
                            { motorcycles ->
                                while (motorcycles.hasNext()) {
                                    val moto = motorcycles.next()
                                    Log.i("AmplifyDB", "Queried item: " + moto.id)
                                    val item = MotorcycleAWS(
                                        moto.id,
                                        moto.vin,
                                        moto.mileage,
                                        moto.motoBrand,
                                        moto.motoModel
                                    )
                                    mMotorcycleList.add(item)
                                }
                            },
                            { failure -> Log.e("AmplifyDB", "Could not query DataStore", failure) }
                        )
                    }

                    AuthSessionResult.Type.FAILURE ->
                        Log.w(
                            "AuthQuickStart",
                            "IdentityId not found",
                            session.identityIdResult.error
                        )
                }
            },
            { Log.e("AuthQuickStart", "Failed to fetch session", it) }
        )
        mAdapter.notifyDataSetChanged()

        val buttonAdd: Button = findViewById(R.id.button_insert)
        val buttonRemove: Button = findViewById(R.id.button_remove)
        val editTextRemove: EditText = findViewById(R.id.edittext_remove)

        fun removeItem(position: Int) {
            if (position - 1 >= mMotorcycleList.size) {
                Toast.makeText(
                    this,
                    "Error: Number exceeds the size of the virtual garage",
                    Toast.LENGTH_SHORT
                ).show()
                return
            }
            if (position == 0) {
                Toast.makeText(this, "Error: Number can't be 0", Toast.LENGTH_SHORT).show()
                return
            }

            Amplify.DataStore.query(MotorcycleDB::class.java, Where.matches(MotorcycleDB.ID.eq(mMotorcycleList[position-1].getID())),
                { matches ->
                    if (matches.hasNext()) {
                        val post = matches.next()
                        Amplify.DataStore.delete(post,
                            { Log.i("AmplifyDB", "Deleted a motorcycle.") },
                            { Log.e("AmplifyDB", "Delete failed.", it) }
                        )
                    }
                },
                { Log.e("AmplifyDB", "Query failed.", it) }
            )
            mMotorcycleList.removeAt(position - 1)
            mAdapter!!.notifyItemRemoved(position - 1)
        }
            buttonRemove.setOnClickListener {
                val position = editTextRemove.text.toString().toInt()
                removeItem(position)
            }

            buttonAdd.setOnClickListener {
                addMotorcycle()
            }




    }
    fun addMotorcycle() {
        val intent3 = Intent(this, AddMotorcycle::class.java)
        startActivity(intent3)
        finish()
    }
}