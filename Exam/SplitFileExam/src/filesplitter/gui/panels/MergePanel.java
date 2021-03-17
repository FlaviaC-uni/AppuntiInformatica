package filesplitter.gui.panels;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import filesplitter.gui.panels.rows.MergeModeOperations;
import filesplitter.gui.panels.rows.MergeModeSelectFile;

/**
 * JPanel for merge mode.
 */
public class MergePanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected MergeModeSelectFile selectFile;
	protected JSeparator sep;
	protected MergeModeOperations decrypt;
	
	/**
	 * Arranges the graphic components horizontally with {@link javax.swing.BoxLayout}.
	 */
	public MergePanel() {
		super();
		
		setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));

		add(Box.createHorizontalStrut(10));
		
		selectFile = new MergeModeSelectFile();
		selectFile.selectFileButton.setToolTipText("Seleziona solo la prima parte del file da ricomporre");
		add(selectFile);
		add(Box.createHorizontalStrut(10));
		
		sep = new JSeparator(JSeparator.VERTICAL);
		add(sep);
		
		
		decrypt = new MergeModeOperations();
		add(decrypt);
	}
}
