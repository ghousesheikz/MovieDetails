package com.sample.hiltdisampleapp.ui.main.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.sample.hiltdisampleapp.R
import com.sample.hiltdisampleapp.utils.isValidEmail
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        btnSubmit.setOnClickListener {
            if (validation()) startActivity(Intent(this@LoginActivity, MainActivity::class.java))
        }

    }

    private fun validation(): Boolean {
        var flag = true
        if (edtEmail.text.toString().trim().isEmpty()) {
            flag = false
            Toast.makeText(this@LoginActivity, "Please enter email", Toast.LENGTH_LONG).show()
        } else if (!isValidEmail(edtEmail.text.toString().trim())) {
            flag = false
            Toast.makeText(this@LoginActivity, "Please enter valid email", Toast.LENGTH_LONG).show()
        } else if (edtPassword.text.toString().trim().isEmpty()) {
            flag = false
            Toast.makeText(this@LoginActivity, "Please enter email", Toast.LENGTH_LONG).show()
        } else if (edtPassword.text.toString().trim().length < 8 || edtPassword.text.toString()
                .trim().length > 15
        ) {
            flag = false
            Toast.makeText(
                this@LoginActivity,
                "Password should be between 8 to 15 characters",
                Toast.LENGTH_LONG
            )
                .show()
        }
        return flag
    }
}