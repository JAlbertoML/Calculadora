package mx.ipn.upiicsa.poo.calculadora.bs;

import mx.ipn.upiicsa.poo.calculadora.exceptions.MalformedExpressionException;
import mx.ipn.upiicsa.poo.calculadora.exceptions.SyntaxErrorException;

public class BasicOperations{

	public BasicOperations() {

	}
	
	private void verificateZeroDivision(Double secondPart) throws SyntaxErrorException{
		if(secondPart == 0) {
			throw new SyntaxErrorException();
		}
	}
	
	private void verificateMalformedExpresions(Double firstPart, Double secondPart) throws MalformedExpressionException {
		if((firstPart == null) || (secondPart == null) || (firstPart == null && secondPart == null)) {
			throw new MalformedExpressionException();
		}
	}

	public Double addition(Double firstPart, Double secondPart) throws MalformedExpressionException {
		verificateMalformedExpresions(firstPart, secondPart);
		return firstPart + secondPart;
	}

	public Double subtraction(Double firstPart, Double secondPart) throws MalformedExpressionException {
		verificateMalformedExpresions(firstPart, secondPart);
		return firstPart - secondPart;
	}

	public Double multiplication(Double firstPart, Double secondPart) throws MalformedExpressionException {
		verificateMalformedExpresions(firstPart, secondPart);
		return firstPart * secondPart;
	}

	public Double division(Double firstPart, Double secondPart) throws SyntaxErrorException, MalformedExpressionException{
		verificateMalformedExpresions(firstPart, secondPart);
		verificateZeroDivision(secondPart);
		return firstPart / secondPart;
	}

	public Double percentage(Double amount, Double percentage) throws MalformedExpressionException {
		verificateMalformedExpresions(amount, percentage);
		return (amount*percentage)/100;
	}
}
