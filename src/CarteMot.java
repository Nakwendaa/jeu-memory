import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 * Classe CarteMot qui hérite de Carte afin de gérer le composant CarteMot.
 * 
 * @author Paul Chaffanet, Samuel Guigui
 * @version 1.0
 */

@SuppressWarnings("serial")
public class CarteMot extends Carte {
	
	/**
	 * Propriété d'instance qui contient le mot de la carte mot.
	 */
	
	private String mot;
	
	/**
	 * Constructeur qui prend en paramètre un booléen recto qui est vrai si la carte est visible, false sinon et un mot.
	 * 
	 * @param recto
	 * 			True si la carte est visible, face sinon.
	 * @param mot
	 * 			Le mot que contient la carte mot.
	 */
	
	protected CarteMot(boolean recto,String mot) {
		super(recto);
		this.mot = mot;
	}
	
	/**
	 * Constructeur qui prend en paramètre une CarteMot et en retourne ainsi une nouvelle instance CarteMot
	 * ayant la même propriété recto et mot.
	 * 
	 * @param carte
	 */
	
	protected CarteMot(CarteMot carte) {
		super(carte);
		this.mot = carte.mot;
	}
	
	/**
	 * Méthode qui permet de peindre un mot dans un contexte graphique.
	 * 
	 * @param g
	 * 		Contexte graphique
	 */
	
	public void paintRecto(Graphics2D g) {
		g.drawRoundRect(0, 0, this.getWidth(), this.getHeight(), 10, 10);
		int x = 25;
		Font font = new Font("TimesRoman ",Font.BOLD,x);
		this.setFont(font);
		FontMetrics metrics = g.getFontMetrics(font);
		while(metrics.stringWidth(this.mot) >= this.getWidth()+2) {
			x -= 1;
			font = new Font("TimesRoman ",Font.BOLD,x);
			this.setFont(font);
			metrics = g.getFontMetrics(font);
		}
		
		this.drawCenteredString(g, this.mot, getFont());
	}
	
	/**
	 * Méthode qui permet de savoir si une carte est égale à une autre
	 * 
	 * @param carte
	 * 			La carte avec laquelle nous voulons comparer notre instance
	 * @return
	 * 			True si les cartes sont les mêmes, false sinon.
	 */

	public boolean rectoIdentique(Carte carte) {
		if (!(carte instanceof CarteMot) ) {
			return false;
		}
		if (this.estMontree() && carte.estMontree() && this.mot == ((CarteMot)carte).mot) {
			return true;
		}
		else return false;
	}
	
	/**
	 * Méthode qui permeter de dessiner un String dans un contexte graphique g selon une certaine police et une certaine chaîne String.
	 * 
	 * @param g
	 * 			Le contexte graphique
	 * @param text
	 * 			Le texte à dessiner
	 * @param font
	 * 			Police de texte
	 */
	public void drawCenteredString(Graphics g, String text, Font font) {
	    // Get the FontMetrics
	    FontMetrics metrics = g.getFontMetrics(font);
	    // Determine the X coordinate for the text
	    int x = (this.getWidth() - metrics.stringWidth(text)) / 2;
	    // Determine the Y coordinate for the text
	    int y = this.getHeight()/2;
	    // Set the font
	    g.setFont(font);
	    // Draw the String
	    g.drawString(text, x, y);
	}
	
	/**
	 * Méthode qui permet de dupliquer une CarteMot.
	 * 
	 * @return
	 * 		Retourne la CarteMot dupliquée.
	 */
	
	public Object duplique() {
		return new CarteMot(this);
	}
	
	/**
	 * Méthode qui retourne l'état de la carte mot sous forme de String.
	 */
	
	public String toString() {
		return "Recto : " + super.estMontree() + " Mot : " + this.mot;
	}
}