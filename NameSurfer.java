/*
 * File: NameSurfer.java
 * ---------------------
 * When it is finished, this program will implements the viewer for
 * the baby-name database described in the assignment handout.
 */

import acm.program.*;
import java.awt.event.*;
import javax.swing.*;

public class NameSurfer extends Program implements NameSurferConstants {

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

		addButtons();

		graph = new NameSurferGraph();
		add(graph);

		addActionListeners();

		nameDataBase = new NameSurferDataBase(NAMES_DATA_FILE);
	}

	// add all necessary buttons
	private void addButtons() {
		JButton Graph = new JButton("Graph");
		add(Graph, SOUTH);

		JButton clear = new JButton("Clear");
		add(clear, SOUTH);
	}

	/* Method: actionPerformed(e) */
	/**
	 * This class is responsible for detecting when the buttons are clicked, so you
	 * will have to define a method to respond to button actions.
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Clear")) {
			graph.clear();
			graph.update();
		} else {
			String name = tf.getText();
			NameSurferEntry entry = nameDataBase.findEntry(name);
			if (entry != null) {
				graph.addEntry(entry);
				graph.update();
				tf.setText("");
			}
		}
	}

	JTextField tf;
	private NameSurferDataBase nameDataBase;
	private NameSurferGraph graph;
}
