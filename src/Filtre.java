import java.io.File; 
import javax.swing.filechooser.*;

public class Filtre extends FileFilter	//Hérite de FileFilter
{
	public String description;
	public String extension;
	public static String theExtension;
	
	
	public Filtre(String description, String extension)	//Declare la fonction de la Class
	{	
		if(description == null || extension ==null)
		{
			throw new NullPointerException("La description (ou extension) ne peut être null.");	//Si l'extension ou description n'est pas défnie
		}
		
		this.description = description;	//On ajoute les paramétres
		this.extension = extension;
		
		getExtension();
	}

	public boolean accept(File file)	//Redéfinie la méthode de base pour s'adapter au nouveau besoin
	{
		if(file.isDirectory()) 
		{ 
			return true; 
		} 
		String nomFichier = file.getName().toLowerCase();
		
		return nomFichier.endsWith(extension);
	}
	
	public String getDescription()
	{	
		return description;
	}
	
	public String getExtension()
	{	
		theExtension = extension;
		return extension;
	}


}
