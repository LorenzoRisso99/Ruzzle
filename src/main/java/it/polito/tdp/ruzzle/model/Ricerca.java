package it.polito.tdp.ruzzle.model;

import java.util.ArrayList;
import java.util.List;

public class Ricerca {
	
	public List<Pos> trovaParola(String parola, Board board) {
		
		//Faccio partitre ricorsione solo se trovo prima lettera --> scorro board per capire se c'è lettera
		for(Pos p: board.getPositions()) {
			//lettera in pos == prima lettera di parola
			if(board.getCellValueProperty(p).get().charAt(0) == parola.charAt(0)) {
				//posso far partire ricorsione
				List<Pos> parziale = new ArrayList<Pos>();              //Creo una parziale che vado a sovrasscrivere
				parziale.add(p);
				if(cerca(parola,parziale,1,board)) {
					return parziale;
				}
			}
		}
		return null;
		
	}
	
	public boolean cerca(String parola, List<Pos> percorso, int livello, Board board) {
		
		//CASO TERMINALE
		if(livello == parola.length()) {
			return true;
		}
		
		// Ultima inserita
		Pos ultima = percorso.get(percorso.size()-1);
		
		//Recupero le adiacenti in model
		List<Pos> adiacenti = board.getAdjacencies(ultima);
		
		//Controllo lettere adiacenti scorrendole
		for(Pos a: adiacenti) {
			if(!percorso.contains(a) && board.getCellValueProperty(a).get().charAt(0) == parola.charAt(livello)) {  //con primo Entro solo se posizione è nuova per me  //Livello sarebbe quella dopo
				//posso continuare percorso facndo andare avanti ricorsione
				percorso.add(a);
				
				if (cerca(parola,percorso,livello+1,board)) {
					return true;                //Esco senza fare backtraking perche ho gia completato il percorson perche la parola l'ho trovata
				}
				percorso.remove(percorso.size()-1);          // Backtraking !!
			}
		}
		
		return false;      //Non l'ho mai trovata
		
	}

}
