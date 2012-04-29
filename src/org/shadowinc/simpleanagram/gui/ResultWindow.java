package org.shadowinc.simpleanagram.gui;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

public class ResultWindow extends JFrame {

	private static final long serialVersionUID = -1613924319560504233L;
	private JPanel contentPane;
	private JTextArea textArea;
	private JScrollPane scrollPane;

	/**
	 * Create the frame.
	 */
	public ResultWindow() {
		setTitle("Results");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 350);
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(1, 0, 0, 0));
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		scrollPane = new JScrollPane(textArea);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		contentPane.add(scrollPane);
	}
	
	public void addMessage(String msg) {
		textArea.append(msg);
	}
	
	public void addMessages(String[] msgs) {
		for (int i=0; i < msgs.length; i++)
			textArea.append(msgs[i]+"\n");
	}
}
