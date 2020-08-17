package mx.ipn.upiicsa.poo.calculadora.bs;

import mx.ipn.upiicsa.poo.calculadora.exceptions.MalformedExpressionException;

public class ScientificOperations {
	
	Double result = null;

	public ScientificOperations() {

	}

	public Double power(Double base, Double power) throws MalformedExpressionException {
		if(base == null || power == null) {
			throw new MalformedExpressionException();
		}else {
			result = Math.pow(base, power);
		}
		return result;
	}

	public Double root(Double base, Double root) throws MalformedExpressionException {
		Double r = 1 / root;
		if(base == null || root == null) {
			throw new MalformedExpressionException();
		}else {
			result = Math.pow(base, r);
		}
		return result;
	}

	public Double sin(Double number) throws MalformedExpressionException {
		if (number == null) {
			throw new MalformedExpressionException();
		} else {
			result = Math.sin(Math.toRadians(number));
		} 
		return result;
	}

	public Double cos(Double number) throws MalformedExpressionException {
		if (number == null) {
			throw new MalformedExpressionException();
		} else {
			result = Math.cos(Math.toRadians(number));
		} 
		return result;
	}

	public Double tan(Double number) throws MalformedExpressionException {
		if (number == null) {
			throw new MalformedExpressionException();
		} else {
			result = Math.tan(Math.toRadians(number));
		} 
		return result;
	}
	
	public Double baseTenLogarithm(Double number) throws MalformedExpressionException{
		if (number == null) {
			throw new MalformedExpressionException();
		} else {
			result = Math.log10(number);
		} 
		return result;
	}
	
	public Double naturalLogarithm(Double number) throws MalformedExpressionException{
		if (number == null) {
			throw new MalformedExpressionException();
		} else {
			result = Math.log(number);
		} 
		return result;
	}
	
	public Double inverse(Double number) throws MalformedExpressionException {
		if (number == null) {
			throw new MalformedExpressionException();
		} else {
			result = Math.pow(number, -1);
		}
		return result;
	}
	
	public Double factorial(Double number) throws MalformedExpressionException {
		if (number == null) {
			throw new MalformedExpressionException();
		} else {
			if (number <= 2) {
		        return number;
		    }
		}
		return number * factorial(number - 1);
	}
	
	public Double eulerToPower(Double number) throws MalformedExpressionException {
		if (number == null) {
			throw new MalformedExpressionException();
		} else {
			result = Math.pow(Math.E, number);
		}
		return result;
	}
}
