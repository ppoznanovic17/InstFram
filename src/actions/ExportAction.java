package actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import message.Message;
import model.Modul;
import model.Node;
import model.Parametar;
import model.Proizvod;
import view.workspace.MainFrame;
import view.workspace.WorkZone;

public class ExportAction extends MyAbsAction {

	 public ExportAction() {
		
		putValue(SMALL_ICON, loadIcon("images/toolbar/export.png"));
		putValue(NAME, "Export");
		putValue(SHORT_DESCRIPTION, "Export");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Node n= (Node) WorkZone.getInstance().getjTree().getLastSelectedPathComponent();
		if(!(n instanceof Proizvod)) return;
		Proizvod p=(Proizvod) n;
		
		
		Path path=null;
		JFileChooser fc= new JFileChooser();
		fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		if(fc.showOpenDialog(MainFrame.getInstance())==JFileChooser.APPROVE_OPTION) {
			try {
				path=Files.createDirectories(Paths.get(fc.getSelectedFile().getAbsolutePath()));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		String name = JOptionPane.showInputDialog(MainFrame.getInstance(), "Unesite ime koje zelite da dodelite fajlu.");
    	if(name!=null){
    		File file= new File(path.toString()+File.separator+name+".ins");
    		
    		
    		
			try {
				FileOutputStream fos = new FileOutputStream(file);
				ObjectOutputStream oos= new ObjectOutputStream(fos);
	    		oos.writeObject((Proizvod)n);
	    		for(Node no: n.getDeca()){
	    			System.out.println(no);
	    		}
	    		oos.close();
	    		fos.close();
	    		((Proizvod) n).setCuvan(true);

			} catch (FileNotFoundException e1) {
				Message.getMessage().fajlNijePronadjen();
			} catch (IOException e1) {
				Message.getMessage().greskaPrilikomUpisaIliIspisa();
			}
    		
    		
    	}
    	
		try {
			path=Files.createDirectories(Paths.get(path.toString()+File.separatorChar+"resursi"));
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
			System.out.println( p.getInstaliraSe());
			System.out.println( p.getInstaliraSeIme());
			System.out.println( path.toString());
		copyToDestFolder(path.toString(),p.getInstaliraSe(), p.getInstaliraSeIme());
		if(p.getSvi().size()>0) {
			int i=1;
		for(Node x: p.getDeca()) {
			if(x instanceof Parametar) {
				if(((Parametar) x).getTip().equals("LOGO")) {
					System.out.println(copyToDestFolder(path.toString(), ((Parametar) x).getPath(), "slika"+i+".png"));
					i++;
				}
			}else {
				for(Node y: x.getDeca()) {
					if(((Parametar) y).getTip().equals("LOGO")) {
						System.out.println(copyToDestFolder(path.toString(), ((Parametar) y).getPath(), "slika"+i+".png"));
						i++;
					}
				}
		}
		}
		}
	}
	public boolean copyToDestFolder(String destPath, String sourcePath, String fileName) {

		boolean result = true;

		Path source = Paths.get(sourcePath);
		Path destination = Paths.get(destPath + File.separatorChar + fileName);

		try {
			Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = false;
		}

		return result;

	}
	
}
