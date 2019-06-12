package com.example.iteradmin.firebaseauthentication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.database.*
import java.lang.StringBuilder

class MainActivity : AppCompatActivity() {

    private lateinit var database:FirebaseDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        database = FirebaseDatabase.getInstance()

        val name = findViewById<EditText>(R.id.name)
        val college = findViewById<EditText>(R.id.college)
        val branch = findViewById<EditText>(R.id.branch)

        val s = findViewById<Button>(R.id.save)

        val txt = findViewById<TextView>(R.id.data)

        s.setOnClickListener {
                val name:String = name.text.toString()
                val clg:String = college.text.toString()
                val brnch:String = branch.text.toString()

                val ref:DatabaseReference = database.getReference("users").child("simi")
                ref.child("name").setValue(name)
                ref.child("college").setValue(clg)
                ref.child("branch").setValue(brnch)
        }
        val ref = database.getReference("users").child("simi")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                Toast.makeText(applicationContext,"values not found",Toast.LENGTH_LONG).show()
            }

            override fun onDataChange(p0: DataSnapshot) {
               /*val str = p0.getValue(String::class.java)
                txt.text = str*/
               /* val child:String? = p0.child("name").getValue(String::class.java)
                txt.text = child*/

                val children = p0.children
                val str = StringBuilder()
                for (child in children){
                    str.append(child.key + " : "+ child.value + "\n")
                }
                txt.text = str
            }
        })
    }
}
