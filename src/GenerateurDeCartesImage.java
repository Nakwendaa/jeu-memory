/**
 * Classe qui permet d'instancer un objet GenerateurDeCartesImage
 * 
 * @author Paul Chaffanet, Samuel Guigui
 * @version 1.0
 *
 */

public class GenerateurDeCartesImage extends GenerateurDeCartes {
	
	/**
	 * Propriété qui contient un tableau de String d'URL qui contient les images du thème.
	 */
	
	private String[] tab_URL;
	
	/**
	 * Propriété qui est un tableau contenant toutes les URL des images du thème qui n'ont pas encore été utilisées.
	 */
	
	private String[] tab_URL_restant;
	
	/**
	 * Compteur représentant l'index du prochain éléments du tableau image qui sera utilisé pour générer la prochaine carte.
	 * Il pointe toujours sur le dernier élément non null du tableau image.
	 */

	private int compteur;
	
	/**
	 * Constructeur du générateur de cartes images.
	 * 
	 * @param nom
	 * 			Le nom du thème.
	 * @param tab_URL
	 * 			Un tableau d'URL.
	 */
	
	
	public GenerateurDeCartesImage(String nom,String[] tab_URL) {
		super(nom);
		this.tab_URL = new String[tab_URL.length];
		this.tab_URL_restant = new String[tab_URL.length];
		for(int i = 0; i < tab_URL.length; i++) {
			this.tab_URL[i] = tab_URL[i];
			this.tab_URL_restant[i] = tab_URL[i];
		}
		this.tab_URL_restant = (String[])GenerateurDeCartes.ShuffleArray(this.tab_URL_restant);
		this.compteur = tab_URL.length-1;
	}
	
	/**
	 * Méthode qui génère une carte contenant le mot à l'index du dernier élément non null du tableau.
	 */
	
	public Carte genereUneCarte(){
		CarteImage c = new CarteImage(false,tab_URL_restant[this.compteur]);
		this.removeURL(this.compteur);
		return c;
	}
	
	/**
	 * Retire l'URL à l'index indiqué et recréer si besoin le tableau URL_restant si il n'y a pas assez d'images dans le thème.
	 * 
	 * @param index
	 * 			L'index de l'URL
	 */
	
	public void removeURL(int index) {
		this.tab_URL_restant[index] = null;
		this.compteur -= 1;
		if(this.compteur == -1) {
			for(int i = 0; i<this.tab_URL.length; i++)
				this.tab_URL_restant[i] = this.tab_URL[i];
			this.tab_URL_restant = (String[])GenerateurDeCartes.ShuffleArray(this.tab_URL_restant);
			this.compteur = this.tab_URL.length-1;
		}	
	}
	
	/**
	 * Méthode qui renvoie le nombre d'éléments différents contenu dans le thème.
	 */
	
	public int nombreDeCartesDifferentes() {
		return this.tab_URL.length;
	}
}
