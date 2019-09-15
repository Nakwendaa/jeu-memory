/**
 * Classe qui permet d'instancer un objet GenerateurDeCartesMultiple
 * 
 * @author Paul Chaffanet, Samuel Guigui
 * @version 1.0
 *
 */

public class GenerateurDeCartesMultiple extends GenerateurDeCartes {
	/**
	 * Tableau contenant les générateurs de chaque thème.
	 */
	
	private GenerateurDeCartes[] generateurs;
	
	/**
	 * Chaine de caractères indiquant le nom du thème dans lequel devra être généré la prochaine carte. 
	 */
	
	private String CurrentThemeName;
	
	/**
	 * Constructeur du générateur de cartes multiples.
	 * 
	 * @param nom
	 * 			Le nom du thème.
	 * @param generateurs
	 * 			Un tableau de générateurs.
	 */
	
	public GenerateurDeCartesMultiple(String nom,GenerateurDeCartes[] generateurs) {
		super(nom);
		this.CurrentThemeName = "";
		this.generateurs = new GenerateurDeCartes[5];
		for(int i = 0; i < generateurs.length; i++) 
			this.generateurs[i] = generateurs[i];
	}
	
	/**
	 * Méthode qui génère une carte dans le thème indiqué par la propriété CurrentThemeName.
	 */
	
	public Carte genereUneCarte(){
		if(this.CurrentThemeName == "couleur") {
			return generateurs[0].genereUneCarte();
		}
		else if(this.CurrentThemeName == "mot1") {
			return generateurs[1].genereUneCarte();
		}
		else if(this.CurrentThemeName == "mot2") {
			return generateurs[2].genereUneCarte();
		}
		else if(this.CurrentThemeName == "Image1") {
			return generateurs[3].genereUneCarte();
		}
		else {
			return generateurs[4].genereUneCarte();
		}
		
	}
	
	/**
	 * Méthode qui distribue de manière équitable le nombre de paire entre chaque thème.
	 * 
	 * @param n
	 * 		Nombre de paires à générer.
	 */
	
	// Elle fait des appels successifs à GenereUneCarte en indiquant dans quel thème appartiendra la carte à générer.
	// ELle retourne le tableau contenant les n cartes générées.
	
	public Carte[] genereCartes(int n) {
		Carte[] t = new Carte[n];
		
		int[] nParTheme = {n/5,n/5,n/5,n/5,n/5};
		
		//Si le reste est non nul, on ne peut pas faire un partage équitable entre les k thèmes
		//On partage le reste r de maniere aleatoire à r thèmes. (k-r) thèmes auront une carte de moins que les autres.  
		
		int nReste = n%5;
		String[] nTheme = {"0","1","2","3","4"};
		nTheme = (String[])GenerateurDeCartes.ShuffleArray(nTheme);
		for(int i = 0; i<nReste; i++)
			nParTheme[Integer.parseInt(nTheme[i])] += 1;
		
		//On fait n appels à générer une carte en fixant à chaque itération la valeur de la propriété CurrentThemeName
		//afin que genereUneCarte puisse savoir dans quel thème générer la carte.
		
		for(int i = 0; i<n ; i++) {
			if(i<nParTheme[0]) {
				this.CurrentThemeName = "couleur";
				t[i] = this.genereUneCarte();
			}
			else if(i>=nParTheme[0] && i<nParTheme[1]+nParTheme[0]) {
				this.CurrentThemeName = "mot1";
				t[i] = this.genereUneCarte();
			}
			else if(i>=nParTheme[1] && i<nParTheme[2]+nParTheme[1]+nParTheme[0]) {
				this.CurrentThemeName = "mot2";
				t[i] = this.genereUneCarte();
			}
			else if(i>=nParTheme[2] && i<nParTheme[3]+nParTheme[2]+nParTheme[1]+nParTheme[0]) {
				this.CurrentThemeName = "Image1";
				t[i] = this.genereUneCarte();
			}
			else {
				this.CurrentThemeName = "Image2";
				t[i] = this.genereUneCarte();
			}	
		}
		return t;
	}
	
	/**
	 * Méthode qui renvoie le nombre d'éléments différents contenu dans le thème.
	 */
	
	public int nombreDeCartesDifferentes(){
		return generateurs[0].nombreDeCartesDifferentes() + generateurs[1].nombreDeCartesDifferentes() + generateurs[2].nombreDeCartesDifferentes()
				+generateurs[3].nombreDeCartesDifferentes()+generateurs[4].nombreDeCartesDifferentes();
	}
}
