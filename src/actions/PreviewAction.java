package actions;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

import message.Message;
import model.Modul;
import model.Node;
import model.Parametar;
import model.Proizvod;
import pomocni.FilterFile;
import view.parametar.Default;
import view.parametar.ParametarCreator;
import view.slider.PanelSlider42;
import view.workspace.MainFrame;

public class PreviewAction extends MyAbsAction {

	 public  PreviewAction() {

		putValue(SMALL_ICON, loadIcon("images/toolbar/play.png"));
		putValue(NAME, "Preview Instalation");
		putValue(SHORT_DESCRIPTION, "Preview");
	}
	
	private int index;
	 private JFrame f;
	@Override
	public void actionPerformed(ActionEvent arg0) {
	
		f= new JFrame();
		final PanelSlider42<JFrame> slider = new PanelSlider42<JFrame>(f);
		final JPanel jPanel = slider.getBasePanel();
		
		
		
		
		
		
		
		ArrayList<JPanel> parametri= new ArrayList<JPanel>();
		f.setLocationRelativeTo(null);
		f.setSize(new Dimension(500,450));
		File file=null;
		JFileChooser fc= new JFileChooser();
		fc.setFileFilter(new FilterFile());
		 if (fc.showOpenDialog(MainFrame.getInstance()) == JFileChooser.APPROVE_OPTION){
			 file= fc.getSelectedFile();
		 }
		 parametri.add(new Default());
		 Node w= null;
		Proizvod o=null;
			try {
				FileInputStream fs = new FileInputStream(file);
				ObjectInputStream os= new ObjectInputStream(fs);
				
				w = (Node) os.readObject();
				 o= (Proizvod) w;
				 slider.setInstaliraSe(o);
				os.close();
				fs.close();
				
				
			} catch (FileNotFoundException e1) {
				Message.getMessage().fajlNijePronadjen();
				
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ClassNotFoundException e1) {
				return;
			}
			for(Node n: o.getDeca()) {
				if(n instanceof Parametar) {
					parametri.add(ParametarCreator.getPanelParametar((Parametar) n));
					System.out.println(n.getName()+"nnnn");
				}
				if(n instanceof Modul) {
					System.out.println("MODULLLL");
					for(Node x: n.getDeca()) {
						System.out.println("deca modula");
						if(x instanceof Parametar) {
							parametri.add(ParametarCreator.getPanelParametar((Parametar) x));
							System.out.println(x.getName()+"xxxx");
						}
					}
				}
			}
			
			System.out.println(parametri.size());
			
				
			for(JPanel par: parametri) {
				slider.addComponent(par);
			}
			slider.setPoslednji(parametri.get(parametri.size()-1));
			slider.setPrvi(parametri.get(0));
			 f.getContentPane().add(jPanel);
			f.setVisible(true);
			
	}

}
