package com.github.oogasawa.utility.types.collection;

import java.util.ArrayList;
import java.util.TreeSet;

public class TreeSetUtil {
	
	public static <T> ArrayList<T> toArrayList(TreeSet<T> set) {
		ArrayList<T> result = new ArrayList<T>(set.size());
		for (T elem : set) {
			result.add(elem);
		}
		return result;
	}

}
