package com.example.demoapp

import com.example.demoapp.utils.UserInputValidation
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import org.junit.Test

class emailValidation {
    @Test
    fun testValidEmail() {
        assertTrue(UserInputValidation.isValidEmail("test@example.com"))
        assertTrue(UserInputValidation.isValidEmail("john.doe@gmail.com"))
        assertTrue(UserInputValidation.isValidEmail("jane_doe-123@domain.co.pk"))
    }

    @Test
    fun testInvalidEmail() {
        assertFalse(UserInputValidation.isValidEmail("invalid_email"))
        assertFalse(UserInputValidation.isValidEmail("test@com"))
        assertFalse(UserInputValidation.isValidEmail("test.name@gmail"))
    }

}