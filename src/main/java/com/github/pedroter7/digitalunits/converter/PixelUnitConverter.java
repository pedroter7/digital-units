package com.github.pedroter7.digitalunits.converter;

import com.github.pedroter7.digitalunits.DigitalQuantity;
import com.github.pedroter7.digitalunits.UnitEnum;

/**
 * <p>An UnitConverter implementation to convert units to pixels.
 * 
 * @author Pedro T Freidinger (pedrotersetti3@gmail.com)
 * @since 1.0
 */
final class PixelUnitConverter extends AbstractScreenResolutionBasedUnitConverter {

	protected PixelUnitConverter(int screenDpi) {
		super(UnitEnum.PIXEL, screenDpi);
	}

	@Override
	protected DigitalQuantity convert(DigitalQuantity digitalQuantity) {
		UnitEnum fromUnit = digitalQuantity.getUnit();
		switch (fromUnit) {
		case METER:
			return doConvert(digitalQuantity, (Double v) -> (v/0.0254) * this.screenDpi);
			
		case CENTIMETER:
			return doConvert(digitalQuantity, (Double v) -> (v/2.54) * this.screenDpi);
			
		case MILLIMETER:
			return doConvert(digitalQuantity, (Double v) -> (v/25.4) * this.screenDpi);
			
		case INCH:
			return doConvert(digitalQuantity, (Double v) -> v * this.screenDpi);
			
		case POINT:
			return doConvert(digitalQuantity, (Double v) -> (v/72) * this.screenDpi);

		default:
			throwUnsupportedConversion(this.getClass().getName(), fromUnit);
			return null;
		}
	}

}
