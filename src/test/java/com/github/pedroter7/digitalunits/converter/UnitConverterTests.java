package com.github.pedroter7.digitalunits.converter;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.Toolkit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import com.github.pedroter7.digitalunits.DigitalQuantity;
import com.github.pedroter7.digitalunits.UnitEnum;

@DisplayName("UnitConverter tests")
public class UnitConverterTests {
	
	@DisplayName("UnitConverters built using the AwtConverterFactory")
	@Nested
	public class AwtBasedConverterTests {
		
		private final UnitConverterFactory awtFactory = new AwtConverterFactory();
		
		@DisplayName("Converting to PIXEL tests")
		@Nested
		public class PixelConvertingTests {
			
			@DisplayName("Convert from null")
			@Test
			public void convertNull() {
				UnitConverter converter = awtFactory.factory(UnitEnum.PIXEL);
				assertEquals(null, converter.convertFrom(null));
			}
			
			@DisplayName("Convert from PIXEL")
			@Test
			public void convertFromPixel() {
				UnitConverter converter = awtFactory.factory(UnitEnum.PIXEL);
				DigitalQuantity digitalQuantity = DigitalQuantity.valueOf("182px");
				DigitalQuantity converted = converter.convertFrom(digitalQuantity);
				assertEquals(0, converted.compareTo(digitalQuantity));
			}
			
			@DisplayName("Convert from CENTIMETER")
			@Test
			public void convertFromCm() {
				UnitConverter converter = awtFactory.factory(UnitEnum.PIXEL);
				DigitalQuantity digitalQuantity = DigitalQuantity.valueOf("50cm");
				DigitalQuantity converted = converter.convertFrom(digitalQuantity);
				// Calculate what should be the result
				int dpi = Toolkit.getDefaultToolkit().getScreenResolution();
				DigitalQuantity shouldBe = new DigitalQuantity(UnitEnum.PIXEL, (50/2.54)*dpi);
				assertEquals(0, converted.compareTo(shouldBe));
			}
			
			@DisplayName("Convert from MILLIMETER")
			@Test
			public void convertFromMm() {
				UnitConverter converter = awtFactory.factory(UnitEnum.PIXEL);
				DigitalQuantity digitalQuantity = DigitalQuantity.valueOf("100mm");
				DigitalQuantity converted = converter.convertFrom(digitalQuantity);
				// Calculate what should be the result
				int dpi = Toolkit.getDefaultToolkit().getScreenResolution();
				DigitalQuantity shouldBe = new DigitalQuantity(UnitEnum.PIXEL, (100/25.4)*dpi);
				assertEquals(0, converted.compareTo(shouldBe));
			}
			
			@DisplayName("Convert from INCH")
			@Test
			public void convertFromIn() {
				UnitConverter converter = awtFactory.factory(UnitEnum.PIXEL);
				DigitalQuantity digitalQuantity = DigitalQuantity.valueOf("250in");
				DigitalQuantity converted = converter.convertFrom(digitalQuantity);
				// Calculate what should be the result
				int dpi = Toolkit.getDefaultToolkit().getScreenResolution();
				DigitalQuantity shouldBe = new DigitalQuantity(UnitEnum.PIXEL, 250*dpi);
				assertEquals(0, converted.compareTo(shouldBe));
			}
			
			@DisplayName("Convert from POINT")
			@Test
			public void convertFromPt() {
				UnitConverter converter = awtFactory.factory(UnitEnum.PIXEL);
				DigitalQuantity digitalQuantity = DigitalQuantity.valueOf("12.01pt");
				DigitalQuantity converted = converter.convertFrom(digitalQuantity);
				// Calculate what should be the result
				int dpi = Toolkit.getDefaultToolkit().getScreenResolution();
				DigitalQuantity shouldBe = new DigitalQuantity(UnitEnum.PIXEL, (12.01/72)*dpi);
				assertEquals(0, converted.compareTo(shouldBe));
			}
			
			@DisplayName("Convert from negative value")
			@Test
			public void convertFromNegativeValue() {
				UnitConverter converter = awtFactory.factory(UnitEnum.PIXEL);
				DigitalQuantity digitalQuantity = DigitalQuantity.valueOf("-50.54mm");
				DigitalQuantity converted = converter.convertFrom(digitalQuantity);
				// Calculate what should be the result
				int dpi = Toolkit.getDefaultToolkit().getScreenResolution();
				DigitalQuantity shouldBe = new DigitalQuantity(UnitEnum.PIXEL, -(50.54/25.4)*dpi);
				assertEquals(0, converted.compareTo(shouldBe));
			}
			
		}
		
	}

}
