package com.github.pedroter7.digitalunits.converter;

import com.github.pedroter7.digitalunits.DigitalQuantity;
import com.github.pedroter7.digitalunits.UnitEnum;

/**
 * <p>An UnitConverter implementation to convert a {@code DigitalQuantity} to centimeters.
 * 
 * @author Pedro T Freidinger (pedrotersetti3@gmail.com)
 * @since 1.0
 */
final class CentimeterUnitConverter extends MeterUnitConverter {

	private final UnitEnum goalUnit = UnitEnum.CENTIMETER;
	
	protected CentimeterUnitConverter(int screenDPI) {
		super(screenDPI);
	}

	@Override
	public DigitalQuantity convertFrom(DigitalQuantity digitalQuantity) {
		if (digitalQuantity == null) return null;
		if (digitalQuantity.getUnit() == this.goalUnit) return digitalQuantity;
		DigitalQuantity inMeters = super.convertFrom(digitalQuantity);
		return new DigitalQuantity(goalUnit, inMeters.getValue()*100);
	}

	@Override
	public UnitEnum getGoalUnit() {
		return goalUnit;
	}

}
