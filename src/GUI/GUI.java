package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;

import javax.swing.JTextArea;


import imagesDir.ImagesCheck;

import java.awt.GridLayout;
import java.awt.Window;

import javax.swing.JComboBox;
import java.awt.Font;
import java.awt.GridBagLayout;
import javax.swing.SpringLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Locale;
import java.awt.event.ActionEvent;

public class GUI {

	private JFrame frame;
	ImagesCheck imagesCheck=new ImagesCheck();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500,300);
		frame.getContentPane().setLayout(null);
		
		JLabel pathLabel = new JLabel("Path:");
		pathLabel.setBounds(11, 16, 115, 33);
		frame.getContentPane().add(pathLabel);
		
		JTextArea path = new JTextArea();
		path.setBounds(77, 28, 266, 15);
		frame.getContentPane().add(path);
		
		JLabel bookLabel = new JLabel("Title:");
		bookLabel.setBounds(11, 64, 61, 33);
		frame.getContentPane().add(bookLabel);
		
		JTextArea book = new JTextArea();
		book.setBounds(87, 77, 72, 15);
		frame.getContentPane().add(book);
		
		JLabel minPageLabel = new JLabel("Min page");
		minPageLabel.setBounds(11, 126, 108, 33);
		frame.getContentPane().add(minPageLabel);
		
		JTextArea minPage = new JTextArea();
		minPage.setBounds(26, 169, 23, 15);
		frame.getContentPane().add(minPage);
		
		JLabel maxPageLabel = new JLabel("max page");
		maxPageLabel.setBounds(166, 126, 116, 33);
		frame.getContentPane().add(maxPageLabel);
		
		JTextArea maxPage = new JTextArea();
		maxPage.setBounds(176, 169, 23, 15);
		frame.getContentPane().add(maxPage);
		
		JButton generateReportButton = new JButton("Generate report");
		generateReportButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		generateReportButton.setBounds(298, 171, 144, 41);
		frame.getContentPane().add(generateReportButton);
		
		generateReportButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e){
				imagesCheck.generateReport(path.getText(), book.getText(), 
					Integer.parseInt(minPage.getText()), 
					Integer.parseInt(maxPage.getText()));
				JOptionPane.showMessageDialog(frame, "Done", "TITLE", 
						JOptionPane.INFORMATION_MESSAGE);
			}
		});
	}
}
