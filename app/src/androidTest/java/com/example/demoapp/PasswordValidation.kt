package com.example.demoapp

import com.example.demoapp.utils.UserInputValidation
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import org.junit.Test

class PasswordValidation {
    @Test
    fun testValidPassword() {
        assertTrue(UserInputValidation.isValidPassword("Password"))
        assertTrue(UserInputValidation.isValidPassword("abc123"))
    }

    @Test
    fun testInvalidPassword() {
        assertFalse(UserInputValidation.isValidPassword("test"))
        assertFalse(UserInputValidation.isValidPassword("123"))
        assertFalse(UserInputValidation.isValidPassword("1@12"))
    }
}