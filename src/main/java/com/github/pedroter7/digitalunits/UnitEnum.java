package com.github.pedroter7.digitalunits;

/**
 * <p>Represents measuring units. Each {@code UnitEnum} has a symbol {@code String} related to it.
 * 
 * @author Pedro T Freidinger (pedrotersetti3@gmail.com)
 * @since 1.0
 */
public enum UnitEnum {
	
	PIXEL ("px"),
	METER ("m"),
	CENTIMETER ("cm"),
	MILLIMETER ("mm"),
	POINT ("pt"),
	INCH ("in");
	
	private final String symbol;
	
	private UnitEnum(String symbol) {
		this.symbol = symbol;
	}

	public String getSymbol() {
		return symbol;
	}
	
	/**
	 * <p>Get the {@code UnitEnum} that represents the unit with the given symbol. If there's no such {@code UnitEnum}
	 * then {@code null} is returned.
	 * 
	 * @param symbol A {@code String} representing the unit symbol.
	 * 
	 * @return The {@code UnitEnum} that represents the given unit or null if there is no such representation.
	 */
	public static UnitEnum getUnit(String symbol) {
		for (UnitEnum u : values()) {
			if (u.symbol.equals(symbol)) return u;
		}
		
		return null;
	}

}
