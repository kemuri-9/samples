package net.kemuri9.samples.dsannotations.impl;

import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.ComponentFactory;
import org.osgi.service.component.ComponentInstance;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.component.annotations.ReferencePolicyOption;
import org.osgi.service.log.LogService;

/**
 * Convert Factory components into their respective services by instantiating them.
 * A quirk of the OSGi specification that some implementations
 * such as Felix offer their own specific workarounds for.
 */
@Component(
		immediate = true,
		// example usage of declaring references as part of the component header definition
		reference = {
				@Reference(name = "log", service = LogService.class)
		})
public class FactoryToInstanceConverter {

	private Map<ComponentFactory, ComponentInstance> components;

	private LogService logService;

	public FactoryToInstanceConverter() {
		/* as apparently some OSGi implementations implement hashcode in a way
		 * that multiple distinct ComponentFactories have the same hashcode HashMap is not usable/reliable,
		 * So the behavior can be avoided by using TreeMap with a custom comparator, in which
		 * the comparator utilized here is one that compares the results of Object.toString() */
		components = Collections.synchronizedMap(new TreeMap<ComponentFactory, ComponentInstance>(new Comparator<ComponentFactory>() {
			@Override
			public int compare(ComponentFactory o1, ComponentFactory o2) {
				String s1 = o1.getClass() + Integer.toHexString(System.identityHashCode(o1));
				String s2 = o2.getClass() + Integer.toHexString(System.identityHashCode(o2));
				return s1.compareTo(s2);
			}
		}));
	}

	@Activate
	protected void activate(ComponentContext ctx) {
		// retrieve the handle to the service declared in the @Component declaration
		// the name here must match the name as declared in the @Reference above
		logService = (LogService) ctx.locateService("log");
	}

	@Deactivate
	protected void deactivate(ComponentContext ctx) {
		components.clear();
	}

	/* when using Reference on a method it indicates the method to call when binding the service.
	 * In this case that's greedily updated as the system gets and removes the instances of the service. */
	@Reference(policy = ReferencePolicy.DYNAMIC,
			cardinality = ReferenceCardinality.MULTIPLE,
			policyOption = ReferencePolicyOption.GREEDY)
	protected void addFactory(ComponentFactory compFactory) {
		ComponentInstance instance = compFactory.newInstance(null);
		// due to the binding occurring before the activate, this can be null!
		if (logService != null) {
			logService.log(LogService.LOG_DEBUG, "instantiated component: " + instance);
		}
		components.put(compFactory, instance);
	}

	/* "Update" or Modified method matchs to bind method of convention changing 'add' to 'updated'. This can be
	 * specified in the @Reference annotation via the updated property if not wanting to leave it to convention. */
	protected void updatedFactory(ComponentFactory compFactory) {
		/* though this is actually here for completion, it's not actually
		 * called since ComponentFactory's don't usually get updated... */
		ComponentInstance instance = components.remove(compFactory);
		if (instance != null) {
			instance.dispose();
		}
		instance = compFactory.newInstance(null);
		components.put(compFactory, instance);
	}

	/* Unbind method matches to bind method by convention of changing 'add' to 'remove'. This can be
	 * specified in the @Reference annotation via the unbind property if not wanting to leave it to convention. */
	protected void removeFactory(ComponentFactory compFactory) {
		ComponentInstance instance = components.remove(compFactory);
		if (instance != null) {
			logService.log(LogService.LOG_INFO, "removed component: " + instance);
			instance.dispose();
		}
	}
}
