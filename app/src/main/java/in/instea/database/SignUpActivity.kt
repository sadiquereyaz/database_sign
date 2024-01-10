package `in`.instea.database

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignUpActivity : AppCompatActivity() {
    lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val username = findViewById<TextInputEditText>(R.id.TIETUsername)
        val email = findViewById<TextInputEditText>(R.id.TIETEmail)
        val password = findViewById<TextInputEditText>(R.id.TIETPassword)
        val signupbtn = findViewById<Button>(R.id.signupBtn)

        signupbtn.setOnClickListener {
            val usernameVar = username.text.toString()
            val emailVar = email.text.toString()
            val passwordVar = password.text.toString()
            val userDataVar = UserData(usernameVar, emailVar, passwordVar)

            database = FirebaseDatabase.getInstance().getReference("users")
            database.child(usernameVar).setValue(userDataVar).addOnSuccessListener {
                Toast.makeText(this, "Registration Successful", Toast.LENGTH_SHORT).show()
                username.text?.clear()
            }.addOnFailureListener {
                Toast.makeText(this, "Registration Failed", Toast.LENGTH_SHORT).show()
            }
        }
    }
}