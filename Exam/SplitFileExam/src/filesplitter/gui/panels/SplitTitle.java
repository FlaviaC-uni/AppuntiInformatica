package filesplitter.gui.panels;

import java.awt.Font;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SplitTitle extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel title2;
	private Font f;
	protected ImageIcon icon;
	protected Image icon1;
	protected Image real;
	
	
	public SplitTitle() {
	
		super();
		
		
		icon = new ImageIcon("\\Users\\cocca\\Desktop\\FSplitter\\File Splitter\\src\\splitter\\gui\\panels\\icon.png");
		icon1 = icon.getImage();
		real = icon1.getScaledInstance(35, 35, java.awt.Image.SCALE_SMOOTH);
	    icon = new ImageIcon(real);
	   
		
		title2 = new JLabel("SPLIT");
		title2.setAlignmentY(CENTER_ALIGNMENT);
		f= new Font("Times New Roman", Font.BOLD, 20);
		title2.setFont(f);
		title2.setIcon(icon);
		add(title2);
		
		
	}
}
