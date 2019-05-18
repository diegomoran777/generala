import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;
import javax.swing.text.html.HTMLDocument.Iterator;

public class Main {

	public static void main(String[] args) {
		
		ArrayList<Integer> lista=new ArrayList<Integer>();
		
		lista.add(4);
		lista.add(4);
		lista.add(4);
		lista.add(3);
		lista.add(3);
	
		for(int i=1;i<=6;i++)
		{
			int cont=0;
			for(int j=0;j<lista.size();j++)
			{
				if(lista.get(j)==i)
				{
					cont++;
				}
			}
			if(cont==4)
			{
				System.out.println("full");
				i=6;
			}
			else {
				if(i==6)
				{
					System.out.println("false");
				}
			}
		}
		
}
}