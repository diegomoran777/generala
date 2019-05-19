import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;
import javax.swing.text.html.HTMLDocument.Iterator;

public class Main {

	public static void main(String[] args) {
		
		ArrayList<Integer> dados=new ArrayList<Integer>();
		
		dados.add(6);
		dados.add(5);
		dados.add(4);
		dados.add(3);
		dados.add(2);
	
		
		JugadaEscalera esc = new JugadaEscalera();
		
		System.out.println(esc.encontrada(dados));
	        
}
}