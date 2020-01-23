import java.util.HashMap;
import java.util.Map.Entry;

import indexation.*;

public class VectorialCart extends IRMode1{
	private Weighter w;
	
	public VectorialCart(Index i, Weighter  w) {
		super(i);
		this.w=w;
	}
	
	public HashMap<String, Double> getDocScores(HashMap<String, Integer> queryProcessed) {
		HashMap<String, Double> docScores = new HashMap<String, Double>();
		HashMap<String, Double> vecDoc = new HashMap<String, Double>();
		HashMap<String, Double> vecReq = new HashMap<String, Double>();
		vecReq = w.PoidsRequete(queryProcessed);
		double score=0.;
		
		for (String doc : i.getDocs().keySet()) {
			vecDoc = w.PoidsDocument(doc);
			for (Entry<String, Double> entry : vecReq.entrySet()) {
				if (vecDoc.containsKey(entry.getKey()))
						score += entry.getValue()*vecDoc.get(entry.getKey());
			}
			docScores.put(doc, score);
			score=0;
		}
			
		return docScores;
	}
}
