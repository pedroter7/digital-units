package com.github.pedroter7.digitalunits.converter;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
		
		private final ScreenResolutionBasedUnitConverterFactory awtFactory = new AwtConverterFactory();
		
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
			
			@DisplayName("Convert from METER")
			@Test
			public void convertFromM() {
				UnitConverter converter = awtFactory.factory(UnitEnum.PIXEL);
				DigitalQuantity digitalQuantity = DigitalQuantity.valueOf("5m");
				DigitalQuantity converted = converter.convertFrom(digitalQuantity);
				// Calculate what should be the result
				int dpi = awtFactory.getScreenResolution();
				DigitalQuantity shouldBe = new DigitalQuantity(UnitEnum.PIXEL, (5/0.0254)*dpi);
				assertEquals(0, converted.compareTo(shouldBe));
			}
			
			@DisplayName("Convert from CENTIMETER")
			@Test
			public void convertFromCm() {
				UnitConverter converter = awtFactory.factory(UnitEnum.PIXEL);
				DigitalQuantity digitalQuantity = DigitalQuantity.valueOf("50cm");
				DigitalQuantity converted = converter.convertFrom(digitalQuantity);
				// Calculate what should be the result
				int dpi = awtFactory.getScreenResolution();
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
				int dpi = awtFactory.getScreenResolution();
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
				int dpi = awtFactory.getScreenResolution();
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
				int dpi = awtFactory.getScreenResolution();
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
				int dpi = awtFactory.getScreenResolution();
				DigitalQuantity shouldBe = new DigitalQuantity(UnitEnum.PIXEL, -(50.54/25.4)*dpi);
				assertEquals(0, converted.compareTo(shouldBe));
			}
			
		}
		
		@DisplayName("Converting to METER tests")
		@Nested
		public class MeterConvertingTests {
			
			@DisplayName("Convert from null")
			@Test
			public void convertNull() {
				UnitConverter converter = awtFactory.factory(UnitEnum.METER);
				assertEquals(null, converter.convertFrom(null));
			}
			
			@DisplayName("Convert from PIXEL")
			@Test
			public void convertFromPixel() {
				UnitConverter converter = awtFactory.factory(UnitEnum.METER);
				DigitalQuantity digitalQuantity = DigitalQuantity.valueOf("182px");
				DigitalQuantity converted = converter.convertFrom(digitalQuantity);
				// Calculate what should be the result
				int dpi = awtFactory.getScreenResolution();
				DigitalQuantity shouldBe = new DigitalQuantity(UnitEnum.METER, (182*0.0254)/dpi);
				assertEquals(0, converted.compareTo(shouldBe));
			}
			
			@DisplayName("Convert from METER")
			@Test
			public void convertFromM() {
				UnitConverter converter = awtFactory.factory(UnitEnum.METER);
				DigitalQuantity digitalQuantity = DigitalQuantity.valueOf("5m");
				DigitalQuantity converted = converter.convertFrom(digitalQuantity);
				assertEquals(0, converted.compareTo(digitalQuantity));
			}
			
			@DisplayName("Convert from CENTIMETER")
			@Test
			public void convertFromCm() {
				UnitConverter converter = awtFactory.factory(UnitEnum.METER);
				DigitalQuantity digitalQuantity = DigitalQuantity.valueOf("50cm");
				DigitalQuantity converted = converter.convertFrom(digitalQuantity);
				// Calculate what should be the result
				DigitalQuantity shouldBe = new DigitalQuantity(UnitEnum.METER, 0.50);
				assertEquals(0, converted.compareTo(shouldBe));
			}
			
			@DisplayName("Convert from MILLIMETER")
			@Test
			public void convertFromMm() {
				UnitConverter converter = awtFactory.factory(UnitEnum.METER);
				DigitalQuantity digitalQuantity = DigitalQuantity.valueOf("100mm");
				DigitalQuantity converted = converter.convertFrom(digitalQuantity);
				// Calculate what should be the result
				DigitalQuantity shouldBe = new DigitalQuantity(UnitEnum.METER, 0.10);
				assertEquals(0, converted.compareTo(shouldBe));
			}
			
			@DisplayName("Convert from INCH")
			@Test
			public void convertFromIn() {
				UnitConverter converter = awtFactory.factory(UnitEnum.METER);
				DigitalQuantity digitalQuantity = DigitalQuantity.valueOf("250in");
				DigitalQuantity converted = converter.convertFrom(digitalQuantity);
				// Calculate what should be the result
				DigitalQuantity shouldBe = new DigitalQuantity(UnitEnum.METER, 250*0.0254);
				assertEquals(0, converted.compareTo(shouldBe));
			}
			
			@DisplayName("Convert from POINT")
			@Test
			public void convertFromPt() {
				UnitConverter converter = awtFactory.factory(UnitEnum.METER);
				DigitalQuantity digitalQuantity = DigitalQuantity.valueOf("12.01pt");
				DigitalQuantity converted = converter.convertFrom(digitalQuantity);
				// Calculate what should be the result
				DigitalQuantity shouldBe = new DigitalQuantity(UnitEnum.METER, 12.01 * 0.00035278);
				assertEquals(0, converted.compareTo(shouldBe));
			}
			
			@DisplayName("Convert from negative value")
			@Test
			public void convertFromNegativeValue() {
				UnitConverter converter = awtFactory.factory(UnitEnum.METER);
				DigitalQuantity digitalQuantity = DigitalQuantity.valueOf("-50.54mm");
				DigitalQuantity converted = converter.convertFrom(digitalQuantity);
				// Calculate what should be the result
				DigitalQuantity shouldBe = new DigitalQuantity(UnitEnum.METER, -0.05054);
				assertEquals(0, converted.compareTo(shouldBe));
			}
			
		}
		
		@DisplayName("Converting to CENTIMETER tests")
		@Nested
		public class CentimeterConvertingTests {
			
			@DisplayName("Convert from null")
			@Test
			public void convertNull() {
				UnitConverter converter = awtFactory.factory(UnitEnum.CENTIMETER);
				assertEquals(null, converter.convertFrom(null));
			}
			
			@DisplayName("Convert from PIXEL")
			@Test
			public void convertFromPixel() {
				UnitConverter converter = awtFactory.factory(UnitEnum.CENTIMETER);
				DigitalQuantity digitalQuantity = DigitalQuantity.valueOf("182px");
				DigitalQuantity converted = converter.convertFrom(digitalQuantity);
				// Calculate what should be the result
				int dpi = awtFactory.getScreenResolution();
				DigitalQuantity shouldBe = new DigitalQuantity(UnitEnum.CENTIMETER, (182.0*2.54)/dpi);
				assertEquals(0, converted.compareTo(shouldBe));
			}
			
			@DisplayName("Convert from METER")
			@Test
			public void convertFromM() {
				UnitConverter converter = awtFactory.factory(UnitEnum.CENTIMETER);
				DigitalQuantity digitalQuantity = DigitalQuantity.valueOf("5m");
				DigitalQuantity converted = converter.convertFrom(digitalQuantity);
				// Calculate what should be the result
				DigitalQuantity shouldBe = new DigitalQuantity(UnitEnum.CENTIMETER, 500.0);
				assertEquals(0, converted.compareTo(shouldBe));
			}
			
			@DisplayName("Convert from CENTIMETER")
			@Test
			public void convertFromCm() {
				UnitConverter converter = awtFactory.factory(UnitEnum.CENTIMETER);
				DigitalQuantity digitalQuantity = DigitalQuantity.valueOf("50cm");
				DigitalQuantity converted = converter.convertFrom(digitalQuantity);
				assertEquals(0, converted.compareTo(digitalQuantity));
			}
			
			@DisplayName("Convert from MILLIMETER")
			@Test
			public void convertFromMm() {
				UnitConverter converter = awtFactory.factory(UnitEnum.CENTIMETER);
				DigitalQuantity digitalQuantity = DigitalQuantity.valueOf("100mm");
				DigitalQuantity converted = converter.convertFrom(digitalQuantity);
				// Calculate what should be the result
				DigitalQuantity shouldBe = new DigitalQuantity(UnitEnum.CENTIMETER, 10.0);
				assertEquals(0, converted.compareTo(shouldBe));
			}
			
			@DisplayName("Convert from INCH")
			@Test
			public void convertFromIn() {
				UnitConverter converter = awtFactory.factory(UnitEnum.CENTIMETER);
				DigitalQuantity digitalQuantity = DigitalQuantity.valueOf("250in");
				DigitalQuantity converted = converter.convertFrom(digitalQuantity);
				// Calculate what should be the result
				DigitalQuantity shouldBe = new DigitalQuantity(UnitEnum.CENTIMETER, 250*2.54);
				assertEquals(0, converted.compareTo(shouldBe));
			}
			
			@DisplayName("Convert from POINT")
			@Test
			public void convertFromPt() {
				UnitConverter converter = awtFactory.factory(UnitEnum.CENTIMETER);
				DigitalQuantity digitalQuantity = DigitalQuantity.valueOf("12.01pt");
				DigitalQuantity converted = converter.convertFrom(digitalQuantity);
				// Calculate what should be the result
				DigitalQuantity shouldBe = new DigitalQuantity(UnitEnum.CENTIMETER, 12.01 * 0.035278);
				assertEquals(0, converted.compareTo(shouldBe));
			}
			
			@DisplayName("Convert from negative value")
			@Test
			public void convertFromNegativeValue() {
				UnitConverter converter = awtFactory.factory(UnitEnum.CENTIMETER);
				DigitalQuantity digitalQuantity = DigitalQuantity.valueOf("-50.54mm");
				DigitalQuantity converted = converter.convertFrom(digitalQuantity);
				// Calculate what should be the result
				DigitalQuantity shouldBe = new DigitalQuantity(UnitEnum.CENTIMETER, -5.054);
				assertEquals(0, converted.compareTo(shouldBe));
			}
			
		}
		
		@DisplayName("Converting to POINT tests")
		@Nested
		public class PointConvertingTests {
			
			@DisplayName("Convert from null")
			@Test
			public void convertNull() {
				UnitConverter converter = awtFactory.factory(UnitEnum.POINT);
				assertEquals(null, converter.convertFrom(null));
			}
			
			@DisplayName("Convert from PIXEL")
			@Test
			public void convertFromPixel() {
				UnitConverter converter = awtFactory.factory(UnitEnum.POINT);
				DigitalQuantity digitalQuantity = DigitalQuantity.valueOf("182px");
				DigitalQuantity converted = converter.convertFrom(digitalQuantity);
				// Calculate what should be the result
				int dpi = awtFactory.getScreenResolution();
				DigitalQuantity shouldBe = new DigitalQuantity(UnitEnum.POINT, (182.0*72)/dpi);
				assertEquals(0, converted.compareTo(shouldBe));
			}
			
			@DisplayName("Convert from METER")
			@Test
			public void convertFromM() {
				UnitConverter converter = awtFactory.factory(UnitEnum.POINT);
				DigitalQuantity digitalQuantity = DigitalQuantity.valueOf("5m");
				DigitalQuantity converted = converter.convertFrom(digitalQuantity);
				// Calculate what should be the result
				DigitalQuantity shouldBe = new DigitalQuantity(UnitEnum.POINT, 5.0 * 2834.64566929);
				assertEquals(0, converted.compareTo(shouldBe));
			}
			
			@DisplayName("Convert from CENTIMETER")
			@Test
			public void convertFromCm() {
				UnitConverter converter = awtFactory.factory(UnitEnum.POINT);
				DigitalQuantity digitalQuantity = DigitalQuantity.valueOf("50cm");
				DigitalQuantity converted = converter.convertFrom(digitalQuantity);
				// Calculate what should be the result
				DigitalQuantity shouldBe = new DigitalQuantity(UnitEnum.POINT, 50.0 * 28.34645669);
				assertEquals(0, converted.compareTo(shouldBe));
			}
			
			@DisplayName("Convert from MILLIMETER")
			@Test
			public void convertFromMm() {
				UnitConverter converter = awtFactory.factory(UnitEnum.POINT);
				DigitalQuantity digitalQuantity = DigitalQuantity.valueOf("100mm");
				DigitalQuantity converted = converter.convertFrom(digitalQuantity);
				// Calculate what should be the result
				DigitalQuantity shouldBe = new DigitalQuantity(UnitEnum.POINT, 100.0 * 2.83464567);
				assertEquals(0, converted.compareTo(shouldBe));
			}
			
			@DisplayName("Convert from INCH")
			@Test
			public void convertFromIn() {
				UnitConverter converter = awtFactory.factory(UnitEnum.POINT);
				DigitalQuantity digitalQuantity = DigitalQuantity.valueOf("250in");
				DigitalQuantity converted = converter.convertFrom(digitalQuantity);
				// Calculate what should be the result
				DigitalQuantity shouldBe = new DigitalQuantity(UnitEnum.POINT, 250*72);
				assertEquals(0, converted.compareTo(shouldBe));
			}
			
			@DisplayName("Convert from POINT")
			@Test
			public void convertFromPt() {
				UnitConverter converter = awtFactory.factory(UnitEnum.POINT);
				DigitalQuantity digitalQuantity = DigitalQuantity.valueOf("12.01pt");
				DigitalQuantity converted = converter.convertFrom(digitalQuantity);
				assertEquals(0, converted.compareTo(digitalQuantity));
			}
			
			@DisplayName("Convert from negative value")
			@Test
			public void convertFromNegativeValue() {
				UnitConverter converter = awtFactory.factory(UnitEnum.POINT);
				DigitalQuantity digitalQuantity = DigitalQuantity.valueOf("-50.54mm");
				DigitalQuantity converted = converter.convertFrom(digitalQuantity);
				// Calculate what should be the result
				DigitalQuantity shouldBe = new DigitalQuantity(UnitEnum.POINT, -50.54 * 2.83464567);
				assertEquals(0, converted.compareTo(shouldBe));
			}
			
		}
		
		@DisplayName("Converting to INCH tests")
		@Nested
		public class InchConvertingTests {
			
			@DisplayName("Convert from null")
			@Test
			public void convertNull() {
				UnitConverter converter = awtFactory.factory(UnitEnum.INCH);
				assertEquals(null, converter.convertFrom(null));
			}
			
			@DisplayName("Convert from PIXEL")
			@Test
			public void convertFromPixel() {
				UnitConverter converter = awtFactory.factory(UnitEnum.INCH);
				DigitalQuantity digitalQuantity = DigitalQuantity.valueOf("182px");
				DigitalQuantity converted = converter.convertFrom(digitalQuantity);
				// Calculate what should be the result
				int dpi = awtFactory.getScreenResolution();
				DigitalQuantity shouldBe = new DigitalQuantity(UnitEnum.INCH, 182.0/dpi);
				assertEquals(0, converted.compareTo(shouldBe));
			}
			
			@DisplayName("Convert from METER")
			@Test
			public void convertFromM() {
				UnitConverter converter = awtFactory.factory(UnitEnum.INCH);
				DigitalQuantity digitalQuantity = DigitalQuantity.valueOf("5m");
				DigitalQuantity converted = converter.convertFrom(digitalQuantity);
				// Calculate what should be the result
				DigitalQuantity shouldBe = new DigitalQuantity(UnitEnum.INCH, 5.0 / 0.0254);
				assertEquals(0, converted.compareTo(shouldBe));
			}
			
			@DisplayName("Convert from CENTIMETER")
			@Test
			public void convertFromCm() {
				UnitConverter converter = awtFactory.factory(UnitEnum.INCH);
				DigitalQuantity digitalQuantity = DigitalQuantity.valueOf("50cm");
				DigitalQuantity converted = converter.convertFrom(digitalQuantity);
				// Calculate what should be the result
				DigitalQuantity shouldBe = new DigitalQuantity(UnitEnum.INCH, 50.0 / 2.54);
				assertEquals(0, converted.compareTo(shouldBe));
			}
			
			@DisplayName("Convert from MILLIMETER")
			@Test
			public void convertFromMm() {
				UnitConverter converter = awtFactory.factory(UnitEnum.INCH);
				DigitalQuantity digitalQuantity = DigitalQuantity.valueOf("100mm");
				DigitalQuantity converted = converter.convertFrom(digitalQuantity);
				// Calculate what should be the result
				DigitalQuantity shouldBe = new DigitalQuantity(UnitEnum.INCH, 100.0 / 25.4);
				assertEquals(0, converted.compareTo(shouldBe));
			}
			
			@DisplayName("Convert from INCH")
			@Test
			public void convertFromIn() {
				UnitConverter converter = awtFactory.factory(UnitEnum.INCH);
				DigitalQuantity digitalQuantity = DigitalQuantity.valueOf("250in");
				DigitalQuantity converted = converter.convertFrom(digitalQuantity);
				assertEquals(0, converted.compareTo(digitalQuantity));
			}
			
			@DisplayName("Convert from POINT")
			@Test
			public void convertFromPt() {
				UnitConverter converter = awtFactory.factory(UnitEnum.INCH);
				DigitalQuantity digitalQuantity = DigitalQuantity.valueOf("12.01pt");
				DigitalQuantity converted = converter.convertFrom(digitalQuantity);
				// Calculate what should be the result
				DigitalQuantity shouldBe = new DigitalQuantity(UnitEnum.INCH, 12.01 / 72);
				assertEquals(0, converted.compareTo(shouldBe));
			}
			
			@DisplayName("Convert from negative value")
			@Test
			public void convertFromNegativeValue() {
				UnitConverter converter = awtFactory.factory(UnitEnum.INCH);
				DigitalQuantity digitalQuantity = DigitalQuantity.valueOf("-50.54mm");
				DigitalQuantity converted = converter.convertFrom(digitalQuantity);
				// Calculate what should be the result
				DigitalQuantity shouldBe = new DigitalQuantity(UnitEnum.INCH, -50.54 / 25.4);
				assertEquals(0, converted.compareTo(shouldBe));
			}
			
		}
		
	}

}
