package filesplitter.gui.panels.rows;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Vector;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import filesplitter.gui.SplitterFrame;
import filesplitter.gui.panels.FileTableModel;

/**
 * A column that allows the user to choose the file to be reassembled (only the first part).
 */
public class MergeModeSelectFile extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JButton selectFileButton;
	public JTable partsList;
	public static FileTableModel dtm;
	public JScrollPane fileParts;
	protected JFileChooser fileChooser;
	protected static enum mergeModeEnum {normalMode, compressMode, cryptMode};
	protected static mergeModeEnum mergeMode;
	protected static String fileName;
	
	/**
	 * Arranges the graphic components vertically with {@link BoxLayout}.
	 */
	public MergeModeSelectFile() {
		super();
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		SplitterFrame.parts = new Vector<File>();
		
		fileChooser = new JFileChooser();
		fileChooser.setMultiSelectionEnabled(false);
		fileChooser.setCurrentDirectory(null);
		FileNameExtensionFilter filter = new FileNameExtensionFilter(".par", "par");
		fileChooser.setFileFilter(filter);
		fileChooser.setAcceptAllFileFilterUsed(false);
		
		selectFileButton = new JButton("Seleziona file");
		selectFileButton.setAlignmentX(CENTER_ALIGNMENT);
		selectFileButton.addActionListener(this);
		
		add(Box.createVerticalStrut(10));
		add(selectFileButton);
		add(Box.createVerticalStrut(10));
		
		dtm = new FileTableModel();
		String header[] = new String[] {"Parti"};
		dtm.setColumnIdentifiers(header);
		
		partsList = new JTable();
		partsList.setModel(dtm);
		partsList.setPreferredScrollableViewportSize(new Dimension(200, 0));
		
		fileParts = new JScrollPane(partsList);

		add(fileParts);
	}
	
	/**
	 * Reacts to the button click event by opening a JFileChooser and configuring the merge mode according to the file chosen by the user.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		fileChooser.showOpenDialog(this);
		File path = null;
		try {
			path = new File(fileChooser.getSelectedFile().getParent());
		} catch (NullPointerException ex) {
			return;
		}
		SplitterFrame.parts.clear();
		int rows = dtm.getRowCount();
		for(int index=rows-1; index>=0; index--) {
			dtm.removeRow(index);
		}
		String firstPart = fileChooser.getSelectedFile().getName();
		int firstPartLenght = firstPart.length();
		fileName = "";
		String ext = "";
		

		if(firstPart.endsWith(".1.crypt.par")) {
			fileName = firstPart.substring(0, firstPartLenght - 12);
			ext = ".crypt.par";
			mergeMode = mergeModeEnum.cryptMode;
			enableCryptField(true);
		} else if(firstPart.endsWith(".1.zip.par")) {
			fileName = firstPart.substring(0, firstPartLenght - 10);
			ext = ".zip.par";
			mergeMode = mergeModeEnum.compressMode;
			enableCryptField(false);
		} else if(firstPart.endsWith(".1.par")) {
			fileName = firstPart.substring(0, firstPartLenght - 6);
			ext = ".par";
			mergeMode = mergeModeEnum.normalMode;
			enableCryptField(false);
		} else {
			dtm.setColumnIdentifiers(new Object[] {"Parti"});
			JOptionPane.showMessageDialog(null,
				    "Assicurati di aver selezionato la \nprima parte del file da ricomporre",
				    "File non valido",
				    JOptionPane.WARNING_MESSAGE);
			enableCryptField(false);
			return;
		}
		int partNumber;
		
		File[] listFiles = path.listFiles();
		for(File f: listFiles) {
			if(f.getName().startsWith(fileName) && f.getName().endsWith(ext)) {
				String numberString = f.getName().substring(fileName.length()+1, f.getName().length()-ext.length());
				partNumber = Integer.parseInt(numberString);
				if(SplitterFrame.parts.size() < partNumber) {
					SplitterFrame.parts.setSize(partNumber);
				}
				SplitterFrame.parts.set(partNumber-1, f);	
			}
		}
		int index = 1;
		for(File f: SplitterFrame.parts) {
			if(f == null) {
				JOptionPane.showMessageDialog(null,
					    "Parte " + index + " mancante",
					    "Errore",
					    JOptionPane.ERROR_MESSAGE);
				return;
			}
			index++;
		}
		dtm.setColumnIdentifiers(new Object[] {SplitterFrame.parts.size() + " Parti"});
		for(File f: SplitterFrame.parts) {
			dtm.addRow(new Object[] {f.getName()});
		}
	}
	
	private void enableCryptField(boolean b) {
		MergeModeOperations.keyField.setText("");
		MergeModeOperations.keyField.setEditable(b);
		MergeModeOperations.showKey.setEnabled(b);
		MergeModeOperations.showKey.setSelected(false);
		MergeModeOperations.keyField.setEchoChar(MergeModeOperations.originalEchoChar);
	}
	
	/**
	 * Sets the status of the file (null/running/completed).
	 * @param state File status.
	 */
	public static void setFileState(String state) {
		dtm.setColumnIdentifiers(new Object[] {SplitterFrame.parts.size() + " Parti" + state});
	}
	
}
