import java.util.HashMap;
import java.util.Map.Entry;
import indexation.*;

public class WeighterTFIDF extends Weighter{
	
	public WeighterTFIDF(Index i) {
		super(i);
	}
	
	public HashMap <String,Double> PoidsDocument(String doc){
		WeighterTF tmp = new WeighterTF(i);
		HashMap <String,Double> val = new HashMap<String, Double>();
		int nb_doc=i.getDocs().size();
		
		for (Entry<String,Double> val_TF : tmp.PoidsDocument(doc).entrySet()) {
			val.put(val_TF.getKey(), val_TF.getValue()*Math.log(nb_doc/i.getTfsForStem(val_TF.getKey()).size()));
		}
		return val;
	}
	
	public HashMap <String,Double> PoidsRequete(HashMap<String, Integer> queryProcessed){
		WeighterTF tmp = new WeighterTF(i);

		return tmp.PoidsRequete(queryProcessed);
		}
}
