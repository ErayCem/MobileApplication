package com.example.loginapp

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.Button
import android.view.View

class NewPage : AppCompatActivity() {

    // Görünümler için val tanımları
    private val emailInput: EditText
        get() = findViewById(R.id.etMail)

    private val passwordInput: EditText
        get() = findViewById(R.id.etPassword)

    private val nextButton: Button
        get() = findViewById(R.id.btnContinue)

    private val createAccountButton: Button
        get() = findViewById(R.id.btnCreateAccount)

    private val mainLayout: View
        get() = findViewById(R.id.main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_new_page)

        // Sistem çubuklarına uyum sağlamak için pencere kenar boşluklarını ayarlayın
        ViewCompat.setOnApplyWindowInsetsListener(mainLayout) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // E-posta ve şifre doğrulama
        nextButton.setOnClickListener {
            val email = emailInput.text.toString()
            val password = passwordInput.text.toString()

            if (!CredentialsManager.isValidEmail(email)) {
                // Yanlış e-posta formatı hatası
                Toast.makeText(this, "Invalid email format", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (!CredentialsManager.isValidPassword(password)) {
                // Şifre boşsa hata göster
                Toast.makeText(this, "Password cannot be empty", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (CredentialsManager.login(email, password)) {
                val intent = Intent(this, register_explora::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Invalid credentials", Toast.LENGTH_SHORT).show()
            }

        }

        // Create Account düğmesine tıklama olayını dinleyici ekleyin
        createAccountButton.setOnClickListener {
            // RegisterExplora aktivitesine geçiş
            val intent = Intent(this, register_explora::class.java)
            startActivity(intent)
        }
    }
}
