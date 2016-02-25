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
	  
	/******************************************************************************
	* Method Name: driver														  *
	* Purpose: Driver will parse inputString and do one of five operations:  	  *
	* 				insert, update, search, delete, or print					  *
	* Returns: None                                                               *
	******************************************************************************/
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
						else{
							System.out.println("[" + inputString + "] is not valid input!!\n");
							System.out.println("***********************************************");
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
						else{
							System.out.println("[" + inputString + "] is not valid input!!\n");
							System.out.println("***********************************************");
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
					System.out.println(product_name + " was found " + total_number + " time(s) in the shopping cart." );
					System.out.println();
				}
				else if(total_number == 0){
					System.out.println("The item \"" + product_name + "\" does not exist in the shopping cart.");
					System.out.println();
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
					System.out.println(total_number + " " + product_name + " item(s) was/were deleted from the shopping cart.");
					System.out.println();
				}
				else if(total_number == 0){
					System.out.println("The item \"" + product_name + "\" does not exist in the shopping cart.");
					System.out.println();
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
						System.out.println("The item \"" + product_name + "\" does not exist in the shopping cart.");
						System.out.println();
					}
					else if(flag == 1){
						System.out.println("New quantity of " + product_name + " is now " + quantity);
						System.out.println();
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
					total_price = Math.round(total_price*100)/100.f; 			//round up to two decimal point
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
	  }
	  	  
	/******************************************************************************
	* Method Name: operation_det                                             	  *
	* Purpose: Determines what operation the user has requested. The operation	  *
	* 		   code is specified in the input argument. It's converted from a	  *
	* 		   a String object to an integer.									  *
	* Operation Codes:															  *
	* 			Insert = 1														  *
	* 			search = 2														  *
	* 			delete = 3														  *
	* 			update = 4														  *
	* 			print = 5														  *
	* 			invalid operation = -1											  *
	* Returns: The requested operation code                                  	  *                             *
	******************************************************************************/
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

	/******************************************************************************
	* Method Name: category                                             		  *
	* Purpose: Determines the current item category, which can be "groceries",	  *
	* 		   "electronics", "clothing", or invalid.                                      *
	* Returns: Integer code representing the item category.						  *
	* 			groceries = 1													  *
	* 			electronics = 2													  *
	* 			clothing = 3													  *
	* 			invalid = -1													  *                                                               *
	******************************************************************************/
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
	 
	/******************************************************************************
	* Method Name: initialerrorhandling                                           *
	* Purpose: Determines if the 'name', 'price', 'quantity', and 'weight'		  *
	* 		   input arguments are valid or invalid.  An input array holding	  *
	* 		   these arguments is the input to this method.                       *                *
	* Returns: A boolean flag indicating valid or invalid arguments.			  *
	* 		   true = all arguments are valid									  *
	* 		   false = at least one argument is invalid							  *                                                               *
	******************************************************************************/
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
	 
	/******************************************************************************
	* Method Name: initial_gro_errorhandling                                      *
	* Purpose: Checks whether or not perishable argument passed by the user		  *
	* 		   is valid.                                     					  *
	* Returns: Boolean flag indicating a valid or nonvalid argument               *
	******************************************************************************/
	 public boolean initial_gro_errorhandling(String[] arry){
		 boolean flag = false;
		 
		 if(arry[6].equals("NP") || arry[6].equals("P"))
		 {
			 flag = true;
		 }
		 
		 return flag;
	 }
	 
	/******************************************************************************
	* Method Name: initial_elec_errorhandling                                     *
	* Purpose: Checks whether or not state and fragile argument passed by 		  *
	* 		   the user are valid.                                      		  *
	* Returns: Boolean flag indicating valid or invalid arguments.				  *
	* 			true = both arguments are valid									  *
	* 			false = at least one argument is invalid						  *
	******************************************************************************/
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

