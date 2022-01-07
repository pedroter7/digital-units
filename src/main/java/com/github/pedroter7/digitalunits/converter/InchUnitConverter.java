package com.github.pedroter7.digitalunits.converter;

import com.github.pedroter7.digitalunits.DigitalQuantity;
import com.github.pedroter7.digitalunits.UnitEnum;

/**
 * <p>An UnitConverter implementation to convert a {@code DigitalQuantity} to inches.
 * 
 * @author Pedro T Freidinger (pedrotersetti3@gmail.com)
 * @since 1.0
 */
final class InchUnitConverter extends AbstractScreenResolutionBasedUnitConverter {

	protected InchUnitConverter(int screenDpi) {
		super(UnitEnum.INCH, screenDpi);
	}

	@Override
	protected DigitalQuantity convert(DigitalQuantity digitalQuantity) {
		UnitEnum fromUnit = digitalQuantity.getUnit();
		switch (fromUnit) {
		case PIXEL:
			return doConvert(digitalQuantity, (Double v) -> v / this.screenDpi);
			
		case MILLIMETER:
			return doConvert(digitalQuantity, (Double v) -> v / 25.4);
			
		case CENTIMETER:
			return doConvert(digitalQuantity, (Double v) -> v / 2.54);
			
		case POINT:
			return doConvert(digitalQuantity, (Double v) -> v / 72);
			
		case METER:
			return doConvert(digitalQuantity, (Double v) -> v / 0.0254);

		default:
			super.throwUnsupportedConversion(this.getClass().getName(), fromUnit);
			return null;
		}
	}

}
