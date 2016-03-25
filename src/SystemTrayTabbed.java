/**
 * @author hugo
 *
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.JTextComponent;


public class SystemTrayTabbed extends JPanel implements FocusListener, ActionListener
{	//Les Varibles
	public Color fond = Color.WHITE;
	public JPanel masterP = new JPanel();
	public JLabel nomMenuItem, choisis2;
	public JTextField MenuItemTF;
	public JButton MenuItemBu, SeparatorBu;
	public ImageIcon add = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/action_add.png")));
	public static JComboBox MenuItemCB;
	public static JCheckBox listener;
	public boolean added = false;
	
	public SystemTrayTabbed()
	{
		masterP.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		masterP.setBackground(fond);
		masterP.setLayout(new BorderLayout());
		
		JPanel menuP = new JPanel(new GridBagLayout());
			menuP.setBackground(fond);
			menuP.setBorder(BorderFactory.createTitledBorder("JMenu - Entrez le nom, puis faites -ajouter-"));
		
		JPanel menuItemP = new JPanel(new GridBagLayout());
			menuItemP.setBackground(fond);
			menuItemP.setBorder(BorderFactory.createTitledBorder("JMenuItem - Entrez le nom, puis le menu d'appartenance, puis faites -ajouter-"));
		
		String nom = "Nom :";
		nomMenuItem = new JLabel(nom);
		choisis2 = new JLabel("Choisis");
		
		MenuItemTF = new JTextField("",35);
			MenuItemTF.addFocusListener(this);
			
		listener = new JCheckBox("Listeners ? ");
			listener.setBackground(fond);
			listener.setEnabled(true);
			listener.setSelected(true);
			
		MenuItemBu = new JButton(add);
			MenuItemBu.addActionListener(this);
			MenuItemBu.setToolTipText("Ajouter");
		SeparatorBu = new JButton("Separator",add);
			SeparatorBu.addActionListener(this);
			SeparatorBu.setToolTipText("Ajouter une séparation");
		
		MenuItemCB = new JComboBox();
			
		GridBagConstraints c = new GridBagConstraints ();
		GridBagConstraints d = new GridBagConstraints ();
			//c.gridx = 0;	// .gridx = abscisse de l'objet
			//c.gridy = 0;	// .gridy = ordonnée de l'objet
			//c.weightx = 1;	// .weightx ou .weighty = défini un poid pour l'objet en largeur et/ou hauteur
			//c.gridwidth = 1;	// .gridwidth = s'étend sur 1, 2, 3, ... cases en largeur
			//c.gridheight = 1;	// .gridheight = s'étend sur 1, 2, 3, ... cases en hauteur
			//c.ipadx = 0;	// .ipadx = marges internes en largeur
			//c.ipady = 0;	// .ipady = marges internes en hauteur
			//c.insets = new Insets(10,10,10,10);	// .insets... = défini des marges
			//c.fill = GridBagConstraints.NONE;	// .fill = rempli, soit Horizontalement, verticalement ou les deux
			//c.anchor = GridBagConstraints.CENTER;	// .anchor = accroche l'objet, en début, milieu ou fin de ligne
			
		masterP.add(menuItemP,BorderLayout.CENTER);
				d.gridx = 0;
				d.gridy = 0;
				d.weightx = 0.25;
				d.ipadx = 0;
				c.gridwidth = 1;
				d.fill = GridBagConstraints.NONE;
				d.anchor = GridBagConstraints.LINE_START;
				d.insets = new Insets(0,3,10,3);
			menuItemP.add(nomMenuItem,d);
				d.gridx = 1;
				d.gridy = 0;
				d.weightx = 1;
				d.ipadx = 0;
				d.gridwidth = 2;
				d.fill = GridBagConstraints.HORIZONTAL;
				d.anchor = GridBagConstraints.CENTER;
				d.insets = new Insets(0,3,10,3);
			menuItemP.add(MenuItemTF,d);
				d.gridx = 2;
				d.gridy = 1;
				d.weightx = 0.4;
				d.ipadx = 0;
				d.gridwidth = 1;
				d.fill = GridBagConstraints.NONE;
				d.anchor = GridBagConstraints.LINE_START;
				d.insets = new Insets(0,15,10,0);
			menuItemP.add(MenuItemBu,d);
				d.gridx = 0;
				d.gridy = 1;
				d.weightx = 0.25;
				d.ipadx = 0;
				d.gridwidth = 1;
				d.fill = GridBagConstraints.NONE;
				d.anchor = GridBagConstraints.LINE_START;
				d.insets = new Insets(0,3,10,3);
			menuItemP.add(choisis2,d);
				d.gridx = 1;
				d.gridy = 1;
				d.weightx = 1;
				d.ipadx = 0;
				d.gridwidth = 1;
				d.fill = GridBagConstraints.HORIZONTAL;
				d.anchor = GridBagConstraints.CENTER;
				d.insets = new Insets(0,3,10,3);
			menuItemP.add(MenuItemCB,d);
				d.gridx = 3;
				d.gridy = 1;
				d.weightx = 0.4;
				d.ipadx = 0;
				d.gridwidth = 1;
				d.fill = GridBagConstraints.NONE;
				d.anchor = GridBagConstraints.LINE_START;
				d.insets = new Insets(0,15,10,0);
			menuItemP.add(listener,d);
				d.gridx = 2;
				d.gridy = 1;
				d.weightx = 0.5;
				d.ipadx = 0;
				d.gridwidth = 1;
				d.fill = GridBagConstraints.NONE;
				d.anchor = GridBagConstraints.LINE_END;
				d.insets = new Insets(0,15,10,3);
			menuItemP.add(SeparatorBu,d);
			
		this.setBackground(fond);
		this.add(masterP, BorderLayout.CENTER);
	}
	
	public boolean isEmpty()
	{				// .trim()= retire les éspaces avant et apres le String
		if ((MenuItemTF.getText().trim().equals("") || MenuItemTF.getText().trim().equals("")))
		{
			JOptionPane.showMessageDialog(this," Le JTextField correspondant est vide,\n" +
											" veuillez indiquer un nom,\n Merci", 
											"Erreur - JTextField vide",JOptionPane.ERROR_MESSAGE);
			return true;
		}
			return false;
	}

	public void ajouterMenuItem()	//Ajoute le MenuItem
	{
		if (!isEmpty())
		{
			MenuItemCB.addItem(MenuItemTF.getText());
		}
	}

	public void ajouterSeparator()	//Ajoute un Separator
	{
		MenuItemCB.addItem("Separator");
	}
	
	public void focusGained(FocusEvent e)	//Comme pour le GeneralTabbed
	{
		((JTextComponent) e.getSource()).selectAll();
			Fenetre.frame.getRootPane().setDefaultButton(MenuItemBu);
	}

	public void focusLost(FocusEvent e) 
	{
		((JTextComponent) e.getSource()).setCaretPosition(0);
		Fenetre.frame.getRootPane().setDefaultButton(null);
		
	}

	public void actionPerformed(ActionEvent e) 
	{
		Object src = e.getSource();
		
		if (src==MenuItemBu)
		{
			ajouterMenuItem();
		}else
		{
			ajouterSeparator();
		}
	}
}
