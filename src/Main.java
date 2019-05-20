import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;
import javax.swing.text.html.HTMLDocument.Iterator;

public class Main {

	public static void main(String[] args) {
		
		ArrayList<Integer> dados=new ArrayList<Integer>();
		
		dados.add(5);
		dados.add(5);
		dados.add(5);
		dados.add(5);
		dados.add(1);
	
	int cont2=0;
		for (int i = 1; i <= 6; i++) 
		{   int cont=0;
			for (int j = 0; j < dados.size(); j++) 
			{
				if(dados.get(j)==i){
					cont++;
				}
			}
			if(cont==3){
				cont2++;
			}
			else{
				if(cont==2){
					cont2++;
				}
			}
		}
		if(cont2==2) {
			System.out.println("true");
		}
		else {
			System.out.println("false");
		}
		
		
		
		
		
	        
}
}