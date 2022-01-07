package com.github.pedroter7.digitalunits.converter;

import com.github.pedroter7.digitalunits.DigitalQuantity;
import com.github.pedroter7.digitalunits.UnitEnum;

/**
 * <p>An UnitConverter implementation to convert a {@code DigitalQuantity} to meters.
 * 
 * <p>This class is meant to be used as base to any multiple of meter
 * conversions, thus it is not declared as final.
 * 
 * @author Pedro T Freidinger (pedrotersetti3@gmail.com)
 * @since 1.0
 */
class MeterUnitConverter extends AbstractScreenResolutionBasedUnitConverter {
	
	protected MeterUnitConverter(int screenDpi) {
		super(UnitEnum.METER, screenDpi);
	}

	@Override
	protected DigitalQuantity convert(DigitalQuantity digitalQuantity) {
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
			super.throwUnsupportedConversion(this.getClass().getName(), fromUnit);
			return null;
		}
	}

}
