package mx.ipn.upiicsa.poo.calculadora.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class SelectCalculatorWindow extends JFrame {
	private static final long serialVersionUID = 1L;

	private static final Integer WIDTH = 300;
	private static final Integer HEIGHT = 220;

	public static final Integer BASIC_OPTION = 0;
	public static final Integer PSEUDOSCIENTIFIC_OPTION = 1;

	// Defining components
	private JRadioButton basicCalculatorRbtn;
	private JRadioButton pseudoscientificCalculatorRbtn;
	private JPanel panel;
	private JLabel titleLbl;
	private JLabel noteLbl;
	private JButton acceptBtn;
	private ButtonGroup groupOfRadioButtons;

	// Defining colors
	private Color backgroundColor = new Color(12, 14, 20);
	private Color textColor = new Color(255, 255, 255);
	private Color buttonColor = new Color(137, 10, 146);

	// Defining fonts
	private Font titleFont = new Font("Dialog", Font.BOLD, 18);
	private Font noteFont = new Font("Dialog", Font.PLAIN, 12);

	public SelectCalculatorWindow() {
		initComponents();
		initializeListeners();
	}

	private void initComponents() {
		setTitle("Calculator");
		setSize(WIDTH, HEIGHT);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setFocusable(true);
		instantiateComponents();
		addComponentsToPanel();
		setupComponents();
	}

	private void instantiateComponents() {
		panel = (JPanel) getContentPane();
		basicCalculatorRbtn = new JRadioButton("Basic Calculator");
		pseudoscientificCalculatorRbtn = new JRadioButton("Pseudoscientific Calculator");
		titleLbl = new JLabel("Please, select a calculator");
		noteLbl = new JLabel(
				"<html><b>Note:</b> When you are in the calculator window,<br>you can press <b>Shift + C</b> to change the view.</html>");
		groupOfRadioButtons = new ButtonGroup();
		acceptBtn = new JButton("Accept");
	}

	private void addComponentsToPanel() {
		panel.add(titleLbl);
		groupRadioButtons();
		panel.add(basicCalculatorRbtn);
		panel.add(pseudoscientificCalculatorRbtn);
		panel.add(acceptBtn);
		panel.add(noteLbl);
	}

	private void groupRadioButtons() {
		groupOfRadioButtons.add(basicCalculatorRbtn);
		groupOfRadioButtons.add(pseudoscientificCalculatorRbtn);
	}

	private void setupComponents() {
		// Panel
		panel.setBackground(backgroundColor);
		panel.setLayout(null);
		panel.setFocusable(false);

		// Title label
		titleLbl.setFont(titleFont);
		titleLbl.setForeground(textColor);
		Dimension titleSize = titleLbl.getPreferredSize();
		titleLbl.setBounds((WIDTH - titleSize.width) / 2, 5, titleSize.width, titleSize.height);
		titleLbl.setFocusable(false);

		// Note label
		noteLbl.setFont(noteFont);
		noteLbl.setForeground(textColor);
		Dimension noteSize = noteLbl.getPreferredSize();
		noteLbl.setBounds((WIDTH - noteSize.width) / 2, 140, noteSize.width, noteSize.height);
		noteLbl.setFocusable(false);

		// Basic Calculator RadioButton
		basicCalculatorRbtn.setBackground(backgroundColor);
		basicCalculatorRbtn.setForeground(textColor);
		Dimension basicOptionSize = basicCalculatorRbtn.getPreferredSize();
		basicCalculatorRbtn.setBounds(30, 40, basicOptionSize.width, basicOptionSize.height);
		basicCalculatorRbtn.setSelected(true);
		basicCalculatorRbtn.setFocusable(false);

		// Pseudoscientific calculator Radio Button
		pseudoscientificCalculatorRbtn.setBackground(backgroundColor);
		pseudoscientificCalculatorRbtn.setForeground(textColor);
		Dimension pseudoscientificOptionSize = pseudoscientificCalculatorRbtn.getPreferredSize();
		pseudoscientificCalculatorRbtn.setBounds(30, 60, pseudoscientificOptionSize.width,
				pseudoscientificOptionSize.height);
		pseudoscientificCalculatorRbtn.setFocusable(false);

		// Accept Button
		acceptBtn.setBackground(buttonColor);
		acceptBtn.setForeground(textColor);
		Dimension buttonSize = acceptBtn.getPreferredSize();
		acceptBtn.setBounds((WIDTH - buttonSize.width) / 2, 95, buttonSize.width, buttonSize.height);
		acceptBtn.setFocusable(false);
	}

	private void initializeListeners() {
		acceptBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				@SuppressWarnings("unused")
				CalcWindow calcWindow;
				setVisible(false);
				if (basicCalculatorRbtn.isSelected()) {
					calcWindow = new CalcWindow(BASIC_OPTION);
				} else if (pseudoscientificCalculatorRbtn.isSelected()) {
					calcWindow = new CalcWindow(PSEUDOSCIENTIFIC_OPTION);
				}
			}
		});

		this.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyChar() == KeyEvent.VK_ENTER) {
					@SuppressWarnings("unused")
					CalcWindow calcWindow;
					setVisible(false);
					if (basicCalculatorRbtn.isSelected()) {
						calcWindow = new CalcWindow(BASIC_OPTION);
					} else if (pseudoscientificCalculatorRbtn.isSelected()) {
						calcWindow = new CalcWindow(PSEUDOSCIENTIFIC_OPTION);
					}
				}
			}

			@Override
			public void keyTyped(KeyEvent e) {

			}

			@Override
			public void keyReleased(KeyEvent e) {

			}
		});
	}
}
