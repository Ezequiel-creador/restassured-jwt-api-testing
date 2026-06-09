package com.proyect.testing.report;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;

public class BaseTest {
 @BeforeEach
    void setup(TestInfo testInfo) {
        ExtentTestManager.startTest(testInfo.getDisplayName());
    }

    @AfterAll
    static void tearDown() {
        ExtentTestManager.extent.flush();
    }
}

