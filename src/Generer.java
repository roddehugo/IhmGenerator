import java.awt.Toolkit;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.ImageIcon;

/**
 * @author hugo
 *
 */
public class Generer //La plus imcompréhensible, mais aussi la plus dur à réaliser
{	//Les variables
	public static MenuTabbed MenTa;
	public static String name;
	public String dimensionH, dimensionW, centre, locationX, locationY, undecorated, resizable, icone;
	public static String general, menu="", menuIt="", fin, menuEnt="", toolbar="", implement="", action="", system="", button ="a";
	public static boolean choice = false;
	public String nomMenu,NomMenuI;
	public int taille = MenuTabbed.JMenuCB.getItemCount(), start=0 , end=1,
	taille2 = MenuTabbed.JMenuItemCB.getItemCount(), startIt=0, endIt=1,
	taille3 = ToolBarTabbed.toolCB.getItemCount(),
	taille4 = SystemTrayTabbed.MenuItemCB.getItemCount();
	public StringTokenizer stk;
	public Vector vector = new Vector(), mnemo = new Vector(), vectorIt = new Vector(),
	letter = new Vector(), toolV = new Vector(), saveItem = new Vector(), saveTool = new Vector();
	public String cchar;
	
	
	public Generer()
	{
		general();	//Pour tout ce qui concerne la JFrame
		if (GeneralTabbed.menuC.isSelected())	//Pour tout ce qui concerne le JMenu
		{
			menuEnt +=
					"\n\n\tpublic void initMenu()" +
					"\n\t{" +
					"\n\t\tJMenuBar menuBar = new JMenuBar();";
			menu();	//Les JMenu
			menuItem();	//Les JMenuItem
			menuEnt +=
					menu + menuIt +
					"\n\t\tframe.setJMenuBar(menuBar);" +
					"\n\t}";
		}
		if (GeneralTabbed.toolbarC.isSelected())	//Pour tout ce qui concerne le JToolBar
		{
			toolbar += 
					"\n\n\tpublic void initToolBar()" +
					"\n\t{" +
					"\n\n\t\tJToolBar toolBar = new JToolBar();" +
					"\n\t\t\ttoolBar.setFloatable(false);";
			toolBar();
			toolbar += "\n\n\t\tframe.add(toolBar,BorderLayout.NORTH);" +
					"\n\t}";
		}
			
		if(MenuTabbed.listener.isSelected() || ToolBarTabbed.listener.isSelected())
		{
			actionListener();	//Ajout des action des boutons
			implement = " implements ActionListener";
		}
		if (GeneralTabbed.systemtrayC.isSelected())
		{
			system +="\n\n\tpublic void initSystemTray()" +
				"\n\t{"+	
				"\n\t\tif(SystemTray.isSupported()){" +
				"\n\t\t\tSystemTray tray = SystemTray.getSystemTray();	" +
				"\n\t\t}" +
				"\n\n\t\ttry" +
				"\n\t\t{"+
				"\n\t\t\tImage image = ImageIO.read(getClass().getClassLoader().getResource(\"/"+GeneralTabbed.iconeF.getText()+"\"));" +
				"\n\t\t} catch (IOException e1) {e1.printStackTrace();}" +
				"\n\n\t\tPopupMenu popup = new PopupMenu();";
			menuPopup();
			system +="\n\n\t\tTrayIcon trayIcon = new TrayIcon(image, \""+name+"\", popup);" +
					"\n\n\t\ttry" +
					"\n\t\t{" +
					"\n\t\t\ttray.add(trayIcon);" +
					"\n\t\t\ttrayIcon.setImageAutoSize(true);" +
					"\n\t\t} catch (AWTException e1) {e1.printStackTrace();}" +
					"\n\t";			
		}
		
		general = 
				"import java.awt.event.*;" +
				"\nimport javax.swing.event.*;" +
				"\nimport javax.swing.*;" +
				"\nimport java.awt.*;" +
				"\nimport javax.imageio.ImageIO;" +
				"\nimport java.io.*;" +
				"\n\n" +
				"public class "+name+implement+
				"\n{\n" +
				"\n\tpublic JFrame frame;" +
				"\n\tpublic PopupMenu popup;" +
				"\n\tpublic Image image;" +
				"\n\tpublic SystemTray tray;" +
				"\n\tpublic JButton" + button +";" +
				"\n\n\tpublic "+name+"()" +
				"\n\t{" +
				"\n\t\tframe= new JFrame();"+"" +
				"\n\t\tframe.setTitle(\""+name+"\");" +
				"\n\t\tframe.setSize("+dimensionW+","+dimensionH+");" +
				"\n\t\tframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);" +
				"\n\t\t"+resizable +
				"\n\t\t"+centre +
				"\n\t\t"+undecorated +
				"\n\t\t"+icone +	
				"\n\t\tframe.setBackground(Color.white);";
		
		if (GeneralTabbed.menuC.isSelected())
		{
			general +=
				"\n\t\tinitMenu();";
		}
		if (GeneralTabbed.toolbarC.isSelected())
		{
			general +=
				"\n\t\tinitToolBar();";
		}
		if (GeneralTabbed.systemtrayC.isSelected())
		{
			general +=
				"\n\t\tinitSystemTray();";
		}
			general +=
				"\n\t\tframe.setVisible(true);" +
				"\n\t}";;
		
		fin = 
			"\n\n\tpublic static void main(String[] args)" +
			"\n\t{" +
			"\n\t\t"+name+" start = new "+name+"();" +
			"\n\t}" +
			"\n\n}";
			
		new MonJDialog();
			
	}
	
	public void general()	//Pour tout ce qui concerne la JFrame
	{
		if(GeneralTabbed.nameC.isSelected())
		{
			 name = GeneralTabbed.nameF.getText();	//Le nom
		}
		
		if(GeneralTabbed.dimensionC.isSelected())
		{
			dimensionH = GeneralTabbed.dimensionHF.getText();	//Dimensions Largeur Hauteur
			dimensionW = GeneralTabbed.dimensionWF.getText();
		}
		
		if(GeneralTabbed.centreC.isSelected())
		{
			centre = "frame.setLocationRelativeTo(null);";	//Centrer la JFaame
		}else 												//ou
		{													//
			locationX = GeneralTabbed.locationXF.getText();	//Coordonées X,Y
			locationY = GeneralTabbed.locationYF.getText();
			centre = locationX+locationY;
		}
		
		if(GeneralTabbed.undecoratedC.isSelected())
		{
			undecorated = "frame.setUndecorated(true);";	//Efface les bordures 
		}else
		{
			undecorated = "frame.setUndecorated(false);";	//ou les laisse, par défaut
		}
		
		if(GeneralTabbed.resizableC.isSelected())	
		{
			resizable = "frame.setResizable(true);";		//Redimensionnable oui ou non
		}else
		{
			resizable = "frame.setResizable(false);";
		}
		
		if(GeneralTabbed.iconeC.isSelected())	//Changer oui ou non l'image de la JFrame
		{
			icone = "frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource(\"/"+GeneralTabbed.iconeF.getText()+"\")));";
		}else
		{
			icone = "//frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource(\"/"+GeneralTabbed.iconeF.getText()+"\")));";
		}
	}

	public void menuItem()
	{
		for (int i=0; i!=taille2;i++) //un passage pour chaque Item dans la ComboBox des JMenuItem
		{
			vectorIt.clear(); //On nétoie le Vector corrésopndant
									//On coupe le String pris en fonction des -;-
			stk = new StringTokenizer(MenuTabbed.JMenuItemCB.getItemAt(i).toString(),";"); 
				while(stk.hasMoreTokens())
				{
					vectorIt.addElement(stk.nextToken().toString()); //On ajoute les éléments dans le Vector, un par un
				}													//tant que c'est pas fini
			
			if (vectorIt.size()==1)	//Si la taille du Vector == 1, soit si on veut ajouter un Separator
			{
				int substart = 0;
				int subend = vectorIt.firstElement().toString().length()-10;
				menuIt += "\n\n\t\t\t"+vectorIt.firstElement().toString().substring(substart,subend).toLowerCase().replace(" ", "")+".addSeparator();";
			}else
			{			//on récupere l'élément à 1 soit le nom du JMenuItem, puis on le met en string minuscule
				String str =vectorIt.elementAt(1).toString().toLowerCase().replace(" ", "");//, puis on enleve les éspaces
				menuIt += "\n\n\t\tJMenuItem "+str+" = new JMenuItem(\""+vectorIt.elementAt(1).toString()+"\");";
					//On a joute les JMenuItem avec les différentes options
				if (MenuTabbed.listener.isSelected())	
				{
					menuIt +="\n\t\t\t"+str+".addActionListener(this);";
					saveItem.addElement(str);
					button+=", "+str;
				}else
				{
					menuIt +="\n\t\t\t//"+str+".addActionListener(this);";
				}
				
				if (MenuTabbed.icone.isSelected())
				{
					menuIt +="\n\t\t\t"+str+".setIcon(new ImageIcon(getClass().getResource(\"/"+vectorIt.elementAt(2).toString()+"\")));";
				}else
				{
					menuIt +="\n\t\t\t//"+str+".setIcon(new ImageIcon(getClass().getResource(\"/\")));";
				}
				
				if (MenuTabbed.raccourci.isSelected())
				{
					verifieLetter();
					menuIt +="\n\t\t\t"+str+".setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_"+vectorIt.elementAt(1).toString().toUpperCase().replace(" ", "").substring(startIt,endIt)+",KeyEvent.CTRL_DOWN_MASK));";
				}else
				{
					menuIt +="\n\t\t\t//"+str+".setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_"+vectorIt.elementAt(1).toString().toUpperCase().replace(" ", "").substring(startIt,endIt)+",KeyEvent.CTRL_DOWN_MASK));";
				}
				
					menuIt +="\n\t\t\t"+vectorIt.elementAt(0).toString().toLowerCase().replace(" ", "")+".add("+vectorIt.elementAt(1).toString().toLowerCase().replace(" ", "")+");";
				}
		}
	}

	public void menu() // De meme pour les JMenu, meme procédure
	{
		for (int i=0; i!=taille;i++)
		{
			vector.clear();
			
			stk = new StringTokenizer(MenuTabbed.JMenuCB.getItemAt(i).toString(),";");
				while(stk.hasMoreTokens())
				{
					vector.addElement(stk.nextToken().toString());
				}
				String str =vector.elementAt(0).toString().toLowerCase().replace(" ", "");
				if (MenuTabbed.mnemonic.isSelected())
				{
					verifieMnemonic();
					menu +=  "\n\n\t\tJMenu "+str+" = new JMenu(\""+vector.elementAt(0).toString()+"\");" +
						"\n\t\t\t"+str+".setMnemonic(\'"+vector.elementAt(0).toString().toLowerCase().toString().toUpperCase().substring(start,end)+"\');";
				}else
				{
					menu +=  "\n\n\t\tJMenu "+str+" = new JMenu(\""+vector.elementAt(0).toString()+"\");" +
					"\n\t\t\t//"+str+".setMnemonic(\'"+vector.elementAt(0).toString().toLowerCase().toString().toUpperCase().substring(start,end)+"\');";;
				}
				
				menu +="\n\t\t\tmenuBar.add("+vector.elementAt(0).toString().replace(" ", "").toLowerCase().replace(" ", "")+");";
		}
		
	}
	
	public void menuPopup()	//Ici pas besoin de StringTokenizer allié avec un Vector, car il n'y a qu'un seul émément transmit
	{	
		for (int i=0; i!=taille4;i++)
		{
			if(SystemTrayTabbed.MenuItemCB.getItemAt(i)=="Separator")
			{
				system +="\n\n\t\t\tpopup.addSeparator();";
			}
			else
			{
				String str=SystemTrayTabbed.MenuItemCB.getItemAt(i).toString().toLowerCase().replace(" ", "");
				system +=  "\n\n\t\tMenuItem "+str+" = new MenuItem(\""+SystemTrayTabbed.MenuItemCB.getItemAt(i).toString()+"\");";
				
				if (SystemTrayTabbed.listener.isSelected())
				{
					system += "\n\t\t\t"+str+".addActionListener(this);";
					button+=", "+str;
				}else
				{
					system += "\n\t\t\t//"+str+".addActionListener(this);";
				}
				system +="\n\t\t\tpopup.add("+str+");";
			}
		}
	}
	
	public void toolBar()	//De meme aue pours les JMenuItem
	{
		for (int i=0; i!=taille3; i++)
		{
			toolV.clear();
			
			stk = new StringTokenizer(ToolBarTabbed.toolCB.getItemAt(i).toString(),";");
				while(stk.hasMoreTokens())
				{
					toolV.addElement(stk.nextToken().toString());
				}
			String str=toolV.elementAt(0).toString().toLowerCase().replace(" ", "");
				toolbar += "\n\n\t\tJButton "+str+" = new JButton(new ImageIcon(getClass().getResource(\"/"+toolV.elementAt(1).toString()+"\")));";
				
			if (ToolBarTabbed.tooltip.isSelected())
			{
				toolbar += "\n\t\t\t"+str+".setToolTipText(\""+toolV.elementAt(0).toString()+"\");";
			}else				
			{
				toolbar += "\n\t\t\t//"+str+".setToolTipText(\""+toolV.elementAt(0).toString()+"\");";
			}
			if (ToolBarTabbed.listener.isSelected())
			{
				toolbar += "\n\t\t\t"+str+".addActionListener(this);";
				saveTool.addElement(str);
				button+=", "+str;
			}else
			{
				toolbar += "\n\t\t\t//"+str+".addActionListener(this);";
				
			}
			
				toolbar += "\n\t\t\ttoolBar.add("+toolV.elementAt(0).toString().replace(" ", "").toLowerCase().replace(" ", "")+");";
		}
	}
	
	public void actionListener()	//Si on a décidé d'ajouter les Actions aux différents boutons
	{
		action = "\n\n\tpublic void actionPerformed(ActionEvent e)" +
				"\n\t{";
		if (MenuTabbed.listener.isSelected())
		{
			for(int j=0; j!=saveItem.size(); j++)
			{
				action += "\n\n\t\tif(e.getSource()=="+saveItem.elementAt(j).toString().toLowerCase().replace(" ", "")+")" +
						"\n\t\t{" +
						"\n\n\t\t}";
			}
		}
		
		if (ToolBarTabbed.listener.isSelected())
		{
			for(int i=0; i!=saveTool.size(); i++)
			{
				action += "\n\n\t\tif(e.getSource()=="+saveTool.elementAt(i).toString().toLowerCase().replace(" ", "")+")" +
						"\n\t\t{" +
						"\n\n\t\t}";
			}
		}
		if(SystemTrayTabbed.listener.isSelected())
		{
			for(int k=0; k!=taille4; k++)
			{
				if(SystemTrayTabbed.MenuItemCB.getItemAt(k)!="Separator")
				{
				action += "\n\n\t\tif(e.getSource()=="+SystemTrayTabbed.MenuItemCB.getItemAt(k).toString().toLowerCase().replace(" ", "")+")" +
						"\n\t\t{" +
						"\n\n\t\t}";
				}
			}
		}
		action += "\n\t}";
	}
	
	public void verifieLetter()	//Pour vérifier si une lettre est déjà prise pour les raccourcis
	{
		while (letter.contains(vectorIt.elementAt(1).toString().toUpperCase().replace(" ", "").substring(startIt,endIt)))
		{	//Tant que le Vector letter contiens la lettre dans son tableau, on va à la lettre suivante dans le nom du JMenuItem
			startIt++;
			endIt++;
		}
		letter.addElement(vectorIt.elementAt(1).toString().toUpperCase().replace(" ", "").substring(startIt,endIt));
	}

	public void verifieMnemonic() //Pour vérifier si une lettre est déjà prise pour les mnémonics
	{
		while (mnemo.contains(vector.elementAt(0).toString().toUpperCase().replace(" ", "").substring(start,end)))
		{	//Tant que le Vector mnemo contiens la lettre dans son tableau, on va à la lettre suivante dans le nom du JMenu
			start++;
			end++;
		}
		
		mnemo.addElement(vector.elementAt(0).toString().toUpperCase().replace(" ", "").substring(start,end));
	}

}
