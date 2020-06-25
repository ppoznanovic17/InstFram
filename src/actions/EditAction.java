package actions;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;

import commands.CommandManager;
import commands.EditCommand;
import model.Node;
import model.Parametar;
import view.workspace.MainFrame;
import view.workspace.WorkZone;

public class EditAction extends MyAbsAction {

	public EditAction() {
		putValue(SMALL_ICON, loadIcon("images/toolbar/edit.png"));
		putValue(NAME, "Edit");
		putValue(SHORT_DESCRIPTION, "Edit");
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		Node n= (Node) WorkZone.getInstance().getjTree().getLastSelectedPathComponent();
		if(n instanceof Parametar) {
			Parametar p=(Parametar) n;
			String tip= p.getTip();
			if(tip.equals("LOGO")) {
				EditCommand cmd= new EditCommand(p);
				cmd.setLogoPath(p.getPath());
				
				
				JFileChooser fc= new JFileChooser();
				fc.addChoosableFileFilter(new FileNameExtensionFilter("Image Files", "jpg", "png", "tif"));
			    if (fc.showOpenDialog(MainFrame.getInstance()) == JFileChooser.APPROVE_OPTION){
			    	cmd.setNoviLogoPath(fc.getSelectedFile().getAbsolutePath());
			    }
			    CommandManager.getCommandMannager().addCommand(cmd);
			    //SwingUtilities.updateComponentTreeUI(WorkZone.getInstance());
			}else if(tip.equals("CUSTOM")) {
				EditCommand cmd= new EditCommand(p);
				cmd.setLabelaCust(p.getLabelText());
				cmd.setKomponentaCust(p.getComponentText());
				JFrame f= new JFrame();
				JPanel pnl= new JPanel();
				JButton btn1= new JButton("Menjaj labelu");
				JButton btn2= new JButton("Menjaj sadrzaj komponente");
				btn1.addActionListener(new ActionListener() {
				    @Override
				    public void actionPerformed(ActionEvent e) {
				    	String name = JOptionPane.showInputDialog(MainFrame.getInstance(), "Unesite zeljeni sadrzaj labele.");
				    	if(name!=null){
				    		cmd.setNovaLabelaCust(name);
				    		cmd.setNovaKomponentaCust(p.getComponentText());
				    		System.out.println(p.getComponentText()+"---");
				    	}
				    	f.setVisible(false);
				    	
				    }}
				);
				btn2.addActionListener(new ActionListener() {
				    @Override
				    public void actionPerformed(ActionEvent e) {
				    	String name = JOptionPane.showInputDialog(MainFrame.getInstance(), "Unesite zeljeni sadrzaj komponente.");
				    	if(name!=null){
				    		cmd.setNovaKomponentaCust(name);
				    		cmd.setNovaLabelaCust(p.getLabelText());
				    	}
				    	f.setVisible(false);
				    }}
				);
				pnl.add(btn1,BorderLayout.NORTH);
				pnl.add(btn2,BorderLayout.SOUTH);
				pnl.add(new JLabel("             Promeni labelu( \u2191 ) ili sadrzaj komponente( \u2191 )"), BorderLayout.CENTER);
				f.add(pnl);
				f.setVisible(true);
				f.setLocationRelativeTo(null);
				f.setSize(new Dimension(400,300));
				CommandManager.getCommandMannager().addCommand(cmd);
				//	SwingUtilities.updateComponentTreeUI(WorkZone.getInstance());
			}else {
				String name = JOptionPane.showInputDialog(MainFrame.getInstance(), "Unesite zeljeni tekst koji zelite da dodelite parametru.");
				EditCommand cmd= new EditCommand(p);
				if(name!=null){
		    		cmd.setProsloIme(p.getSadrzaj());
		    		cmd.setNovoIme(name);
		    		}
				CommandManager.getCommandMannager().addCommand(cmd);
		    	//SwingUtilities.updateComponentTreeUI(WorkZone.getInstance());
			}
			
		}
		
	}
	
}
