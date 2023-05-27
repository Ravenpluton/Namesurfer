/*
 * File: NameSurferGraph.java
 * ---------------------------
 * This class represents the canvas on which the graph of
 * names is drawn. This class is responsible for updating
 * (redrawing) the graphs whenever the list of entries changes or the window is resized.
 */

import acm.graphics.*;
import java.awt.event.*;
import java.util.*;
import java.awt.*;

public class NameSurferGraph extends GCanvas implements NameSurferConstants, ComponentListener {

	private ArrayList<NameSurferEntry> entries;
	private double oneVerticalStripe = APPLICATION_WIDTH / NDECADES;

	/**
	 * Creates a new NameSurferGraph object that displays the data.
	 */
	public NameSurferGraph() {
		addComponentListener(this);
		entries = new ArrayList<NameSurferEntry>();
	}

	/**
	 * Clears the list of name surfer entries stored inside this class.
	 */
	public void clear() {
		entries.clear();
	}

	/* Method: addEntry(entry) */
	/**
	 * Adds a new NameSurferEntry to the list of entries on the display. Note that
	 * this method does not actually draw the graph, but simply stores the entry;
	 * the graph is drawn by calling update.
	 */
	public void addEntry(NameSurferEntry entry) {
		entries.add(entry);
	}

	/**
	 * Updates the display image by deleting all the graphical objects from the
	 * canvas and then reassembling the display according to the list of entries.
	 * Your application must call update after calling either clear or addEntry;
	 * update is also called whenever the size of the canvas changes.
	 */
	public void update() {
		removeAll();
		drawBackground();
		for (int i = 0; i < entries.size(); i++) {
			NameSurferEntry entryForGraph = entries.get(i);
			drawEntry(entryForGraph, i);
		}
	}

	// draws graph for the entered name
	private void drawEntry(NameSurferEntry entry, int entryNumber) {
		Color color = getColor(entryNumber);
		

		double lengthOfColumn = getHeight() - 2 * GRAPH_MARGIN_SIZE;

		drawGraph(entry, lengthOfColumn, color);

		addNameRankLabels(entry, lengthOfColumn, color);

	}

	// draws name and rank labels
	private void addNameRankLabels(NameSurferEntry entry, double lengthOfColumn, Color color) {
		for (int i = 0; i < NDECADES; i++) {
			int rank = entry.getRank(i);
			String rankString = "";
			if (rank == 0)
				rankString = "*";
			else
				rankString = Integer.toString(rank);
			String s = entry.getName() + " " + rankString;
			GLabel label = new GLabel(s);
			
			double x = i * oneVerticalStripe + 2;
			double y = GRAPH_MARGIN_SIZE + (lengthOfColumn * entry.getRank(i) / MAX_RANK) - 2;
			
			if (y == GRAPH_MARGIN_SIZE - 2)
				y += lengthOfColumn;
			label.setColor(color);
			add(label, x, y);
		}
	}

	// draws graph lines
	private void drawGraph(NameSurferEntry entry, double lengthOfColumn, Color color) {
		for (int i = 0; i < NDECADES - 1; i++) {
			double x1 = i * oneVerticalStripe;
			double x2 = (i + 1) * oneVerticalStripe;
			
			double y1 = GRAPH_MARGIN_SIZE + (lengthOfColumn * entry.getRank(i) / MAX_RANK);
			double y2 = GRAPH_MARGIN_SIZE + (lengthOfColumn * entry.getRank(i + 1) / MAX_RANK);
			
			if (y1 == GRAPH_MARGIN_SIZE)
				y1 += lengthOfColumn;
			if (y2 == GRAPH_MARGIN_SIZE)
				y2 += lengthOfColumn;
			
			GLine line = new GLine(x1, y1, x2, y2);
			line.setColor(color);
			add(line);
		}
	}

	// gets color for graph and name label
	private Color getColor(int entryNumber) {
		Color color = Color.BLACK;
		if (entryNumber % 4 == 0)
			color = Color.BLACK;
		else if (entryNumber % 4 == 1)
			color = Color.RED;
		else if (entryNumber % 4 == 2)
			color = Color.BLUE;
		else if (entryNumber % 4 == 3)
			color = Color.YELLOW;
		return color;
	}

	// draws background for program
	private void drawBackground() {
		drawVerticalLines();
		drawHorizontalLines();
		addLabels();
	}

	// adds decade labels
	private void addLabels() {
		for (int i = 0; i < NDECADES; i++) {
			int decade = START_DECADE + i * 10;
			String s = Integer.toString(decade);
			GLabel label = new GLabel(s);
			double x = 2 + i * (oneVerticalStripe);
			double y = getHeight() - GRAPH_MARGIN_SIZE + label.getHeight();
			add(label, x, y);
		}
	}

	// draws horizontal lines of background
	private void drawHorizontalLines() {
		double x1 = 0;
		double x2 = getWidth();
		double y1 = GRAPH_MARGIN_SIZE;
		double y2 = getHeight() - GRAPH_MARGIN_SIZE;
		GLine lineOne = new GLine(x1, y1, x2, y1);
		add(lineOne);
		GLine lineTwo = new GLine(x1, y2, x2, y2);
		add(lineTwo);
	}

	// draws vertical lines of background
	private void drawVerticalLines() {
		double y1 = 0;
		double y2 = getHeight();
		for (int i = 0; i < NDECADES; i++) {
			double x = 0 + i * oneVerticalStripe;
			GLine line = new GLine(x, y1, x, y2);
			add(line);
		}
	}

	/* Implementation of the ComponentListener interface */
	public void componentHidden(ComponentEvent e) {
	}

	public void componentMoved(ComponentEvent e) {
	}

	public void componentResized(ComponentEvent e) {
		update();
	}

	public void componentShown(ComponentEvent e) {
	}
}
