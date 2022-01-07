# digital-units

A small Java library to wrap usually digital/graphical quantities, such as cm, px, in, etc... And deal with them. For example, instead of having to deal with a string like `"157px"`, you can use instantiate a `DigitalUnity` object to encapsulate the values `157` and `"px"`. You can then use an implementation of `UnitConverter` to easily obtain another `DigitalUnity` object that contains those 157px in another unit, for example centimeters, based on the current screen DPI.

## Contributing

This project is always open for contributions!!

# Release Notes

 - **1.0**: There's a ZIP on GitHub release page that contains a java 8 version that was compiled with a little change in the codebase, specifically in class `DigitalQuantity` to use the String methods `trim().isEmpty()` instead of `isBlank()` that is only available from jdk 11 onwards.
