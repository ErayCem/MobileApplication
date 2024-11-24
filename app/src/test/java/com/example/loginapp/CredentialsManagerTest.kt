import org.junit.Assert.*
import org.junit.Test

class CredentialsManagerTest {

    @Test
    fun testEmptyEmail() {
        // Boş e-posta testi
        val email = ""
        val result = CredentialsManager.isValidEmail(email)
        assertFalse("E-posta boş olduğunda geçerli olmamalı.", result)
    }

    @Test
    fun testWrongFormatEmail() {
        // Yanlış formatta e-posta testi
        val email = "wrongformat.com"
        val result = CredentialsManager.isValidEmail(email)
        assertFalse("Yanlış formatta e-posta geçerli olmamalı.", result)
    }

    @Test
    fun testWellFormattedEmail() {
        // Doğru formatta e-posta testi
        val email = "example@example.com"
        val result = CredentialsManager.isValidEmail(email)
        assertTrue("Doğru formatta e-posta geçerli olmalı.", result)
    }

    @Test
    fun testEmptyPassword() {
        // Boş şifre testi
        val password = ""
        val result = CredentialsManager.isValidPassword(password)
        assertFalse("Boş şifre geçerli olmamalı.", result)
    }

    @Test
    fun testFilledPassword() {
        // Dolu şifre testi
        val password = "securepassword123"
        val result = CredentialsManager.isValidPassword(password)
        assertTrue("Dolu şifre geçerli olmalı.", result)
    }

    @Test
    fun testCorrectCredentials() {
        // Sabit kimlik bilgileriyle doğrulama testi
        val email = "test@te.st"
        val password = "1234"

        // Geçerli e-posta ve şifreyi kontrol et
        assertTrue(CredentialsManager.isValidEmail(email))
        assertTrue(CredentialsManager.isValidPassword(password))

        // Sabit kimlik bilgileriyle giriş kontrolü
        assertEquals("test@te.st", email)
        assertEquals("1234", password)
    }

    @Test
    fun testIncorrectCredentials() {
        // Yanlış kimlik bilgileriyle doğrulama testi
        val email = "wrong@te.st"
        val password = "wrongpassword"

        // E-posta formatını ve şifreyi kontrol et
        assertFalse(CredentialsManager.isValidEmail(email))
        assertFalse(CredentialsManager.isValidPassword(password))

        // Yanlış kimlik bilgisi kontrolü
        assertNotEquals("test@te.st", email)
        assertNotEquals("1234", password)
    }
}
