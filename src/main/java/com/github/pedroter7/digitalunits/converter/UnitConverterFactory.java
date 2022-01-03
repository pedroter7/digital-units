package com.github.pedroter7.digitalunits.converter;

import com.github.pedroter7.digitalunits.UnitEnum;

/*
 * All unit converter factories must implement this interface.
 */
public interface UnitConverterFactory {

	/*
	 * Factories a new UnitConverter.
	 * 
	 * @param goalUnit The goal unit of the UnitConverter
	 * 					to be created. The goal unit is the
	 * 					unit that the converter converts
	 * 					units to.
	 * 
	 * @return A new UnitConverter.
	 */
	public UnitConverter factory(UnitEnum goalUnit);
	
}
