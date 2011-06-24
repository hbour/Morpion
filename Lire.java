/*********************************************************************
  Nom du programme  :  Lire.java
  Auteur            :  Lécu Regis 
  Mise a jour       :  février 2001, maj nov 2003 jcc
  Fonction          :  bibliothèque de fonctions de lecture

  Pour compatibilité dans la progression avec "Le livre de Java comme
  premier langage", des alias de chaque fonction sont donnés en fin de la
  classe.
  Tous les noms de fonctions devraient commencer par une minuscule.En 
  dehors de cette boîte à outils de fonctions, cette convention sera 
  scrupuleusement respectée.
**********************************************************************/
public class Lire
{
	// Par défaut, la bibliothèque ne lit que les premiers caractères de chaque ligne
    private static boolean filtre = true;

	
	// Filtre :
	//  si leFiltre = vrai, seul le premier caractère de chaque ligne
	//  sera lu par la fonction Lire.c() (appel automatique de la fonction Purge)
	// sinon tous les caractères sont lus, y compris les caractères de contrôle 
    public static void Filtre ( boolean leFiltre)
    {
       filtre = leFiltre;
    }   
	
	// Purge : élimine tous les caractères jusqu'à la fin de la ligne
    public static void Purge()
    {      
		try 
		{
			char car ;	
			do
			{
                car = (char) System.in.read ();
			}
			while (car != '\n');
		}	
     	catch (java.io.IOException e)
		{
			System.out.println ("Erreur de saisie");
			System.exit (0);
		}	
    }   

	// Lecture d'une chaine terminée par un "RETURN" : saute la fin de ligne
	public static String Chaine()
	{
		char car ;
		String result = "";
		
		try 
		{
			car = (char) System.in.read ();
	        //lecture de la ligne jusqu'au retour charriot (13, 0xD)
			while (car != '\r')	
			{
				result = result + car;
				car = (char) System.in.read ();
			}
            
            // Saut du fin de ligne (10, 0xA)
			System.in.skip (1);				
		}
		catch (java.io.IOException e)
		{
			System.out.println ("Erreur de saisie");
			System.exit (0);
		}
		return result;
	}
   	
	// Lecture d'un caractère : uniquement le premier caractère de la nouvelle ligne
	//  si filtrage, n'importe quel caractère sinon
	public static char Caractere ()
	{
		char result = 0;
				
		try 
		{
			result = (char) System.in.read ();	       
		}

		catch (java.io.IOException e)
		{
			System.out.println ("Erreur de saisie");
			System.exit (0);
		}
		
        if (filtre)
		{
			Purge ();
		}

		return result;
	}

	public static int Entier ()
	{		
		int result = 0;
		
        try
		{
			result = Integer.parseInt ( Chaine () );						  
        }
			
		catch (NumberFormatException e)
		{
			System.out.println ("Format entier incorrect !");
            System.exit(0);				
		}
				
		return result;
	}
	
	public static short EntierCourt ()
	{
		short  result = 0;

		try
		{
			result = Short.parseShort ( Chaine () );			
		}			
		
		catch (NumberFormatException e)
		{
			System.out.println ("Format entier incorrect !");	
            System.exit(0);				
		}
				
		return result;
	}

	public static long EntierLong ()
	{
		long result = 0;
		
		try
		{		  
			result = Long.parseLong ( Chaine () );			
		}			
		
		catch (NumberFormatException e)
		{
			System.out.println ("Format entier incorrect !");				
            System.exit(0);	
		}		
		
		return result;
	}

	public static float Reel ()
	{		
		float result = 0;
	
		try
		{
			result = Float.valueOf( Chaine() ).floatValue () ;						  
		}			
		
		catch (NumberFormatException e)
		{
			System.out.println ("Format reel incorrect!");				
            System.exit(0);	
		}		
		
		return result;
	}	
	
	public static double ReelDouble ()
	{		
		double result = 0;
	
		try
		{
			result = Double.valueOf( Chaine() ).doubleValue () ;						  
		}			
		
		catch (NumberFormatException e)
		{
			System.out.println ("Format reel incorrect!");				
            System.exit(0);	
		}		
		
		return result;
	}	

	// Attente : permet de visualiser les résultats avant la sortie
	//	de l'application. 
    public static void Attente()
    {
		System.out.println ();
        System.out.println ("*** Tapez Entree pour Terminer ***");
        Lire.c();
    }
	
	// Attente : permet de visualiser les résultats avant la suite
	//	de l'application. 
    public static void Suite()
    {
		System.out.println ();
        System.out.println ("*** Tapez Entree pour Continuer ***");
        Lire.c();
    }
	
	public static boolean Question(String msg)
    {
		char reponse ;
		
		do
		{	
	        System.out.print (msg + " (O/N ) ?" );			
			reponse = Lire.c();
		}while ((reponse!='O')&&(reponse!='o')&&(reponse!='n')&&(reponse!='N'));
		// arrêt quand reponse est égal à O,o,N,n
		return (reponse == 'O') || (reponse == 'o') ;
    }

// Alias des fonctions : pour compatibilité avec le livre
//		"Le livre de Java comme premier langage"

	public static String S ()
	{
        return Chaine();
    }

	public static short s ()
	{
        return EntierCourt();
    }

	public static long l ()
	{
        return EntierLong();
    }

	public static int i ()
	{
        return Entier();
    }

	public static char c ()
	{
        return Caractere();
    }

    public static float f ()
	{
        return Reel ();
    }

	public static double d ()
	{
        return ReelDouble ();
    }
	
}


