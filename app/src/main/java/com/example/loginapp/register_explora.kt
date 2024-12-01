package com.example.loginapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class register_explora : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register_explora)

        // Pencere kenar boşluklarını ayarlıyoruz
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // "Already have an account?" butonuna tıklama olayı
        findViewById<Button>(R.id.btnAlreadyHaveAccount).setOnClickListener {
            val intent = Intent(this, NewPage::class.java)
            startActivity(intent)
        }

        // Kayıt ol butonuna tıklama olayı
        findViewById<Button>(R.id.btnContinue).setOnClickListener {
            // Kullanıcıdan e-posta ve şifre alıyoruz
            val emailInput: EditText = findViewById(R.id.etMail)
            val passwordInput: EditText = findViewById(R.id.etPassword)

            val email = emailInput.text.toString().trim()
            val password = passwordInput.text.toString().trim()

            // CredentialsManager'ın `register` fonksiyonunu çağırıyoruz
            val result = CredentialsManager.register(email, password)

            // Kayıt başarılıysa, LoginActivity'ye yönlendiriyoruz
            if (result == "Kayıt başarılı") {
                Toast.makeText(this, "Kayıt başarılı, giriş yapabilirsiniz", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, NewPage::class.java) // LoginActivity'ye yönlendir
                startActivity(intent)
            } else {
                // Kayıt başarısızsa, hata mesajını gösteriyoruz
                Toast.makeText(this, result, Toast.LENGTH_SHORT).show()
            }
        }
    }
}
