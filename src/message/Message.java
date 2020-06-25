package message;

import javax.swing.JOptionPane;

import view.workspace.MainFrame;

public class Message {

	private static Message message=null;
	
	public Message() {
		
	}
	
	public int gasenjeInstaFrama(){
		return JOptionPane.showConfirmDialog(null, "Da li stvarno zelite zatvoriti aplikaciju? Svi nesacuvani podaci bice izbrisani!"); 
	}
	
	public void nijeSelektovanPorizvod(String funkcija){
		JOptionPane.showMessageDialog(MainFrame.getInstance(), "Funkcija "+funkcija+" se moze izvrsiti samo na nivou parametra");
	}
	
	public void nijedanResursNijeSelektovan(){
		JOptionPane.showMessageDialog(MainFrame.getInstance(), "Nijedan resurs nije selektovan.");
	}
	
	public int brisanjeCvoraKojiImaPotomkeUpozorenje (){
		return JOptionPane.showConfirmDialog(
                MainFrame.getInstance(), "Ovaj resurs sadrzi podresurse. Ukoliko obrisete njega, obrisacete i sve pod resurse."
                		+ "\nDa li ste sigurni da zelite da nastavite  radnju?",
                "An Inane Question",
                JOptionPane.YES_NO_OPTION);
	}
	
	public void fajlNijePronadjen(){
		JOptionPane.showMessageDialog(MainFrame.getInstance(), "Doslo je do greske prilikom biranja fajla.");
	}
	
	public void greskaPrilikomUpisaIliIspisa(){
		JOptionPane.showMessageDialog(MainFrame.getInstance(), "Doslo je do greske prilikom cuvanja ili prikupljanja podataka.");
	}
	
	public static Message getMessage() {
		if(message ==null) message= new Message();
		return message;
	}
	
	public void prazanTextField() {
		JOptionPane.showConfirmDialog(MainFrame.getInstance(), "Molim vas unesite ime parametra");
	}
	
	
}
