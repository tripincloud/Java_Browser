import java.util.ArrayList;
import java.util.Set;

import core.*;

public class Evaluation {
	private ArrayList<Document> docN = new ArrayList<Document>();
	private Requete q;
	
	public Evaluation(ArrayList<Document> docRes, Requete q, int n) {
		this.q=q;
		int i=0;
		for (Document doc : docRes) {
			if (i<n)	
				this.docN.add(doc);
			i++;
		}
	}
	
	public double Precision() {
		double docCom=0.;
		for (Document doc1 : docN) {
			for (Document doc2 : q.DocAttendus()) {
				if (doc2.getId().equals(doc1.getId()))
					docCom++;
			}
		}
		
		return (double) docCom/docN.size();
	}
	
	public double Rappel() {
		double docCom=0.;
		for (Document doc1 : docN) {
			for (Document doc2 : q.DocAttendus()) {
				if (doc2.getId().equals(doc1.getId()))
					docCom++;
			}
		}
		
		return docCom/q.DocAttendus().size();
	}
	
	public double  Fmesure(){
		double p=this.Precision(),r=this.Rappel();
		if (p+r != 0)
			return 2*(p*r)/(p+r);
		return 0.;
	}
	
	public ArrayList<Document> getDocN(){
		return docN;
	}
}
