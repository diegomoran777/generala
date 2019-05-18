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
		
		ArrayList<Integer> lista=new ArrayList<Integer>();
		
		lista.add(1);
		lista.add(3);
		lista.add(5);
		lista.add(4);
		lista.add(2);
	
		Collections.sort(lista);
		for (int i = 1; i < lista.size(); i++) {
			
			System.out.println(i);
		}
		
System.out.println(lista.size());
}
}