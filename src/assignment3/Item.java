package assignement3;
import java.util.Comparator;


public class Item
{
// Variables for this class
	public String name;
	public float price;
	public int quantity;
	public float weight;
	

	
// Constructor

	public Item(){
		name = "";
		price = 0;
		quantity = 0;
		weight = 0;
	}
	
	public Item(String name, float price, int quantity, float weight){
		this.name = name;
		this.price = price;
		this.quantity = quantity;
		this.weight = weight;
	}
	
// Getters
	String getName(){
		return name;
	}
	float getPrice(){
		return price;
	}
	int getQuantity(){
		return quantity;
	}
	float getWeight(){
		return weight;
	}
	
// Setters
	void setName(String name){
		this.name = name;
	}
	void setPrice(float price){
		this.price = price;
	}
	void setQuantity(int quantity){
		this.quantity = quantity;
	}
	void setWeight(float weight){
		this.weight = weight;
	}

// Methods
	
/**
 * Determine price of electronic item
 * @return price as a floating point number
 */
	float calculatePrice () 
	{
		float final_price = 0;
		float shipping_price;
		
		shipping_price = (20*this.getWeight())*this.getQuantity();
		final_price = this.getPrice() + shipping_price;
	
		return final_price;
	}
	
/**
 * print item's attributes
 */
	void printItemAttributes () 
	{
		System.out.println("Name:" + this.getName());
		System.out.println("\nQuantity: " + this.getQuantity());
		System.out.println("\nWeight: " + this.getWeight());
		System.out.println("\nPrice: " + this.getPrice());

	}
	 
 /*Comparator for sorting a shopping list by item Name*/
    public static Comparator<Item> ItemNameComparator = new Comparator<Item>() {

	public int compare(Item s1, Item s2) {
	   String ItemName1 = s1.getName().toUpperCase();
	   String ItemName2 = s2.getName().toUpperCase();

	   //ascending order
	   return ItemName1.compareTo(ItemName2);
    }};

}
