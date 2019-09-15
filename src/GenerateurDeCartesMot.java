
/**
 * Classe qui permet d'instancer un objet GenerateurDeCartesMot
 * 
 * @author Paul Chaffanet, Samuel Guigui
 * @version 1.0
 *
 */

public class GenerateurDeCartesMot extends GenerateurDeCartes {
	
	/**
	 * Propriété qui contient un tableau des mots du thème.
	 */
	
	private String[] tabMots; 
	
	/**
	 * Propriété qui est un tableau contenant tous les mots du thème qui n'ont pas encore été utilisés.
	 */
	
	private String[] tabMotsRestant;
	
	/**
	 * Compteur représentant l'index du prochain éléments du tableau de mots qui sera utilisé pour générer la prochaine carte.
	 * Il pointe toujours sur le dernier élément non null du tableau cd mogx.
	 */
	
	private int compteur;
	
	/**
	 * Constructeur du générateur de cartes mots.
	 * 
	 * @param nom
	 * 			Le nom du thème.
	 * @param tabMots
	 * 			Un tableau de mots.
	 */
	
	public GenerateurDeCartesMot(String nom, String[] tabMots) {
		super(nom);
		this.tabMots = new String[tabMots.length];
		this.tabMotsRestant = new String[tabMots.length];
		
		for(int i = 0; i < tabMots.length; i++) {
			this.tabMots[i] = tabMots[i];
			this.tabMotsRestant[i] = tabMots[i];
		}
		//On melange le tableau de mot pour que ce soit aleatoire.
		this.tabMotsRestant = (String[])GenerateurDeCartes.ShuffleArray(this.tabMotsRestant); 
		this.compteur = tabMots.length-1;
	}
	
	/**
	 * Méthode qui génère une carte contenant le mot à l'index du dernier élément non null du tableau.
	 */
	
	public Carte genereUneCarte(){
		CarteMot c = new CarteMot(false,tabMotsRestant[this.compteur]);
		this.removeMot(this.compteur);
		return c;
	}
	
	/**
	 * Méthode qui prend en paramètre un entier j, qui supprime l'objet à l'index j du tableau de mot.
	 * 
	 * @param j
	 * 			L'index.
	 * 			
	 */
	
	public void removeMot(int j) {
		this.tabMotsRestant[j] = null;
		this.compteur -= 1;
		//Si on a utilisé tous les mots du theme, on re initialise le compteur et on remet tous les mots du theme dans tabMotsRestant. 
		if(this.compteur == -1) {
			for(int i = 0; i<this.tabMots.length; i++)
				this.tabMotsRestant[i] = this.tabMots[i];
			this.tabMotsRestant = (String[])GenerateurDeCartes.ShuffleArray(this.tabMotsRestant);
			this.compteur = this.tabMots.length-1;
		}
	}
	
	/**
	 * Méthode qui renvoie le nombre d'éléments différents contenu dans le thème.
	 */
	
	public int nombreDeCartesDifferentes(){
		return this.tabMots.length;
	}
}
