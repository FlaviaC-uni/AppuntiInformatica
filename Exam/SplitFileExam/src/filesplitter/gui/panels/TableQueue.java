package filesplitter.gui.panels;

import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import filesplitter.gui.panels.rows.SplitOperations;

/**
 * A JTable to list queued files.
 */
public class TableQueue extends JPanel implements MouseListener, ListSelectionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static JTable tableQueue;
	protected JScrollPane scrollPaneQueue;
	public static FileTableModel dtm;
	
	public TableQueue() {
		dtm = new FileTableModel();
		String header[] = new String[] { "File", "Dimensione", "Descrizione", "Stato"};
		dtm.setColumnIdentifiers(header);
		
		tableQueue = new JTable();
		tableQueue.setModel(dtm);
		tableQueue.getSelectionModel().addListSelectionListener(this);
		
		tableQueue.setPreferredScrollableViewportSize(new Dimension(0, 100));
		
		scrollPaneQueue = new JScrollPane(tableQueue);
		scrollPaneQueue.addMouseListener(this);
		
		setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
		add(scrollPaneQueue);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(!tableQueue.isEnabled()) {
			return;
		}
		if(!(e.getComponent().equals(tableQueue))) {
			tableQueue.clearSelection();
			SplitOperations.removeFiles.setEnabled(false);
			SplitOperations.editFiles.setEnabled(false);
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if(!tableQueue.isEnabled()) {
			return;
		}
		SplitOperations.removeFiles.setEnabled(true);
		SplitOperations.editFiles.setEnabled(true);
	}
}
