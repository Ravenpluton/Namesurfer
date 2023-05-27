/*
 * File: NameSurferEntry.java
 * --------------------------
 * This class represents a single entry in the database.  Each
 * NameSurferEntry contains a name and a list giving the popularity
 * of that name for each decade stretching back to 1900.
 */

import acm.util.*;
import java.util.*;

public class NameSurferEntry implements NameSurferConstants {

	String name, rankingList;
	int[] rankings = new int[NDECADES];

	/* Constructor: NameSurferEntry(line) */
	/**
	 * Creates a new NameSurferEntry from a data line as it appears in the data
	 * file. Each line begins with the name, which is followed by integers giving
	 * the rank of that name for each decade.
	 */
	public NameSurferEntry(String line) {
		parse(line);
	}

	// parses line. gets name and ranking list
	private void parse(String line) {
		int indexOfSpace = line.indexOf(' ');
		name = line.substring(0, indexOfSpace);

		rankingList = line.substring(indexOfSpace + 1);
		intRankingList();
	}

	// turns string of ranking list into array of rankings
	private void intRankingList() {
		StringTokenizer tokenizer = new StringTokenizer(rankingList);
		for (int count = 0; tokenizer.hasMoreTokens(); count++) {
			int rank = Integer.parseInt(tokenizer.nextToken());
			rankings[count] = rank;
		}
	}

	/* Method: getName() */
	/**
	 * Returns the name associated with this entry.
	 */
	public String getName() {
		return name;
	}

	/* Method: getRank(decade) */
	/**
	 * Returns the rank associated with an entry for a particular decade. The decade
	 * value is an integer indicating how many decades have passed since the first
	 * year in the database, which is given by the constant START_DECADE. If a name
	 * does not appear in a decade, the rank value is 0.
	 */
	public int getRank(int decade) {
		if (rankings[decade] > 0) {
			return rankings[decade];
		} else
			return 0;
	}

	/* Method: toString() */
	/**
	 * Returns a string that makes it easy to see the value of a NameSurferEntry.
	 */
	public String toString() {
		return name + " [ " + rankingList + " ]";
	}

}
