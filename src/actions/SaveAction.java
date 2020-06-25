package actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

import message.Message;
import model.Modul;
import model.Node;
import model.Parametar;
import model.Proizvod;
import pomocni.FilterFile;
import view.tree.MyJTree;
import view.workspace.MainFrame;
import view.workspace.WorkZone;


public class SaveAction extends MyAbsAction {

	public SaveAction() {
		
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
		        KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		putValue(SMALL_ICON, loadIcon("images/toolbar/save.png"));
		putValue(NAME, "Save ");
		putValue(SHORT_DESCRIPTION, "Save");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		Node n= (Node) WorkZone.getInstance().getjTree().getLastSelectedPathComponent();
		
		if(!(n instanceof Proizvod)){
			Message.getMessage().nijeSelektovanPorizvod("Save");
			return;
		}
		
		
		if(n.isMenjan()){
		
		
		
			FileOutputStream fos;
			ObjectOutputStream oos;
		
			
		if(((Proizvod) n).isCuvan()==true){
		File f= (((Proizvod) n).getFile());
		
		try {
			fos = new FileOutputStream(f);
    		oos= new ObjectOutputStream(fos);
    		oos.writeObject((Proizvod)n);
    		for(Node no: n.getDeca()){
    			System.out.println(no);
    		}
    		oos.close();
    		fos.close();

		} catch (FileNotFoundException e1) {
			Message.getMessage().fajlNijePronadjen();
		} catch (IOException e1) {
			Message.getMessage().greskaPrilikomUpisaIliIspisa();
		}
		
		}else{
	
			
		
		JFileChooser fc= new JFileChooser();
	    fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	    if (fc.showOpenDialog(MainFrame.getInstance()) == JFileChooser.APPROVE_OPTION){
	    	String path=	fc.getSelectedFile().getAbsolutePath();
	    	
	    	String name = JOptionPane.showInputDialog(MainFrame.getInstance(), "Unesite ime koje zelite da dodelite fajlu.");
	    	if(name!=null){
	    		File file= new File(path+File.separator+name+".ins");
	    		((Proizvod) n).setFile(path+File.separator+name+".ins");
	    		
	    		FileOutputStream fos1;
	    		ObjectOutputStream oos1;
				try {
					fos = new FileOutputStream(file);
		    		oos= new ObjectOutputStream(fos);
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
	    	((Proizvod) n).setCuvan(true);
	    }else{
	    	return;
	    }
		}	
		n.setMenjan(false);
		SwingUtilities.updateComponentTreeUI(WorkZone.getInstance().getjTree());
		
		}
	}

}
