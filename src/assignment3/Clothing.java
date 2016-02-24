package assignement3;


public class Clothing extends Item 
{

	public Clothing(String name, float price, int quantity, float weight){
		super(name, price, quantity, weight);
	}	
	// variables, constructors as necessary
	
	float calculatePrice () 
	{
		float final_price = 0;
		float shipping_price;
		float sales_tax;
		
		sales_tax = this.getPrice()*(float)0.1;
		
		shipping_price = (20*this.getWeight())*this.getQuantity();
		final_price = this.getPrice() + shipping_price + sales_tax;
	
		final_price = Math.round(final_price*100)/100.f; //round up to two decimal point
		return final_price;
	}
	
	void printItemAttributes () 
	{
		System.out.println("Name:" + this.getName());
		System.out.println("\nQuantity: " + this.getQuantity());
		System.out.println("\nWeight: " + this.getWeight());
		System.out.println("\nPrice: " + this.getPrice());	//Print all applicable attributes of this sub-class
	}
	

}
