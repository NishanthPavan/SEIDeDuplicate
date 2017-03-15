package com.sei.dup;

import static org.junit.Assert.fail;

import java.util.Arrays;

public class AssertorUtil {
	public static void assertArrayOfInteger(String testCaseID, int[] expected, int[] actual) {

		if (expected.length == 0 && actual.length != 0) {
			fail("The actual list should be empty for testcaseId: " + testCaseID);
		} else if (expected.length != 0 && actual.length == 0) {
			fail("The acutal list should not be empty for testcaseId: " + testCaseID);
		} else if (expected.length != actual.length) {
			fail("The size of actual and expected varies. expected size: " + expected.length + " actual size: "
					+ actual.length + " for testcaseId: " + testCaseID);
		} else if (!Arrays.toString(expected).equalsIgnoreCase(Arrays.toString(actual))) {
			fail("The acutal entires are not as expected. expected entries: " + Arrays.toString(expected)
					+ " & actual entries: " + Arrays.toString(actual) + " for testcaseId: " + testCaseID);
		}
	}
}