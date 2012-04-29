package org.shadowinc.simpleanagram.gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import org.shadowinc.simpleanagram.NoAnagramException;
import org.shadowinc.simpleanagram.Wordlist;

public class SimpleAnagram extends JFrame {

	private static final long serialVersionUID = -2580742659640293230L;
	private JPanel contentPane;
	private JTextField txtWord;
	private JTextField txtLength;
	private JTextField txtResults;
	private ResultWindow result;
	private Wordlist wl;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SimpleAnagram frame = new SimpleAnagram();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SimpleAnagram() {
		setResizable(false);
		setTitle("Simple Anagram v0.1b");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 250, 150);
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblWord = new JLabel("Word");
		lblWord.setBounds(20, 20, 32, 16);
		contentPane.add(lblWord);
		
		JLabel lblMinLength = new JLabel("Min. Length");
		lblMinLength.setBounds(20, 48, 74, 16);
		contentPane.add(lblMinLength);
		
		txtWord = new JTextField();
		txtWord.setBounds(64, 14, 176, 28);
		contentPane.add(txtWord);
		txtWord.setColumns(10);
		
		txtLength = new JTextField();
		txtLength.setHorizontalAlignment(SwingConstants.CENTER);
		txtLength.setText("4");
		txtLength.setBounds(106, 42, 32, 28);
		contentPane.add(txtLength);
		txtLength.setColumns(10);
		
		JLabel lblResults = new JLabel("Results");
		lblResults.setBounds(150, 48, 46, 16);
		contentPane.add(lblResults);
		
		txtResults = new JTextField();
		txtResults.setHorizontalAlignment(SwingConstants.CENTER);
		txtResults.setText("5");
		txtResults.setBounds(208, 42, 32, 28);
		contentPane.add(txtResults);
		txtResults.setColumns(10);
		
		JButton btnGo = new JButton("Go!");
		btnGo.setBounds(20, 76, 117, 29);
		btnGo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					String[] found = wl.search(
							txtWord.getText().toLowerCase().toCharArray(),
							Integer.valueOf(txtLength.getText()),
							Integer.valueOf(txtResults.getText())
							);
					for (int i=0; i<found.length; i++)
						found[i] = found[i]+" => "+found[i].length();
					result.addMessage("\nResult for "+txtWord.getText()+":\n");
					result.addMessages(found);
				} catch (NoAnagramException nae) {
					result.addMessage("No anagram found for "+txtWord.getText()+"\n");
					//nae.printStackTrace();
				}
			}
		});
		contentPane.add(btnGo);
		
		result = new ResultWindow();
		result.setLocation(this.getLocation().x, this.getLocation().y+this.getSize().height);
		result.setVisible(true);
		
		wl = new Wordlist();
		wl.loadWordlist(Wordlist.class.getResourceAsStream("sr_lat_only.wl"));
		result.addMessage("Simple Anagram v0.1b\n");
		result.addMessage("by Djordje Stojanovic (nighthawk)\n");
		result.addMessage("- Loaded "+wl.size()+" words.\n");
	}
}
