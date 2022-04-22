package com.example.employee_demo_external_wiith_registration

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        txtRegister.setOnClickListener {
            var intent = Intent(this,RegisterActivity::class.java)
            startActivity(intent)
        }

        btnLogin.setOnClickListener {
            var user = edtUsername.text.toString()
            var pass = edtPassword.text.toString()
            var db = DBHelper(this)
            var arr = ArrayList<User>()
            arr = db.getUser(user)

            if(arr.size<0)
                Toast.makeText(this,"User Doesn't Exist or Incorrect Email", Toast.LENGTH_LONG).show()
            else
            {
                if(user.equals("") || pass.equals("") )
                {
                    Toast.makeText(this,"Fill Data Accurately", Toast.LENGTH_LONG).show()
                }
                else if((user.equals(arr[0].ur_email)) && (pass.equals(arr[0].ur_password)))
                {
                    var sp: SharedPreferences = getSharedPreferences("MyPref", MODE_PRIVATE)
                    var prefedit = sp.edit()
                    prefedit.putString("UserName",edtUsername.text.toString())
                    prefedit.commit()
                    var intent = Intent(this,HomeActivity::class.java)
                    startActivity(intent)
                }
                else
                    Toast.makeText(this,"Incorrect Password", Toast.LENGTH_LONG).show()
            }

        }

    }
}