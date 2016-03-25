/**
 * @author hugo
 */
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.JTextComponent;


public class GeneralTabbed extends JPanel implements ChangeListener, FocusListener
{
		//Les variables
	public Color fond = Color.WHITE;
	public JPanel masterP = new JPanel();
	
	public static JCheckBox iconeC,nameC,dimensionC,undecoratedC,centreC,resizableC,menuC,toolbarC,systemtrayC;
	public static JTextField nameF;
	public static JTextField dimensionHF, dimensionWF, iconeF, locationXF, locationYF;
	
	public GeneralTabbed()
	{
		JPanel general = new JPanel();
			general.setBackground(fond);
			general.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
			general.setLayout(new BorderLayout());
		JPanel eastG = new JPanel();
			eastG.setBackground(fond);
			eastG.setLayout(new GridBagLayout());
		JPanel centerG = new JPanel();
			centerG.setBackground(fond);
			centerG.setLayout(new GridLayout(9,1,10,10));
			
		GridBagConstraints c = new GridBagConstraints ();	//Pour le GridBagLayout : Ajout de contraintes
	
		iconeC = new JCheckBox("Changer l'icone : ");
			iconeC.setBackground(fond);
			iconeC.addChangeListener(this);
		nameC = new JCheckBox("Nom de la fentre : ");
			nameC.setBackground(fond);
			nameC.setSelected(true);
			nameC.setEnabled(false);
		dimensionC = new JCheckBox("Dimension : ");
			dimensionC.setBackground(fond);
			dimensionC.setSelected(true);
			dimensionC.setEnabled(false);
		undecoratedC = new JCheckBox("Enlever les bordures ? ");
			undecoratedC.setBackground(fond);
		centreC = new JCheckBox("Centrer la fentre ? ");
			centreC.setBackground(fond);
			centreC.setSelected(true);
			centreC.addChangeListener(this);
		resizableC = new JCheckBox("Redimensionable ? ");
			resizableC.setBackground(fond);
		menuC = new JCheckBox("Menu ? ");
			menuC.setBackground(fond);
			menuC.setEnabled(true);
			menuC.addChangeListener(this);
		toolbarC = new JCheckBox("Toolbar ? ");
			toolbarC.setBackground(fond);
			toolbarC.setEnabled(true);
			toolbarC.addChangeListener(this);
		systemtrayC = new JCheckBox("System Tray ? ");
			systemtrayC.setBackground(fond);
			systemtrayC.setEnabled(true);
			systemtrayC.addChangeListener(this);
		
		nameF = new JTextField(30);
		dimensionWF = new JTextField("Width",15);
			dimensionWF.addFocusListener(this);
		dimensionHF = new JTextField("Height",15);
		dimensionHF.addFocusListener(this);
		iconeF = new JTextField(15);
			iconeF.setEditable(false);
		locationXF = new JTextField("X",15);
			locationXF.setEditable(false);
			locationXF.addFocusListener(this);
		locationYF = new JTextField("Y",15);
			locationYF.setEditable(false);
			locationYF.addFocusListener(this);
		
		JLabel info1 = new JLabel(" Pour l'icone, entrer le nom et le format de l'image, vous mettrez");
		JLabel info2 = new JLabel(" celle-ci dans un dossier nomm \"images\" dans le dossier Projet.");
		JLabel info3 = new JLabel(" Ensuite faites :");
		JLabel info4 = new JLabel("   - cliquez droit sur le dossier image dans l'explorer Eclipse");
		JLabel info5 = new JLabel("   - allez dans \"Build Path\", puis \"Use As Source Folder\"");
		

		general.add(eastG, BorderLayout.CENTER);
				c.fill = GridBagConstraints.HORIZONTAL;	// .fill = rempli, soit Horizontalement, verticalement ou les deux
				c.gridx = 1;							// .gridx = abscisse de l'objet
				c.gridy = 0;							// .gridy = ordonnée de l'objet
				c.gridwidth = 2; 						// .gridwidth = s'étend sur 1, 2, 3, ... cases en largeur
				//c.gridheight = 1;						// .gridheight = s'étend sur 1, 2, 3, ... cases en hauteur
				c.insets = new Insets(0,0,20,0);		// .insets... = défini des marges
			eastG.add(nameF, c);
				c.fill = GridBagConstraints.HORIZONTAL;
				c.gridx = 1;
				c.gridy = 1;
				c.gridwidth = 1; 
				c.insets = new Insets(0,0,20,0);
			eastG.add(dimensionWF, c);
				c.fill = GridBagConstraints.HORIZONTAL;
				c.gridx = 2;
				c.gridy = 1;
				c.gridwidth = 1; 
				c.insets = new Insets(0,0,20,0);
			eastG.add(dimensionHF, c);
				c.fill = GridBagConstraints.HORIZONTAL;
				c.gridx = 1;
				c.gridy = 2;
				c.gridwidth = 1; 
				c.insets = new Insets(0,0,20,0);
			eastG.add(locationXF, c);
				c.fill = GridBagConstraints.HORIZONTAL;
				c.gridx = 2;
				c.gridy = 2;
				c.gridwidth = 1; 
				c.insets = new Insets(0,0,20,0);
			eastG.add(locationYF,c);
				c.fill = GridBagConstraints.HORIZONTAL;
				c.gridx = 1;
				c.gridy = 3;
				c.gridwidth = 2; 
				c.insets = new Insets(0,0,20,0);
			eastG.add(iconeF, c);
				c.fill = GridBagConstraints.HORIZONTAL;
				c.gridx = 1;
				c.gridy = 4;
				c.gridwidth = 2; 
				c.insets = new Insets(0,0,20,0);
			eastG.add(info1, c);
				c.fill = GridBagConstraints.HORIZONTAL;
				c.gridx = 1;
				c.gridy = 5;
				c.gridwidth = 2; 
				c.insets = new Insets(0,0,20,0);
			eastG.add(info2 ,c);
				c.fill = GridBagConstraints.HORIZONTAL;
				c.gridx = 1;
				c.gridy = 6;
				c.gridwidth = 2; 
				c.insets = new Insets(0,0,20,0);
			eastG.add(info3 ,c);
				c.fill = GridBagConstraints.HORIZONTAL;
				c.gridx = 1;
				c.gridy = 7;
				c.gridwidth = 2; 
				c.insets = new Insets(0,0,20,0);
			eastG.add(info4 ,c);
				c.fill = GridBagConstraints.HORIZONTAL;
				c.gridx = 1;
				c.gridy = 8;
				c.gridwidth = 2; 
				c.insets = new Insets(0,0,20,0);
			eastG.add(info5 ,c);
			
		//general.add(centerG, BorderLayout.CENTER);
				c.fill = GridBagConstraints.HORIZONTAL;
				c.gridx = 0;
				c.gridy = 0;
				c.gridwidth = 1; 
				c.insets = new Insets(0,0,20,0);
			eastG.add(nameC,c);
				c.fill = GridBagConstraints.HORIZONTAL;
				c.gridx = 0;
				c.gridy = 1;
				c.gridwidth = 1; 
				c.insets = new Insets(0,0,20,0);
			eastG.add(dimensionC,c);
				c.fill = GridBagConstraints.HORIZONTAL;
				c.gridx = 0;
				c.gridy = 2;
				c.gridwidth = 1; 
				c.insets = new Insets(0,0,20,0);
			eastG.add(centreC,c);
				c.fill = GridBagConstraints.HORIZONTAL;
				c.gridx = 0;
				c.gridy = 3;
				c.gridwidth = 1; 
				c.insets = new Insets(0,0,20,0);
			eastG.add(iconeC,c);
				c.fill = GridBagConstraints.HORIZONTAL;
				c.gridx = 0;
				c.gridy = 4;
				c.gridwidth = 1; 
				c.insets = new Insets(0,0,20,0);
			eastG.add(undecoratedC,c);
				c.fill = GridBagConstraints.HORIZONTAL;
				c.gridx = 0;
				c.gridy = 5;
				c.gridwidth = 1; 
				c.insets = new Insets(0,0,20,0);
			eastG.add(resizableC,c);
				c.fill = GridBagConstraints.HORIZONTAL;
				c.gridx = 0;
				c.gridy = 6;
				c.gridwidth = 1; 
				c.insets = new Insets(0,0,20,0);
			eastG.add(menuC,c);
				c.fill = GridBagConstraints.HORIZONTAL;
				c.gridx = 0;
				c.gridy = 7;
				c.gridwidth = 1; 
				c.insets = new Insets(0,0,20,0);
			eastG.add(toolbarC,c);
				c.fill = GridBagConstraints.HORIZONTAL;
				c.gridx = 0;
				c.gridy = 8;
				c.gridwidth = 1; 
				c.insets = new Insets(0,0,10,0);
			eastG.add(systemtrayC,c);
			
		this.add(general, BorderLayout.CENTER);
		this.setBackground(fond);
	}
	
	public static boolean isEmpty()
	{		// .trim()= retire les éspaces avant et apres le String
		if (nameF.getText().trim().equals("") || 
			(dimensionHF.getText().trim().equals("") || dimensionHF.getText().trim().equals("Height")) ||
			(dimensionWF.getText().trim().equals("") || dimensionWF.getText().trim().equals("Width")) ||
			(centreC.isSelected() &&
			(locationXF.getText().trim().equals("") || locationXF.getText().trim().equals("X")) || 
			(locationYF.getText().trim().equals("") || locationYF.getText().trim().equals("Y"))) ||
			(iconeF.getText().trim().equals("") && iconeC.isSelected()))
		{
			JOptionPane.showMessageDialog(Fenetre.frame," Un ou plusieurs JTextField ne sont pas remplis,\n" +
											" veuillez bien tout vérifier,\n Merci", 
											"Erreur - JTextField vide",JOptionPane.ERROR_MESSAGE);
			return true;
		}
			return false;
	}

	public void stateChanged(ChangeEvent arg0)
	{
		if(centreC.isSelected())
		{
			locationXF.setEditable(false);
			locationYF.setEditable(false);
		}else
		{
			locationXF.setEditable(true);
			locationYF.setEditable(true);
		}
		
		if(iconeC.isSelected())
		{
			iconeF.setEditable(true);
		}else
		{
			iconeF.setEditable(false);
		}
		
		if(GeneralTabbed.menuC.isSelected())
		{
			Fenetre.onglets.setEnabledAt(1,true);
		}else
		{
			Fenetre.onglets.setEnabledAt(1,false);
		}
		
		if(GeneralTabbed.toolbarC.isSelected())
		{
			Fenetre.onglets.setEnabledAt(2,true);
		}else
		{
			Fenetre.onglets.setEnabledAt(2,false);
		}
		
		if(GeneralTabbed.systemtrayC.isSelected())
		{
			Fenetre.onglets.setEnabledAt(3,true);
		}else
		{
			Fenetre.onglets.setEnabledAt(3,false);
		}
	}

	public void focusGained(FocusEvent e) 
	{
		((JTextComponent) e.getSource()).selectAll();	// Pour prendre tout le text lors de la séléction d'un JTextField
	}

	public void focusLost(FocusEvent e) 
	{
		((JTextComponent) e.getSource()).setCaretPosition(0);	// Pour annuler la séléction d'avant d'un JTextField
		
	}

}
