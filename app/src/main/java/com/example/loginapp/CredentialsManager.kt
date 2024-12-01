object CredentialsManager {

    // E-posta doğrulama fonksiyonu
    fun isValidEmail(email: String): Boolean {
        if (email.isEmpty()) return false
        val emailRegex = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})"
        return email.matches(emailRegex.toRegex())
    }

    // Şifre doğrulama fonksiyonu
    fun isValidPassword(password: String): Boolean {
        if (password.isEmpty()) return false
        return true
    }

    // Kayıt olma fonksiyonu
    private val accounts = mutableMapOf<String, String>() // E-posta => şifre map'i

    // Getter for testing purpose
    fun getAccounts(): Map<String, String> = accounts

    fun register(email: String, password: String): String {
        if (!isValidEmail(email)) {
            return "Geçersiz e-posta formatı"
        }

        if (!isValidPassword(password)) {
            return "Şifre geçersiz"
        }

        val normalizedEmail = email.trim().lowercase()

        if (accounts.containsKey(normalizedEmail)) {
            return "E-posta zaten alınmış"
        }

        accounts[normalizedEmail] = password
        return "Kayıt başarılı"
    }

    fun login(email: String, password: String): Boolean {
        val normalizedEmail = email.trim().lowercase()
        return accounts[normalizedEmail] == password
    }
}
