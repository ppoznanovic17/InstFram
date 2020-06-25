package actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import message.Message;
import model.Node;
import model.Proizvod;
import view.workspace.MainFrame;
import view.workspace.WorkZone;

public class SaveAsAction extends MyAbsAction {

	public SaveAsAction() {
		
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
		        KeyEvent.VK_P, ActionEvent.CTRL_MASK));
		putValue(SMALL_ICON, loadIcon("images/toolbar/saveas.png"));
		putValue(NAME, "Save As ");
		putValue(SHORT_DESCRIPTION, "Save As");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Node n= (Node) WorkZone.getInstance().getjTree().getLastSelectedPathComponent();
		n.setMenjan(false);
		
		if(n instanceof Proizvod){
			JFileChooser fc= new JFileChooser();
		    fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		    if (fc.showOpenDialog(MainFrame.getInstance()) == JFileChooser.APPROVE_OPTION){
		    	String path=	fc.getSelectedFile().getAbsolutePath();
		    	
		    	String name = JOptionPane.showInputDialog(MainFrame.getInstance(), "Unesite ime koje zelite da dodelite fajlu.");
		    	if(name!=null){
		    		File file= new File(path+File.separator+name+".ins");
		    		((Proizvod) n).setFile(path+File.separator+name+".ins");
		    		((Proizvod) n).setCuvan(true);
		    		FileOutputStream fos1;
		    		ObjectOutputStream oos1;
					try {
						fos1 = new FileOutputStream(file);
			    		oos1= new ObjectOutputStream(fos1);
			    		oos1.writeObject((Proizvod)n);
			    		oos1.close();
			    		fos1.close();

					} catch (FileNotFoundException e1) {
						Message.getMessage().fajlNijePronadjen();
					} catch (IOException e1) {
						Message.getMessage().greskaPrilikomUpisaIliIspisa();
					}
		    		
		    		
		    	}
		    }
		    ((Proizvod) n).setCuvan(true);
			}else
			Message.getMessage().nijeSelektovanPorizvod("Save As");
	    		
	    		
	    	
	    }
		
	}


