package filesplitter.logic.config;

import filesplitter.gui.panels.rows.SplitMode2;

/**
 * A class containing the options for the second split mode.
 */
public class Mode2Config extends Config {
	private int parts;
	/**
	 * Sets the parameters for the second split mode.
	 * @param description File description.
	 */
	public Mode2Config(StringBuffer description) {
		super();
		try {
			parts = Integer.parseInt(SplitMode2.parts.getText());
			if(parts<=0) throw new NumberFormatException();
		} catch (NumberFormatException exception2) {
			missingValue("Inserisci il numero di parti da generare");
			return;
		}
		description.append("Dividi in " + parts + " parti");
	}
	/**
	 * Returns the number of parts.
	 * @return the number of parts.
	 */
	@Override
	public Object getValue() {
		return parts;
	}
	/**
	 * Not used in this class.
	 */
	@Override
	public String getSizeUnit() {
		// TODO Auto-generated method stub
		return null;
	}
}
