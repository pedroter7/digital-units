package com.github.pedroter7.digitalunits.converter;

import com.github.pedroter7.digitalunits.DigitalQuantity;
import com.github.pedroter7.digitalunits.UnitEnum;

/**
 * <p>This interface serves as base for classes that deal with
 * {@code DigitalQuantity} conversions. All converters must implement this interface.
 * 
 * @author Pedro T Freidinger (pedrotersetti3@gmail.com)
 * @since 1.0
 */
public interface UnitConverter {

	/**
	 * <p>Converts the given quantity to the converter goal type.
	 * 
	 * <p>This method should return {@code null} and not throw any exception 
	 * if the given parameter is {@code null}.
	 * 
	 * <p>This method may cause a {@link UnsupportedOperationException} if the
	 * implementation doesn't have the means to convert from the given unit.
	 * 
	 * @param digitalQuantity The quantity to be converted from.
	 * 
	 * @return The equivalent quantity in the converter goal unit.
	 */
	public DigitalQuantity convertFrom(DigitalQuantity digitalQuantity);
	
	/**
	 * <p>Get the goal unit that this converter is specialized in
	 * converting to.
	 */
	public UnitEnum getGoalUnit();
	
}
