package com.github.pedroter7.digitalunits;

import java.io.Serializable;
import java.util.Objects;

/**
 * <p>The {@code DigitalQuantity} class is represents a digital measuring unit.
 * 
 * <p>For example, instead of having a string saying <i>"15 cm"</i>, this class can be used
 * to have the value 15 and the unit <i>"cm"</i> encapsulated and accessed through an object.
 * 
 * <p>Unless otherwise noted, passing a {@code null} parameter to the constructor or other
 * static method that constructs an object of this class will cause a {@link NullPointerException}.
 * 
 * <p> During comparison of objects of this class, units are never automatically converted. Two
 * different units will cause a {@link UnsupportedOperationException}. A {@code null} value will
 * cause a {@link NullPointerException}.
 * 
 * @author Pedro T Freidinger
 * @since 1.0
 */
public final class DigitalQuantity implements Serializable, Comparable<DigitalQuantity> {

	private static final long serialVersionUID = 8472738932779828798L;
	
	private final UnitEnum unit;
	private final double value;
	
	public DigitalQuantity(UnitEnum unit, double value) {
		Objects.requireNonNull(unit, "To create a new DigitalQuantity, unit can't be null.");
		this.unit = unit;
		this.value = value;
	}

	public UnitEnum getUnit() {
		return unit;
	}

	public double getValue() {
		return value;
	}
	
	/**
	 * <p>Tries to create a {@code DigitalQuantity} from a {@code string}.
	 * 
	 * <p>The {@code string} must be a fully qualified quantity, that is, a number that represents
	 * a measurement plus a symbol that represents an unit. For example: <i>"15 cm"</i>, 
	 * <i>"1.2 in"</i>, <i>"50.123pt"</i>, ...
	 * 
	 * <p>The string is trimmed using {@link String#trim()} so there is no need to deal with leading and
	 * trailing white spaces before passing the string to this method.
	 * 
	 * <p>There is also no need to deal with space between the number and the measuring unit symbol.
	 * 
	 * <p>The unit symbol, when found, is normalized so there is no need to deal with case or anything else.
	 * For example, the strings <i>"15IN"</i>, <i>"15 in"</i>, <i>"15    in"</i> and <i>"15 In"</i> will
	 * produce the same result.
	 * 
	 * <p>If the given string is not fully qualified a {@code IllegalArgumentException} is thrown.
	 * 
	 * <p>If the unit found in the fully qualified string has no {@code UnitEnum} representation 
	 * then a {@code UnsupportedOperationException} is thrown.
	 * 
	 * <p>Since the method {@link Double#valueOf(String)} is used, other types of exception may be thrown.
	 * 
	 * @param string A fully qualified quantity in a string.
	 * 
	 * @return A {@code DigitalQuantity} object created from the given string
	 */
	public static DigitalQuantity valueOf(String fullyQualifiedQuantity) {
		Objects.requireNonNull(fullyQualifiedQuantity,
				"To create a new DigitalQuantity from a String, the string must not be null");
		if (fullyQualifiedQuantity.isBlank()) throw new IllegalArgumentException(
				"To create a new DigitalQuantity from a String, the string must be fully qualified"
				+ " with a number representing a measure plus a symbol representing a unit."
				+ " The given string was empty.");
		
		if (fullyQualifiedQuantity.length() < 2) throw new IllegalArgumentException(
				"To create a new DigitalQuantity from a String, the string must have length >= 2.");
		
		fullyQualifiedQuantity = fullyQualifiedQuantity.trim();
		// Get the unit symbol
		String unitChar1 = String.valueOf(fullyQualifiedQuantity.charAt(fullyQualifiedQuantity.length() - 2));
		String unitChar2 = String.valueOf(fullyQualifiedQuantity.charAt(fullyQualifiedQuantity.length() - 1));
		if (!Character.isLetter(unitChar2.codePointAt(0))) throw new IllegalArgumentException(
				"To create a new DigitalQuantity from a String, the string must be fully qualified"
				+ " with a number representing a measure plus a symbol representing an unit.");
		// Allows single char unit symbols such as 'm'
		String unit = (Character.isLetter(unitChar1.codePointAt(0)) ? unitChar1 : "") + unitChar2;
		unit = unit.toLowerCase();
		UnitEnum unitEnum = UnitEnum.findBySymbol(unit);
		if (unitEnum == null) throw new UnsupportedOperationException(
				"There is no support for the given measuring unit."
				+ " The given unit symbol was " + unit);
		
		// Finally construct the object
		double qtValue = Double.valueOf(fullyQualifiedQuantity.substring(0, fullyQualifiedQuantity.length() - unit.length()));
		return new DigitalQuantity(unitEnum, qtValue);
	}

	@Override
	public int compareTo(DigitalQuantity o) {
		Objects.requireNonNull(o);
		if (this.unit != o.unit) throw new UnsupportedOperationException(
				"During a comparison, both DigitalQuantity objects' unit must be the same. "
				+ "There is no automatic conversion between any units.");
		if (this.value == o.value) return 0;
		else if (this.value > o.value) return 1;
		else return -1;
	}
	
}
