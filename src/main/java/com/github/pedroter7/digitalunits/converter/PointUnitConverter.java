package com.github.pedroter7.digitalunits.converter;

import com.github.pedroter7.digitalunits.DigitalQuantity;
import com.github.pedroter7.digitalunits.UnitEnum;

/**
 * <p>An UnitConverter implementation to convert units to points.
 * 
 * @author Pedro T Freidinger (pedrotersetti3@gmail.com)
 * @since 1.0
 */
final class PointUnitConverter extends AbstractScreenResolutionBasedUnitConverter {

	protected PointUnitConverter(int screenDpi) {
		super(UnitEnum.POINT, screenDpi);
	}

	@Override
	protected DigitalQuantity convert(DigitalQuantity digitalQuantity) {
		UnitEnum fromUnit = digitalQuantity.getUnit();
		switch (fromUnit) {
		case PIXEL:
			return doConvert(digitalQuantity, (Double v) -> (v * 72) / this.screenDpi);
			
		case MILLIMETER:
			return doConvert(digitalQuantity, (Double v) -> v * 2.83464567);
			
		case CENTIMETER:
			return doConvert(digitalQuantity, (Double v) -> v * 28.34645669);
			
		case INCH:
			return doConvert(digitalQuantity, (Double v) -> v * 72);
			
		case METER:
			return doConvert(digitalQuantity, (Double v) -> v * 2834.64566929);

		default:
			super.throwUnsupportedConversion(this.getClass().getName(), fromUnit);
			return null;
		}
	}
	

}
