package filesplitter.gui.panels.rows;

import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.*;

/**
 * A row that allows the user to select the second split mode.
 */
public class SplitMode2 extends TemplateRow implements ItemListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected JRadioButton mode2;
	public static JTextField parts;
	protected JLabel partsLabel;
	
	/**
	 * Creates components to select this mode.
	 * @param splitModeGroup ButtonGroup containing the two split modes.
	 */
	public SplitMode2(ButtonGroup splitModeGroup) {
		super();
		
		mode2 = new JRadioButton("Specifica il numero di parti");
		splitModeGroup.add(mode2);
		mode2.addItemListener(this);
		add(mode2);
		add(Box.createHorizontalStrut(10));
		parts = new JTextField();
		parts.setEditable(false);
		parts.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		parts.setMaximumSize(new Dimension(50, 24));
		parts.setMinimumSize(new Dimension(50, 24));
		parts.setPreferredSize(new Dimension(50, 24));
		add(parts);
		partsLabel = new JLabel("parti");
		add(partsLabel);
	}
	
	/**
	 * Manages the graphic components of this mode.
	 */
	public void itemStateChanged(ItemEvent e) {
		if(mode2.isSelected()) {
			parts.setEditable(true);
		} else {
			parts.setEditable(false);
		}
	}
}
