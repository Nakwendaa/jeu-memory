import java.util.Random;

/**
 * Classe abstraite qui permet d'instancer un objet GenerateurDeCartes
 * 
 * @author Paul Chaffanet, Samuel Guigui
 * @version 1.0
 *
 */

public abstract class GenerateurDeCartes {
	
	/**
	 * Propriété qui contient le nom du thème.
	 */
	
	private String nom;
	
	/**
	 * Constructeur de générateur de cartes qui prend en paramètre le nom du thème
	 * 
	 * @param nom
	 * 			Nom de thème.
	 */
	
	public GenerateurDeCartes(String nom) {
		this.nom = nom;
	}
	
	/**
	 * Méthode qui permet d'obtenir le nom du thème.
	 * 
	 * @return
	 * 		Le nom du thème du générateur.
	 */
	
	public String getNom() {
		return this.nom;
	}
	
	/**
	 * Une méthode abstraite Carte genereUneCarte() qui retournera une référence de type Carte à une carte choisie aléatoirement parmi celles du thème.
	 * @return
	 * 		 une référence de type Carte
	 */
	
	public abstract Carte genereUneCarte();
	
	/**
	 * Une méthode abstraite int nombreDeCartesDifferentes() qui retournera un entier indiquant le nombre de cartes différentes disponibles dans ce thème.
	 * 
	 * @return
	 * 		un entier indiquant le nombre de cartes différentes disponibles dans le thème.
	 */
	
	public abstract int nombreDeCartesDifferentes();
	
	/**
	 * Une méthode Carte[] genereCartes(int n) qui générera un tableau de n cartes selon le thème tout différentes
	 *  en faisant des appels répétés à genereUneCarte.
	 *  
	 * @param n
	 * 		Le nombre de paires à créer.
	 * 
	 * @return
	 * 		Retourne un tableau de Cartes.
	 */
	
	public Carte[] genereCartes(int n) {
		Carte[] t = new Carte[n];
		for(int i = 0; i<n ; i++) {
			t[i] = this.genereUneCarte();
		}
		return t;
	}
	
	/**
	 * Une méthode Carte[] generePairesDeCartesMelangees(int n) qui générere 2n cartes par paires identiques, selon le thème, et 
	 * les mélange.
	 * 
	 * @param n
	 * 			Nombre de cartes initial.
	 * @return
	 * 			Un tableau de paires de cartes mélangées.
	 */
	
	// La méthode génère un paquet de n Cartes en appelant genereCartes, duplique ce paquet, 
	// et retourne la concatenation des deux paquets clones melangés.
	
	public Carte[] generePairesDeCartesMelangees(int n) {
		Carte[] temp = genereCartes(n); 					// Paquet de carte comportant les cartes sans leur paire.
		Carte[] copie = new Carte[n];
		Carte[] t = new Carte[2*n]; 						// Paquet de carte final comportant les paires.	
		int j=0;											// Algorithme qui duplique le tableau de Carte temp
		for(int i=0; i<n; i++) 
			copie[i] = (Carte) temp[i].duplique();
		for(int i=0; i<n; i++) { 							// Algorithme qui concatenne temp et sa copie.
			t[j] = temp[i];
			t[j+1] = copie[i];
			j += 2;
		}
		Carte.melangeCartes(t); 							// On mélange le paquet final pour que la position des cartes soit aléatoire.
		return t;
	}
	
	
	/**
	 * Mélange un tableau d'objets de manière aléatoire.
	 * 
	 * @param array
	 * 			Un tableau d'objets
	 */
	
	public static Object[] ShuffleArray(Object[] array)
	{
	    int index;
	    Object temp;
	    Random random = new Random();
	    for (int i = array.length - 1; i > 0; i--)
	    {
	        index = random.nextInt(i + 1);
	        temp = array[index];
	        array[index] = array[i];
	        array[i] = temp;
	    }
	    return array;
	}
	
}
