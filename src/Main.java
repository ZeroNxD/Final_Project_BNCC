import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;

import javax.swing.*;

public class Main extends JFrame implements ActionListener{
	
	Connection con;
	Statement State;
	JButton InsertButton, ViewButton, UpdateButton, DeleteButton, ExitButton;
	JTextField namaField, HargaField, StockField;
	Random rnd = new Random();
	MenuDatabase Database = new MenuDatabase();

	public Main() {
		GUI();
	}

	public static void main(String[] args) {
		new Main();

	}
	
	public void GUI() {
		IsiGUI();
		this.setTitle("PT Pudding New Application");
		this.setLayout(new GridLayout(1,2));
		this.setSize(600,400);
		this.setLocation(500,200);
		this.setVisible(true);
	}
	
	public void IsiGUI() {
		JPanel MainPanel = new JPanel(); //Panel Untuk memilih menu
		MainPanel.setBackground(Color.red);
		JPanel FormPanel = new JPanel(); //Panel Untuk Form
		
		JPanel AddPanel = new JPanel();
		JLabel AddTitle = new JLabel("Silahkan Berikan Inputan Anda");
		AddTitle.setPreferredSize(new Dimension(270,20));
		AddPanel.add(AddTitle);
		FormPanel.add(AddPanel);
		
		JPanel NamaPanel = new JPanel();
		JLabel namaMenu = new JLabel("Nama Menu: "); 
		namaField = new JTextField(); 
		namaField.setPreferredSize(new Dimension(200, 25)); 
		NamaPanel.add(namaMenu);
		NamaPanel.add(namaField);
		FormPanel.add(NamaPanel);
		
		JPanel InfoPanel = new JPanel();
		JLabel Informasi = new JLabel("Mohon untuk menginput Angka!!");
		InfoPanel.add(Informasi);
		FormPanel.add(InfoPanel);
		
		JPanel HargaPanel = new JPanel();
		JLabel HargaMenu = new JLabel("Harga Menu: "); 
		HargaField = new JTextField(); 
		HargaField.setPreferredSize(new Dimension(200, 25)); 
		HargaPanel.add(HargaMenu);
		HargaPanel.add(HargaField);
		FormPanel.add(HargaPanel);
		
		JPanel StockPanel = new JPanel();
		JLabel StockMenu = new JLabel("Stock Menu: ");
		StockField = new JTextField();
		StockField.setPreferredSize(new Dimension(200,25));
		StockPanel.add(StockMenu);
		StockPanel.add(StockField);
		FormPanel.add(StockPanel);
		
		JPanel TitlePanel = new JPanel();
		TitlePanel.setBackground(Color.red);
		JLabel Title = new JLabel("~Silahkan memilih diantara menu-menu berikut~");
		Title.setForeground(Color.white);
		Title.setPreferredSize(new Dimension(270,20));
		TitlePanel.add(Title);
		MainPanel.add(TitlePanel);
		
		JPanel InsertPanel = new JPanel();
		InsertPanel.setBackground(Color.red);
		InsertButton = new JButton("Insert the Menu");
		InsertButton.setPreferredSize(new Dimension(200,30));
		InsertButton.addActionListener(this);
		InsertPanel.add(InsertButton);
		MainPanel.add(InsertPanel);
		
		JPanel ViewPanel = new JPanel();
		ViewPanel.setBackground(Color.red);
		ViewButton = new JButton("View the Menu");
		ViewButton.setPreferredSize(new Dimension(200,30));
		ViewButton.addActionListener(this);
		ViewPanel.add(ViewButton);
		MainPanel.add(ViewPanel);
		
		JPanel UpdatePanel = new JPanel();
		UpdatePanel.setBackground(Color.red);
		UpdateButton = new JButton("Update the Menu");
		UpdateButton.setPreferredSize(new Dimension(200,30));
		UpdateButton.addActionListener(this);
		UpdatePanel.add(UpdateButton);
		MainPanel.add(UpdatePanel);
		
		JPanel DeletePanel = new JPanel();
		DeletePanel.setBackground(Color.red);
		DeleteButton = new JButton("Delete the Menu (Insert Name Only)");
		DeleteButton.setPreferredSize(new Dimension(270,30));
		DeleteButton.addActionListener(this);
		DeletePanel.add(DeleteButton);
		MainPanel.add(DeletePanel);
		
		JPanel ExitPanel = new JPanel();
		ExitPanel.setBackground(Color.red);
		ExitButton = new JButton("Exit Menu");
		ExitButton.setPreferredSize(new Dimension(200,30));
		ExitButton.addActionListener(this);
		ExitPanel.add(ExitButton);
		MainPanel.add(ExitPanel);
		
		add(FormPanel);
		add(MainPanel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == ExitButton) {
			this.dispose();
		} else if(e.getSource() == InsertButton) {
			Insert();
			JOptionPane.showMessageDialog(null, "Data telah ditambahkan");
		} else if(e.getSource()== UpdateButton) {
			Update();
			JOptionPane.showMessageDialog(null, "Data telah diupdate");
		} else if(e.getSource() == DeleteButton) {
			Delete();
			JOptionPane.showMessageDialog(null, "Data telah dihapus");
		} else if(e.getSource() == ViewButton) {
			View();
		}
	}
	
	public void Insert() {
		String ID = "PD-";
		int Size = ID.length();
		while(Size != 6) {
			int number = rnd.nextInt(10);
			ID = ID + number;
			Size = ID.length();
		}
		
		String Name = namaField.getText();
		String Harga = HargaField.getText(); 
		String Stock = StockField.getText();
		
		Menu newMenu = new Menu(ID, Name, Harga, Stock);
		Database.Insert(newMenu);
	}
	
	public void Update() {
		String name = namaField.getText();
		String Harga = HargaField.getText();
		String Stock = StockField.getText();
		
		Database.Update(name, Harga, Stock);
	}
	
	public void Delete() {
		String name = namaField.getText();
		
		Database.Delete(name);
	}
	
	public void View() {
		ResultSet ListMenu = Database.View();
		System.out.format("|%4s|%15s|%15s|%15s|%15s|\n", "----", "---------------", "---------------", "---------------", "---------------");
		System.out.format("|%-4s|%-15s|%-15s|%-15s|%-15s|\n", "No", "ID Menu", "Nama Menu", "Harga Menu", "Stock Menu");
		System.out.format("|%4s|%15s|%15s|%15s|%15s|\n", "----", "---------------", "---------------", "---------------", "---------------");
		try {
			while(ListMenu.next()) {
				System.out.format("|%4d|%15s|%15s|%15s|%15s|\n", ListMenu.getInt("Nomor_Menu"), ListMenu.getString("ID_Menu"), ListMenu.getString("Nama_Menu")
						, ListMenu.getString("Harga_Menu"), ListMenu.getString("Stok_Menu"));
			}
			System.out.format("|%4s|%15s|%15s|%15s|%15s|\n", "----", "---------------", "---------------", "---------------", "---------------");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
