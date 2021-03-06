import java.util.HashMap;

import indexation.*;

public class Boolean extends IRMode1{
	
	public Boolean(Index i) {
		super(i);
	}

	public HashMap<String, Double> getDocScores(HashMap<String, Integer> queryProcessed) {
		HashMap<String, Double> docScores = new HashMap<String, Double>();
		int ok=1;
		for (String doc : i.getDocs().keySet()) {
			for (String mot : queryProcessed.keySet()) {
				if (i.getTfsForDoc(doc).containsKey(mot) && ok==1) {
					ok=1;
				}else
					ok=0;
			}
			if (ok==1) {
				docScores.put(doc, 1.0);
			}else {
				ok=1;
			}
		}
		
		return docScores;
	}
	
}
	
