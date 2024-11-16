object CredentialsManager {

    // E-posta doğrulama fonksiyonu
    fun isValidEmail(email: String): Boolean {
        // Boşsa geçersizdir
        if (email.isEmpty()) return false

        // Basit bir e-posta format kontrolü
        val emailRegex = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})"
        return email.matches(emailRegex.toRegex())
    }

    // Şifre doğrulama fonksiyonu
    fun isValidPassword(password: String): Boolean {
        // Boşsa geçersizdir
        if (password.isEmpty()) return false

        // Basit kontrol: sadece boş olmaması gerektiği test edilmiştir.
        return true
    }
}
