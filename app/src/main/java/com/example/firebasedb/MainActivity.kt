package com.example.firebasedb

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import com.google.protobuf.Value

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val db = FirebaseFirestore.getInstance()
        val idName = findViewById<EditText>(R.id.name)
        val idAge = findViewById<EditText>(R.id.age)
        val idCity = findViewById<EditText>(R.id.city)
        val button = findViewById<Button>(R.id.button)
        val upBtn =findViewById<Button>(R.id.update)
        val delete=findViewById<Button>(R.id.delete)
        delete.setOnClickListener {
            db.collection("users").document("V9Z3hvuHNpNjbpwWX9PP").delete()
                .addOnSuccessListener { Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show() }
                .addOnFailureListener { Toast.makeText(this, "Delete Failed", Toast.LENGTH_SHORT).show() }
        }
        upBtn.setOnClickListener {
            val user:Map<String,Any> = hashMapOf(
                "name" to idName.text.toString(),
                "age" to idAge.text.toString(),
                "city" to idCity.text.toString()
            )
            db.collection("users").document("V9Z3hvuHNpNjbpwWX9PP").update(user)
                .addOnSuccessListener { Toast.makeText(this, "Updated", Toast.LENGTH_SHORT).show() }
                .addOnFailureListener { Toast.makeText(this, "Update Failed", Toast.LENGTH_SHORT).show() }
        }

        button.setOnClickListener {
            val user = hashMapOf(
                "name" to idName.text.toString(),
                "age" to idAge.text.toString(),
                "city" to idCity.text.toString()
            )
            db.collection("users").add(user)
                .addOnSuccessListener {
                    Toast.makeText(this, "success", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this,GetUserDataActivity::class.java))
                }
                .addOnFailureListener {
                    Toast.makeText(this, "failed", Toast.LENGTH_SHORT).show()
                }

        }
    }
}