package com.github.pedroter7.digitalunits.converter;

import java.util.function.Function;

import com.github.pedroter7.digitalunits.DigitalQuantity;
import com.github.pedroter7.digitalunits.UnitEnum;

/**
 * <p>An UnitConverter implementation to convert units to centimeters.
 * 
 * @author Pedro T Freidinger (pedrotersetti3@gmail.com)
 * @since 1.0
 */
final class CentimeterUnitConverter implements UnitConverter {
	
	private final int screenDPI;
	private final UnitEnum goalUnit = UnitEnum.CENTIMETER;
	
	protected CentimeterUnitConverter(int screenDPI) {
		this.screenDPI = screenDPI;
	}
	
	private DigitalQuantity create(double value) {
		return new DigitalQuantity(goalUnit, value);
	}
	
	/**
	 * <p>Actual conversion happens here.
	 * 
	 * @param original The original {@code DigitalQuantity} to be converted
	 * @param conversionFunction A function that converts the numerical value of the
	 * original {@code DigitalQuantity} to the centimeters numerical value 
	 * equivalent.
	 * @return The original {@code DigitalQuantity} in centimeters.
	 */
	private DigitalQuantity doConvert(DigitalQuantity original, Function<Double, Double> conversionFunction) {
		double value = conversionFunction.apply(original.getValue());
		return create(value);
	}
	
	private DigitalQuantity convert(DigitalQuantity digitalQuantity) {
		UnitEnum fromUnit = digitalQuantity.getUnit();
		switch (fromUnit) {
		case PIXEL:
			return doConvert(digitalQuantity, (Double v) -> (v * 2.54) / this.screenDPI);
			
		case MILLIMETER:
			return doConvert(digitalQuantity, (Double v) -> v/10);
			
		case INCH:
			return doConvert(digitalQuantity, (Double v) -> v*2.54);
			
		case POINT:
			return doConvert(digitalQuantity, (Double v) -> v/28.346);

		default:
			throw new UnsupportedOperationException("The class " + getClass().getName() 
					+ " does not support conversion from " + fromUnit
					+ " to " + this.goalUnit.getSymbol());
		}
	}

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
