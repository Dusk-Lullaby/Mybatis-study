package com.sonnet.utils;

import org.junit.Test;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class ExecutorTest {

    @Test
    public void convertsJdbcNumberToBeanPropertyType() {
        Object value = Executor.convertValue("id", Long.valueOf(42), Integer.class);

        assertEquals(Integer.valueOf(42), value);
    }

    @Test
    public void convertsJdbcLocalDateTimeToDatePropertyType() {
        LocalDateTime localDateTime = LocalDateTime.of(2024, 5, 6, 7, 8, 9);
        Object value = Executor.convertValue("birthday", localDateTime, Date.class);

        assertEquals(Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant()), value);
    }

    @Test
    public void rejectsNumericOverflowWithColumnDetails() {
        try {
            Executor.convertValue("id", Long.valueOf(Long.MAX_VALUE), Integer.class);
            fail("Expected an out-of-range value to be rejected");
        } catch (IllegalArgumentException exception) {
            assertTrue(exception.getMessage().contains("id"));
        }
    }
}
