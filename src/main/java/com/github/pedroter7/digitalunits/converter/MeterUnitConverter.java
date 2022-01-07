package com.github.pedroter7.digitalunits.converter;

import java.util.function.Function;

import com.github.pedroter7.digitalunits.DigitalQuantity;
import com.github.pedroter7.digitalunits.UnitEnum;

/**
 * <p>An UnitConverter implementation to convert units to meters.
 * 
 * <p>This class is meant to be used as base to any multiple of meter
 * conversions, thus it is not declared as final.
 * 
 * @author Pedro T Freidinger (pedrotersetti3@gmail.com)
 * @since 1.0
 */
class MeterUnitConverter implements UnitConverter {
	
	private final int screenDpi;
	private final UnitEnum goalUnit = UnitEnum.METER;
	
	protected MeterUnitConverter(int screenDpi) {
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
	 * original {@code DigitalQuantity} to the meters numerical value equivalent.
	 * @return The original {@code DigitalQuantity} in meters.
	 */
	private DigitalQuantity doConvert(DigitalQuantity original, Function<Double, Double> conversionFunction) {
		double value = conversionFunction.apply(original.getValue());
		return create(value);
	}
	
	private DigitalQuantity convert(DigitalQuantity digitalQuantity) {
		UnitEnum fromUnit = digitalQuantity.getUnit();
		switch (fromUnit) {
		case PIXEL:
			return doConvert(digitalQuantity, (Double v) -> (v * 0.0254) / this.screenDpi);
			
		case MILLIMETER:
			return doConvert(digitalQuantity, (Double v) -> v/1000);
			
		case CENTIMETER:
			return doConvert(digitalQuantity, (Double v) -> v/100);
			
		case INCH:
			return doConvert(digitalQuantity, (Double v) -> v * 0.0254);
			
		case POINT:
			return doConvert(digitalQuantity, (Double v) -> v * 0.00035278);

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
