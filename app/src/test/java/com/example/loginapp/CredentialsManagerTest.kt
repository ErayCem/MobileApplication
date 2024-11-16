package com.example.loginapp

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

internal class CredentialsManagerTest {


    @Test
    fun givenEmptyEmail_thenReturnFalse() {
        val credentialsManager = CredentialsManager()
        val email="email@te.st"

        val result=credentialsManager.isEmailValid(email)

        assertEquals(
            false,
            credentialsManager.isEmailValid("")
        )
    }


    fun givenEmptyEmail_thenReturnTrue() {
}