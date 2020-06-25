package login;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import actions.ActionManager;

import view.workspace.MainFrame;

public class LogInView extends JFrame {
	
	
	private JPanel p;
	private String korisnik;
	
	public LogInView() {
		
	
   	
		
		setSize(350, 150);
		setLocationRelativeTo(null);
		String [] s={"Operativni korisnik","Administrator"};
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel cmbp= new JPanel();
		cmbp.setSize(new Dimension(200, 40));
		cmbp.setAlignmentX(CENTER_ALIGNMENT);
		
		p= new JPanel();
		p.setAlignmentX(CENTER_ALIGNMENT);
		JPanel btnp= new JPanel();
		btnp.setAlignmentX(CENTER_ALIGNMENT);
		
		JComboBox<String> cmb= new JComboBox<>(s);
		cmb.setSelectedIndex(0);
		cmbp.add(cmb);
		
		JButton btn = new JButton("Log In");
		btnp.add(btn);
		p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS) );
		p.add(cmbp);
		p.add(btnp);
		add(p);
		setVisible(true);

		 btn.addActionListener(new ActionListener() {
			    @Override
			    public void actionPerformed(ActionEvent e) {
			    	setVisible(false);
			    	korisnik=cmb.getSelectedItem().toString();
			    	
			    	if(korisnik.equals("Operativni korisnik")) {
			    		ActionManager.getActionManager().getNewNode().setEnabled(false);
			    		ActionManager.getActionManager().getDelete().setEnabled(false);
			    		ActionManager.getActionManager().getCopy().setEnabled(false);
			    		ActionManager.getActionManager().getCut().setEnabled(false);
			    		ActionManager.getActionManager().getPaste().setEnabled(false);
			    	}
			    	MainFrame.getInstance().setVisible(true);
			    	MainFrame.getInstance().setKorinisk(korisnik);
			    	
			    	
			    }
		    });
}
}