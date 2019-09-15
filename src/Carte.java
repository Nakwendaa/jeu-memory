
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;
import javax.swing.JComponent;

/**
 * Classe abstraite Carte qui hérite de JComponent afin de gérer le composant Carte.
 * 
 * @author Paul Chaffanet, Samuel Guigui
 * @version 1.0
 */
@SuppressWarnings("serial")
public abstract class Carte extends JComponent {
	
	/**
	 * Propriété d'instance boléenne qui permet de savoir si une carte est recto ou non.
	 */
	private boolean recto; 
	
	/**
	 * Constructeur qui prend en paramètre un booléen recto qui est vrai si la carte est visible, false sinon.
	 * 
	 * @param recto
	 * 			True si la carte est visible, face sinon.
	 */
	
	protected Carte (boolean recto) {
		this.recto = recto;
		//Calcul de la taille des cartes en fonction du nombre de Rangées, Colonnes désirées par l'utilisateur
		//ansi que la taille de son écran.
		int x = (JeuMemory.height-250-(JeuMemory.nRangees+1)*5)/JeuMemory.nRangees;
		int y = (JeuMemory.width-250-(JeuMemory.nColonnes+1)*5)/JeuMemory.nColonnes;
		this.setSize(Math.min(x, y),Math.min(x, y));
	}
	
	/**
	 * Constructeur qui prend en paramètre une carte et en retourne ainsi un nouvel objet ayant la même propriété recto.
	 * @param carte
	 */
	
	protected Carte (Carte carte) {
		this.recto = carte.recto;
	}
	
	/**
	 * Méthode qui permet de modifier l'état recto d'une carte.
	 * 
	 * @param recto
	 * 			Si visible, alors true, false sinon.
	 * 
	 */
	
	public void setRecto(boolean recto) {
		this.recto = recto;
	}
	
	/**
	 * Méthode qui indique si une carte est visible ou non.
	 * 
	 * @return
	 * 		la propriété recto de la carte. True si visible, false sinon.
	 */
	
	public boolean estMontree() { //Méthode pour savoir si la carte est sur son côté recto ou pas
		return this.recto;
	}
	
	/**
	 * Méthode qui indique si une carte est visible ou non.
	 * 
	 * @return
	 * 		l'inverse de la propriété recto de la carte. True si cachée, false sinon.
	 */
	
	public boolean estCachee() { //Méthode pour savoir si la carte est sur son côté verso ou pas
		return !this.recto;
	}
	
	/**
	 * Méthode qui permet de repeindre le composant Carte avec le recto.
	 */
	
	public void montre() {  //Montre la carte
		this.recto = true;
		repaint();
	}
	
	/**
	 * Méthode qui permet de repeindre le composant Carte avec le verso
	 */
	
	public void cache() {  //Cache la carte
		this.recto = false;
		repaint();
	}
	
	/**
	 * Méthode qui permet de retourner une carte en inversant sa propriété recto, et repeint le composant.
	 */
	
	public void retourne() {
		this.recto = !this.recto;
		repaint();
	}
	
	/**
	 * Méthode qui permet de peindre le vers0 de la composante graphique.
	 * 
	 * @param g
	 * 			Contexte graphique
	 */
	
	public void paintVerso(Graphics2D g) {
			g.drawImage(CarteImage.createImageIconFromURLString("https://lh3.googleusercontent.com/-H1KqX5HicIU/AAAAAAAAAAI/AAAAAAABL30/Isieb22PckE/s0-c-k-no-ns/photo.jpg").getImage(), 0,0,this.getWidth(),this.getHeight(),this);
	}
	
	/**
	 * Méthode abstraite (définie dans les classes filles) qui permet de peindre le recto de la composante graphique g.
	 * 
	 * @param g
	 * 			Contexte graphique
	 */
	
	public abstract void paintRecto(Graphics2D g);
	
	/**
	 * Méthode qui permet de peindre une carte.
	 * 
	 * @param g
	 * 		Contexte graphique
	 */
	
	public void paintComponent (Graphics g) {
	//	super.paintComponent(g);
		if (this.estMontree()) {
			paintRecto((Graphics2D)g);
		}
		else {
			paintVerso((Graphics2D)g);
		}
	}
	
	/**
	 * Méthode abstraite qui permet de savoir si une carte est égale à une autre
	 * 
	 * @param carte
	 * 			La carte avec laquelle nous voulons comparer notre instance
	 * @return
	 * 			True si les cartes sont les mêmes, false sinon.
	 */
	
	public abstract boolean rectoIdentique(Carte carte);
	
	/**
	 * Méthode abstraite qui permet de dupliquer une carte.
	 * 
	 * @return
	 * 		Retourne la carte dupliquée.
	 */
	
	public abstract Object duplique();
	
	/**
	 * Mélange un tableau de Carte de manière aléatoire.
	 * 
	 * @param cartes
	 */
	
	public static void melangeCartes(Carte[] cartes) {
		int length = cartes.length;
		Random random = new Random(); // Objet aleatoire
		for (int i = length - 1; i > 1; i--) {  //Algo de Fisher-Yates
			int randomInt = random.nextInt(i + 1);
			Carte temp = cartes[randomInt] ;
			cartes[randomInt] = cartes[i];
			cartes[i] = temp;
		}
	}	
}