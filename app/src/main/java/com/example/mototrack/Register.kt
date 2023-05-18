package com.example.mototrack

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

class Register : AppCompatActivity() {

    lateinit private var mName: EditText
    lateinit private var mEmail: EditText
    lateinit private var mPassword: EditText
    lateinit private var mRegisterBtn: Button
    lateinit private var fAuth: FirebaseAuth
    lateinit private var mProgressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            Snackbar.make(view, "Support email: paul.cvasa00@e-uvt.ro", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mEmail.setText("")
        mPassword.setText("")
    }

    //initialize vars
    private fun initVars()
    {
        mName= findViewById(R.id.register_name)
        mEmail= findViewById(R.id.register_email)
        mPassword = findViewById(R.id.register_password)
        mRegisterBtn = findViewById(R.id.registerAccount)
        fAuth = FirebaseAuth.getInstance()
        mProgressBar = findViewById(R.id.progressBar2)
    }

    //register to firebase
    fun register(view: View)
    {
        initVars()

        if (fAuth.currentUser != null) {
            startActivity(Intent(applicationContext, DashboardActivity::class.java))
            finish()
        }

        mRegisterBtn.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val email: String = mEmail.text.toString().trim()
                val password: String = mPassword.text.toString().trim()
                //verification
                if (TextUtils.isEmpty(email)) {
                    mEmail.error = "Error: Email is required"
                    return
                }
                if (TextUtils.isEmpty(password)) {
                    mPassword.error = "Error: Password is required"
                    return
                }
                mProgressBar.visibility = View.VISIBLE
                fAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(object : OnCompleteListener<AuthResult> {
                        override fun onComplete(p0: Task<AuthResult>) {
                            if (p0.isSuccessful) {
                                Toast.makeText(this@Register, "Account Created.", Toast.LENGTH_SHORT).show()
                                startActivity(Intent(applicationContext, DashboardActivity::class.java))
                                mProgressBar.visibility = View.INVISIBLE
                            }
                            else {
                                Toast.makeText(this@Register, "Error: " + (p0.exception?.message), Toast.LENGTH_SHORT).show()
                                mProgressBar.visibility = View.INVISIBLE
                            }
                        }
                    })
            }
        })
    }

    //switch to login screen
    fun switch_toLogin(view: View)
    {
        initVars()
        val intent = Intent(this, MainActivityScreen::class.java)
        startActivity(intent)
        finish()
    }
}