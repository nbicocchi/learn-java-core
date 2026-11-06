package com.nbicocchi.exercises.e5_exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CheckLicencePlateTest {

    @Test
    void checkLicencePlate() {
        assertDoesNotThrow(() -> CheckLicencePlate.checkLicencePlate("AA958LV"));
        assertThrows(IllegalArgumentException.class, () -> CheckLicencePlate.checkLicencePlate("BO95889"));
        assertThrows(IllegalArgumentException.class, () -> CheckLicencePlate.checkLicencePlate("1295889"));
        assertThrows(IllegalArgumentException.class, () -> CheckLicencePlate.checkLicencePlate("AA958L"));
        assertThrows(IllegalArgumentException.class, () -> CheckLicencePlate.checkLicencePlate("AA958LVA"));
    }
}