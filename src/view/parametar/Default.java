package view.parametar;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import view.workspace.MainFrame;

public class Default extends JPanel{
	
	private JTextField pathTa= new JTextField();
	private JButton btn;
	private String path ;
	
	public Default() {
		btn= new JButton("Biraj");
		pathTa= new JTextField("C:\\Program Files");
		path="C:\\Program Files";
		pathTa.setSize(new Dimension(300,30));
		btn.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	JFileChooser fc= new JFileChooser();
		    	fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		    	if(fc.showOpenDialog(MainFrame.getInstance())== JFileChooser.APPROVE_OPTION){
		    		path= fc.getSelectedFile().getAbsolutePath();
		    		pathTa.setText(path);
		    	}
		    	
		    }
		  });
		JPanel pnl= new JPanel();
		pnl.setSize(new Dimension(300,150));
		pnl.setLayout(new BoxLayout(pnl, BoxLayout.Y_AXIS));
		pnl.add(pathTa);
		pnl.add(btn);
		this.add(pnl);
	
	}
	public JTextField getPath() {
		return pathTa;
	}
}
