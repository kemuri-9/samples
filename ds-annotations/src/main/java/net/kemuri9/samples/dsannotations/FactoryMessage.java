package net.kemuri9.samples.dsannotations;

/**
 * Simple service that is expected to instantiated as a factory, so many instances
 * can exist simultaneously within the OSGi system.
 */
public interface FactoryMessage {

	/**
	 * Retrieve the configured message on the particular instance
	 */
	public String getMessage();
}
