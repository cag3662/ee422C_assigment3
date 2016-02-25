package assignement3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class A3Driver
	{
	
	  public ArrayList<Item> shoppingCart = new ArrayList<Item>(); 

	  public static void main(String[] args) 
	  {
		  if (args.length != 1) {
				System.err.println("Error: Incorrect number of command line arguments");
				System.exit(-1);
			}
			processLinesInFile(args[0]);
	  }
	  
	  
	  public static void processLinesInFile(String filename){
		  
		  A3Driver a3driver = new A3Driver();
	  
		  try {
				FileReader freader = new FileReader(filename);
				BufferedReader reader = new BufferedReader(freader);

				for (String s = reader.readLine(); s != null; s = reader.readLine()) {
					a3driver.driver(s);
				}
			} catch (FileNotFoundException e) {
				System.err.println("Error: File not found. Exiting...");
				e.printStackTrace();
				System.exit(-1);
			} catch (IOException e) {
				System.err.println("Error: IO exception. Exiting...");
				e.printStackTrace();
				System.exit(-1);
			}
	  }
	  
	  
	  /*
	   * Driver will parse inputString and do five operations
	   * 
	   */
	  public void driver(String inputString){
		  	
		  
		  	if (inputString.length() == 0){
		  		return;
		  	}
		  	
		  	
		  	String arry[] = inputString.split("\\s+");
		  	
		  	
		  	int operation_check = operation_det(arry[0]);
		  	
			
			/*
			 * Insert
			 */
			if(operation_check == 1){
				
				int category_check = category(arry[1]);
				
				/*
				 * grocery insert
				 */
				if(category_check == 1 && arry.length == 7){
					
					boolean initialchecking = initialerrorhandling(arry);
					
					
					
					if(initialchecking){
						boolean grocery_check = initial_gro_errorhandling(arry);
						
						if(grocery_check){
							String name = arry[2];
							float price = Float.parseFloat(arry[3]);
							int quantity = Integer.parseInt(arry[4]);
							float weight = Float.parseFloat(arry[5]);
							String perish = arry[6];
							Grocery grocery = new Grocery(name, price, quantity, weight, perish);
							shoppingCart.add(grocery);
						}
					}
					else{
							System.out.println("[" + inputString + "] is not valid input!!\n");
							System.out.println("***********************************************");
					}
					
				}
				/*
				 * electronic insert
				 */
				else if(category_check == 2 && arry.length == 8)
				{
					boolean initialchecking = initialerrorhandling(arry);
					
					if(initialchecking){
						boolean electronic_check = initial_elec_errorhandling(arry);
						
						if(electronic_check){
							String name = arry[2];
							float price = Float.parseFloat(arry[3]);
							int quantity = Integer.parseInt(arry[4]);
							float weight = Float.parseFloat(arry[5]);
							String fragile = arry[6];
							String state = arry[7];
							Electronics electronic = new Electronics(name, price, quantity, weight, fragile, state);
							shoppingCart.add(electronic);
						}
					}
					else{
						System.out.println("[" + inputString + "] is not valid input!!\n");
						System.out.println("***********************************************");
					}
				}
				/*
				 * Clothing insert
				 */
				else if(category_check == 3 && arry.length == 6)
				{
					boolean initialchecking = initialerrorhandling(arry);
					
					if(initialchecking){
					
							String name = arry[2];
							float price = Float.parseFloat(arry[3]);
							int quantity = Integer.parseInt(arry[4]);
							float weight = Float.parseFloat(arry[5]);
						
							Clothing cloth = new Clothing(name, price, quantity, weight);
							shoppingCart.add(cloth);
						}
					else{
						System.out.println("[" + inputString + "] is not valid input!!\n");
						System.out.println("***********************************************");
					}
				}
				else{
						System.out.println("[" + inputString + "] is not valid input!!\n");
						System.out.println("***********************************************");
				}
				
			}	//The end of insert algorithm
			/*
			 * 
			 * search
			 */
			else if(operation_check == 2){
				
				int total_number = 0;
				String product_name = arry[1];		//product name is stored in arry[1]
				
				
				for(int i=0; i<shoppingCart.size();i++){
					if(shoppingCart.get(i).getName().equals(product_name)){	
						total_number = total_number + 1;
					}
				}
				if(total_number > 0){
					System.out.println(product_name + " exists " + total_number + " times in shopping cart!!" );
				}
				else if(total_number == 0){
					System.out.println(product_name + " is not exist in the shopping cart!!");
				}
				
			}	//the end of search algorithm
			/*
			 * 
			 * delete
			 */
			else if(operation_check == 3){
				
				int total_number = 0;
				String product_name = arry[1];
				
				for(int i = 0; i<shoppingCart.size(); i++){
					if(shoppingCart.get(i).getName().equals(product_name)){
						shoppingCart.remove(i);
						total_number = total_number + 1;
					}
				}
				
				if(total_number > 0){
					System.out.println(total_number + " " + product_name + " item(s) is/are deleted from shopping cart");
				}
				else if(total_number == 0){
					System.out.println(product_name + " is not existed in the shopping cart!");
				}
				
			}	//the end of delete algorithm
			/*
			 * 
			 * update
			 */
			else if(operation_check == 4){
				
				String product_name = arry[1];
				
				if(arry[2].matches("\\d+") && arry.length == 3){
					
					int flag = 0;
					int quantity = Integer.parseInt(arry[2]);
					//String product_name = arry[1];
					
					for(int i=0; i<shoppingCart.size(); i++){
						if(shoppingCart.get(i).getName().equals(product_name)){
							shoppingCart.get(i).setQuantity(quantity);
							flag = 1;
							break;
						}
					}
					
					if(flag == 0){
						System.out.println(product_name + " is not existed in shopping cart!!");
					}
					else if(flag == 1){
						System.out.println("New quantity of " + product_name + " is now " + quantity);
					}
					
					
				}	
				
			}	//the end of update algorithm
			/*
			 * 
			 * print
			 */
			else if(operation_check == 5){
				
				float total_price = 0;
				
				Collections.sort(shoppingCart, Item.ItemNameComparator);
				
				for(int i=0; i<shoppingCart.size(); i++){
					shoppingCart.get(i).printItemAttributes();
					System.out.println();
					total_price = total_price + shoppingCart.get(i).calculatePrice();
					System.out.println("\tTotal Charge for individual item: $" + shoppingCart.get(i).calculatePrice());
					System.out.println();
				}
				
				System.out.println();
				System.out.println("Total price: $" + total_price);
				System.out.println("***********************************************");
				
				
			}	//the end of print algorithm
			else if(operation_check == -1){
				System.out.println("[" + inputString + "] is not valid input!!\n");
				System.out.println("***********************************************");
			}
			
			
			
			// General code example for how to iterate an array list. You will have to modify this heavily, to suit your needs.
/*			Iterator<Item> i = shoppingCart.iterator();
			while (i.hasNext()) 
			{
				Item temp = i.next();
				temp.calculatePrice(); 
				temp.printItemAttributes();
				
			}
*/
		  
	  }
	  
	 //public void insert
	  
	  
/*
 * Insert = 1, search = 2, delete = 3, update = 4, print = 5, invalid operation = -1
 */
	 public int operation_det(String operation){
		 int flag = -1;
		 
		 if(operation.equals("insert")){
			 flag = 1;
		 }
		 else if(operation.equals("search")){
			 flag = 2;
		 }
		 else if(operation.equals("delete")){
			 flag = 3;
		 }
		 else if(operation.equals("update")){
			 flag = 4;
		 }
		 else if(operation.equals("print")){
			 flag = 5;
		 }
		 
		 
		 return flag;
	 }
		  
	 public int category(String category){
		 int flag = -1;
		 if(category.equals("groceries")){
			 flag = 1;
		 }
		 else if(category.equals("electronics")){
			 flag = 2;
		 }
		 else if(category.equals("clothing")){
			 flag = 3;
		 }
		 
		 return flag;
	 }
	 
	 public boolean initialerrorhandling(String[] arry){
		 boolean flag = false;
		 boolean flag1 = false;
		 boolean flag2 = false;
		 boolean flag3 = false;
		 
		 if(arry[3].matches("\\d+.\\d\\d") || arry[3].matches("\\d+")){
			 flag1 = true;
		 }
		 
		 if(arry[4].matches("\\d+")){
			 flag2 = true;
		 }
		 
		 if(arry[5].matches("\\d+.\\d") || arry[5].matches("\\d+")){
			 flag3 = true;
		 }
		 
		 if(flag1 && flag2 && flag3){
			 flag = true;
		 }
		 
		 return flag;
	 }
	 
	 public boolean initial_gro_errorhandling(String[] arry){
		 boolean flag = false;
		 
		 if(arry[6].equals("NP") || arry[6].equals("P"))
		 {
			 flag = true;
		 }
		 
		 return flag;
	 }
	 
/*
 * 
 * 
 */
	 public boolean initial_elec_errorhandling(String[] arry){
		 boolean flag = false;
		 boolean flag1 = false;
		 boolean flag2 = false;
		 
		 ArrayList<String> states = new ArrayList<String>();
		 states.add("AL");
		 states.add("AK");
		 states.add("AZ");
		 states.add("AR");
		 states.add("CA");
		 states.add("CO");
		 states.add("CT");
		 states.add("DE");
		 states.add("FL");
		 states.add("GA");
		 states.add("HI");
		 states.add("ID");
		 states.add("IL");
		 states.add("IN");
		 states.add("IA");
		 states.add("KS");
		 states.add("KY");
		 states.add("LA");
		 states.add("ME");
		 states.add("MD");
		 states.add("MA");
		 states.add("MI");
		 states.add("MN");
		 states.add("MS");
		 states.add("MO");
		 states.add("MT");
		 states.add("NE");
		 states.add("NV");
		 states.add("NH");
		 states.add("NJ");
		 states.add("NM");
		 states.add("NY");
		 states.add("NC");
		 states.add("ND");
		 states.add("OH");
		 states.add("OK");
		 states.add("OR");
		 states.add("PA");
		 states.add("RI");
		 states.add("SC");
		 states.add("SD");
		 states.add("TN");
		 states.add("TX");
		 states.add("UT");
		 states.add("VT");
		 states.add("VA");
		 states.add("WA");
		 states.add("WV");
		 states.add("WI");
		 states.add("WY");
		 
		 if(arry[6].equals("F") || arry[6].equals("NF"))
		 {
			 flag1 = true;
		 }
		 
		 if(states.contains(arry[7])){
			 flag2 = true;
		 }
		 
		 if(flag1 && flag2){
			 flag = true;
		 }
		 return flag;
	 }

}

