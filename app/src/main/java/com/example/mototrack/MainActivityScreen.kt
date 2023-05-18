package com.example.mototrack

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

class MainActivityScreen : AppCompatActivity() {

    lateinit private var mEmail : EditText
    lateinit private var mPassword : EditText
    lateinit private var fAuth : FirebaseAuth
    lateinit private var mLoginBtn : Button
    lateinit private var mProgressBar : ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_screen)

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

    private fun initVars()
    {
        mEmail = findViewById(R.id.login_email)
        mPassword = findViewById(R.id.login_password)
        fAuth = FirebaseAuth.getInstance()
        mLoginBtn = findViewById(R.id.loginAccount)
        mProgressBar = findViewById(R.id.progressBar)
    }

    fun login(view: View)
    {
        initVars()

        mLoginBtn.setOnClickListener(object: View.OnClickListener {
            override fun onClick(v: View?) {
                val email : String = mEmail.text.toString().trim()
                val password : String = mPassword.text.toString().trim()

                //verification
                if(TextUtils.isEmpty(email)) {
                    mEmail.error = "Error: Email is required"
                    return
                }
                if(TextUtils.isEmpty(password)) {
                    mPassword.error = "Error: Password is required"
                    return
                }
                mProgressBar.visibility = View.VISIBLE
                fAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(object: OnCompleteListener<AuthResult>{
                    override fun onComplete(p0: Task<AuthResult>) {
                        if(p0.isSuccessful){
                            Toast.makeText(this@MainActivityScreen, "Logged in successfully.", Toast.LENGTH_SHORT ).show()
                            startActivity(Intent(applicationContext, DashboardActivity::class.java))
                            mProgressBar.visibility = View.INVISIBLE
                        }
                        else{
                            Toast.makeText(this@MainActivityScreen, "ERROR: Invalid credentials", Toast.LENGTH_SHORT).show()
                            mProgressBar.visibility = View.INVISIBLE
                            mPassword.setText("")
                        }
                    }
                })
            }
        })
    }

    fun offline(view: View)
    {
        initVars()
        val intent2 = Intent(this, DashboardActivity::class.java)
        this.finish()
        startActivity(intent2)

    }

    fun register(view: View)
    {
        initVars()
        val intent3 = Intent(this, Register::class.java)
        startActivity(intent3)
        this.finish()
    }

}