package com.example.logandsignproject

import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.view.isVisible
import com.example.logandsignproject.databinding.ActivitySignBinding

class SignActivity : AppCompatActivity() {

    private var binding: ActivitySignBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        val db = AppDataBase.getDB(this)

        binding?.btLogIn?.setOnClickListener {
            val name = binding?.etName?.text.toString()
            val surname = binding?.edSurName?.text.toString()
            val login = binding?.edLogin?.text.toString()
            val password = binding?.edPassword?.text.toString()
            val email = binding?.edEmail?.text.toString()
            var gender: String? = null
            when(binding?.radioGroup?.checkedRadioButtonId){
                binding?.male?.id ->{ gender = "Male"}
                binding?.Female?.id ->{gender = "Female"}
                else -> {}
            }

            if (name.isEmpty() || surname.isEmpty() || login.isEmpty() || password.isEmpty() || email.isEmpty() || gender.isNullOrEmpty()){
                binding?.tvError?.isVisible = true
            } else{
                binding?.tvError?.text = "Ваш Аккаунт создан успешно!"
                binding?.tvError?.setTextColor(Color.GREEN)
                Toast.makeText(applicationContext,"Ваш Аккаунт создан успешно!", Toast.LENGTH_SHORT).show()

                val closeKeyboard = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                closeKeyboard.hideSoftInputFromWindow(currentFocus?.windowToken, 0)

                Thread{
                    db.getDao().insertPerson(
                        Person(null, name, surname, login, password, email, gender)
                    )
                }.start()

                val intent = Intent(this, AuthActivity::class.java)
                intent.putExtra("login", binding?.edLogin?.text.toString())
//                intent.putExtra("password", binding?.edPassword?.text.toString())
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }
        }
    }
}