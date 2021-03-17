package filesplitter.logic.config;

import filesplitter.gui.panels.rows.SplitMode1Encrypt;

/**
 * A class containing the options for the first split mode (with encryption).
 */
public class EncryptConfig extends Mode1Config {
	private String key;
	/**
	 * Sets the parameters for the first split mode (with encryption).
	 * @param description File description.
	 */
	public EncryptConfig(StringBuffer description) {
		super(description);
		description.append(" | Criptato");
		if(errorOccurred()) return;
		if(SplitMode1Encrypt.isKeyFieldEmpty()) {
			missingValue("Inserisci la chiave di cifratura");
			return;
		}
		key = SplitMode1Encrypt.getKey();
	}
	/**
	 * Not used in this class.
	 */
	@Override
	public String getExtension() {
		return "";
	}
	/**
	 * Returns the encryption key.
	 * @return the encryption key.
	 */
	@Override
	public String getKey() {
		return key;
	}
}
