package com.github.pedroter7.digitalunits.converter;

import com.github.pedroter7.digitalunits.DigitalQuantity;
import com.github.pedroter7.digitalunits.UnitEnum;

/*
 * This interface serves as base for classes that deal with
 * DigitalQuantity conversions.
 * All converters must implement this interface.
 */
public interface UnitConverter {

	/*
	 * Converts the given quantity to the converter goal type
	 * 
	 * @param digitalQuantity The quantity to be converted from
	 * 
	 * @return The equivalent quantity in the converter goal unit.
	 */
	public DigitalQuantity convertFrom(DigitalQuantity digitalQuantity);
	
	/*
	 * Get the goal unit that this converter is specialized in
	 * converting to.
	 */
	public UnitEnum getGoalUnit();
	
}
