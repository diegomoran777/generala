package generala;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.swing.JOptionPane;

import org.json.JSONObject;

public class AdministradorPartidas {

	public AdministradorPartidas()
	{
		
	}
	
	public JSONObject cargarPartida(String nombreArchivo)
	{   
		if(nombreArchivo != null)
		{
			try 
			{
				JSONObject load =  new JSONObject(new String(Files.readAllBytes(Paths.get(nombreArchivo + ".json"))));
				return load;
			}
			catch (Exception e) 
			{
				JOptionPane.showMessageDialog(null, "LA PARTIDA NO EXISTE");
			}
		}
		else if(nombreArchivo == null) 
		{
		
			JOptionPane.showMessageDialog(null, "SALIENDO");
			
		}
		return null;
	}
	
	public void salvarPartida(String nombrePartida, JSONObject juego ) throws IOException
	{
		try { 
			OutputStream save = new FileOutputStream(nombrePartida + ".json");
			Writer writer = new OutputStreamWriter(save,"UTF-8");
			writer.write(juego.toString());
			writer.close();
			save.close();
			JOptionPane.showMessageDialog(null, "LA PARTIDA HA SIDO GUARDADA CON EXITO");
			} 
		catch (UnsupportedEncodingException e) 
		{
			JOptionPane.showMessageDialog(null, "ERROR AL GUARDAR, VUELVA A INTENTARLO");
		}
	}
	
}
