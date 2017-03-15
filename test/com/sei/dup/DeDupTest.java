package com.sei.dup;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class DeDupTest {

	private static String fileName = "C://Users//sony//workspace//SEIDeDuplicate//test//com//sei//dup//testdata.dat";

	private String testCaseID;

	private int[] input;

	private int[] expectedOutput;

	private DeDup deDup;

	public DeDupTest(String testCaseID, int[] input, int[] expectedOutput) {
		this.testCaseID = testCaseID;
		this.input = input;
		this.expectedOutput = expectedOutput;
	}

	@Parameters
	public static Collection<Object[]> testData() {
		List<Object[]> testData = new ArrayList<Object[]>();

		List<String> lines = new ArrayList<>();
		// read file into stream, try-with-resources
		try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
			lines = stream.skip(1).collect(Collectors.toList());
		} catch (IOException e) {
			e.printStackTrace();
		}

		for (String line : lines) {
			String[] cols = line.split(":");

			String t = null;
			int[] o = null;
			int[] i = null;
			if (cols.length < 1) {
				throw new RuntimeException("Invalid Test Case");
			} else if (cols.length < 2) {
				t = cols[0];
				i = null;
				o = new int[0];
			} else if (cols.length < 3) {
				t = cols[0];
				i = Stream.of(cols[1].split("~")).mapToInt(Integer::parseInt).toArray();
				o = new int[0];
			} else {
				t = cols[0];
				i = Stream.of(cols[1].split("~")).mapToInt(Integer::parseInt).toArray();
				o = Stream.of(cols[2].split("~")).mapToInt(Integer::parseInt).toArray();
			}

			testData.add(new Object[] { t, i, o });
		}
		return testData;
	}

	@Before
	public void setUp() throws Exception {
		deDup = new DeDup();
	}

	@Test
	public void testRemoveDuplicates1() {
		int[] actualOutput = deDup.removeDuplicates1(input);
		AssertorUtil.assertArrayOfInteger(testCaseID, expectedOutput, actualOutput);
	}

	@Test
	public void testRemoveDuplicates2() {
		int[] actualOutput = deDup.removeDuplicates2(input);
		AssertorUtil.assertArrayOfInteger(testCaseID, expectedOutput, actualOutput);
	}

	@Test
	public void testRemoveDuplicates3() {
		int[] actualOutput = deDup.removeDuplicates3(input);
		AssertorUtil.assertArrayOfInteger(testCaseID, expectedOutput, actualOutput);
	}

}
