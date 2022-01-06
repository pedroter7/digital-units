package com.github.pedroter7.digitalunits.converter;

/**
 * <p>Unit converter factories that create {@code UnitConverter} that use
 * screen resolution value to perform conversions must implement this interface
 * instead of directly implementing the {@code UnitConverterFactory} interface. 
 */
public interface ScreenResolutionBasedUnitConverterFactory extends UnitConverterFactory {
	
	/**
	 * <p>Get the screen resolution in DPIs that was obtained by the
	 * factory and is currently being used to instantiate {@code UnitConverter} s.
	 * @return
	 */
	public int getScreenResolution();

}
