package actions;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AboutAction extends MyAbsAction {

	public  AboutAction(){
		
		
		putValue(SMALL_ICON, loadIcon("images/toolbar/info.png"));
		putValue(NAME, "About");
		putValue(SHORT_DESCRIPTION, "About");
		
		
	}
	
	@Override
	public void actionPerformed(ActionEvent arg) {
		JFrame about = new JFrame();
		about.setSize(400,400);
		JLabel lblName = new JLabel("PETAR POZNANOVIC");
		lblName.setSize(250,40);
		JLabel lblGroup = new JLabel("Grupa 205, RN49/17");
		lblGroup.setSize(250,40);
		JPanel panel= new JPanel();
		JPanel panelPic= new JPanel();
		BufferedImage myPicture;
		try {
			myPicture = ImageIO.read(new File("images/moja slika.jpg"));
			JLabel lblPic = new JLabel(new ImageIcon(myPicture));
			panelPic.add(lblPic);
			panelPic.setSize(new Dimension(200, 200));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		panel.add(lblName);
		panel.add(lblGroup);
	
		JPanel mainPanel= new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.add(panel);
		mainPanel.add(panelPic);
		about.add(mainPanel);
		about.setVisible(true);
		
		
		about.setLocationRelativeTo(null);
		
	}

	
}
