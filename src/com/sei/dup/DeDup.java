package com.sei.dup;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.IntStream;

public class DeDup {

	// This uses java 8 stream feature
	// We can also use parallel streams based on processor cores.
	public int[] removeDuplicates1(int[] nums) {
		if (null == nums)
			return new int[0];

		return IntStream.of(nums).distinct().toArray();
	}

	// Time complexity and space complexity O(n^2)
	// LinkedHashSet is inherently using much space and time
	public int[] removeDuplicates2(int[] nums) {
		if (null == nums)
			return new int[0];

		LinkedHashSet<Integer> result = new LinkedHashSet<>();
		for (int i = 0; i < nums.length; i++) {
			result.add(nums[i]);
		}
		return result.stream().mapToInt(x -> x).toArray();
	}

	// Use the same array instead of creating new array.
	// Time Complexity O(n + m) if n is initial size and m is final size
	// Space Complexity O(n)
	// Need to follow this approach when space is critical and order need to be
	// maintained. This approach will handle cases when a large no. of
	// duplicates are present. since we are iterating on a valid indexes we can
	// avoid O(n^2). Also we are avoiding more left shift operations in Array
	public int[] removeDuplicates3(int[] nums) {
		if (null == nums)
			return new int[0];

		StringBuilder nonDuplicateIndexes = new StringBuilder();
		Set<Integer> indexSet = new HashSet<>();
		for (int i = 0; i < nums.length; i++) {
			if (!indexSet.contains(nums[i])) {
				nonDuplicateIndexes.append(String.valueOf(i)).append(",");
				indexSet.add(nums[i]);
			}
		}

		if (0 == nonDuplicateIndexes.length())
			return new int[0];
		String[] validIndexes = nonDuplicateIndexes.toString().split(",");
		int i = 0;
		while (i < validIndexes.length) {
			nums[i] = nums[Integer.parseInt(validIndexes[i])];
			i++;
		}
		return Arrays.stream(nums).limit(i).toArray();
	}
}
