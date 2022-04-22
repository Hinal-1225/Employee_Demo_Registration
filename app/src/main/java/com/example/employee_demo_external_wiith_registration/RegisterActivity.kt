package com.example.employee_demo_external_wiith_registration

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        btnRegister.setOnClickListener {
            if(edtEmail.text.toString().equals("") || edtCity.text.toString().equals("")
                || edtRePassword.text.toString().equals("") )
            {
                Toast.makeText(this,"Fill Data Accurately", Toast.LENGTH_LONG).show()
            }
            else if(edtRePassword.text.toString().length<=7)
            {
                Toast.makeText(this,"Password Must be 8 Character Long!", Toast.LENGTH_LONG).show()
            }
            else
            {
                var email = edtEmail.text.toString()
                var city = edtCity.text.toString()
                var password = edtRePassword.text.toString()
                var user = User(email,city,password)
                var db = DBHelper(this)

                var res = db.insertUser(user)
                if(res>0)
                {
                    Toast.makeText(this,"Register Successfully", Toast.LENGTH_LONG).show()
                    var intent = Intent(this,LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }

    }
}