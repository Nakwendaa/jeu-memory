
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.Timer;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Classe PanneauDeCartes qui construit le panneau du jeu Memory.
 * 
 * @author Paul Chaffanet, Samuel Guigui
 * @version 1.0
 *
 */
@SuppressWarnings("serial")
public class PanneauDeCartes extends JPanel implements MouseListener {
	
	/**
	 * Le compteurClic permet de compter le nombre de coups et de gérer l'affichage les différentes actions du clic.
	 */
	
	private int nRangees, nColonnes, delaiAffichageMauvaisePaire, nombrePaire, compteurClic;
	
	/**
	 * Si le setTimer vaut true, cela signifie que le compteur est en marche.
	 */
	
	private boolean setTimer = false;
	
	/**
	 * Création de deux timers pour gérer le delaiAffichageIntial et delaiAffichageMauvaisePaire
	 */
	
	private final Timer timer; //timer initial
	private Timer timer2;
	
	/**
	 * Un attribut static Carte qui permet de garder la référence à la première carte dévoilée
	 */
	
	private static Carte carte;
	
	/**
	 * Constructeur de PanneauDeCartes qui construit un panneau composées de Cartes disposées en nRangees et nColonnes
	 * avec un délai où ces cartes sont visibles pendant un temps delaiAffichageInitial et si les deux cartes choisies
	 * n'ont pas le recto identique, alors celles-ci restent affichées pendant un temps delaiAffichageMauvaisePaire).
	 * Un layout de type GridLayout est associé au pane instancié.
	 * Des MouseListener sont égalements ajoutés.
	 * Le Timer du delaiAffichageInitial est déclenché.
	 * 
	 * @param nRangees
	 * 				Nombre de rangées.
	 * @param nColonnes
	 * 				Nombre de colonnes.
	 * @param cartes
	 * 				Tableau de cartes à afficher.
	 * @param delaiAffichageInitial
	 * 				Délai d'affichage Initial en ms.
	 * @param delaiAffichageMauvaisePaire
	 * 				Délai d'affichage de la mauvaise paire en ms.
	 */
	
	public PanneauDeCartes(int nRangees, int nColonnes, Carte[] cartes, int delaiAffichageInitial, int delaiAffichageMauvaisePaire) {
		this.delaiAffichageMauvaisePaire = delaiAffichageMauvaisePaire;
		this.nRangees = nRangees;
		this.nColonnes = nColonnes;
		this.compteurClic = 0;
		this.nombrePaire = 0;
		GridLayout layout = new GridLayout(nRangees,nColonnes, 10, 10);
		this.setLayout(layout);
	    for (int i = 0; i < cartes.length; i++) {
			for (int j = 0; j < nRangees; j++) {
				for (int k = 0; k < nColonnes; k++) {
					this.add(cartes[i]).addMouseListener(this);
				}
			}
		}
	    for (int i = 0; i < cartes.length; i++) {
	    	cartes[i].setRecto(true);
	    }
	    timer = new Timer (delaiAffichageInitial, new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		for (int i = 0; i < cartes.length; i++) {
	    			cartes[i].setRecto(false);
	    			repaint();
	    		}
	    		timer.stop();
	    	}
	    });
	    timer.start();
	}
	
	/**
	 * Implémentations des méthodes de l'interface MouseListener.
	 */
	
	/**
	 * Méthode qui gère le clic de la souris
	 */
	
	public void	mouseClicked(MouseEvent e) {
		Carte carte1 = ((Carte) e.getSource());
		if (!setTimer) {
			if (carte1.estCachee() && this.compteurClic % 2 == 0) {
				this.compteurClic++;
				carte1.setRecto(true);
				carte = carte1;
				repaint();
			}
			else if (carte1.estCachee() && this.compteurClic % 2 == 1) {
				this.compteurClic++;
				carte1.setRecto(true);
				repaint();
				if (!carte1.rectoIdentique(carte)) {
					timer2 = new Timer (this.delaiAffichageMauvaisePaire, new ActionListener() {
				    	public void actionPerformed(ActionEvent e) {
				    		carte1.setRecto(false);
							carte.setRecto(false);
							setTimer = false;
							timer2.stop();
							repaint();
				    	}
				    });
					setTimer = true;
				    timer2.start();
				}
				else { // On ouvre la boite de dialogue ici.
					this.nombrePaire++;
					int n = this.nRangees*this.nColonnes / 2;
					if (n == this.nombrePaire) {
						JOptionPane.showMessageDialog(null, "Bravo vous avez gagné la partie en " +  this.compteurClic + " coups.", "Fin de partie", JOptionPane.INFORMATION_MESSAGE, null);
						System.exit(0);
					}
				}
			}
		}
	}
	
	public void	mouseEntered(MouseEvent e) {
		
	}
	
	public void	mouseExited(MouseEvent e) {
		
	}
	
	public void	mousePressed(MouseEvent e) {
		
	}
	
	public void	mouseReleased(MouseEvent e) {
		
	}
}
