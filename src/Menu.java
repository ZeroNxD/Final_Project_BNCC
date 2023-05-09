
public class Menu {
	
	private String ID, Name;
	private String Harga, Stock;

	public Menu(String ID, String Nama, String Harga, String Stock) {
		this.ID = ID;
		this.Name = Nama;
		this.Harga = Harga;
		this.Stock = Stock;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getHarga() {
		return Harga;
	}

	public void setHarga(String harga) {
		Harga = harga;
	}

	public String getStock() {
		return Stock;
	}

	public void setStock(String stock) {
		Stock = stock;
	}

	
}
