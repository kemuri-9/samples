package net.kemuri9.samples.dsannotations;

/**
 * Enumerations that can be used as a configuration option
 */
public enum EnumOption {

	NONE("none"),

	OPTION1("opt1"),

	OPTION2("opt2"),

	OPTION3("opt3");

	public final String optValue;

	private EnumOption(String optValue) {
		this.optValue = optValue;
	}
}
