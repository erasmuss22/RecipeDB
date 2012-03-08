///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Title:            Recipe Database
// Files:            RecipeDBApp.java, RecipeDatabase.java, Recipe.java,
//					 Calculations.java
// Semester:         Spring 2011
//
// Author:           Erin Rasmussen 	ejrasmussen2@wisc.edu
// CS Login:         rasmusse
// Lecturer's Name:  Beck Hasti
// Lab Section:      Lecture 2
//
//                  
//                   STUDENTS WHO GET HELP FROM ANYONE OTHER THAN THEIR PARTNER
// Credits:          Xiaoming Shi for iterator method in RecipeDatabase.java
//////////////////////////// 80 columns wide //////////////////////////////////

import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

/**
 * This class controls user input for navigating the recipe database and 
 * accepts .txt files as args.
 *
 * <p>Bugs: none known
 *
 * @author Erin Rasmussen
 */
public class RecipeDBApp {
	public static void main(String[] args) throws FileNotFoundException {
		RecipeDatabase db = new RecipeDatabase();
		Calculations cal = new Calculations(db);
		//the index of the string position
		int position = 0;
		if (args.length != 1){
			System.out.println("Usage: java RecipeDBApp FileName");
			System.exit(0);
		}

		if (!(new File(args[0]).exists())){
			System.out.println("Error: Cannot access input file");
			System.exit(0);
		}

		try {
			File infile = new File(args[0]);
			Scanner in = new Scanner(infile);
			while (in.hasNextLine()){
				position = 0;
				String a = in.nextLine();
				while(a.charAt(position) != ':'){
					position++;
				}
				db.addRecipe(a.substring(0,position).toLowerCase());
				int j = position + 1;
				for (int i = position + 1; i < a.length(); i++){
					if (a.charAt(i) == ':'){
						db.addIngredient(a.substring(j, i).toLowerCase(),
								a.substring(0,position).toLowerCase());
						j = i + 1;
					}
					if (i == a.length()-1){
						db.addIngredient(a.substring(j).toLowerCase(),
								a.substring(0,position).toLowerCase());
					}
				}

			}
		} catch(FileNotFoundException e){
			System.out.println("Error: Cannot access input file");
			System.exit(0);
		} 


		Scanner stdin = new Scanner(System.in);  // for reading console input
		printOptions();
		boolean done = false;
		while (!done) {
			System.out.print("Enter option ( fhiqrs ): ");
			String input = stdin.nextLine();
			input = input.toLowerCase();  // convert input to lower case

			// only do something if the user enters at least one character
			if (input.length() > 0) {
				char choice = input.charAt(0);  // strip off option character
				String remainder = "";  // used to hold the remainder of input
				if (input.length() > 1) {
					// trim off any leading or trailing spaces
					remainder = input.substring(1).trim(); 
				}

				switch (choice) {

				case 'f':
					if (db.containsRecipe(remainder)){
						System.out.print(remainder + ": ");
						List<String> ingredients = db.getIngredients(remainder);
						for (int i = 0; i < ingredients.size() - 1; i++){
							System.out.print(ingredients.get(i) + ", ");
						}
						System.out.print(ingredients.get(ingredients.size() - 1)
								+ " ");
						System.out.println();
					}
					else {
						System.out.println("Recipe not found");
					}
					break;

				case 'h': 
					printOptions();
					break;

				case 'i':
					System.out.println("Recipes: " + db.size() + " Ingredients"
							+ ": " + cal.uniqueIngredients().size());
					System.out.println("# ingredients/recipe: most " + 
							cal.mostIngredients() + ", least " + 
							cal.leastIngredients() + ", average " + cal.mean());
					System.out.println("# recipes/ingredient: most " + 
							cal.mostRecipes() + ", least " + cal.leastRecipes()
							+ ", average " + cal.meanRecipes());
					if (cal.mostCommon().size() == 1){
						System.out.print("Most Common: " + cal.mostCommon().get
								(0) + " ");
					}
					else {
						for (int i = 0; i < cal.mostCommon().size() - 1; i++){
							System.out.print(cal.mostCommon().get(i) + ", ");
						}
						System.out.print(cal.mostCommon().get(cal.mostCommon().
								size() - 1));
					}
					System.out.print("[" + cal.mostRecipes() + "]");
					System.out.println();

					break;

				case 'q':
					done = true;
					System.out.println("quit");
					break;

				case 'r':
					if (db.containsRecipe(remainder)){
						db.removeRecipe(remainder);
						System.out.println("recipe removed");
					}
					else {
						System.out.println("recipe not found");
					}
					break;

				case 's':
					// The following code reads in a comma-separated sequence 
					// of strings and puts them into a List.
					List<String> list = new ArrayList<String>();
					String[] tokens = remainder.split("[,]+");
					for (int i = 0; i < tokens.length; i++) {
						list.add(tokens[i].trim());
					}
					List<String> recipes = cal.search(list);
					if (recipes.size() > 0){
						for (int i = 0; i < recipes.size() - 1; i++){
							System.out.print(recipes.get(i) + ", ");
						}
						System.out.print(recipes.get(recipes.size() - 1));
						System.out.println();
					}
					else {
						System.out.println("none");
					}
					break;

				default:  // ignore any unknown commands
					break;
				}
			}
		}

	}

	/**
	 * Prints the list of command options along with a short description of
	 * one.  This method should not be modified.
	 */
	private static void printOptions() {
		System.out.println("f <name> - find the recipe with the given <name>");
		System.out.println("h - display this help menu");
		System.out.print("i - display information about this recipe database");
		System.out.println();
		System.out.println("q - quit");
		System.out.print("r <name> - remove the recipe with the given <name>");
		System.out.println();
		System.out.print("s <list> - search for recipes containing all the ");
		System.out.println("ingredients in <list>");
		System.out.print("           <list> should be a list of ingredients ");
		System.out.println("separated by commas");
	}
}
