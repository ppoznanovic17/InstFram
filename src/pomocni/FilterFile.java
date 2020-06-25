package pomocni;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class FilterFile extends FileFilter {

	public FilterFile() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean accept(File f) {
		return (f.isDirectory() || f.getName().toLowerCase().endsWith(".json")); 
	}

	@Override
	public String getDescription() {
		return "JSON (*.json)"; 

	}

	
}
