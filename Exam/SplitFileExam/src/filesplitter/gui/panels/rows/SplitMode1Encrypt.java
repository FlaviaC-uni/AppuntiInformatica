package filesplitter.gui.panels.rows;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.*;

/**
 * A row that allows the user to select the first split mode (with encryption).
 */
public class SplitMode1Encrypt extends TemplateRow implements ItemListener, ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected static JRadioButton encrypted;
	protected JSeparator sep;
	protected JLabel keyLabel;
	private static JPasswordField keyField;
	protected static JCheckBox showKey;
	private static char originalEchoChar;
	
	/**
	 * Creates components to select (and configure) this mode.
	 * @param splitMode1Options ButtonGroup containing the options of the first split mode.
	 */ 
	public SplitMode1Encrypt(ButtonGroup splitMode1Options) {
		super();
		
		
		add(Box.createHorizontalStrut(30));
		encrypted = new JRadioButton("Proteggi i file");
		splitMode1Options.add(encrypted);
		encrypted.addItemListener(this);
		add(encrypted);
		add(Box.createHorizontalStrut(10));
		
		sep = new JSeparator(JSeparator.VERTICAL);
		sep.setMinimumSize(new Dimension(10, 24));
		sep.setMaximumSize(new Dimension(10, 24));
		sep.setPreferredSize(new Dimension(10, 24));
		add(sep);
		add(Box.createHorizontalStrut(10));
		
		keyLabel = new JLabel("Chiave");
		add(keyLabel);
		add(Box.createHorizontalStrut(10));
		keyField = new JPasswordField();
		keyField.setEditable(false);
		
		
		keyField.setMaximumSize(new Dimension(200, 24));
		keyField.setMinimumSize(new Dimension(200, 24));
		keyField.setPreferredSize(new Dimension(200, 24));
		
		
		add(keyField);
		
		showKey = new JCheckBox("Mostra chiave");
		showKey.setEnabled(false);
		showKey.addActionListener(this);
		add(showKey);
		add(Box.createHorizontalStrut(10));
		originalEchoChar = keyField.getEchoChar();
		
	}
	
	/**
	 * Manages the graphic components of this mode.
	 */
	public void itemStateChanged(ItemEvent e) {
		if(encrypted.isSelected()) {
			keyField.setEditable(true);
			showKey.setEnabled(true);
		} else {
			keyField.setEditable(false);
			showKey.setEnabled(false);
			resetKeyField();
		}
		
	}
	
	/**
	 * Class method to enable/disable a JPasswordField.
	 * @param b Boolean value to enable/disable JPasswordField.
	 */
	protected static void setkeyFieldEditable(boolean b) {
		keyField.setEditable(b);
		showKey.setEnabled(b);
		
	}
	
	/**
	 * Class method to restore JPasswordField content.
	 */
	protected static void resetKeyField() {
		keyField.setText("");
		keyField.setEchoChar(originalEchoChar);
		showKey.setSelected(false);
	}
	
	/**
	 * Class method that checks if JPasswordField is empty.
	 * @return true if it's empty, false otherwise.
	 */
	public static boolean isKeyFieldEmpty() {
		if(encrypted.isSelected() && keyField.getPassword().length==0) {
			return true;
		}
		return false;
	}
	
	/**
	 * Reacts to the JCheckBox click event by showing/hiding the entered password.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(showKey.isSelected()) {
			keyField.setEchoChar((char)0);
		} else {
			keyField.setEchoChar(originalEchoChar);
		}
		
	}
	
	/**
	 * Returns the password entered in the JPasswordField.
	 * @return a string containing the password.
	 */
	public static String getKey() {
		String passText = new String(keyField.getPassword());
		return passText;
	}
	
	
}
