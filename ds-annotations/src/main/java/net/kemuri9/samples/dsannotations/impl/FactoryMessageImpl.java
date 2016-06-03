package net.kemuri9.samples.dsannotations.impl;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.metatype.annotations.Designate;

import net.kemuri9.samples.dsannotations.FactoryMessage;

/**
 * A Configurable Factory component that can be instantiated many times over
 */
@Component(
		service = {FactoryMessage.class},
		configurationPolicy = ConfigurationPolicy.REQUIRE,
		factory = "net.kemuri9.samples.dsannotations.impl.FactoryMessageImpl")
@Designate(ocd = FactoryMessageConfig.class, factory = true)
public class FactoryMessageImpl implements FactoryMessage {

	public static final String MESSAGE_PROP_NAME = "message";

	private FactoryMessageConfig config;

	@Activate
	protected void activate(FactoryMessageConfig config) {
		this.config = config;
	}

	@Modified
	protected void modified(FactoryMessageConfig config) {
		/* Modified is rather important, as without it on modification the service will be deactivated
		 * and not recoverable with the simplicity of the FactoryToInstanceConverter. */
		this.config = config;
	}

	@Deactivate
	protected void deactivate() {
		config = null;
	}

	@Override
	public String getMessage() {
		return config.message();
	}
}
