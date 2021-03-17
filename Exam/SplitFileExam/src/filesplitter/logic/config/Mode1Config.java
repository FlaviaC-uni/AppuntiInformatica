package filesplitter.logic.config;

import filesplitter.gui.panels.rows.SplitMode1;

/**
 * A class containing the options for the first split mode.
 */
public class Mode1Config extends Config {
	private long partSize;
	private Config.partUnit unit;
	/**
	 * Sets the parameters for the first split mode.
	 * @param description File description.
	 */
	public Mode1Config(StringBuffer description) {
		super();
		try {
			partSize = Integer.parseInt(SplitMode1.byteSize.getText()); 
			if(partSize<=0) throw new NumberFormatException();
		} catch (NumberFormatException exception) {
			missingValue("Inserisci la dimensione dei file da generare.");
			return;
		}
		
		switch (SplitMode1.sizeUnit.getSelectedItem().toString()) {
		case "Byte": {
			unit = partUnit.B;
			break;
		}
		case "Kilobyte": {
			unit = partUnit.KB;
			break;
		}
		case "Megabyte": {
			unit = partUnit.MB;
			break;
		}
		case "Gigabyte": {
			unit = partUnit.GB;
			break;
		}
		}
		description.append("Parti uguali di " + partSize + getSizeUnit());
	}

	/**
	 * Returns the size of each part.
	 * @return the size of each part.
	 */
	@Override
	public Object getValue() {
		return partSize;
	}
	/**
	 * Returns a string containing the unit of measurement of the file size.
	 * @return a string containing the unit of measurement of the file size..
	 */
	@Override
	public String getSizeUnit() {
			switch (unit) {
			case B: {
				return " B";
			}
			case KB: {
				return " KB";
			}
			case MB: {
				return " MB";
			}
			case GB: {
				return " GB";
			}
			default: {
				return " B";
			}
			}
		
	}
	
	
}
