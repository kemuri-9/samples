package net.kemuri9.samples.dsannotations.impl;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;
import org.osgi.service.metatype.annotations.Option;

import net.kemuri9.samples.dsannotations.EnumOption;

/**
 * Example Configuration class that has OSGi automatically map configurable properties to the respective types.
 * Also with metatype declaration information to have it be visible in the configuration admin
 * Also with use of localization for metadata information.
 */
@ObjectClassDefinition(name = "%net.kemuri9.samples.dsannotations.impl.ConfigurableServiceImpl.name",
	description = "%net.kemuri9.samples.dsannotations.impl.ConfigurableServiceImpl.description",
	id = "net.kemuri9.samples.dsannotations.impl.ConfigurableServiceImpl"
)
@interface ConfigurableServiceConfig {

	@AttributeDefinition(name = "%net.kemuri9.samples.dsannotations.impl.ConfigurableServiceImpl.byteValue.name",
			description = "%net.kemuri9.samples.dsannotations.impl.ConfigurableServiceImpl.byteValue.description")
	byte byteValue() default 5;

	/* Bug: default for char values get written to XML as integer value instead of character itself,
	 * so does not actually utilize declared default in the configuration admin correctly.
	 * Likely tied to the compiler's character behaviorism in that characters are converted to their integer values. */
	@AttributeDefinition(name = "%net.kemuri9.samples.dsannotations.impl.ConfigurableServiceImpl.charValue.name",
			description = "%net.kemuri9.samples.dsannotations.impl.ConfigurableServiceImpl.charValue.description")
	char charValue() default 'c';

	@AttributeDefinition(name = "%net.kemuri9.samples.dsannotations.impl.ConfigurableServiceImpl.doubleValue.name",
			description = "%net.kemuri9.samples.dsannotations.impl.ConfigurableServiceImpl.doubleValue.description")
	double doubleValue() default 3.14;

	// let options auto-generate from enum type information
	@AttributeDefinition(name = "%net.kemuri9.samples.dsannotations.impl.ConfigurableServiceImpl.enumValue.name",
			description = "%net.kemuri9.samples.dsannotations.impl.ConfigurableServiceImpl.enumValue.description")
	EnumOption enumValue() default EnumOption.NONE;

	// specify options ourselves to allow localization
	@AttributeDefinition(name = "%net.kemuri9.samples.dsannotations.impl.ConfigurableServiceImpl.enumValue2.name",
			description = "%net.kemuri9.samples.dsannotations.impl.ConfigurableServiceImpl.enumValue2.description",
			options = {
					@Option(label = "%net.kemuri9.samples.dsannotations.impl.ConfigurableServiceImpl.enumValue2.NONE",
							value = "NONE"),
					@Option(label = "%net.kemuri9.samples.dsannotations.impl.ConfigurableServiceImpl.enumValue2.OPTION1",
							value = "OPTION1"),
					@Option(label = "%net.kemuri9.samples.dsannotations.impl.ConfigurableServiceImpl.enumValue2.OPTION2",
							value = "OPTION2"),
					@Option(label = "%net.kemuri9.samples.dsannotations.impl.ConfigurableServiceImpl.enumValue2.OPTION3",
							value = "OPTION3"),
			})
	EnumOption enumValue2() default EnumOption.NONE;

	@AttributeDefinition(name = "%net.kemuri9.samples.dsannotations.impl.ConfigurableServiceImpl.floatValue.name",
			description = "%net.kemuri9.samples.dsannotations.impl.ConfigurableServiceImpl.floatValue.description")
	float floatValue() default 1.73f;

	@AttributeDefinition(name = "%net.kemuri9.samples.dsannotations.impl.ConfigurableServiceImpl.integerValue.name",
			description = "%net.kemuri9.samples.dsannotations.impl.ConfigurableServiceImpl.integerValue.description")
	int integerValue() default 1000;

	@AttributeDefinition(name = "%net.kemuri9.samples.dsannotations.impl.ConfigurableServiceImpl.longValue.name",
			description = "%net.kemuri9.samples.dsannotations.impl.ConfigurableServiceImpl.longValue.description")
	long longValue() default 5000;

	@AttributeDefinition(name = "%net.kemuri9.samples.dsannotations.impl.ConfigurableServiceImpl.longArrayValues.name",
			description = "%net.kemuri9.samples.dsannotations.impl.ConfigurableServiceImpl.longArrayValues.description")
	long[] longArrayValues() default { 1, 2, 3 };

	@AttributeDefinition(name = "%net.kemuri9.samples.dsannotations.impl.ConfigurableServiceImpl.password.name",
			description = "%net.kemuri9.samples.dsannotations.impl.ConfigurableServiceImpl.password.description",
			type = AttributeType.PASSWORD)
	String password() default "";

	@AttributeDefinition(name = "%net.kemuri9.samples.dsannotations.impl.ConfigurableServiceImpl.shortValue.name",
			description = "%net.kemuri9.samples.dsannotations.impl.ConfigurableServiceImpl.shortValue.description")
	short shortValue() default 500;

	@AttributeDefinition(name = "%net.kemuri9.samples.dsannotations.impl.ConfigurableServiceImpl.stringArrayValues.name",
			description = "%net.kemuri9.samples.dsannotations.impl.ConfigurableServiceImpl.stringArrayValues.description")
	String[] stringArrayValues() default { "one", "two", "three" };

	@AttributeDefinition(name = "%net.kemuri9.samples.dsannotations.impl.ConfigurableServiceImpl.stringValue.name",
			description = "%net.kemuri9.samples.dsannotations.impl.ConfigurableServiceImpl.stringValue.description")
	String stringValue() default "some string";
}
