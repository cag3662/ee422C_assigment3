package assignement3;


public class Item 
{
//Declare variables for this class. Think about its type: public, protected or private?
	public String name;
	public float price;
	public int quantity;
	public float weight;
	

	
// You will need a constructor (Why?). Create it here.

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
	
	float calculatePrice () 
	{
		float final_price = 0;
		float shipping_price;
		
		shipping_price = (20*this.getWeight())*this.getQuantity();
		final_price = this.getPrice() + shipping_price;
	
		return final_price;
	}
	

	void printItemAttributes () 
	{
		System.out.println("Name:" + this.getName());
		System.out.println("\nQuantity: " + this.getQuantity());
		System.out.println("\nWeight: " + this.getWeight());
		System.out.println("\nPrice: " + this.getPrice());
		
		
		
		//Testing git
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		
	}

}
