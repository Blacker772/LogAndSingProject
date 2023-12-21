package com.example.logandsignproject

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import com.example.logandsignproject.databinding.ActivityAuthBinding

class AuthActivity : AppCompatActivity() {

    private var binding: ActivityAuthBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        val login = intent.getStringExtra("login")
        val password = intent.getStringExtra("password")

        binding?.edLogin?.setText(login)
        binding?.edPassword?.setText(password)

        val db = AppDataBase.getDB(this)

        binding?.btSignUp?.setOnClickListener {
            val intent = Intent(this, SignActivity::class.java)
            startActivity(intent)
        }

        binding?.btLogIn?.setOnClickListener {

            Thread{

                val result: Entity? = db.getDao().qetUserByLogin(binding?.edLogin?.text.toString())

                runOnUiThread{
                    binding?.let { safeBinding ->
                        if (result != null && result.password == binding?.edPassword?.text.toString()){

                            val intent = Intent(this, MainActivity::class.java)
                            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

                            intent.putExtra("name", result.name)
                            intent.putExtra("email", result.email)
                            intent.putExtra("surname", result.surname)
                            intent.putExtra("gender", result.gender)
                            startActivity(intent)

                            binding?.edLogin?.setTextColor(Color.BLACK)
                            binding?.edPassword?.setTextColor(Color.BLACK)
                            binding?.tvError?.isVisible = false
                        } else{
                            binding?.edLogin?.setTextColor(Color.RED)
                            binding?.edPassword?.setTextColor(Color.RED)
                            binding?.tvError?.isVisible = true
                        }
                    }
                }
            }.start()
        }
    }
}