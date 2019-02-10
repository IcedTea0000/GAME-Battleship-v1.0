package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import bin.GameProcessForGUI;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextArea;
import java.awt.ScrollPane;
import javax.swing.JScrollBar;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BattleshipGUI extends JFrame {

	private JPanel contentPane;
	private static JTextArea textArea;
	private JTextField tfRow;
	private JTextField tfCol;
	private static int xInput, yInput;
	private static GameProcessForGUI game = new GameProcessForGUI();

	// Launch the application
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BattleshipGUI frame = new BattleshipGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
				game.introPhase(textArea);
			}
		});
	}

	// Create the frame
	public BattleshipGUI() {
		setTitle("BATTLESHIP V1.0");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		textArea = new JTextArea();
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 20));
		textArea.setEditable(false);
		textArea.setWrapStyleWord(true);
		textArea.setLineWrap(true);

		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		JLabel lblRow = new JLabel("Row (1-6)");
		lblRow.setFont(new Font("Tahoma", Font.BOLD, 20));

		JLabel lblColumn = new JLabel("Column (1-6)");
		lblColumn.setFont(new Font("Tahoma", Font.BOLD, 20));

		tfRow = new JTextField();
		tfRow.setFont(new Font("Tahoma", Font.PLAIN, 20));
		tfRow.setColumns(10);

		tfCol = new JTextField();
		tfCol.setFont(new Font("Tahoma", Font.PLAIN, 20));
		tfCol.setColumns(10);

		JButton btnEnter = new JButton("Enter");
		btnEnter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					xInput = Integer.parseInt(tfRow.getText());
					yInput = Integer.parseInt(tfCol.getText());
					if ((xInput < 1) || (xInput > 6) || (yInput < 1) || (yInput > 6)) {
						JOptionPane.showMessageDialog(null, "Wrong Input. Only number from 1-6.", "Error",
								JOptionPane.ERROR_MESSAGE);
						tfCol.setText("");
						tfRow.setText("");
					} else {
						game.shootingPhase(textArea, xInput, yInput);
						if (game.setup.shipList.isEmpty())
							game.resultPhase(textArea);
					}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Wrong Input. Enter number from 1-6.", "Error",
							JOptionPane.ERROR_MESSAGE);
					tfCol.setText("");
					tfRow.setText("");
				}
			}
		});
		btnEnter.setFont(new Font("Tahoma", Font.BOLD, 20));

		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tfCol.setText("");
				tfRow.setText("");
			}
		});
		
		//Creat layout for frame
		btnReset.setFont(new Font("Tahoma", Font.BOLD, 20));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
				.createSequentialGroup()
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup().addComponent(lblColumn).addGap(18)
								.addComponent(tfCol, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addGap(18).addComponent(btnReset, GroupLayout.DEFAULT_SIZE, 406, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(lblRow, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)
								.addGap(27)
								.addComponent(tfRow, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addGap(18).addComponent(btnEnter, GroupLayout.DEFAULT_SIZE, 406, Short.MAX_VALUE)))
				.addContainerGap()).addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 768, Short.MAX_VALUE));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING).addGroup(gl_contentPane
				.createSequentialGroup().addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 334, Short.MAX_VALUE)
				.addGap(18)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblRow, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
						.addComponent(tfRow, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(btnEnter))
				.addGap(7)
				.addGroup(gl_contentPane
						.createParallelGroup(Alignment.BASELINE).addComponent(lblColumn).addComponent(tfCol,
								GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnReset))
				.addGap(9)));
		contentPane.setLayout(gl_contentPane);
	}
}
