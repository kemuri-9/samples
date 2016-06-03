package net.kemuri9.samples.dsannotations.impl;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

/**
 * Example Configuration class that has OSGi automatically map configurable properties to the respective types.
 * Also with metatype declaration information to have it be visible in the configuration admin service list
 */
@ObjectClassDefinition(name = "Factory Message",
	description = "Configure a Factory Message",
	id = "net.kemuri9.samples.dsannotations.impl.FactoryMessageImpl",
	/* Bug in bnd < 3.2, in needing to specify the pid that this applies to as the
	 * @Designate isn't doing this as it should */
	factoryPid = {"net.kemuri9.samples.dsannotations.impl.FactoryMessageImpl"}
)
public @interface FactoryMessageConfig {

	@AttributeDefinition(name = "Message",
			description = "Configure the Message here")
	String message() default "Default Message";
}
