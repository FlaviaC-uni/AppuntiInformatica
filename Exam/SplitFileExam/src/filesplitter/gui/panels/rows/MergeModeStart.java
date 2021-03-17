package filesplitter.gui.panels.rows;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import filesplitter.gui.SplitterFrame;
import filesplitter.logic.threadExecution.DecryptModeThread;
import filesplitter.logic.threadExecution.MergeNormalModeThread;
import filesplitter.logic.threadExecution.UnzipModeThread;

/**
 * A row contained in the {@link MergeModeOperations} column.
 */
public class MergeModeStart extends TemplateRow implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static JButton start;
	public static JProgressBar progressBar;
	
	/**
	 * Arranges the graphic components horizontally with {@link BoxLayout}.
	 */
	public MergeModeStart() {
		super();
		setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
		
		start = new JButton("Inizia");
		start.addActionListener(this);
		add(start);
		add(Box.createHorizontalStrut(10));
		
		progressBar = new JProgressBar();
		progressBar.setStringPainted(true);
		add(progressBar);
		add(Box.createHorizontalStrut(10));
		
	}

	/**
	 * Reacts to the button click event by starting the file merging process.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		MergeModeStart.progressBar.setValue(0);
		if(SplitterFrame.parts.size() == 0) {
			MergeModeSelectFile.dtm.setColumnIdentifiers(new Object[] {"Parti"});
			
			int rows = MergeModeSelectFile.dtm.getRowCount();
			for(int index=rows-1; index>=0; index--) {
				MergeModeSelectFile.dtm.removeRow(index);
			}
			
			JOptionPane.showMessageDialog(null,
				    "Seleziona un file da ricomporre",
				    "Nessun file selezionato",
				    JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		Thread mergeOp;
		String fileName = MergeModeSelectFile.fileName;
		
		MergeModeSelectFile.setFileState(" | In esecuzione");
		
		switch (MergeModeSelectFile.mergeMode) {
		case normalMode: {
			mergeOp = new MergeNormalModeThread(fileName);
			mergeOp.start();
			break;
		}
		case compressMode: {
			mergeOp = new UnzipModeThread(fileName);
			mergeOp.start();
			break;
		}
		case cryptMode: {
			mergeOp = new DecryptModeThread(fileName);
			mergeOp.start();
			break;
		}
		}
	}
	
	/**
	 * Updates the status of the file as "Completed".
	 */
	public static void threadCompleted() {
		MergeModeSelectFile.setFileState(" | Completato");
		SplitterFrame.parts.clear();
	}
}
