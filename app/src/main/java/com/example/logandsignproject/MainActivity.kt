package com.example.logandsignproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.logandsignproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        val name = intent.getStringExtra("name")
        val surname = intent.getStringExtra("surname")
        val email = intent.getStringExtra("email")
        val gender = intent.getStringExtra("gender")

        binding?.tvName?.text = name
        binding?.tvSurName?.text = surname
        binding?.tvEmail?.text = email
        binding?.tvGender?.text = gender
    }

}