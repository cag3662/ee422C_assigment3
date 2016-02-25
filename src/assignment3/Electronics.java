package assignement3;


public class Electronics extends Item 
{

// Variables, constructors etc. here.
	private String fragile;
	private String state;

	public Electronics(String name, float price, int quantity, float weight, String frag, String st){
		super(name, price, quantity, weight);
		this.fragile = frag;
		this.state = st;
		
	}
	
// Getters and setters
	/**
	 * @return the fragile
	 */
	public String getFragile() {
		return fragile;
	}
	/**
	 * @param fragile the fragile to set
	 */
	public void setFragile(String fragile) {
		this.fragile = fragile;
	}
	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}
	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}
	
// Class Methods	
	
/**
 * Determine price of electronic item
 * @return price as a floating point number
 */
	float calculatePrice () 
	{
		float final_price = 0;
		float shipping_price = 0;
		float sales_tax;
		boolean check_salestax = check_state(this.getState()); 
		if(check_salestax){
			sales_tax = this.getPrice()*(float)0.1;
		}
		else{
			sales_tax = 0;
		}
		
		if(this.getFragile().equals("F")){
			shipping_price = (20*this.getWeight())*this.getQuantity() * (float)1.2;
		}
		else if(this.getFragile().equals("NF")){
			shipping_price = (20*this.getWeight())*this.getQuantity();
		}
		
		final_price = this.getPrice() + shipping_price + sales_tax;
	
		final_price = Math.round(final_price*100)/100.f; //round up to two decimal point
		return final_price;
	}
	
/**
 * Check if state requires sales tax or not
 * @param the state to check
 * @return a flag indicating sales tax or no sales tax
 * No sales tax = false, Sales tax = true
 */
	public boolean check_state(String state){
		boolean check = true;
		
		if(state.equals("TX")){
			check = false;
		}
		else if(state.equals("NM")){
			check = false;
		}
		else if(state.equals("VA")){
			check = false;
		}
		else if(state.equals("AZ")){
			check = false;
		}
		else if(state.equals("AK")){
			check = false;
		}
		else{
			//invalid input
		}
		
		return check;
	}
	
/**
 * print electronic item's attributes
 */
	void printItemAttributes () 
	{
		System.out.println("Name:" + this.getName());
		System.out.println("\nQuantity: " + this.getQuantity());
		System.out.println("\nWeight: " + this.getWeight());
		System.out.println("\nPrice: " + this.getPrice());	//Print all applicable attributes of this sub-class
		System.out.println("\nFragile: " + this.getFragile());
		System.out.println("\nDelivery location: " + this.getState());
	}

}
