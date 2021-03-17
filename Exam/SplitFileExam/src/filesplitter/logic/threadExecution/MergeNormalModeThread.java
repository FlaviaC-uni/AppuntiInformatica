package filesplitter.logic.threadExecution;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import filesplitter.gui.SplitterFrame;
import filesplitter.gui.panels.rows.MergeModeStart;

/**
 * A thread that merges multiple parts to rebuild a file.
 */
public class MergeNormalModeThread extends Thread {
	String fileName;
	
	/**
	 * Configures the thread before execution.
	 * @param fileName The name of the file that will be reassembled.
	 */
	public MergeNormalModeThread(String fileName){
		super();
		this.fileName = fileName;
		MergeModeStart.start.setEnabled(false);
	}
	
	/**
	 * Starts rebuilding the file.
	 */
	public void run() {
		double partPercentage = 100/SplitterFrame.parts.size();
		double totalPercentage = 0;
		
		try {
			String parent = SplitterFrame.parts.elementAt(0).getParent();
			File f = new File(parent, fileName);
			FileOutputStream out = new FileOutputStream(f);	
			for(File part: SplitterFrame.parts) {
				FileInputStream fis = new FileInputStream(part);

				fis.transferTo(out);
				fis.close();
				totalPercentage+=partPercentage;
				MergeModeStart.progressBar.setValue((int)totalPercentage);
				part.delete();
			}
			totalPercentage = 100;
			MergeModeStart.progressBar.setValue((int)totalPercentage);
			out.close();
			MergeModeStart.start.setEnabled(true);
		} catch (IOException e) {
			System.out.println("I/O Error");
		}
		MergeModeStart.threadCompleted();
	}
	
}
