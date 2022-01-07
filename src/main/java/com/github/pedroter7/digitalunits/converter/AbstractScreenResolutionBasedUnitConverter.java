package com.github.pedroter7.digitalunits.converter;

import java.util.function.Function;

import com.github.pedroter7.digitalunits.DigitalQuantity;
import com.github.pedroter7.digitalunits.UnitEnum;

/**
 * <p>This class serves as base for all {@code UnitConverter} implementations that simply
 * perform a simple conversion from original {@code UnitEnum} numerical value to destination 
 * {@code UnitEnum} numerical value and that some conversion depends on screen resolution (DPI).
 * 
 * @author Pedro T Freidinger (pedrotersetti3@gmail.com)
 * @since 1.0
 */
abstract class AbstractScreenResolutionBasedUnitConverter implements UnitConverter {

	protected final int screenDpi;
	private final UnitEnum goalUnit;
	
	protected AbstractScreenResolutionBasedUnitConverter(UnitEnum goalUnit, int screenDpi) {
		this.goalUnit = goalUnit;
		this.screenDpi = screenDpi;
	}
	
	private DigitalQuantity create(double value) {
		return new DigitalQuantity(goalUnit, value);
	}
	
	/**
	 * <p>Actual conversion happens here.
	 * 
	 * @param original The original {@code DigitalQuantity} to be converted
	 * @param conversionFunction A function that converts the numerical value of the
	 * original {@code DigitalQuantity} to the {@code goalUnit} numerical value equivalent.
	 * @return The original {@code DigitalQuantity} in units of {@code goalUnit}.
	 */
	protected DigitalQuantity doConvert(DigitalQuantity original, Function<Double, Double> conversionFunction) {
		double value = conversionFunction.apply(original.getValue());
		return create(value);
	}
	
	/**
	 * <p>Throws an {@link UnsupportedOperationException}. Should be called when
	 * implementing classes can not deal with the given {@code UnitEnum}.
	 * 
	 * @param implementingClassName
	 * @param originUnit
	 */
	protected void throwUnsupportedConversion(String implementingClassName, UnitEnum originUnit) {
		throw new UnsupportedOperationException("The class " + implementingClassName 
				+ " does not support conversion from " + originUnit
				+ " to " + this.goalUnit.getSymbol());
	}
	
	/**
	 * <p>Implementing classes must use this method to decide a {@code Function<Double, Double>}
	 * method that converts the numerical value in the given {@code DigitalQuantity} to the 
	 * {@code goalUnit} numerical equivalent. After performing this, the method must return the
	 * result of a call to {@link AbstractScreenResolutionBasedUnitConverter#doConvert(DigitalQuantity, Function)}.
	 * 
	 * <p>There is no need to check if the conversion is from the same type as {@code this.goalUnit}
	 * or to check if the parameter is {@code null}, the base class abstracts these checks within the 
	 * implementation of {@code UnitConverter#convertFrom(DigitalQuantity)}.
	 * 
	 * <p> In case the implementing class can not convert the from the given {@code UnitEnum}, it
	 * should cause a {@link UnsupportedOperationException} by calling 
	 * {@link AbstractScreenResolutionBasedUnitConverter#throwUnsupportedConversion(String, UnitEnum)}.
	 * 
	 * @param digitalQuantity
	 * @return A {@code DigitalQuantity} converted from the given {@code DigitalQuantity} to a numerical
	 * equivalent in units of {@code this.goalUnit}
	 */
	abstract protected DigitalQuantity convert(DigitalQuantity digitalQuantity);

	@Override
	public DigitalQuantity convertFrom(DigitalQuantity digitalQuantity) {
		if (digitalQuantity == null) return null;
		if (digitalQuantity.getUnit() == this.goalUnit) return digitalQuantity;
		return convert(digitalQuantity);
	}

	@Override
	public UnitEnum getGoalUnit() {
		return goalUnit;
	}

}
