import java.awt.Graphics2D;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.ImageIcon;

/**
 * Classe CarteImage qui hérite de Carte afin de gérer le composant CarteImage.
 * 
 * @author Paul Chaffanet, Samuel Guigui
 * @version 1.0
 */
@SuppressWarnings("serial")
public class CarteImage extends Carte {

	/**
	 * Propriété d'instance qui contient l'url de l'image de la carte image.
	 */
	
	private String img_urlstring;
	

	/**
	 * Propriété d'instance qui contient l'image sous forme d'ImageIcon.
	 */
	
	private ImageIcon img_icon;
	
	/**
	 * Constructeur qui prend en paramètre un booléen recto qui est vrai si la carte est visible, false sinon et une couleur.
	 * 
	 * @param recto
	 * 			True si la carte est visible, face sinon.
	 * @param img_urlstring
	 * 			L'url de la page sous forme de String.
	 */
	
	protected CarteImage(boolean recto,String img_urlstring) {
		super(recto);
		this.img_urlstring = img_urlstring;
		this.img_icon = createImageIconFromURLString(img_urlstring);
	}
	
	/**
	 * Constructeur qui prend en paramètre une CarteImage et en retourne ainsi une nouvelle instance CarteImage
	 * ayant la même propriété recto et image.
	 * 
	 * @param carte
	 */
	
	protected CarteImage(CarteImage carte) {
		super(carte);
		this.img_urlstring = carte.img_urlstring;
		this.img_icon = carte.img_icon;
	}
	

	/**
	 * Méthode qui permet de peindre une image dans un contexte graphique.
	 * 
	 * @param g
	 * 		Contexte graphique
	 */
	
	public void paintRecto(Graphics2D g) {
		g.drawImage(this.img_icon.getImage(),0,0,this.getWidth(),this.getHeight(),this);
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
		if (!(carte instanceof CarteImage) ) {
			return false;
		}
		if (this.estMontree() && carte.estMontree() && this.img_urlstring == ((CarteImage)carte).img_urlstring) {
			return true;
		}
		else return false;
	}
	

	/**
	 * Méthode qui permet de dupliquer une CarteImage.
	 * 
	 * @return
	 * 		Retourne la CarteImage dupliquée.
	 */
	
	public Object duplique() {
		return new CarteImage(this);
	}
	
	/**
	 * Méthode qui retourne l'état de la carte image sous forme de String.
	 */
	
	public String toString() {
		return "Recto : " + super.estMontree() + " URL : " + this.img_urlstring;
	}
	
	/**
	 * Méthode static qui crée une instance d'ImageIcon à partir d'une URL sous forme de String.
	 * 
	 * @param img_urlstring
	 * 			L'URL sous forme de String.
	 * 
	 * @return
	 * 			Une ImageIcon de l'image située à l'URL.
	 */
	
	public static ImageIcon createImageIconFromURLString(String img_urlstring)
    {
        URL img_url = null;
        try { img_url = new URL(img_urlstring); }
        catch(MalformedURLException e)
        { 
            try {img_url = new URL("https://"+"webtoolfeed.files.wordpress.com/2012/01/create-your-url1.jpg");}
            catch(MalformedURLException e2) {}
        }
        ImageIcon img_icon = new ImageIcon(img_url);
        return img_icon;
    }
	
}
