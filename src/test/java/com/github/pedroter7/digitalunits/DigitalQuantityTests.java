package com.github.pedroter7.digitalunits;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("DigitalQuantity class tests")
public class DigitalQuantityTests {
	
	@DisplayName("Object creation tests")
	@Nested
	public class ObjectCreationTests {
		
		@DisplayName("Test directly creating a DigitalQuantity")
		@Test
		public void happyPathway() {
			assertNotNull(new DigitalQuantity(UnitEnum.PIXEL, 500.666));
		}
		
		@DisplayName("Test creating a DigitalQuantity via valueOf(String) method")
		@Test
		public void happyPathway_ValueOfString() {
			DigitalQuantity digitalQuantityDoubleString = DigitalQuantity.valueOf("145.20 cm");
			DigitalQuantity digitalQuantityIntString = DigitalQuantity.valueOf("15px");
			DigitalQuantity digitalQuantitySpacedString = DigitalQuantity.valueOf("  366      in    ");
			DigitalQuantity digitalQuantityNegativeValue = DigitalQuantity.valueOf("-55cm");
			assertNotNull(digitalQuantitySpacedString);
			assertNotNull(digitalQuantityDoubleString);
			assertNotNull(digitalQuantityIntString);
			assertNotNull(digitalQuantityNegativeValue);
			assertEquals(UnitEnum.CENTIMETER, digitalQuantityDoubleString.getUnit());
			assertEquals(145.20, digitalQuantityDoubleString.getValue());
			assertEquals(UnitEnum.PIXEL, digitalQuantityIntString.getUnit());
			assertEquals(15, digitalQuantityIntString.getValue());
			assertEquals(UnitEnum.INCH, digitalQuantitySpacedString.getUnit());
			assertEquals(366, digitalQuantitySpacedString.getValue());
			assertEquals(UnitEnum.CENTIMETER, digitalQuantityNegativeValue.getUnit());
			assertEquals(-55, digitalQuantityNegativeValue.getValue());
		}
		
		@DisplayName("Test directly creating a DigitalQuantity with null UnitEnum")
		@Test
		public void directlyCreateObjectWithNullParameters_ShouldThrowNullPointer() {
			assertThrows(NullPointerException.class, () -> new DigitalQuantity(null, 0));
		}
		
		@DisplayName("Test creating a DigitalQuantity by valueOf(String) with null String")
		@Test
		public void createUsingValueOfStringWithNullString_ShouldThrowNullPointer() {
			assertThrows(NullPointerException.class, () -> DigitalQuantity.valueOf(null));
		}
		
		@DisplayName("Test creating a DigitalQuantity by valueOf(String) with blank Strings")
		@Test
		public void createUsingValueOfStringWithEmptyString_ShouldThrowIllegalArgument() {
			assertThrows(IllegalArgumentException.class, () -> DigitalQuantity.valueOf(""));
			assertThrows(IllegalArgumentException.class, () -> DigitalQuantity.valueOf("          "));
		}
		
		@DisplayName("Test creating a DigitalQuantity by valueOf(String) with a String that is too small to be valid")
		@Test
		public void createUsingValueOfStringWithSmallString_ShouldThrowIllegalArgument() {
			assertThrows(IllegalArgumentException.class, () -> DigitalQuantity.valueOf("a"));
		}
		
		@DisplayName("Test creating a DigitalQuantity by valueOf(String) with a String that is not a fully qualified quantity")
		@Test
		public void createUsingValueOfStringWithNotFullyQualifiedString_ShouldThrowIllegalArgument() {
			assertThrows(IllegalArgumentException.class, () -> DigitalQuantity.valueOf("222"));
		}
		
		@DisplayName("Test creating a DigitalQuantity by valueOf(String) with a quantity that is not supported")
		@Test
		public void createUsingValueOfStringWithUnsupportedUnit_ShouldThrowUnsupported() {
			assertThrows(UnsupportedOperationException.class, () -> DigitalQuantity.valueOf("1500 kg"));
		}
		
		@DisplayName("Test creating a DigitalQuantity by valueOf(String) with an invalid numerical value")
		@Test
		public void createUsingValueOfStringWithInvalidNumericalValue_ShouldThrowIllegalArgument() {
			assertThrows(IllegalArgumentException.class, () -> DigitalQuantity.valueOf("abcd cm "));
		}
		
	}
	
	@DisplayName("Comparison tests")
	@Nested
	public class ObjectComparisonTests {
		
		@DisplayName("Compare DigitalQuantity created with constructor to created with valueOf(String)")
		@Test
		public void compareCreatedWithConstructorAndWithValueOfString() {
			DigitalQuantity constructorCreated = new DigitalQuantity(UnitEnum.CENTIMETER, 155.50);
			DigitalQuantity valueOfCreated = DigitalQuantity.valueOf("155.50cm");
			assertEquals(0, constructorCreated.compareTo(valueOfCreated));
			assertEquals(0, valueOfCreated.compareTo(constructorCreated));
		}
		
		@DisplayName("Compare two DigitalQuantity with different units")
		@Test
		public void compareWithDifferentUnits_ShouldThrowUnsupported() {
			DigitalQuantity digitalQuantity = new DigitalQuantity(UnitEnum.CENTIMETER, -22);
			assertThrows(UnsupportedOperationException.class, () -> digitalQuantity.compareTo(new DigitalQuantity(UnitEnum.INCH, 55)));
		}
		
		@DisplayName("Compare to null")
		@Test
		public void compareWithNull_ShouldThrowNullPointer() {
			DigitalQuantity digitalQuantity = new DigitalQuantity(UnitEnum.POINT, 22.5);
			assertThrows(NullPointerException.class, () -> digitalQuantity.compareTo(null));
		}
		
		@DisplayName("Compare two equal DigitalQuantity")
		@Test
		public void CompareEqualQuantities() {
			DigitalQuantity digitalQuantity1 = new DigitalQuantity(UnitEnum.POINT, 12);
			DigitalQuantity digitalQuantity2 = new DigitalQuantity(UnitEnum.POINT, 12);
			assertEquals(0, digitalQuantity1.compareTo(digitalQuantity2));
		}
		
		@DisplayName("Compare two different DigitalQuantity")
		@Test
		public void CompareDifferentQuantities() {
			DigitalQuantity digitalQuantity1 = new DigitalQuantity(UnitEnum.INCH, 44);
			DigitalQuantity digitalQuantity2 = new DigitalQuantity(UnitEnum.INCH, 2);
			assertEquals(true, digitalQuantity1.compareTo(digitalQuantity2) > 0);
			assertEquals(true, digitalQuantity2.compareTo(digitalQuantity1) < 0);
		}
		
	}

}