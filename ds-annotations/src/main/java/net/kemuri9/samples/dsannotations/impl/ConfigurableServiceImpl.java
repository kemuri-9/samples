package net.kemuri9.samples.dsannotations.impl;

import org.osgi.framework.ServiceReference;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.log.LogService;
import org.osgi.service.metatype.annotations.Designate;

import net.kemuri9.samples.dsannotations.ConfigurableService;
import net.kemuri9.samples.dsannotations.EnumOption;

@Component(
		// not necessary to explicitly specify here, as it is defaulted to be all the implemented interfaces
		//service = {ConfigurableService.class},
		immediate = true,
		// example of one variety of non configurable properties
		property = {
				"prop1=string",
				/* when using non-string properties, the type is specified with via ":<type>", such as below.
				 * type names should match the values declared in org.osgi.service.metatype.annotations.AttributeDefinition */
				"prop2:Boolean=true", // boolean property
				"prop3:Integer=5", // integer property
				// multivalue property
				"propm=one",
				"propm=two",
				"propm=three"
		}
	)
@Designate(ocd = ConfigurableServiceConfig.class)
public class ConfigurableServiceImpl implements ConfigurableService {

	/* utilize Reference on field, the simple use-case */
	@Reference
	private LogService log;

	private ServiceReference<?> myself;

	private ConfigurableServiceConfig config;

	/** Life cycle method called when the component is activated.
	 * valid arguments are ComponentContext - to receive the component's context
	 * BundleContext - to receive the component bundle's context,
	 * int - to receive the reason for activation,
	 * Map<String, Object> - to receive the component's properties,
	 * any custom annotation - to receive the component's properties mapped/converted into the annotation. */
	@Activate
	protected void activate(ComponentContext context, ConfigurableServiceConfig config) {
		myself = context.getServiceReference();
		log.log(myself, LogService.LOG_INFO, "activated: " + context.getProperties());
		this.config = config;
	}

	@Modified
	protected void modified(ComponentContext context, ConfigurableServiceConfig config) {
		log.log(myself, LogService.LOG_INFO, "modified: " + context.getProperties());
		this.config = config;
	}

	@Deactivate
	protected void deactivate(ComponentContext context) {
		log.log(myself, LogService.LOG_INFO, "deactivated");
		myself = null;
		config = null;
	}

	@Override
	public byte getByte() {
		return config.byteValue();
	}

	@Override
	public char getChar() {
		return config.charValue();
	}

	@Override
	public double getDouble() {
		return config.doubleValue();
	}

	@Override
	public EnumOption getEnumValue() {
		return config.enumValue();
	}

	@Override
	public EnumOption getEnumValue2() {
		return config.enumValue2();
	}

	@Override
	public float getFloat() {
		return config.floatValue();
	}

	@Override
	public int getInteger() {
		return config.integerValue();
	}

	@Override
	public long getLong() {
		return config.longValue();
	}

	@Override
	public long[] getLongArray() {
		return config.longArrayValues();
	}

	@Override
	public String getPassword() {
		return config.password();
	}

	@Override
	public short getShort() {
		return config.shortValue();
	}

	@Override
	public String getString() {
		return config.stringValue();
	}

	@Override
	public String[] getStringArray() {
		return config.stringArrayValues();
	}
}
