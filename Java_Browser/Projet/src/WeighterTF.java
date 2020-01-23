import java.util.HashMap;
import java.util.Map.Entry;

import indexation.*;

public class WeighterTF extends Weighter{
	
	public WeighterTF(Index i) {
		super(i);
	}
	
	public HashMap <String,Double> PoidsDocument(String doc){
		HashMap <String,Double> val = new HashMap<String, Double>();
		int nb_mots=0;
		for(Entry<String, Integer> entry : i.getTfsForDoc(doc).entrySet()) {
		    nb_mots += entry.getValue();
		}	
		for (Entry<String, Integer> mot : i.getTfsForDoc(doc).entrySet()) {
			val.put(mot.getKey(), (double) mot.getValue()/nb_mots);
		}
		return val;
	}
	
	public HashMap <String,Double> PoidsRequete(HashMap<String, Integer> queryProcessed){
		HashMap <String,Double> val = new HashMap<String, Double>();
		int nb_mots=0;
		for(Entry<String, Integer> entry : queryProcessed.entrySet()) {
		    nb_mots += entry.getValue();
		}
		for (Entry<String, Integer> mot : queryProcessed.entrySet()) {
			val.put(mot.getKey(), (double) mot.getValue()/nb_mots);
		}
		return val;
	}
}