import java.awt.Color;

/**
 * Classe qui permet d'instancer un objet GenerateurDeCarteCouleur
 * 
 * @author Paul Chaffanet, Samuel Guigui
 * @version 1.0
 *
 */

public class GenerateurDeCartesCouleur extends GenerateurDeCartes {
	
	/**
	 * Tableau contenant toutes les couleurs du thème Couleur.
	 */
	
	private Color[] couleurs = {Color.BLUE,Color.CYAN,Color.GREEN,Color.MAGENTA,Color.ORANGE,Color.PINK,Color.RED,Color.YELLOW};
	
	/**	Compteur représentant l'index du prochain éléments du tableau couleurs qui sera utilisé pour générer la prochaine carte.
	 *	Il pointe toujours sur le dernier élément non null du tableau couleurs.
	 */
	private int compteur;
	
	/**
	 * Constructeur du générateur de cartes couleur.
	 * 
	 * @param nom
	 * 			Le nom du thème.
	 */
	
	public GenerateurDeCartesCouleur(String nom) {
		super(nom);
		
		//On melange le tableau de couleurs afin que les éléments du tableau soit sélèctionné dans un ordre aléatoire
		this.couleurs = (Color[])GenerateurDeCartes.ShuffleArray(this.couleurs);
		
		this.compteur = 7; //Initialisation du compteur à l'index du dernier élément du tableau.
	}
	
	/**
	 * Méthode qui génère une carte contenant l'élément du tableau couleurs à l'index pointé par le compteur.
	 */
	
	public Carte genereUneCarte() {
		CarteCouleur c = new CarteCouleur(false,this.couleurs[this.compteur]);
		
		//Retire l'éléments qui vient d'être utilisé pour générer la carte afin que les cartes
		//soit au maximum différentes.
		this.removeColor(this.compteur); 
		
		return c;
	}
	
	/**
	 * Méthode qui supprime l'élément du tableau couleurs à l'index pointé par le compteur.
	 * 
	 * @param index
	 */
	public void removeColor(int index) {
		this.couleurs[index] = null;
		this.compteur -= 1; 
		
		//Il se peut que l'utilisateur veuille plus de carte que le thème couleur contienne. Ainsi il y aura
		//des couleurs en double. Si il n'y a plus d'élément dans le tableau couleurs, on le re créer
		//et on le mélange pour que les éléments soit dans un ordre aléatoire.
		if(this.compteur == -1) {
			this.couleurs[0] = Color.BLUE;
			this.couleurs[1] = Color.CYAN;
			this.couleurs[2] = Color.GREEN;
			this.couleurs[3] = Color.MAGENTA;
			this.couleurs[4] = Color.ORANGE;
			this.couleurs[5] = Color.PINK;
			this.couleurs[6] = Color.RED;
			this.couleurs[7] = Color.YELLOW;
			this.couleurs = (Color[])GenerateurDeCartes.ShuffleArray(this.couleurs);
			this.compteur = 7;
		}
	}
	
	/**
	 * Nombre de cartes différentes dans le thème.
	 */
	
	public int nombreDeCartesDifferentes() {
		return 8;
	}
}
