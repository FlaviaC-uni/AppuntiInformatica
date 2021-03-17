package filesplitter.logic.config;

import java.io.File;

/**
 * A file with its split configuration.
 */
public class SplitFile {
	/**
	 * The file.
	 */
	private File f;
	/**
	 * Split configuration
	 */
	public FileConfig config;
	/**
	 * File status (Queued/Running/Completed).
	 */
	public String state;
	
	/**
	 * Creates a file with its split configuration.
	 * @param f associated file
	 */
	public SplitFile(File f) {
		this.f = f;
		config = new FileConfig(this);
	}
	
	/**
	 * Returns file status.
	 * @return the string containing the status of the file.
	 */
	public String getState() {
		return state;
	}
	
	/**
	 * Sets file state.
	 * @param state The new state of the file.
	 */
	public void setState(String state) {
		this.state = state;
	}
	
	/**
	 * Returns the file.
	 * @return the file.
	 */
	public File getFile() {
		return f;
	}
	
	/**
	 * Returns the absolute pathname string of the file.
	 * @return the absolute pathname string of the file.
	 */
	public String getPath() {
		return f.getAbsolutePath();
	}
	
	/**
	 * Returns the name of the file.
	 * @return the name of the file.
	 */
	public String getName() {
		return f.getName();
	}
}
