package net.kemuri9.samples.dsannotations.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

import org.osgi.framework.ServiceReference;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.FieldOption;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.component.annotations.ReferencePolicyOption;
import org.osgi.service.log.LogService;

import net.kemuri9.samples.dsannotations.FactoryMessage;
import net.kemuri9.samples.dsannotations.Messages;

/**
 * Example of using multiply-bound @Reference on a field
 *
 */
@Component(immediate = true)
public class MessagesServiceImpl implements Messages {

	/* Using a multiple cardinality reference on a field.
	 * Here methods are not invoked, instead the field is directly modified.
	 * There are two field Options to control the behavior - update or replace.
	 * The behaviors are exactly as the name implies,
	 *  whether to update the field with the new service instances,
	 *  or to replace the entire collection with the new service instances. */
	@Reference(
			policy = ReferencePolicy.DYNAMIC,
			cardinality = ReferenceCardinality.MULTIPLE,
			policyOption = ReferencePolicyOption.GREEDY,
			fieldOption = FieldOption.UPDATE)
	private Collection<FactoryMessage> messages = Collections.synchronizedList(new ArrayList<FactoryMessage>());

	@Reference
	private LogService logService;

	private ServiceReference<?> myself;

	@Activate
	protected void activate(ComponentContext context) {
		myself = context.getServiceReference();
		logService.log(myself, LogService.LOG_DEBUG, "activated");
	}

	@Deactivate
	protected void deactivate(ComponentContext context) {
		logService.log(myself, LogService.LOG_DEBUG, "deactivated");
		myself = null;
	}

	@Override
	public Collection<String> getMessages() {
		// make a copy to snapshot the current values
		Collection<FactoryMessage> messages = new ArrayList<>(this.messages);
		// and now get the messages on each of the services
		return messages.stream().map(FactoryMessage::getMessage).collect(Collectors.toList());
	}
}
