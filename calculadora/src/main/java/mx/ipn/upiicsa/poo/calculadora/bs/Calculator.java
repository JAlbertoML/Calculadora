package mx.ipn.upiicsa.poo.calculadora.bs;

import java.util.ArrayList;

import mx.ipn.upiicsa.poo.calculadora.exceptions.MalformedExpressionException;
import mx.ipn.upiicsa.poo.calculadora.exceptions.SyntaxErrorException;

public class Calculator {

	// Defining constants for calculator states
	public static final int STATE_INIT = 0;
	public static final int STATE_CAPTURING = 1;
	public static final int STATE_CAPTURING_OPERATOR = 2;
	public static final int STATE_CALCULATING = 3;
	public static final int STATE_CAPTURING_SCIENTIFIC_NUMBER = 4;

	public int state;
	public int beforeState;

	// Defining constants for user actions
	public static final int ACTION_PRESS_NUMBER = 0;
	public static final int ACTION_PRESS_OPERATOR = 1;
	public static final int ACTION_PRESS_EQUALSTO = 2;
	public static final int ACTION_PRESS_CLEAN_DISPLAY = 3;
	public static final int ACTION_PRESS_MIDLE_OPERATOR = 4;
	public static final int ACTION_PRESS_SCIENTIFIC_NUMBER = 5;

	// Defining constants for operators
	public static final int ADDING_OPERATOR = 0;
	public static final int SUBTRACT_OPERATOR = 1;
	public static final int MULTIPLICATION_OPERATOR = 2;
	public static final int DIVISION_OPERATOR = 3;
	public static final int PERCENTAGE_OPERATOR = 4;
	public static final int SECOND_POWER_OPERATOR = 5;
	public static final int THIRD_POWER_OPERATOR = 6;
	public static final int N_POWER_OPERATOR = 7;
	public static final int SQUARE_ROOT_OPERATOR = 8;
	public static final int CUBE_ROOT_OPERATOR = 9;
	public static final int N_ROOT_OPERATOR = 10;
	public static final int SIN_OPERATOR = 11;
	public static final int COS_OPERATOR = 12;
	public static final int TAN_OPERATOR = 13;
	public static final int BASE_TEN_LOGARITHM_OPERATOR = 14;
	public static final int NATURAL_LOGARITHM_OPERATOR = 15;
	public static final int INVERSE_OPERATOR = 16;
	public static final int FACTORIAL_OPERATOR = 17;
	public static final int EULER_TO_A_VARIABLE_POWER_OPERATOR = 18;
	public static final int SCIENTIFIC_EXPONENT_OPERATOR = 19;

	public ArrayList<Integer> scientificOperators = new ArrayList<Integer>();

	public void setscientificOperators(int n) {
		this.scientificOperators.add(n);
	}

	public ArrayList<Integer> getscientificOperators() {
		return scientificOperators;
	}

	private void addscientificOperators() {
		setscientificOperators(SECOND_POWER_OPERATOR);
		setscientificOperators(THIRD_POWER_OPERATOR);
		setscientificOperators(SQUARE_ROOT_OPERATOR);
		setscientificOperators(CUBE_ROOT_OPERATOR);
		setscientificOperators(SIN_OPERATOR);
		setscientificOperators(COS_OPERATOR);
		setscientificOperators(TAN_OPERATOR);
		setscientificOperators(BASE_TEN_LOGARITHM_OPERATOR);
		setscientificOperators(NATURAL_LOGARITHM_OPERATOR);
		setscientificOperators(INVERSE_OPERATOR);
		setscientificOperators(FACTORIAL_OPERATOR);
		setscientificOperators(EULER_TO_A_VARIABLE_POWER_OPERATOR);
	}

	public Calculator() {
		addscientificOperators();
	}

	public void updateState(int action) {
		if (action == ACTION_PRESS_NUMBER) {
			state = STATE_CAPTURING;
		} else if (action == ACTION_PRESS_OPERATOR) {
			state = STATE_CAPTURING_OPERATOR;
		} else if (action == ACTION_PRESS_EQUALSTO || action == ACTION_PRESS_MIDLE_OPERATOR) {
			state = STATE_CALCULATING;
		} else if (((state == STATE_CALCULATING || state == STATE_CAPTURING_SCIENTIFIC_NUMBER)
				&& (action == ACTION_PRESS_NUMBER || action == ACTION_PRESS_SCIENTIFIC_NUMBER))
				|| action == ACTION_PRESS_CLEAN_DISPLAY) {
			state = STATE_INIT;
		} else if (action == ACTION_PRESS_SCIENTIFIC_NUMBER) {
			state = STATE_CAPTURING_SCIENTIFIC_NUMBER;
		}

		/*
		 * if (((state == STATE_INIT) || (state == STATE_CAPTURING_OPERATOR) || (state
		 * == STATE_CAPTURING_WRITTEN_OPERATOR)) && (action == ACTION_PRESS_NUMBER)) {
		 * state = STATE_CAPTURING; } else if ((((state == STATE_CAPTURING) || (state ==
		 * STATE_CALCULATING)) && (action == ACTION_PRESS_OPERATOR)) || (state ==
		 * STATE_INIT) && (action == ACTION_PRESS_OPERATOR)) { state =
		 * STATE_CAPTURING_OPERATOR; } else if ((action == ACTION_PRESS_EQUALSTO) ||
		 * (action == ACTION_PRESS_MIDLE_OPERATOR)) { state = STATE_CALCULATING; } else
		 * if ((((state == STATE_CALCULATING)) && (action == ACTION_PRESS_NUMBER) ||
		 * (action == ACTION_PRESS_CLEAN_DISPLAY)) || ((state == STATE_CAPTURING) &&
		 * (action == ACTION_PRESS_CLEAN_DISPLAY)) || ((state == STATE_INIT) && ((action
		 * == ACTION_PRESS_OPERATOR) || (action == ACTION_PRESS_MIDLE_OPERATOR)))) {
		 * state = STATE_INIT; } else if (((state == STATE_INIT) || (state ==
		 * STATE_CAPTURING)) && (action == ACTION_PRESS_WRITTEN_OPERATOR)) { state =
		 * STATE_CAPTURING_WRITTEN_OPERATOR; }
		 */
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getBeforeState() {
		return beforeState;
	}

	public void setBeforeState(int beforeState) {
		this.beforeState = beforeState;
	}

	public Double doAnOperation(Integer operation, Double firstAmount, Double secondAmount)
			throws SyntaxErrorException, MalformedExpressionException {
		Double result = 0.0;
		BasicOperations basicCalculation = new BasicOperations();
		ScientificOperations scientificCalculation = new ScientificOperations();
		switch (operation) {
		case ADDING_OPERATOR:
			result = basicCalculation.addition(firstAmount, secondAmount);
			break;
		case SUBTRACT_OPERATOR:
			result = basicCalculation.subtraction(firstAmount, secondAmount);
			break;
		case MULTIPLICATION_OPERATOR:
			result = basicCalculation.multiplication(firstAmount, secondAmount);
			break;
		case DIVISION_OPERATOR:
			result = basicCalculation.division(firstAmount, secondAmount);
			break;
		case PERCENTAGE_OPERATOR:
			result = basicCalculation.percentage(firstAmount, secondAmount);
			break;
		case SECOND_POWER_OPERATOR:
			result = scientificCalculation.power(secondAmount, 2.0);
			break;
		case THIRD_POWER_OPERATOR:
			result = scientificCalculation.power(secondAmount, 3.0);
			break;
		case N_POWER_OPERATOR:
			result = scientificCalculation.power(firstAmount, secondAmount);
			break;
		case SQUARE_ROOT_OPERATOR:
			result = scientificCalculation.root(secondAmount, 2.0);
			break;
		case CUBE_ROOT_OPERATOR:
			result = scientificCalculation.root(secondAmount, 3.0);
			break;
		case N_ROOT_OPERATOR:
			result = scientificCalculation.root(secondAmount, firstAmount);
			break;
		case SIN_OPERATOR:
			result = scientificCalculation.sin(secondAmount);
			break;
		case COS_OPERATOR:
			result = scientificCalculation.cos(secondAmount);
			break;
		case TAN_OPERATOR:
			result = scientificCalculation.tan(secondAmount);
			break;
		case BASE_TEN_LOGARITHM_OPERATOR:
			result = scientificCalculation.baseTenLogarithm(secondAmount);
			break;
		case NATURAL_LOGARITHM_OPERATOR:
			result = scientificCalculation.naturalLogarithm(secondAmount);
			break;
		case INVERSE_OPERATOR:
			result = scientificCalculation.inverse(secondAmount);
			break;
		case FACTORIAL_OPERATOR:
			result = scientificCalculation.factorial(secondAmount);
			break;
		case EULER_TO_A_VARIABLE_POWER_OPERATOR:
			result = scientificCalculation.eulerToPower(secondAmount);
			break;
		default:
			throw new SyntaxErrorException();
		}
		return result;
	}
}
