package com.github.pedroter7.digitalunits.converter;

import java.util.function.Function;

import com.github.pedroter7.digitalunits.DigitalQuantity;
import com.github.pedroter7.digitalunits.UnitEnum;

/**
 * <p>An UnitConverter implementation to convert units to pixels.
 * 
 * @author Pedro T Freidinger (pedrotersetti3@gmail.com)
 * @since 1.0
 */
final class PixelUnitConverter implements UnitConverter {
	
	private final int screenDPI;
	private final UnitEnum goalUnit = UnitEnum.PIXEL;
	
	protected PixelUnitConverter(int screenDPI) {
		this.screenDPI = screenDPI;
	}
	
	private DigitalQuantity create(double value) {
		return new DigitalQuantity(goalUnit, value);
	}
	
	/**
	 * <p>Actual conversion happens here.
	 * 
	 * @param original The original {@code DigitalQuantity} to be converted
	 * @param toInches A function to convert the value of the given {@code DigitalQuantity}
	 * to inches.
	 * @return The original {@code DigitalQuantity} in pixels.
	 */
	private DigitalQuantity doConvert(DigitalQuantity original, Function<Double, Double> toInches) {
		double value = toInches.apply(original.getValue()) * this.screenDPI;
		return create(value);
	}
	
	private DigitalQuantity convert(DigitalQuantity digitalQuantity) {
		UnitEnum fromUnit = digitalQuantity.getUnit();
		switch (fromUnit) {
		case CENTIMETER:
			return doConvert(digitalQuantity, (Double v) -> v/2.54);
			
		case MILLIMETER:
			return doConvert(digitalQuantity, (Double v) -> v/25.4);
			
		case INCH:
			return doConvert(digitalQuantity, (Double v) -> v);
			
		case POINT:
			return doConvert(digitalQuantity, (Double v) -> v/72);

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
