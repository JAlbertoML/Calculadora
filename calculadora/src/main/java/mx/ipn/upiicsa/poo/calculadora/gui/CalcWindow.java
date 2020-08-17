package mx.ipn.upiicsa.poo.calculadora.gui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import mx.ipn.upiicsa.poo.calculadora.bs.Calculator;
import mx.ipn.upiicsa.poo.calculadora.exceptions.MalformedExpressionException;
import mx.ipn.upiicsa.poo.calculadora.exceptions.SyntaxErrorException;

public class CalcWindow extends JFrame {
	private static final long serialVersionUID = 1L;

	/*
	 * The point is not a number, but, in this program, it behaves as if it were
	 * 
	 * 'clear display' and 'equals to' buttons are not operators but I don't know
	 * where put it
	 * 
	 */

	// Defining constants for numbers
	private static final Integer ZERO = 0;
	private static final Integer ONE = 1;
	private static final Integer TWO = 2;
	private static final Integer THREE = 3;
	private static final Integer FOUR = 4;
	private static final Integer FIVE = 5;
	private static final Integer SIX = 6;
	private static final Integer SEVEN = 7;
	private static final Integer EIGHT = 8;
	private static final Integer NINE = 9;
	private static final Double EULER = Math.E;
	private static final Double PI = Math.PI;
	private static final String POINT = ".";
	private static final String EMPTY_STRING = "";

	// Defining constants for positions in array of basic number buttons
	private static final Integer ZERO_BTN = 0;
	private static final Integer ONE_BTN = 1;
	private static final Integer TWO_BTN = 2;
	private static final Integer THREE_BTN = 3;
	private static final Integer FOUR_BTN = 4;
	private static final Integer FIVE_BTN = 5;
	private static final Integer SIX_BTN = 6;
	private static final Integer SEVEN_BTN = 7;
	private static final Integer EIGHT_BTN = 8;
	private static final Integer NINE_BTN = 9;
	private static final Integer POINT_BTN = 10;

	// Defining constants for positions in array of scientific number buttons
	private static final Integer EULER_BTN = 0;
	private static final Integer PI_BTN = 1;

	// Defining constants for positions in array of basic operator buttons
	private static final Integer PERCENTAGE_BTN = 0;
	private static final Integer DIVISION_BTN = 1;
	private static final Integer MULTIPLICATION_BTN = 2;
	private static final Integer ADDITION_BTN = 3;
	private static final Integer SUBTRACTION_BTN = 4;
	private static final Integer EQUALS_TO_BTN = 5;
	private static final Integer CLEAN_DISPLAY_BTN = 6;

	// Defining constants for positions in array of scientific operator buttons
	private static final Integer SECOND_POWER_BTN = 0;
	private static final Integer THIRD_POWER_BTN = 1;
	private static final Integer N_POWER_BTN = 2;
	private static final Integer LOGARITHM_TEN_BASE_BTN = 3;
	private static final Integer SQUARE_ROOT_BTN = 4;
	private static final Integer CUBE_ROOT_BTN = 5;
	private static final Integer N_ROOT_BTN = 6;
	private static final Integer NATURAL_LOGARITHM_BTN = 7;
	private static final Integer SIN_BTN = 8;
	private static final Integer COS_BTN = 9;
	private static final Integer TAN_BTN = 10;
	private static final Integer EULER_TO_A_VARIABLE_POWER_BTN = 11;
	private static final Integer INVERSE_BTN = 12;
	private static final Integer FACTORIAL_BTN = 13;

	// Defining types of listeners
	private static final int NUMBER = 0;
	private static final int CLEAN = 1;
	private static final int OPERATOR = 2;
	private static final int EQUALSTO = 3;

	// Creating buttons
	private JTextField display;
	private JButton[] basicNumberButtons = new JButton[] { new JButton(new ImageIcon("resources/zero.png")),
			new JButton(new ImageIcon("resources/one.png")), new JButton(new ImageIcon("resources/two.png")),
			new JButton(new ImageIcon("resources/three.png")), new JButton(new ImageIcon("resources/four.png")),
			new JButton(new ImageIcon("resources/five.png")), new JButton(new ImageIcon("resources/six.png")),
			new JButton(new ImageIcon("resources/seven.png")), new JButton(new ImageIcon("resources/eight.png")),
			new JButton(new ImageIcon("resources/nine.png")), new JButton(new ImageIcon("resources/point.png")) };

	// Creating scientific number buttons
	private JButton[] scientificNumberButtons = new JButton[] { new JButton(new ImageIcon("resources/e.png")),
			new JButton(new ImageIcon("resources/pi.png")) };

	// Creating basic operator buttons
	private JButton[] basicOperatorButtons = new JButton[] { new JButton(new ImageIcon("resources/percentage.png")),
			new JButton(new ImageIcon("resources/division.png")),
			new JButton(new ImageIcon("resources/multiplication.png")),
			new JButton(new ImageIcon("resources/addition.png")),
			new JButton(new ImageIcon("resources/subtraction.png")),
			new JButton(new ImageIcon("resources/equalsTo.png")),
			new JButton(new ImageIcon("resources/clearDisplay.png")) };

	// Creating scientific operator buttons
	private JButton[] scientificOperatorButtons = new JButton[] {
			new JButton(new ImageIcon("resources/secondPower.png")),
			new JButton(new ImageIcon("resources/thirdPower.png")), new JButton(new ImageIcon("resources/nPower.png")),
			new JButton(new ImageIcon("resources/baseTenLogarithm.png")),
			new JButton(new ImageIcon("resources/squareRoot.png")),
			new JButton(new ImageIcon("resources/cubeRoot.png")), new JButton(new ImageIcon("resources/nRoot.png")),
			new JButton(new ImageIcon("resources/naturalLogarithm.png")),
			new JButton(new ImageIcon("resources/sin.png")), new JButton(new ImageIcon("resources/cos.png")),
			new JButton(new ImageIcon("resources/tan.png")), new JButton(new ImageIcon("resources/eToPower.png")),
			new JButton(new ImageIcon("resources/oneOnX.png")), new JButton(new ImageIcon("resources/factorial.png")) };

	// Defining container and grid
	private Container pane;
	private GridBagLayout calcGrid;
	private GridBagConstraints calcGridConstraints;

	// Defining Colors
	private Color backgroundColor;
	private Color numbersColor;
	private Color basicOperatorsColor;
	private Color scientificColor;

	// Defining Fonts
	private Font displayFont;

	// Defining parts of the operation
	private Double firstAmount = null;
	private Double secondAmount = null;
	private Double result = null;
	private String resultString = null;
	private int operator;

	// Instantiating calculator for functionality
	private Calculator calculator = new Calculator();

	public CalcWindow(Integer option) {
		calculator.setState(Calculator.STATE_INIT);
		System.out.println("--> ESTADO: " + calculator.getState());
		initComponents(option);
	}

	private void initComponents(Integer option) {
		// Calculator interface
		instantiateCalculatorComponents();
		setupCalculatorComponents();
		buildWindow(option);
		setFocusable(true);
		initializeKeyListeners();

		// Calculator functionality
		addActionListeners();

		setVisible(true);
	}

	private void initializeKeyListeners() {
		this.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				char key = e.getKeyChar();
				switch (key) {
				case '0':
					basicNumberButtons[ZERO_BTN].doClick();
					break;
				case KeyEvent.VK_1:
					basicNumberButtons[ONE_BTN].doClick();
					break;
				case KeyEvent.VK_2:
					basicNumberButtons[TWO_BTN].doClick();
					break;
				case KeyEvent.VK_3:
					basicNumberButtons[THREE_BTN].doClick();
					break;
				case KeyEvent.VK_4:
					basicNumberButtons[FOUR_BTN].doClick();
					break;
				case KeyEvent.VK_5:
					basicNumberButtons[FIVE_BTN].doClick();
					break;
				case KeyEvent.VK_6:
					basicNumberButtons[SIX_BTN].doClick();
					break;
				case KeyEvent.VK_7:
					basicNumberButtons[SEVEN_BTN].doClick();
					break;
				case KeyEvent.VK_8:
					basicNumberButtons[EIGHT_BTN].doClick();
					break;
				case KeyEvent.VK_9:
					basicNumberButtons[NINE_BTN].doClick();
					break;
				case KeyEvent.VK_PERIOD:
					basicNumberButtons[POINT_BTN].doClick();
					break;
				case KeyEvent.VK_ENTER:
					basicOperatorButtons[EQUALS_TO_BTN].doClick();
					/*calculator.updateState(Calculator.ACTION_PRESS_EQUALSTO);
					updateDisplay("");*/
					System.out.println("--> ESTADO: " + calculator.getState());
					break;
				case '+':
					basicOperatorButtons[ADDITION_BTN].doClick();
					break;
				case KeyEvent.VK_MINUS:
					basicOperatorButtons[SUBTRACTION_BTN].doClick();
					break;
				case KeyEvent.VK_SLASH:
					basicOperatorButtons[DIVISION_BTN].doClick();
					break;
				case '*':
					basicOperatorButtons[MULTIPLICATION_BTN].doClick();
					break;
				case '%':
					basicOperatorButtons[PERCENTAGE_BTN].doClick();
					break;
				case KeyEvent.VK_BACK_SPACE:
					basicOperatorButtons[CLEAN_DISPLAY_BTN].doClick();
					break;
				case 'C':
					SelectCalculatorWindow selectCalculatorWindow = new SelectCalculatorWindow();
					setVisible(false);
					selectCalculatorWindow.setVisible(true);
					break;
				default:
					try {
						throw new SyntaxErrorException();
					} catch (SyntaxErrorException e1) {
						resultString = "SYNTAX ERROR";
						calculator.updateState(Calculator.ACTION_PRESS_CLEAN_DISPLAY);
					} finally {
						display.setText(resultString);
						calculator.updateState(Calculator.STATE_CAPTURING);
					}
					break;
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {

			}
		});
	}

	private void instantiateCalculatorComponents() {
		// Instantiating and Setting up display
		display = new JTextField("");

		// Creating grid to add components
		calcGrid = new GridBagLayout();

		// Creating grid constraints
		calcGridConstraints = new GridBagConstraints();

		// Creating pane
		pane = getContentPane();

		// Creating colors
		backgroundColor = new Color(12, 14, 20);
		numbersColor = new Color(22, 15, 37);
		basicOperatorsColor = new Color(137, 10, 146);
		scientificColor = new Color(15, 17, 26);

		// Creating fonts
		displayFont = new Font("Free Mono", Font.PLAIN, 30);
	}

	private void buildWindow(Integer option) {
		// setting up window
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		if (option == SelectCalculatorWindow.BASIC_OPTION) {
			setTitle("Basic Calculator");
			setSize(240, 380);
		} else if (option == SelectCalculatorWindow.PSEUDOSCIENTIFIC_OPTION) {
			setTitle("Pseudoscientific Calculator");
			setSize(464, 380);
		}
		setResizable(false);
		setLocationRelativeTo(null);

		// Adding display and buttons
		addDisplay(calcGridConstraints, pane, option);
		addButtons(calcGridConstraints, pane, option);
	}

	private ActionListener initializeActionListeners(int type, String content) {
		ActionListener listener = null;
		switch (type) {
		case NUMBER:
			listener = new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					showNumberOnDisplay(content);
				}
			};
			break;
		case CLEAN:
			listener = new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					cleanDisplay();
				}
			};
			break;
		case OPERATOR:
			listener = new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					captureOperator(Integer.parseInt(content));
				}
			};
			break;
		case EQUALSTO:
			listener = new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					calculator.updateState(Calculator.ACTION_PRESS_EQUALSTO);
					updateDisplay("");
					System.out.println("--> ESTADO: " + calculator.getState());
				}
			};
			break;
		}
		return listener;
	}

	private void addActionListeners() {
		// Number listeners
		basicNumberButtons[ZERO_BTN].addActionListener(initializeActionListeners(NUMBER, ZERO.toString()));
		basicNumberButtons[ONE_BTN].addActionListener(initializeActionListeners(NUMBER, ONE.toString()));
		basicNumberButtons[TWO_BTN].addActionListener(initializeActionListeners(NUMBER, TWO.toString()));
		basicNumberButtons[THREE_BTN].addActionListener(initializeActionListeners(NUMBER, THREE.toString()));
		basicNumberButtons[FOUR_BTN].addActionListener(initializeActionListeners(NUMBER, FOUR.toString()));
		basicNumberButtons[FIVE_BTN].addActionListener(initializeActionListeners(NUMBER, FIVE.toString()));
		basicNumberButtons[SIX_BTN].addActionListener(initializeActionListeners(NUMBER, SIX.toString()));
		basicNumberButtons[SEVEN_BTN].addActionListener(initializeActionListeners(NUMBER, SEVEN.toString()));
		basicNumberButtons[EIGHT_BTN].addActionListener(initializeActionListeners(NUMBER, EIGHT.toString()));
		basicNumberButtons[NINE_BTN].addActionListener(initializeActionListeners(NUMBER, NINE.toString()));
		basicNumberButtons[POINT_BTN].addActionListener(initializeActionListeners(NUMBER, POINT));
		scientificNumberButtons[PI_BTN].addActionListener(initializeActionListeners(NUMBER, PI.toString()));
		scientificNumberButtons[EULER_BTN].addActionListener(initializeActionListeners(NUMBER, EULER.toString()));

		// Basic operators listeners
		basicOperatorButtons[CLEAN_DISPLAY_BTN].addActionListener(initializeActionListeners(CLEAN, null));
		basicOperatorButtons[ADDITION_BTN]
				.addActionListener(initializeActionListeners(OPERATOR, Integer.toString(Calculator.ADDING_OPERATOR)));
		basicOperatorButtons[SUBTRACTION_BTN]
				.addActionListener(initializeActionListeners(OPERATOR, Integer.toString(Calculator.SUBTRACT_OPERATOR)));
		basicOperatorButtons[MULTIPLICATION_BTN].addActionListener(
				initializeActionListeners(OPERATOR, Integer.toString(Calculator.MULTIPLICATION_OPERATOR)));
		basicOperatorButtons[DIVISION_BTN]
				.addActionListener(initializeActionListeners(OPERATOR, Integer.toString(Calculator.DIVISION_OPERATOR)));
		basicOperatorButtons[PERCENTAGE_BTN].addActionListener(
				initializeActionListeners(OPERATOR, Integer.toString(Calculator.PERCENTAGE_OPERATOR)));

		// Scientific operators listeners
		scientificOperatorButtons[SECOND_POWER_BTN].addActionListener(
				initializeActionListeners(OPERATOR, Integer.toString(Calculator.SECOND_POWER_OPERATOR)));
		scientificOperatorButtons[THIRD_POWER_BTN].addActionListener(
				initializeActionListeners(OPERATOR, Integer.toString(Calculator.THIRD_POWER_OPERATOR)));
		scientificOperatorButtons[N_POWER_BTN]
				.addActionListener(initializeActionListeners(OPERATOR, Integer.toString(Calculator.N_POWER_OPERATOR)));
		scientificOperatorButtons[SQUARE_ROOT_BTN].addActionListener(
				initializeActionListeners(OPERATOR, Integer.toString(Calculator.SQUARE_ROOT_OPERATOR)));
		scientificOperatorButtons[CUBE_ROOT_BTN].addActionListener(
				initializeActionListeners(OPERATOR, Integer.toString(Calculator.CUBE_ROOT_OPERATOR)));
		scientificOperatorButtons[N_ROOT_BTN]
				.addActionListener(initializeActionListeners(OPERATOR, Integer.toString(Calculator.N_ROOT_OPERATOR)));
		scientificOperatorButtons[SIN_BTN]
				.addActionListener(initializeActionListeners(OPERATOR, Integer.toString(Calculator.SIN_OPERATOR)));
		scientificOperatorButtons[COS_BTN]
				.addActionListener(initializeActionListeners(OPERATOR, Integer.toString(Calculator.COS_OPERATOR)));
		scientificOperatorButtons[TAN_BTN]
				.addActionListener(initializeActionListeners(OPERATOR, Integer.toString(Calculator.TAN_OPERATOR)));
		scientificOperatorButtons[LOGARITHM_TEN_BASE_BTN].addActionListener(
				initializeActionListeners(OPERATOR, Integer.toString(Calculator.BASE_TEN_LOGARITHM_OPERATOR)));
		scientificOperatorButtons[NATURAL_LOGARITHM_BTN].addActionListener(
				initializeActionListeners(OPERATOR, Integer.toString(Calculator.NATURAL_LOGARITHM_OPERATOR)));
		scientificOperatorButtons[INVERSE_BTN]
				.addActionListener(initializeActionListeners(OPERATOR, Integer.toString(Calculator.INVERSE_OPERATOR)));
		scientificOperatorButtons[FACTORIAL_BTN].addActionListener(
				initializeActionListeners(OPERATOR, Integer.toString(Calculator.FACTORIAL_OPERATOR)));
		scientificOperatorButtons[EULER_TO_A_VARIABLE_POWER_BTN].addActionListener(
				initializeActionListeners(OPERATOR, Integer.toString(Calculator.EULER_TO_A_VARIABLE_POWER_OPERATOR)));

		// Equals to listener
		basicOperatorButtons[EQUALS_TO_BTN].addActionListener(initializeActionListeners(EQUALSTO, null));
	}

	private void addDisplay(GridBagConstraints calcGridConstraints, Container pane, Integer option) {
		// Adding the calculator display
		calcGridConstraints.gridx = 0;
		calcGridConstraints.gridy = 0;
		calcGridConstraints.gridwidth = 8;
		calcGridConstraints.ipady = 20;
		pane.add(display, calcGridConstraints);
	}

	private void addButtons(GridBagConstraints calcGridConstraints, Container pane, Integer option) {
		calcGridConstraints.ipady = 40;
		addFirstRowOfButtons(calcGridConstraints, pane, option);
		addSecondRowOfButtons(calcGridConstraints, pane, option);
		addThirdRowOfButtons(calcGridConstraints, pane, option);
		addFourthRowOfButtons(calcGridConstraints, pane, option);
		addFifthRowOfButtons(calcGridConstraints, pane, option);
	}

	private void setupCalculatorComponents() {
		setupDisplay();
		setupPane();
		setupGrid();
		setupButtons();
	}

	private void setupButtons() {
		// Setting up number buttons
		for (int i = 0; i < basicNumberButtons.length; i++) {
			basicNumberButtons[i].setBackground(numbersColor);
			basicNumberButtons[i].setBorderPainted(false);
			basicNumberButtons[i].setMargin(new Insets(-21, -21, -21, -21));
			basicNumberButtons[i].setFocusable(false);
		}

		// Setting up basic operations buttons
		for (int i = 0; i < basicOperatorButtons.length; i++) {
			basicOperatorButtons[i].setBackground(basicOperatorsColor);
			basicOperatorButtons[i].setBorderPainted(false);
			basicOperatorButtons[i].setMargin(new Insets(-21, -21, -21, -21));
			basicOperatorButtons[i].setFocusable(false);
		}

		// Setting up scientific part
		for (int i = 0; i < scientificNumberButtons.length; i++) {
			scientificNumberButtons[i].setBackground(numbersColor);
			scientificNumberButtons[i].setBorderPainted(false);
			scientificNumberButtons[i].setMargin(new Insets(-21, -21, -21, -21));
			scientificNumberButtons[i].setFocusable(false);
		}
		for (int i = 0; i < scientificOperatorButtons.length; i++) {
			scientificOperatorButtons[i].setBackground(scientificColor);
			scientificOperatorButtons[i].setBorderPainted(false);
			scientificOperatorButtons[i].setMargin(new Insets(-21, -21, -21, -21));
			scientificOperatorButtons[i].setFocusable(false);
		}
	}

	private void setupGrid() {
		// Setting up the initial grid constraints
		calcGridConstraints.fill = GridBagConstraints.HORIZONTAL;
		calcGridConstraints.ipadx = 40;
		calcGridConstraints.insets = new Insets(1, 1, 1, 1);
	}

	private void setupPane() {
		// Setting up Pane
		pane.setLayout(calcGrid);
		pane.setBackground(backgroundColor);
	}

	private void setupDisplay() {
		// Setting up Display
		display.setEditable(false);
		display.setHorizontalAlignment(JTextField.RIGHT);
		display.setFont(displayFont);
		display.setBackground(backgroundColor);
		display.setForeground(Color.WHITE);
		display.setBorder(null);
		display.setFocusable(false);
	}

	private void updateDisplay(String amount) {
		if (calculator.getState() == Calculator.STATE_INIT) {
			firstAmount = null;
			secondAmount = null;
			result = null;
			setOperator(0);
			display.setText(amount);
		} else if (calculator.getState() == Calculator.STATE_CAPTURING) {
			/*
			 * When state is capturing, display shows the concatenation of pressed number
			 * buttons
			 */
			String actualAmount = display.getText();
			display.setText(actualAmount + amount);
		} else if (calculator.getState() == Calculator.STATE_CAPTURING_OPERATOR) {
			/*
			 * When state is capturing an operator, we save the first amount and show the
			 * second amount pressed
			 */
			firstAmount = Double.parseDouble(display.getText());
			display.setText(amount);
		} else if (calculator.getState() == Calculator.STATE_CALCULATING) {
			/*
			 * When user press equals to button we save the second amount, do the operation
			 * and show result on display
			 */
			if (display.getText().isEmpty()) {
				secondAmount = null;
			} else {
				secondAmount = Double.parseDouble(display.getText());
			}

			try {
				result = calculator.doAnOperation(operator, firstAmount, secondAmount);
				resultString = result.toString();
			} catch (SyntaxErrorException e) {
				resultString = "SYNTAX ERROR";
				calculator.updateState(Calculator.ACTION_PRESS_CLEAN_DISPLAY);
			} catch (MalformedExpressionException e) {
				resultString = "MALFORMED EXPRESSION";
				calculator.updateState(Calculator.ACTION_PRESS_CLEAN_DISPLAY);
			} finally {
				display.setText(resultString);
				calculator.updateState(Calculator.STATE_CAPTURING);
			}
			setOperator(-1);
		}
	}

	private void cleanDisplay() {
		display.setText(EMPTY_STRING);
		calculator.updateState(Calculator.ACTION_PRESS_CLEAN_DISPLAY);
	}

	private void showNumberOnDisplay(String number) {
		updateDisplay(number);
		calculator.updateState(Calculator.ACTION_PRESS_NUMBER);
		System.out.println("--> ESTADO: " + calculator.getState());
		
	}

	private void captureOperator(int operator) {
		setOperator(operator);
		if (calculator.getscientificOperators().contains(getOperator())) {
			calculator.updateState(Calculator.ACTION_PRESS_MIDLE_OPERATOR);
			updateDisplay("");
		} else {
			calculator.updateState(Calculator.ACTION_PRESS_OPERATOR);
		}

		System.out.println("--> ESTADO: " + calculator.getState());
	}

	private void addFirstRowOfButtons(GridBagConstraints calcGridConstraints, Container pane, Integer option) {
		// Setting up the grid constraints for the first row of buttons
		calcGridConstraints.gridy = 1;
		calcGridConstraints.gridwidth = 1;

		// Adding clean button
		calcGridConstraints.gridx = 4;
		pane.add(basicOperatorButtons[CLEAN_DISPLAY_BTN], calcGridConstraints);

		// Adding percent button
		calcGridConstraints.gridx = 5;
		pane.add(basicOperatorButtons[PERCENTAGE_BTN], calcGridConstraints);

		// Adding division button
		calcGridConstraints.gridx = 6;
		pane.add(basicOperatorButtons[DIVISION_BTN], calcGridConstraints);

		// Adding multiplication button
		calcGridConstraints.gridx = 7;
		pane.add(basicOperatorButtons[MULTIPLICATION_BTN], calcGridConstraints);

	}

	private void addSecondRowOfButtons(GridBagConstraints calcGridConstraints, Container pane, Integer option) {
		// Setting up the grid constraints for the second row of buttons
		calcGridConstraints.gridy = 2;

		if (option == SelectCalculatorWindow.PSEUDOSCIENTIFIC_OPTION) {
			// Adding second power button
			calcGridConstraints.gridx = 0;
			pane.add(scientificOperatorButtons[SECOND_POWER_BTN], calcGridConstraints);

			// Adding third power button
			calcGridConstraints.gridx = 1;
			pane.add(scientificOperatorButtons[THIRD_POWER_BTN], calcGridConstraints);

			// Adding variable power button
			calcGridConstraints.gridx = 2;
			pane.add(scientificOperatorButtons[N_POWER_BTN], calcGridConstraints);

			// Adding log 10 button
			calcGridConstraints.gridx = 3;
			pane.add(scientificOperatorButtons[LOGARITHM_TEN_BASE_BTN], calcGridConstraints);
		}

		// Adding number seven button
		calcGridConstraints.gridx = 4;
		pane.add(basicNumberButtons[SEVEN_BTN], calcGridConstraints);

		// Adding number eight button
		calcGridConstraints.gridx = 5;
		pane.add(basicNumberButtons[EIGHT_BTN], calcGridConstraints);

		// Adding number nine button
		calcGridConstraints.gridx = 6;
		pane.add(basicNumberButtons[NINE_BTN], calcGridConstraints);

		// Adding Addition button
		calcGridConstraints.gridx = 7;
		pane.add(basicOperatorButtons[ADDITION_BTN], calcGridConstraints);

	}

	private void addThirdRowOfButtons(GridBagConstraints calcGridConstraints, Container pane, Integer option) {
		// Setting up the grid constraints for the third row of buttons
		calcGridConstraints.gridy = 3;

		if (option == SelectCalculatorWindow.PSEUDOSCIENTIFIC_OPTION) {
			// Adding square root button
			calcGridConstraints.gridx = 0;
			pane.add(scientificOperatorButtons[SQUARE_ROOT_BTN], calcGridConstraints);

			// Adding cube root button
			calcGridConstraints.gridx = 1;
			pane.add(scientificOperatorButtons[CUBE_ROOT_BTN], calcGridConstraints);

			// Adding variable root button
			calcGridConstraints.gridx = 2;
			pane.add(scientificOperatorButtons[N_ROOT_BTN], calcGridConstraints);

			// Adding natural logarithm button
			calcGridConstraints.gridx = 3;
			pane.add(scientificOperatorButtons[NATURAL_LOGARITHM_BTN], calcGridConstraints);
		}

		// Adding number four button
		calcGridConstraints.gridx = 4;
		pane.add(basicNumberButtons[FOUR_BTN], calcGridConstraints);

		// Adding number five button
		calcGridConstraints.gridx = 5;
		pane.add(basicNumberButtons[FIVE_BTN], calcGridConstraints);

		// Adding number six button
		calcGridConstraints.gridx = 6;
		pane.add(basicNumberButtons[SIX_BTN], calcGridConstraints);

		// Adding subtraction button
		calcGridConstraints.gridx = 7;
		pane.add(basicOperatorButtons[SUBTRACTION_BTN], calcGridConstraints);
	}

	private void addFourthRowOfButtons(GridBagConstraints calcGridConstraints, Container pane, Integer option) {
		// Setting up the grid constraints for the fourth row of buttons
		calcGridConstraints.gridy = 4;

		if (option == SelectCalculatorWindow.PSEUDOSCIENTIFIC_OPTION) {
			// Adding sin button
			calcGridConstraints.gridx = 0;
			pane.add(scientificOperatorButtons[SIN_BTN], calcGridConstraints);

			// Adding cos button
			calcGridConstraints.gridx = 1;
			pane.add(scientificOperatorButtons[COS_BTN], calcGridConstraints);

			// Adding tan button
			calcGridConstraints.gridx = 2;
			pane.add(scientificOperatorButtons[TAN_BTN], calcGridConstraints);

			// Adding pi button
			calcGridConstraints.gridx = 3;
			pane.add(scientificNumberButtons[PI_BTN], calcGridConstraints);
		}

		// Adding number one button
		calcGridConstraints.gridx = 4;
		pane.add(basicNumberButtons[ONE_BTN], calcGridConstraints);

		// Adding number two button
		calcGridConstraints.gridx = 5;
		pane.add(basicNumberButtons[TWO_BTN], calcGridConstraints);

		// Adding number three button
		calcGridConstraints.gridx = 6;
		pane.add(basicNumberButtons[THREE_BTN], calcGridConstraints);

		// Adding equals button
		calcGridConstraints.gridx = 7;
		calcGridConstraints.gridheight = 2;
		calcGridConstraints.ipady = 96;
		pane.add(basicOperatorButtons[EQUALS_TO_BTN], calcGridConstraints);
	}

	private void addFifthRowOfButtons(GridBagConstraints calcGridConstraints, Container pane, Integer option) {
		// Setting up the grid constraints for the fifth row of buttons
		calcGridConstraints.gridy = 5;
		calcGridConstraints.gridheight = 1;
		calcGridConstraints.ipady = 40;

		if (option == SelectCalculatorWindow.PSEUDOSCIENTIFIC_OPTION) {
			// Adding one on x button
			calcGridConstraints.gridx = 0;
			pane.add(scientificOperatorButtons[INVERSE_BTN], calcGridConstraints);

			// Adding factorial button
			calcGridConstraints.gridx = 1;
			pane.add(scientificOperatorButtons[FACTORIAL_BTN], calcGridConstraints);

			// Adding Euler to exponent button
			calcGridConstraints.gridx = 2;
			pane.add(scientificOperatorButtons[EULER_TO_A_VARIABLE_POWER_BTN], calcGridConstraints);

			// Adding Euler button
			calcGridConstraints.gridx = 3;
			pane.add(scientificNumberButtons[EULER_BTN], calcGridConstraints);
		}

		// Adding number zero button
		calcGridConstraints.gridx = 4;
		calcGridConstraints.gridwidth = 2;
		pane.add(basicNumberButtons[ZERO_BTN], calcGridConstraints);

		// Adding point button
		calcGridConstraints.gridx = 6;
		calcGridConstraints.gridwidth = 1;
		pane.add(basicNumberButtons[POINT_BTN], calcGridConstraints);
	}

	public int getOperator() {
		return operator;
	}

	public void setOperator(int operator) {
		this.operator = operator;
	}

}
