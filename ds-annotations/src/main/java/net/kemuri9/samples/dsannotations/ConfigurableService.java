package net.kemuri9.samples.dsannotations;

/** Example Service declaration */
public interface ConfigurableService {

	/**
	 * Retrieve configured byte value, or the default value if one is not configured
	 * @return configured byte value
	 */
	public byte getByte();

	/**
	 * Retrieve configured byte value, or the default value if one is not configured
	 * @return configured byte value
	 */
	public char getChar();

	/**
	 * Retrieve configured double value, or the default value if one is not configured
	 * @return configured double value
	 */
	public double getDouble();

	/**
	 * Retrieve configured enum value, or the default value if one is not configured
	 * @return configured enum value
	 */
	public EnumOption getEnumValue();

	/**
	 * Retrieve configured second enum value, or the default value if one is not configured
	 * @return configured second enum value
	 */
	public EnumOption getEnumValue2();

	/**
	 * Retrieve configured float value, or the default value if one is not configured
	 * @return configured float value
	 */
	public float getFloat();

	/**
	 * Retrieve configured integer value, or the default value if one is not configured
	 * @return configured integer value
	 */
	public int getInteger();

	/**
	 * Retrieve configured long value, or the default value if one is not configured
	 * @return configured long value
	 */
	public long getLong();

	/**
	 * Retrieve configured long values, or the default values if not configured
	 * @return configured long values
	 */
	public long[] getLongArray();

	/**
	 * Retrieve configured password value, or the default value if one is not configured
	 * @return configured password value
	 */
	public String getPassword();

	/**
	 * Retrieve configured short value, or the default value if one is not configured
	 * @return configured short value
	 */
	public short getShort();

	/**
	 * Retrieve configured string value, or the default value if one is not configured
	 * @return configured string value
	 */
	public String getString();

	/**
	 * Retrieve configured string values, or the default values if not configured
	 * @return configured long values
	 */
	public String[] getStringArray();
}
