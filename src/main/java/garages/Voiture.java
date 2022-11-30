package garages;

import java.io.PrintStream;
import java.util.*;

public class Voiture {

	private final String immatriculation;
	private final List<Stationnement> myStationnements = new LinkedList<>();

	public Voiture(String i) {
		if (null == i) {
			throw new IllegalArgumentException("Une voiture doit avoir une immatriculation");
		}

		immatriculation = i;
	}

	public String getImmatriculation() {
		return immatriculation;
	}

	/**
	 * Fait rentrer la voiture au garage
	 * Précondition : la voiture ne doit pas être déjà dans un garage
	 *
	 * @param g le garage où la voiture va stationner
	 * @throws java.lang.Exception Si déjà dans un garage
	 */
	public void entreAuGarage(Garage g) throws Exception {
		// Et si la voiture est déjà dans un garage ?
		if (!estDansUnGarage()){
			Stationnement s = new Stationnement(this, g);
			myStationnements.add(s);
		} else {
			throw new Exception();
		}

	}

	/**
	 * Fait sortir la voiture du garage
	 * Précondition : la voiture doit être dans un garage
	 *
	 * @throws java.lang.Exception si la voiture n'est pas dans un garage
	 */
	public void sortDuGarage() throws Exception {
		if(estDansUnGarage()){
			this.myStationnements.get(myStationnements.size()-1).terminer();
		}else {
		throw new Exception();
		}
		// Trouver le dernier stationnement de la voiture
		// Terminer ce stationnement
	}

	/**
	 * @return l'ensemble des garages visités par cette voiture
	 */
	public Set<Garage> garagesVisites() {
		Set<Garage> mesGarages = new HashSet<>();
		for(Stationnement s : myStationnements){
			mesGarages.add(s.getGarage());
		}
		return mesGarages;
	}

	/**
	 * @return vrai si la voiture est dans un garage, faux sinon
	 */
	public boolean estDansUnGarage() {
		boolean estDansGarage = false;
		if(myStationnements.size()>0){
			if(this.myStationnements.get(myStationnements.size()-1).estEnCours()){
				estDansGarage = true;
			}
		};
		// Vrai si le dernier stationnement est en cours

		return estDansGarage;
	}

	/**
	 * Pour chaque garage visité, imprime le nom de ce garage suivi de la liste des
	 * dates d'entrée / sortie dans ce garage
	 * <br>
	 * Exemple :
	 * 
	 * <pre>
	 * Garage Castres:
	 *		Stationnement{ entree=28/01/2019, sortie=28/01/2019 }
	 *		Stationnement{ entree=28/01/2019, en cours }
	 *  Garage Albi:
	 *		Stationnement{ entree=28/01/2019, sortie=28/01/2019 }
	 * </pre>
	 *
	 * @param out l'endroit où imprimer (ex: System.out)
	 */
	public void imprimeStationnements(PrintStream out) {
		List<Garage> tempGarage = new LinkedList<>();

		for (Stationnement s : myStationnements) {
			if (!tempGarage.contains(s.getGarage())){
				tempGarage.add(s.getGarage());
			}
			out.println(s);
		}
		for(Garage g : tempGarage){
			out.println(g.toString());
			System.out.println(g);
		}
	}
}
