import org.junit.Assert.*
import org.junit.Test

class CredentialsManagerTest {

    @Test
    fun testEmptyEmail() {
        val email = ""
        val result = CredentialsManager.isValidEmail(email)
        assertFalse("E-posta boş olduğunda geçerli olmamalı.", result)
    }

    @Test
    fun testWrongFormatEmail() {
        val email = "wrongformat.com"
        val result = CredentialsManager.isValidEmail(email)
        assertFalse("Yanlış formatta e-posta geçerli olmamalı.", result)
    }

    @Test
    fun testWellFormattedEmail() {
        val email = "example@example.com"
        val result = CredentialsManager.isValidEmail(email)
        assertTrue("Doğru formatta e-posta geçerli olmalı.", result)
    }

    @Test
    fun testEmptyPassword() {
        val password = ""
        val result = CredentialsManager.isValidPassword(password)
        assertFalse("Boş şifre geçerli olmamalı.", result)
    }

    @Test
    fun testFilledPassword() {
        val password = "securepassword123"
        val result = CredentialsManager.isValidPassword(password)
        assertTrue("Dolu şifre geçerli olmalı.", result)
    }

    @Test
    fun testRegisterSuccess() {
        val email = "newuser@example.com"
        val password = "newpassword123"
        val result = CredentialsManager.register(email, password)
        assertEquals("Kayıt başarılı", result)

        // Verify the account has been added
        val accounts = CredentialsManager.getAccounts()
        assertTrue(accounts.containsKey(email.lowercase()))
        assertEquals(password, accounts[email.lowercase()])
    }

    @Test
    fun testRegisterEmailAlreadyTaken() {
        val email = "test@te.st"
        val password = "1234"

        // Register the first account
        CredentialsManager.register(email, password)

        // Try to register again with the same email
        val result = CredentialsManager.register(email, "newpassword123")
        assertEquals("E-posta zaten alınmış", result)
    }

    @Test
    fun testIncorrectCredentials() {
        val email = "wrong@te.st"
        val password = "wrongpassword"
        val result = CredentialsManager.login(email, password)
        assertFalse("Yanlış kimlik bilgileri geçerli olmamalı.", result)
    }

    @Test
    fun testCorrectCredentials() {
        val email = "test@te.st"
        val password = "1234"
        CredentialsManager.register(email, password)

        // Test login with correct credentials
        val result = CredentialsManager.login(email, password)
        assertTrue("Geçerli kimlik bilgileriyle giriş başarılı olmalı.", result)
    }
}
