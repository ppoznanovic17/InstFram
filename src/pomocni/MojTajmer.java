package pomocni;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class MojTajmer extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
	
	private static final JLabel timerLabel = new JLabel("Datum i vreme:(", JLabel.CENTER);
	private static final JLabel endLabel = new JLabel(")", JLabel.CENTER);
	private static JLabel datetime = new JLabel();
	
	public LocalDateTime now = null;
	 
	public MojTajmer() {
		
		kreiranjeTajmerKomponente();
	}
	
  private void kreiranjeTajmerKomponente() {
	  
	  timerLabel.setFont(new Font("Ariel",Font.ITALIC,14)); 
	  timerLabel.setForeground(Color.GRAY);
	  endLabel.setFont(new Font("Ariel",Font.ITALIC,14)); 
	  endLabel.setForeground(Color.GRAY);
	  
	  this.setLayout(new FlowLayout());
	  this.add(timerLabel); 
	 
	  
	  //-------------------------(Pribavljanje tekuceg vremena)---------------------------
	  ActionListener timerListener = new ActionListener()
      {
          
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				now = LocalDateTime.now();
				String loc = dtf.format(now);
				
				datetime.setText(loc);
				datetime.setFont(new Font("Ariel",Font.ITALIC,12)); 
				datetime.setForeground(Color.BLACK);
			}
      };
      
      Timer timer = new Timer(1000, timerListener);
      
      timer.setInitialDelay(0);
      timer.start();
      
	  this.add(datetime); 
	  this.add(endLabel); 
  }
}

