package net.kemuri9.samples.dsannotations;

import java.util.Collection;

/**
 * Service that retrieves all the messages from the individual FactoryMessage instances.
 */
public interface Messages {

	/**
	 * All messages from each individual FactoryMessage instance
	 * @return all FactoryMessage messages.
	 */
	public Collection <String> getMessages();
}
