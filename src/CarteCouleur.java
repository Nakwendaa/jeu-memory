
import java.awt.Color;
import java.awt.Graphics2D;

/**
 * Classe CarteCouleur qui hérite de Carte afin de gérer le composant CarteCouleur.
 * 
 * @author Paul Chaffanet, Samuel Guigui
 * @version 1.0
 */
@SuppressWarnings("serial")
public class CarteCouleur extends Carte {

	/**
	 * Propriété couleur de la CarteCouleur.
	 */
	public Color couleur;
	
	
	/**
	 * Constructeur qui prend en paramètre un booléen recto qui est vrai si la carte est visible, false sinon et une couleur.
	 * 
	 * @param recto
	 * 			True si la carte est visible, face sinon.
	 * @param couleur
	 * 			La couleur de la carte
	 */
	
	protected CarteCouleur(boolean recto, Color couleur) {
		super(recto);
		this.couleur = couleur;
	}
	
	/**
	 * Constructeur qui prend en paramètre une CarteCouleur et en retourne ainsi une nouvelle instance Carte Couleur 
	 * ayant la même propriété recto et couleur.
	 * 
	 * @param carte
	 */
	
	protected CarteCouleur(CarteCouleur carte) {
		super(carte);
		this.couleur = carte.couleur;
	}

	/**
	 * Méthode qui permet de peindre un rectangle dans un contexte graphique.
	 * 
	 * @param g
	 * 		Contexte graphique
	 */
	
	public void paintRecto(Graphics2D g) {
		int width = this.getWidth();
		int height = this.getHeight();
		g.drawRect(0, 0, width, height);
		g.setColor(this.couleur);
		g.fillRect(0, 0, width, height);
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
		if (!(carte instanceof CarteCouleur) ) {
			return false;
		}
		if (this.estMontree() && carte.estMontree() && this.couleur == ((CarteCouleur)carte).couleur) {
			return true;
		}
		else return false;
	}

	/**
	 * Méthode qui permet de dupliquer une CarteCouleur.
	 * 
	 * @return
	 * 		Retourne la CarteCouleur dupliquée.
	 */
	
	public Object duplique() {
		CarteCouleur carte = new CarteCouleur(this);
		return carte;
	}
	
	/**
	 * Méthode qui retourne l'état de la carte couleur sous forme de String.
	 */
	
	public String toString() {
		return "Recto: " + super.estMontree() + " Couleur: " + this.couleur.toString();
	}
	
}
