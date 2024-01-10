package `in`.instea.database

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignInActivity : AppCompatActivity() {

    lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        val signInButton = findViewById<Button>(R.id.signinBtn)
        val username = findViewById<TextInputEditText>(R.id.TIETUsername)
        val password = findViewById<TextInputEditText>(R.id.TIETPassword)

        signInButton.setOnClickListener {
            val usernameVar = username.text.toString()
            val passwordVar = password.text.toString()

            if (usernameVar.isNotEmpty()) {
                readData(usernameVar, passwordVar)
            } else {
                Toast.makeText(this, "Please Enter a username", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun readData(username: String, password: String) {
        databaseReference = FirebaseDatabase.getInstance().getReference("users")
        databaseReference.child(username).get().addOnSuccessListener {
            if (it.exists()) {
                val passwordFromDB = it.child("password").value
//                Log.i("itContent", it.toString())
                if (passwordFromDB.toString().equals(password)) {
                    Toast.makeText(this, "User Exists with password ${passwordFromDB}", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(this, "Wrong Password", Toast.LENGTH_SHORT).show()

                }
            } else {
                Toast.makeText(this, "User Doesn't Exists", Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener {
            Toast.makeText(this, "failed due to technical Issue", Toast.LENGTH_SHORT).show()
        }

    }

    fun moveToSignUp(view: View) {
        val intent = Intent(this, SignUpActivity::class.java)
        startActivity(intent)
    }
}