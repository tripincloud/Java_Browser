import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedHashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import indexation.Index;

public class InterfaceGraphique extends JFrame implements ActionListener{
	private final static int NB_DOCS=10;
	private JTextField q =  new JTextField();
	private JButton entrer = new JButton("Entrer");
	private JPanel contentPane = (JPanel) this.getContentPane();
	private JScrollPane res;
	private JLabel label= new JLabel("");
	private Index i;
	
	public InterfaceGraphique(Index i) {
		super("Moteur de recherche");
		this.i=i;
		
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setSize(600, 400);
		this.setLocationRelativeTo(null);
		
		entrer.addActionListener(this);
		q.addActionListener(this);
		contentPane.add(BarreDeRecherche(), BorderLayout.NORTH);
		
		res = new JScrollPane(label);
		contentPane.add(res);
	}
	
	public JPanel BarreDeRecherche(){
		JPanel panel = new JPanel(new FlowLayout());
		q.setPreferredSize(new Dimension(200,30));
		panel.add(q);
		panel.add(entrer);
		
		return panel;
	}
	
	public void Resultat(String text) {
		String tmp = label.getText();
		label.setText(tmp+text);
	}
	
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == entrer){
			label.setText("<html>");
			VectorialCart modCart = new VectorialCart(i, new WeighterTF(i));
			LinkedHashMap<String, Double> res_mod = modCart.runMode1(q.getText());
			int n=0;
			for (String doc : res_mod.keySet()) {
				if ( n<NB_DOCS) {
					try {
						Resultat("Document "+doc+" :"+"<br><br>"+i.getDoc(doc).getText()+"<br><br>");
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
					n++;
				}
			}
			Resultat("<html>");
		}
 	}	
	
	public static void main(String[] args) {
		Index i = Index.deserialize("CISIserialize");
		InterfaceGraphique g = new InterfaceGraphique(i);
		g.setVisible(true);
	}

}
