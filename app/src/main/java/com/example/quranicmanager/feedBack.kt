package com.example.quranicmanager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Adapter
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.model.Document
//first commit in the file 

class feedBack : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed_back)
        getAllDocs()
        val list = findViewById<ListView>(R.id.userfeedbacklist)




    }

    fun pubulateListView() {

    }
    private fun getAllDocs() {
        var i = 0;
        val array = ArrayList<String>()
        val list = findViewById<ListView>(R.id.userfeedbacklist)
        // [START get_multiple_all]
        val db = FirebaseFirestore.getInstance()
        db.collection("Feedback")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    print( "${document.id} => ${document.data}"+"!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!")
                    array.add("${document.id}".toString())
                    //Toast.makeText(this,"${document.id}",Toast.LENGTH_LONG).show()
                }
            }
            .addOnFailureListener { exception ->

            }.addOnCompleteListener{
                try {
                    val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, array)
                    list.adapter = adapter
                    list.setOnItemClickListener{parent, view, position, id ->
                        Toast.makeText(this,parent.getItemAtPosition(position).toString(),Toast.LENGTH_LONG).show()
                        val intent = Intent(this,feedbackdetail::class.java)
                        intent.putExtra("userdata",parent.getItemAtPosition(position).toString())
                        startActivity(intent)


                    }
                }
                catch (e:Exception)
                {
                    Toast.makeText(this,e.message,Toast.LENGTH_LONG).show()
                }

            }

        // [END get_multiple_all]
    }


}