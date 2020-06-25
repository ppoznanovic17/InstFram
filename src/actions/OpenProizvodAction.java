package actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileFilter;

import message.Message;
import model.Modul;
import model.Node;
import model.Proizvod;
import model.SoftverskaKompanija;

import pomocni.FilterFile;
import view.workspace.MainFrame;
import view.workspace.WorkZone;

public class OpenProizvodAction extends MyAbsAction {
	
	public OpenProizvodAction() {
		
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
		        KeyEvent.VK_O, ActionEvent.CTRL_MASK));
		putValue(SMALL_ICON, loadIcon("images/toolbar/open.png"));
		putValue(NAME, "Open workspace");
		putValue(SHORT_DESCRIPTION, "Open workspace");
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			JFileChooser fc= new JFileChooser();
			fc.setFileFilter(new FilterFile());
			if(fc.showOpenDialog(MainFrame.getInstance())== JFileChooser.APPROVE_OPTION){
				File f= fc.getSelectedFile();
				boolean pom=false;
				for(Node pomoc: WorkZone.getInstance().getRoot().getDeca()){
				 if(fc.getSelectedFile().getName().equals(pomoc)){
					 pom=true;
				 }
					}
				if(pom==false){
				Node w= null;
				Proizvod o=null;
				try {
					FileInputStream fs = new FileInputStream(f);
					ObjectInputStream os= new ObjectInputStream(fs);
					
					w = (Node) os.readObject();
					 o= (Proizvod) w;
					 o.setCuvan(true);
					o.setFile(fc.getSelectedFile().getAbsolutePath());
					//System.out.println(w.getDeca().get(0));
					/*System.out.println("@@@@"+w+"@@@@");
					for(Node k: w.getDeca()){
						System.out.println(k);
						for(Node j: k.getDeca()){
							System.out.println("--"+j);
							for(Node l: j.getDeca()){
								System.out.println("----"+l);
								if(l instanceof Modul){
									for(Node m: l.getDeca()){
										System.out.println("------"+m);
									}
								}
							}
						}
					}*/
					os.close();
					fs.close();
					
					
				} catch (FileNotFoundException e1) {
					Message.getMessage().fajlNijePronadjen();
					
				} catch (IOException e1) {
					Message.getMessage().greskaPrilikomUpisaIliIspisa();
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					return;
				}
				
				WorkZone.getInstance().getModel().addOnRoot(w);
				
			}
			}else{
				return;
			}
			
			SwingUtilities.updateComponentTreeUI(WorkZone.getInstance());
		}}
