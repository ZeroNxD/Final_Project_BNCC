import java.sql.*;

public class MenuDatabase {
	
	Connection con;
	Statement State;
	PreparedStatement Prep;
	ResultSet Result;

	public MenuDatabase() {
		Connect();
	}
	
	public void Connect() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String url = "jdbc:mysql://localhost:3306/final_project_bncc"; //link ke database
		String username = "root";
		String password = "";
		
		try {
			con = DriverManager.getConnection(url, username, password);
			State = con.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	public void Insert(Menu MenuBaru) {
		try {
			Prep = con.prepareStatement("INSERT INTO data_ptpudding (ID_Menu, Nama_Menu, Harga_Menu, Stok_Menu) VALUES (?,?,?,?)");
			Prep.setString(1, MenuBaru.getID());
			Prep.setString(2, MenuBaru.getName());
			Prep.setString(3, MenuBaru.getHarga());
			Prep.setString(4, MenuBaru.getStock());
			Prep.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Failed To Insert");
		}
	}
	
	public void Update(String Name, String Harga, String Stock) {
		try {
			Prep = con.prepareStatement("update data_ptpudding set Harga_Menu = ?, Stok_Menu = ? where Nama_Menu = ?");
			Prep.setString(1, Harga);
			Prep.setString(2, Stock);
			Prep.setString(3, Name);
			Prep.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void Delete(String Name) {
		try {
			Prep = con.prepareStatement("delete from data_ptpudding where Nama_Menu = ?");
			Prep.setString(1, Name);
			Prep.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public ResultSet View() {
		try {
			Result = State.executeQuery("select * from data_ptpudding");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Result;
	}
	
}
