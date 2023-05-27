/*
 * File: NameSurfer.java
 * ---------------------
 * When it is finished, this program will implements the viewer for
 * the baby-name database described in the assignment handout.
 */

import acm.graphics.GCanvas;
import acm.program.*;
import java.awt.event.*;
import javax.swing.*;

public class NameSurfer2 extends Program implements NameSurferConstants {

	/* Method: init() */
	/**
	 * This method has the responsibility for reading in the data base and
	 * initializing the interactors at the bottom of the window.
	 */
	public void init() {
		JLabel name = new JLabel("Name");
		add(name, SOUTH);

		tf = new JTextField(20);
		add(tf, SOUTH);
		tf.addActionListener(this);

		addjbuttons();

		graph = new NameSurferGraph2();
		add(graph);

		addActionListeners();

		nameDataBase = new NameSurferDataBase2(NAMES_DATA_FILE);
	}

	// adds all JButtons needed
	private void addjbuttons() {
		JButton Graph = new JButton("Graph");
		add(Graph, SOUTH);

		JButton clear = new JButton("Clear");
		add(clear, SOUTH);

		JButton delete = new JButton("Delete");
		add(delete, SOUTH);

		JButton lucky = new JButton("I'm Feeling Lucky");
		add(lucky, NORTH);
	}

	/* Method: actionPerformed(e) */
	/**
	 * This class is responsible for detecting when the buttons are clicked, so you
	 * will have to define a method to respond to button actions.
	 */
	public void actionPerformed(ActionEvent e) {
		String name = tf.getText();

		if (e.getActionCommand().equals("Clear")) {
			clearCase();
		} else if (e.getActionCommand().equals("Delete")) {
			deleteCase(name);
		} else if (e.getActionCommand().equals("I'm Feeling Lucky")) {
			luckyCase();
		} else {
			elseCase(name);
		}
	}

	// what happens when pressing graph or enter
	private void elseCase(String name) {
		NameSurferEntry entry = nameDataBase.findEntry(name);
		if (entry != null) {
			graph.addEntry(entry);
			graph.update();
			tf.setText("");
		}
	}

	// what happens when pressing im feeling lucky
	private void luckyCase() {
		NameSurferEntry randomEntry = nameDataBase.getRandomEntry();
		graph.addEntry(randomEntry);
		graph.update();
	}

	// what happens when pressing delete
	private void deleteCase(String name) {
		NameSurferEntry entry = nameDataBase.findEntry(name);
		graph.delete(entry);
		graph.update();
		tf.setText("");
	}

	// what happens when pressing clear
	private void clearCase() {
		graph.clear();
		graph.update();
	}

	JTextField tf;
	private NameSurferDataBase2 nameDataBase;
	private NameSurferGraph2 graph;
}
