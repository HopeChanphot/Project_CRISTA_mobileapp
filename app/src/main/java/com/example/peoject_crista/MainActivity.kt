package com.example.peoject_crista

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private  lateinit var buttonClick: Button
    private  lateinit var buttonClick2: Button
    private  lateinit var mobliephone: EditText
    private  lateinit var countrycode: com.hbb20.CountryCodePicker
    private  lateinit var Testb: Button


    var number: String = ""
    var country: String = ""

    lateinit var storedVerificationId: String
    lateinit var resendToken: PhoneAuthProvider.ForceResendingToken
    private lateinit var callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = Firebase.auth
        auth = FirebaseAuth.getInstance()


        val registertext: TextView = findViewById(R.id.Register_Now_l)
        registertext.setOnClickListener {
            val intent = Intent(this, Register::class.java)
            startActivity(intent)
        }


         buttonClick = findViewById(R.id.Register_l)
        buttonClick.setOnClickListener {
       // performLogin()
            login()

        }
         buttonClick2 = findViewById(R.id.Button_s)
        buttonClick2.setOnClickListener {
            val intent = Intent(this, SupportActivity::class.java)
            startActivity(intent)

        }

        Testb = findViewById(R.id.Testb)
        Testb.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)

        }




        // Callback function for Phone Auth
        callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            // This method is called when the verification is completed
            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                startActivity(Intent(applicationContext, MainActivity::class.java))
                finish()
                Log.d("Msg", "onVerificationCompleted Success")
            }
            // Called when verification is failed add log statement to see the exception
            override fun onVerificationFailed(e: FirebaseException) {
                Log.d("MSg", "onVerificationFailed  $e")
            }

            // On code is sent by the firebase this method is called
            // in here we start a new activity where user can enter the OTP
            override fun onCodeSent(
                verificationId: String,
                token: PhoneAuthProvider.ForceResendingToken
            ) {
                Log.d("Msg", "onCodeSent: $verificationId")
                storedVerificationId = verificationId
                resendToken = token

                // Start a new activity using intent
                // also send the storedVerificationId using intent
                // we will use this id to send the otp back to firebase
                val intent = Intent(applicationContext, OTPActivity::class.java)
                intent.putExtra("storedVerificationId", storedVerificationId)
                startActivity(intent)
                finish()
            }
        }




    }

    private fun login() {
        //val number: EditText = findViewById(R.id.mobliephone)
        mobliephone = findViewById(R.id.mobliephone)
        countrycode = findViewById(R.id.ccp)
        number =  mobliephone.text.toString().trim()

        var country = countrycode.selectedCountryCodeWithPlus



        // get the phone number from edit text and append the country cde with it
        if (number.isNotEmpty()) {
            number = "$country$number"
            sendVerificationCode(number)
        } else {
            Toast.makeText(this, "Enter mobile number", Toast.LENGTH_SHORT).show()
        }
    }

    // this method sends the verification code
    // and starts the callback of verification
    // which is implemented above in onCreate
    private fun sendVerificationCode(number: String) {
        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(number) // Phone number to verify
            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
            .setActivity(this) // Activity (for callback binding)
            .setCallbacks(callbacks) // OnVerificationStateChangedCallbacks
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
        Log.d("Msg", "Auth started")
    }

















    private fun performLogin(){

        val email: EditText = findViewById(R.id.email_login_l)
        val password: EditText = findViewById(R.id.email_password_l)

        if(email.text.isEmpty() || password.text.isEmpty()) {
            Toast.makeText( this, "Please fill all field", Toast.LENGTH_SHORT)
                .show()
            return


        }






        val emailInput = email.text.toString()
        val passwordInput = password.text.toString()

        auth.signInWithEmailAndPassword(emailInput, passwordInput)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information

                    val intent = Intent( this, MainActivity2::class.java)
                    startActivity(intent)

                    Toast.makeText(baseContext, "Success",
                        Toast.LENGTH_SHORT
                    ).show()



                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                }
            }
            .addOnFailureListener{
                Toast.makeText( this, "Authentication failed. ${it.localizedMessage}", Toast.LENGTH_SHORT)
                    .show()
            }



    }








}