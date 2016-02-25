package assignement3;


public class Grocery extends Item
{
//variables, constructor here
	public String perish;
	
	
	public Grocery(String name, float price, int quantity, float weight, String perish){
		super(name, price, quantity, weight);
		this.perish = perish;
		
	}
	
// Getters and setters
	public String getPerish() {
		return perish;
	}

	public void setPerish(String perish) {
		this.perish = perish;
	}

// Methods
	
/**
 * Determine price of grocery item
 * @return price as a floating point number
 */
	float calculatePrice () 
	{
		float final_price = 0;
		float shipping_price = 0;
		
		if(this.getPerish().equals("P")){
			shipping_price = (20*this.getWeight())*this.getQuantity() * (float)1.2;
		}
		else if(this.getPerish().equals("NP")){
			shipping_price = (20*this.getWeight())*this.getQuantity();
		}
		
		final_price = this.getPrice() + shipping_price;
	
		final_price = Math.round(final_price*100)/100.f; //round up to two decimal point
		return final_price;
	}
	
/**
 * print grocery item's attributes
 */
	void printItemAttributes () 
	{
		System.out.println("Name:" + this.getName());
		System.out.println("\nQuantity: " + this.getQuantity());
		System.out.println("\nWeight: " + this.getWeight());
		System.out.println("\nPrice: " + this.getPrice());	//Print all applicable attributes of this sub-class
		System.out.println("\nPerishable: " + this.getPerish());
	}
	
}
