# Declarative Services (DS) Annotations #

[Back](../README.md "Back")

This is a sample of how to use OSGi R6/Declarative Services 1.3 annotations.
If you were previously using the Felix SCR annotations, they have been deprecated in favor of the DS 1.3 annotations, so you should probably be more inclined to take a peak.

## building the code ##
Currently only maven and gradle are supported

### maven ###

#### Requirements ####
* Maven >= 3.1.0
* Java 1.8

#### Build Command ####
    mvn clean package

### gradle ###
* Java 1.8

#### Build Commands ####

##### Non-Windows or Linux-like environment on Windows #####
    ../gradlew clean jar

##### Windows #####
	..\gradlew clean jar

## Executing the code ##
Deploy the generated jar from whichever build system into your OSGi container of choice.

### Accessing/Testing Services ###
I personally use Felix, which offers a scripting console plugin.
This plugin makes on-the-fly testing pretty easy, so I recommend it for this.
You'll need the Felix Web console installed, with the scripting console plugin, as well as a scripting language.

#### Groovy console script example of accessing services ####
Here is an example Groovy-style script for accessing the services and retrieving their data to see things in effect. 

```groovy
Object messages = osgi.getService(net.kemuri9.samples.dsannotations.Messages.class);
out.println("messages are: ${messages.messages}");

Object configurable = osgi.getService(net.kemuri9.samples.dsannotations.ConfigurableService.class);
out.println("byte: ${configurable.byte}");
out.println("char: ${configurable.char}");
out.println("double: ${configurable.double}");
out.println("enumValue: ${configurable.enumValue}");
out.println("enumValue2: ${configurable.enumValue2.optValue}");
out.println("float: ${configurable.float}");
out.println("integer: ${configurable.integer}");
out.println("long: ${configurable.long}");
out.println("longArray: ${configurable.longArray.join(', ')}");
out.println("password: ${configurable.password}");
out.println("short: ${configurable.short}");
out.println("string: ${configurable.string}");
out.println("stringArray: ${configurable.stringArray.join(', ')}");```
