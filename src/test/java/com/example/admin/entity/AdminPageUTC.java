package com.example.admin.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AdminPageUTC {
    private AdminPage adminPage;

    @BeforeEach
    void setUpOk() {
		adminPage = AdminPage.builder()
                             .body("This is test body")
                             .build();
        assertNotNull(adminPage);
    }

    @Test
    void testToStringOk() {
        assertTrue(adminPage.toString().length() > 0);
    }

    @Test
    void getBodyOk() {
        assertEquals("This is test body", adminPage.getBody());
    }

    @Test
    void setBodyOk() {
        adminPage.setBody("This is a new test body");
        assertEquals("This is a new test body", adminPage.getBody());
    }
}
