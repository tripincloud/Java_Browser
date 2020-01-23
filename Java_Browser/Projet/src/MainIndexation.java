import indexation.*;

public class MainIndexation {
	public static void main(String[] args) {
		Parser p = new ParserCISI();  
		TextRepresenter r = new Stemmer();
		Index i = new Index("CISI",p,r);
		
		i.index("cisi.txt");
		i.serialize("CISIserialize");
		
		
		
		}
}
