package view.parametar;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Logo extends JPanel {
	
	private BufferedImage img;
	
	public Logo(String s) {
		
			try {
				img = ImageIO.read(new File(s));
				JLabel lblPic = new JLabel(new ImageIcon(img));
				this.add(lblPic);
				this.setSize(new Dimension(200, 200));
				
			} catch (IOException e) {
				System.out.println(s);
				e.printStackTrace();
			}
			
		
	}
	
	
	
	
}
