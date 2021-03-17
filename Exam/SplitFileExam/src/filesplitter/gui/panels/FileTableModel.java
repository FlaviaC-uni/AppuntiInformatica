package filesplitter.gui.panels;

import javax.swing.table.DefaultTableModel;

/**
 * A {@link javax.swing.table.DefaultTableModel} with non-editable cells.
 */
public class FileTableModel extends DefaultTableModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FileTableModel() {
		super();
	}

	public boolean isCellEditable(int row,int column){
	    return false;
	  }
}
