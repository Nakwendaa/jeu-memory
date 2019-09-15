/**
 * Classe qui permet d'instancier un compteur pour le Jeu de Memory.
 * 
 * @author Paul Chaffanet
 * @version 1.0
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComponent;
import javax.swing.Timer;

public class Compteur extends JComponent {
	
	private int nombreCoups, time;
	
	public Compteur() {
		this.nombreCoups = 0;
		this.time = 0;
		this.setSize(20, 20);
	}
	
	public void incrCompteur() {
		this.nombreCoups++;
	}
	
	public void countTime() {
		Timer time = new Timer(0, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
						
			}
		});
	}
	
	
	
}
