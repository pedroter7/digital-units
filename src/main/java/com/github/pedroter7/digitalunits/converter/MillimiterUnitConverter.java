package com.github.pedroter7.digitalunits.converter;

import com.github.pedroter7.digitalunits.DigitalQuantity;
import com.github.pedroter7.digitalunits.UnitEnum;

/**
 * <p>An UnitConverter implementation to convert units to millimeters.
 * 
 * @author Pedro T Freidinger (pedrotersetti3@gmail.com)
 * @since 1.0
 */
final class MillimiterUnitConverter extends MeterUnitConverter {

	private final UnitEnum goalUnit = UnitEnum.MILLIMETER;
	
	protected MillimiterUnitConverter(int screenDPI) {
		super(screenDPI);
	}

	@Override
	public DigitalQuantity convertFrom(DigitalQuantity digitalQuantity) {
		if (digitalQuantity == null) return null;
		if (digitalQuantity.getUnit() == this.goalUnit) return digitalQuantity;
		DigitalQuantity inMeters = super.convertFrom(digitalQuantity);
		return new DigitalQuantity(goalUnit, inMeters.getValue()*1000);
	}

	@Override
	public UnitEnum getGoalUnit() {
		return goalUnit;
	}
	
}
