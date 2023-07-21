package com.example.firebasedb

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

class MainActivity2 : AppCompatActivity() {
    private var storageRef = Firebase.storage.reference
    private lateinit var imageView: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        var galleryAccess = findViewById<Button>(R.id.button)
        imageView = findViewById<ImageView>(R.id.image)

        galleryAccess.setOnClickListener {
            val pickImg = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            changeImage.launch(pickImg)
        }
    }
        private val changeImage =
            registerForActivityResult(
                ActivityResultContracts.StartActivityForResult()
            ) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    val data = result.data
                    val imgUri = data?.data
                    imageView.setImageURI(imgUri)

                    if (imgUri != null) {
                        val fileName = System.currentTimeMillis().toString()
                        val imageRef = storageRef.child("image/").child(fileName)

                        imageRef.putFile(imgUri)
                            .addOnSuccessListener { taskSnapshot ->
                                // File downloaded successfully
                                Toast.makeText(this,"Upload successfully",Toast.LENGTH_SHORT).show()
                                taskSnapshot.storage.downloadUrl.addOnSuccessListener { uri ->
                                    val downloadUrl = uri.toString()
                                    // Process the downloaded file or handle the download URL
                                }.addOnFailureListener { exception ->
                                    Toast.makeText(this, exception.toString()+"download url", Toast.LENGTH_SHORT).show()
                                }
                            }
                            .addOnFailureListener { exception ->
                                // File download failed, handle the error
                                Toast.makeText(this, "upload image$exception", Toast.LENGTH_SHORT).show()
                            }
                    }
                }
            }



    }
