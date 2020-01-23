import java.util.HashMap;
import java.util.Map.Entry;

import indexation.*;

public class VectorialCos extends IRMode1{
	private Weighter w;
	
	public VectorialCos(Index i, Weighter w) {
		super(i);
		this.w=w;
	}

	public Double CalculNorme(HashMap<String, Integer> entry) {
		return null;
	}
	public HashMap<String, Double> getDocScores(HashMap<String, Integer> queryProcessed) {
		HashMap<String, Double> docScores = new HashMap<String, Double>();
		VectorialCart tmp = new VectorialCart(i, w);
		HashMap<String, Double> scoreTmp = tmp.getDocScores(queryProcessed);
		HashMap<String, Double> vecReq = new HashMap<String, Double>();
		vecReq = w.PoidsRequete(queryProcessed);
		HashMap<String, Double> vecDoc = new HashMap<String, Double>();
		double normeReq=0.;
		double normeDoc=0.;
		
		for (Entry<String, Double> mot : vecReq.entrySet()) {
			normeReq += Math.pow(mot.getValue(),2);
		}
		for (String doc : i.getDocs().keySet()) {
			vecDoc = w.PoidsDocument(doc);
			for (Entry<String, Double> mot : vecDoc.entrySet()) {
				normeDoc += Math.pow(mot.getValue(), 2);
			}
			docScores.put(doc, scoreTmp.get(doc) / (normeReq*normeDoc));
			normeDoc = 0.;
		}
		return docScores;
	}
	
	
}
