package filesplitter.gui;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.*;
import filesplitter.gui.panels.*;
import filesplitter.logic.config.SplitFile;

/**
 * A class representing a custom JFrame.
 */
public class SplitterFrame extends JFrame  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private SplitPanel sPanel;
	private MergePanel mPanel;
	
	private JSeparator sep;
	
	protected MergeTitle mtitle;
	
	
	public static ArrayList<SplitFile> splitFiles;
	public static Vector<File> parts;
	
	/**
	 * Sets the title of the JFrame and adds panels to it.
	 * @param title JFrame title
	 */
	public SplitterFrame(String title) {
		super(title);
		
		SplitterFrame.splitFiles = new ArrayList<SplitFile>();
	
		Container c = getContentPane();		
		c.setLayout(new BoxLayout(c, BoxLayout.PAGE_AXIS));

        
       sPanel = new SplitPanel();
       c.add(sPanel);
       
       sep = new JSeparator(JSeparator.HORIZONTAL);
       add(sep);
       
       mtitle = new MergeTitle();
       add(mtitle);
      
       sep = new JSeparator(JSeparator.HORIZONTAL);
       add(sep);
      
	
       mPanel = new MergePanel();
       c.add(mPanel);
	
	
	}
}
	
