package filesplitter.gui.panels.rows;

import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Enumeration;

import javax.swing.*;
/**
 * A row that allows the user to select the first split mode.
 */
public class SplitMode1 extends TemplateRow implements ItemListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected JRadioButton mode1;
	public static JTextField byteSize;
	public static JComboBox<String> sizeUnit;
	
	ButtonGroup splitMode1Options;
	
	/**
	 * Creates components to select this mode.
	 * @param splitModeGroup ButtonGroup containing the two split modes.
	 * @param splitMode1Options ButtonGroup containing the options of the first split mode.
	 */
	public SplitMode1(ButtonGroup splitModeGroup, ButtonGroup splitMode1Options) {
		super();
		
		mode1 = new JRadioButton("Specifica la dimensione (uguale) di ogni parte");
		mode1.setSelected(true);
		splitModeGroup.add(mode1);
		
		this.splitMode1Options = splitMode1Options;
		
		mode1.addItemListener(this);
		add(mode1);
		add(Box.createHorizontalStrut(10));
		byteSize = new JTextField();
		byteSize.setMaximumSize(new Dimension(100, 24));
		byteSize.setMinimumSize(new Dimension(100, 24));
		byteSize.setPreferredSize(new Dimension(100, 24));
		byteSize.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		add(byteSize);
		sizeUnit = new JComboBox<String>();
		
		sizeUnit.addItem("Byte");
		sizeUnit.addItem("Kilobyte");
		sizeUnit.addItem("Megabyte");
		sizeUnit.addItem("Gigabyte");
		sizeUnit.setMaximumSize(new Dimension(120, 24));
		sizeUnit.setMinimumSize(new Dimension(120, 24));
		sizeUnit.setPreferredSize(new Dimension(120, 24));
		sizeUnit.setEditable(false);
		add(sizeUnit);
		
		add(Box.createHorizontalStrut(10));
		
		
	}
	
	/**
	 * Manages the graphic components of this mode.
	 */
	public void itemStateChanged(ItemEvent e) {
		Enumeration<AbstractButton> r = splitMode1Options.getElements();
		if(mode1.isSelected()) {
			byteSize.setEditable(true);
			sizeUnit.setEnabled(true);
			while (r.hasMoreElements()) {
				JRadioButton b = (JRadioButton)r.nextElement();
				b.setEnabled(true);
				if(b.getText().equals("Proteggi i file") && b.isSelected()) {
					SplitMode1Encrypt.setkeyFieldEditable(true);
				}
			}
			
		} else {
			SplitMode1Encrypt.resetKeyField();
			byteSize.setEditable(false);
			sizeUnit.setEnabled(false);
			while (r.hasMoreElements()) {
				r.nextElement().setEnabled(false);	
			}
			SplitMode1Encrypt.setkeyFieldEditable(false);
		}
		
	}
	
	
}
