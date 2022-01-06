package com.github.pedroter7.digitalunits.converter;

import java.awt.Toolkit;
import java.util.Objects;

import com.github.pedroter7.digitalunits.UnitEnum;

/**
 * <p>A {@code UnitConverter} factory that gets screen information to
 * perform graphical unit conversion from the {@link java.awt.Toolkit} class.
 * 
 * <p>More specifically, screen DPI is obtained from {@link java.awt.Toolkit#getScreenResolution()}
 * method using the {@link java.awt.Toolkit#getDefaultToolkit()} method to
 * obtain a {@code Toolkit} reference.
 * 
 * @author Pedro T Freidinger (pedrotersetti3@gmail.com)
 * @since 1.0
 */
public final class AwtConverterFactory implements ScreenResolutionBasedUnitConverterFactory {
	
	private final int screenDpi;

	/**
	 * <p>Since this factory uses {@link java.awt.Toolkit#getScreenResolution()}
	 * and {@link java.awt.Toolkit#getDefaultToolkit()} methods, the constructor
	 * may throw whatever those methods throw.
	 */
	public AwtConverterFactory() {
		this.screenDpi = Toolkit.getDefaultToolkit().getScreenResolution();;
	}
	

	@Override
	public UnitConverter factory(UnitEnum goalUnit) {
		Objects.requireNonNull(goalUnit);
		
		switch (goalUnit) {
		case PIXEL:
			return new PixelUnitConverter(this.screenDpi);
			
		case CENTIMETER:
			return new CentimeterUnitConverter(screenDpi);

		default:
			throw new UnsupportedOperationException(
					"The factory " + getClass().getName() + " can not create factory a "
					+ UnitConverter.class.getName() + " for the goal unit" + goalUnit.getSymbol());
		}
	}


	@Override
	public int getScreenResolution() {
		return this.screenDpi;
	}

}
