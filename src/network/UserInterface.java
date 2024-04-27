package network;

import java.util.Scanner;


/**
 * The  UserInterface class provides  interface for interacting with a Petri Network.
 */
public class UserInterface {
	/**
	 * The main method to run the software.
	 *
	 * @param args used for the main method.
	 */
	public static void main (String[] args) {
		int placeId ; 
		int transId ;
		boolean sens ;
		int weight ;
		int tokens;
		String decision ; 
		boolean valueA1 = true ;
		boolean valueA2 = true ;
		boolean valueT = true ;
		boolean valueP = true ;
		Scanner myObj = new Scanner(System.in); 
		System.out.println("-----starting-----") ; 
		System.out.print("Enter Network name : ") ; 
		String networkName = myObj.next() ; 
		PetriNetwork pn = new PetriNetwork() ; 
		boolean keepUp = true ; 
		while (keepUp) { 
			System.out.println("Choose action : " 
					+ "\n" + "1 : add transition"
					+ "\n" + "2 : show transitions" 
					+ "\n" + "3 : remove transition"
					+ "\n" + "4 : add place " 
					+ "\n" + "5 : show places " 
					+ "\n" + "6 : remove place " 
					+ "\n" + "7 : set tokens"
					+ "\n" + "8 : add normal arc " 
					+ "\n" + "9 : add arc videur" 
					+ "\n" + "10 : add arc zero " 
					+ "\n" + "11 : show arcs " 
					+ "\n" + "12 : remove  arc "
					+ "\n" + "13 : execute " 
					+ "\n" + "14 : show petri" 
					+ "\n" + "E : end" 
					) ;
			String choice = myObj.next() ;
			switch (choice) {

			case "1" :
				pn.addTransition();
				System.out.println("Transition added") ;
				break ; 

			case "2" : 
				System.out.println("Number of transitions is : " + String.valueOf(pn.getTransitions().size())) ; 
				break ;

			case "3" : 
				System.out.print("Choose index to remove : ");
				String index = myObj.next() ; 
				valueT = (pn.getTransitions().size() >= Integer.valueOf(index)) ; 	
				if (valueT) {
					pn.rmTransition(pn.getTransitions().get(Integer.valueOf(index)-1));
					System.out.println("Transition removed");
				}else {
					System.out.println("Transition not found");
				}
				break ; 

			case "4" : 
				System.out.print("Enter Number of tokens : ") ; 
				String nbre = myObj.next() ;
				int jetons = Integer.parseInt(nbre);
				pn.addPlace(jetons);
				System.out.println("Place added") ;
				break ; 

			case "5" : 
				System.out.println("Number of places is : " + String.valueOf(pn.getPlaces().size())) ; 
				break ;


			case "6" : 
				System.out.print("Choose index to remove : ");
				String index1 = myObj.next() ; 
				valueP = (pn.getPlaces().size() >= Integer.valueOf(index1)) ; 	
				if (valueP ) {
					pn.rmPlace(pn.getPlaces().get(Integer.valueOf(index1)-1));
					System.out.println("Place removed");
				}else {
					System.out.println("Place not found");
				}
				break;


			case "7" :
				System.out.print("Choose Place : ");
				placeId = myObj.nextInt();
				System.out.print("Choose Number of tokens : ");
				tokens = myObj.nextInt();
				pn.setTokenPlace((pn.getPlaces().get(Integer.valueOf(placeId)-1)),tokens );
				System.out.print("Tokens changed \n");
				break ; 

			case "8" : 
				System.out.println("Choose Place : ") ;
				placeId = myObj.nextInt()  ;
				System.out.println("Choose Transition : ") ;
				transId = myObj.nextInt()  ;
				System.out.println("Choose Sens : ") ;
				sens = myObj.nextBoolean()  ;
				System.out.println("Choose Weight : ") ;
				weight = myObj.nextInt()  ;


				boolean a=true;
				for (Arc arc : pn.getArcs()) {
					if (arc.getPlace() == pn.getPlaces().get(Integer.valueOf(placeId)-1) && arc.getTransition() == pn.getTransitions().get(Integer.valueOf(transId)-1) && arc.getSens() == sens) {
						System.out.println("Double arcs not allowed, please choose another one ") ;
						a=false;
						break;
					}
				}
				if (a==true) {
					pn.addArcN(pn.getPlaces().get(Integer.valueOf(placeId)-1),pn.getTransitions().get(Integer.valueOf(transId)-1) , sens, weight);
					System.out.println("Normal Arc added ") ;
				}

				break ; 

			case "9" : 
				System.out.println("Choose Place : ") ;
				placeId = myObj.nextInt()  ;
				System.out.println("Choose Transition : ") ;
				transId = myObj.nextInt()  ;

				boolean b=true;
				for (Arc arc : pn.getArcs()) {
					if (arc.getPlace() == pn.getPlaces().get(Integer.valueOf(placeId)-1) && arc.getTransition() == pn.getTransitions().get(Integer.valueOf(transId)-1) && arc.getSens() == true) {
						System.out.println("Double arcs not allowed, please choose another one ") ;
						b=false;
						break;
					}
				}
				if (b==true) {
					pn.addArcV(pn.getPlaces().get(Integer.valueOf(placeId)-1),pn.getTransitions().get(Integer.valueOf(transId)-1) );
					System.out.println("Arc Videur added ") ;
				}
				break ; 


			case "10" : 
				System.out.println("Choose Place : ") ;
				placeId = myObj.nextInt()  ;
				System.out.println("Choose Transition : ") ;
				transId = myObj.nextInt()  ;

				boolean c=true;
				for (Arc arc : pn.getArcs()) {
					if (arc.getPlace() == pn.getPlaces().get(Integer.valueOf(placeId)-1) && arc.getTransition() == pn.getTransitions().get(Integer.valueOf(transId)-1) && arc.getSens() == true) {
						System.out.println("Double arcs not allowed, please choose another one ") ;
						c=false;
						break;
					}
				}
				if (c==true) {
					pn.addArcZ(pn.getPlaces().get(Integer.valueOf(placeId)-1),pn.getTransitions().get(Integer.valueOf(transId)-1) );
					System.out.println("Arc Zero added ") ;
				}
				break ; 


			case "11" : 
				System.out.println("Number of Arcs is : " + String.valueOf(pn.getArcs().size())) ;

				break ; 


			case "12" : 
				System.out.println("Choose Place : ") ;
				placeId = myObj.nextInt()  ;
				System.out.println("Choose Transition : ") ;
				transId = myObj.nextInt()  ;
				System.out.println("Choose Option : N/E") ;
				String decide = myObj.next();
				if (decide.equals("N")) {

					System.out.println("Choose Sens : ") ;
					sens = myObj.nextBoolean()  ;
					System.out.println("Choose Weight : ") ;
					weight = myObj.nextInt()  ;	
					valueA1 = pn.rmArc(pn.getPlaces().get(Integer.valueOf(placeId)-1),pn.getTransitions().get(Integer.valueOf(transId)-1) , sens) ; 
					if (valueA1 == true) {
						System.out.println("Arc removed");
					}else {
						System.out.println("Arc not found");
					}
				} else {
					valueA2 =pn.rmArc(pn.getPlaces().get(Integer.valueOf(placeId)-1),pn.getTransitions().get(Integer.valueOf(transId)-1));

				}
				if (valueA2 == true) {
					System.out.println("Arc removed ") ;
				} else {
					System.out.println("Arc Not Found ") ;
				}

				break ; 


			case "13" :
				System.out.print("Choose Transition : \n");
				int deci = myObj.nextInt();
				if (pn.getTransitions().get(deci-1).isFireable()) {
					pn.Execute(pn.getTransitions().get(deci-1)) ;
					System.out.print("Transition executed \n");
				}else {
					System.out.print("Transition not fireable \n");
				}
				break;


			case "14" : 

				//summary of places
				String sumPLaces = "Places :" + "\n"; 
				for (int i = 0 ; i < pn.getPlaces().size(); i++) {
					sumPLaces += "P" + String.valueOf(i) + " => jetons : " + String.valueOf(pn.getPlaces().get(i).getTokens() ) + "\n"; 
				}

				//Summary of transitions
				String sumTransitions = "Transitions :" + "\n" ; 
				for (int i = 0 ; i < pn.getTransitions().size(); i++) {
					sumTransitions += "T" + String.valueOf(i) + "\n"; 
				}

				//Summary of Arcs 
				String sumArcs = "Arcs :" + "\n" ; 
				String arcsens ;
				String Name ; 
				String poids ;

				for (int i = 0 ; i < pn.getArcs().size(); i++) {
					if (pn.getArcs().get(i).getSens()) {
						arcsens = " -> " ; 
					} else {
						arcsens = " <- " ; 
					}
					Name = pn.getArcs().get(i).getClass().getName() ; 
					if (Name.equals("network.ArcN")) {
						Name += " weight : " ; 
						poids =String.valueOf(pn.getArcs().get(i).getWeight()) ;
					} else {
						poids = "" ;
					}
					Place pl = pn.getArcs().get(i).getPlace() ;
					Transition tr = pn.getArcs().get(i).getTransition() ;

					sumArcs += "Arc " + String.valueOf(i) + ": " +"P"+  pn.getPlaces().indexOf(pl) + arcsens + "T"+  pn.getTransitions().indexOf(tr) + " type : " + Name + poids + "\n";

				}

				System.out.println("Petri summary :" + "\n"

				+ sumPLaces 
				+ sumTransitions
				+ sumArcs) ; 


				break ; 



			case "E" : 
				keepUp = false ; 

				break ; 

			}
			if (keepUp == true) {
				System.out.print("Continue "+ networkName + " y/n : ") ;
				decision = myObj.next();
				if (decision.equals("y")) {
					keepUp = true ; 
				} else {
					keepUp = false ; 
				}
			}



		}
		System.out.println("-----Ending-----") ;

	}
}
