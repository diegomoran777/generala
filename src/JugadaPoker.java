import java.util.ArrayList;

public class JugadaPoker implements Jugada{

	public JugadaPoker() {
		
	}
	
	
	@Override
	public String nombre() {
		
		return "Poker";
	}

	@Override
	public int puntos() {
		
		return 40;
	}

	@Override
	public boolean encontrada(ArrayList<Integer> dados) {
		boolean detectar=false;
		for(int i=1;i<=6;i++)
		{
			int cont=0;
			for(int j=0;j<dados.size();j++)
			{
				if(dados.get(j)==i)
				{
					cont++;
				}
			}
			if(cont==4)
			{
				i=6;
				detectar= true;
			}
			else {
				if(i==6)
				{
					detectar= false;
				}
			}
		}
       return detectar;
	}

	
	
}
