import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import core.*;

import java.io.FileNotFoundException;
import java.io.IOException;
	
import indexation.*;
public class TestInterrogation {
	public static void main(String[] args) throws FileNotFoundException, IOException {
		Index i = Index.deserialize("CISIserialize");
		String query = "";
		Weighter w_TF = new WeighterTF(i);
		Weighter w_TFIDF = new WeighterTFIDF(i);
		IRMode1 modB = new Boolean(i);
		IRMode1 modCart = new VectorialCart(i,w_TF);
		HashMap<String, Integer> queryProcessed = modB.getQueryProcessed(query);
		/*System.out.println(i.getTfsForDoc("2300"));
		
		System.out.println("Les id des documents contenants le mot young avec son occurence\n");
		System.out.println(i.getTfsForStem("computer_aid")+"\n");
		System.out.println("Les id des documents contenants le mot attempt avec son occurence\n");
		System.out.println(i.getTfsForStem("attempt")+"\n");
		
		for (String doc : i.getDocs().keySet()) {	
			if (i.getTfsForDoc(doc).containsKey("computer_aid") && i.getTfsForDoc(doc).containsKey("young"))
				System.out.println(doc);
		}
		*/
		
		Requete q1 = new Requete("22",i);
		query="What is the usual relevance of the content of articles to their titles??";
		System.out.println(query);
		
		LinkedHashMap<String, Double> res = modCart.runMode1(query);
		
		
		System.out.println(modB.runMode1("attempt"));
		System.out.println(modB.getDocScores(modB.getQueryProcessed("attempt")).size());
		System.out.println(i.getTfsForStem("attempt"));
		
		for (String test : modB.runMode1("attempt").keySet()) {
			int ok=0;
			for (String test2 : modB.getDocScores(modB.getQueryProcessed("attempt")).keySet()) {
				if (test == test2) {
					ok=1;
				}
			}
			if (ok==0) {
				System.out.println(test);
			}else
				ok=0;
		}
		System.out.println(i.getTfsForDoc("873"));
		
		ArrayList<Document> docN = new ArrayList<Document>();
		
		for (String id : res.keySet()){
			docN.add(i.getDoc(id));
		}

		Evaluation e1 = new Evaluation(docN, q1, 10);
		ArrayList<Document> d2 = e1.getDocN();
		for (Document d1 : docN) {
			//for (Document d2 : docN) {
			//	System.out.println(d1.getId());
			//}
		}
		//System.out.println(res);
	
		//System.out.println(e1.Precision());
		 /*for (String i4 : res.keySet()) {
			System.out.println(i.getDoc(i4).getText());
		 }*/
	}
}
