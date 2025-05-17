package com.example.lucas.util;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StringUtilsTest {

    @Test
    void testStringReverse() {
        // Given
        String input = "hello";
        String expected = "olleh";
        
        // When
        String result = new StringBuilder(input).reverse().toString();
        
        // Then
        assertEquals(expected, result, "String should be reversed correctly");
    }
}