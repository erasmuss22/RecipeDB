///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  RecipeDBApp.java
// File:             Calculations.java
// Semester:         Spring 2011
//
// Author:           Erin Rasmussen		ejrasmussen2@wisc.edu
// CS Login:         rasmusse
// Lecturer's Name:  Beck Hasti
// Lab Section:      Lecture 2
//
//////////////////////////// 80 columns wide //////////////////////////////////
import java.util.*;

/**
 * This class performs all calculations and list creations that are printed
 * when the command 'i' is called from the RecipeDBApp.
 *
 * <p>Bugs: none known
 *
 * @author Erin Rasmussen
 */
public class Calculations {
	
	//Creates a RecipeDatabase to be used for the constructor
	private RecipeDatabase db;
	//Creates an iterator for moving through lists
	private Iterator<Recipe> iter;
	
	/**
	 * Creates a new class of Calculations with the given RecipeDatabase
	 * 
	 * @param db the RecipeDatabase to do calculations on
	 */
	public Calculations (RecipeDatabase db){
		this.db = db;
	}

	/**
	 * Creates a List<String> of each unique ingredient in the entire database.
	 *
	 * @return a List<String> of unique ingredients
	 */
	public List<String> uniqueIngredients(){
		List<String> ingredients = new ArrayList<String>();
		iter = db.iterator();
		Recipe r;
		while (iter.hasNext()){
			r = iter.next();
			for (int i = 0; i < r.getIngredients().size(); i ++){
				ingredients.add(r.getIngredients().get(i));
			}
		}
		for (int i = 0; i < ingredients.size(); i++){
			for (int j = 0; j < ingredients.size(); j++){
				if (i == j){

				}
				else if (ingredients.get(i).equals(ingredients.get(j))){
					ingredients.remove(j);
				}
			}
		} 
		return ingredients;
	}

	/**
	 * Returns the number of ingredients in the recipe with the most
	 * ingredients.
	 *
	 * @return integer of most ingredients in a recipe
	 */
	public int mostIngredients(){
		int most = 0;
		int intermediate = 0;
		iter = db.iterator();
		Recipe r;
		while (iter.hasNext()){
			r = iter.next();
			intermediate = r.getIngredients().size();
			if (intermediate > most){
				most = intermediate;
			}
		}
		return most;
	}

	/**
	 * Returns an integer of the amount of ingredients in the recipe with
	 * the fewest amount of ingredients.
	 *
	 * @return integer of the fewest ingredients in a recipe
	 */
	public int leastIngredients(){
		int least = mostIngredients();
		int intermediate;
		iter = db.iterator();
		Recipe r;
		while (iter.hasNext()){
			r = iter.next();
			intermediate = r.getIngredients().size();
			if (intermediate < least){
				least = intermediate;
			}

		}
		return least;
	}

	/**
	 * Returns the integer mean amount of ingredients per recipe.
	 *
	 * @return mean of ingredients per recipe
	 */
	public int mean(){
		double ingredients = 0;
		int mean = 0;
		iter = db.iterator();
		Recipe r;
		while (iter.hasNext()){
			r = iter.next();
			ingredients = ingredients + db.getIngredients(r.getName()).size();
		}
		mean = (int) Math.round(ingredients / (double) db.size());
		return mean;
	}

	/**
	 * Returns an integer of the amount of recipes the ingredients with the 
	 * most recipes appearances has.
	 *
	 * @return integer of the most recipes of the most frequent ingredient
	 */
	public int mostRecipes(){
		int most = 0;
		int intermediate = 0;
		int counter = 0;
		List<String> ingredients = uniqueIngredients();
		for (int i = 0; i < ingredients.size(); i++){
			iter = db.iterator();
			Recipe r;
			while (iter.hasNext()){
				r = iter.next();
				if (db.hasIngredient(ingredients.get(i), r.getName())){
					counter++;
				}
			}
			intermediate = counter;
			counter = 0;
			if (intermediate > most){
				most = intermediate;
			}
		}
		return most;
	}

	/**
	 * Returns an integer of the fewest recipes the ingredient with the
	 * fewest appearances.
	 *
	 * @return integer of fewest recipe appearances
	 */
	public int leastRecipes() {
		int intermediate = 0;
		int counter = 0;
		List<String> ingredients = uniqueIngredients();
		int least = ingredients.size();
		for (int i = 0; i < ingredients.size(); i++){
			iter = db.iterator();
			Recipe r;
			while (iter.hasNext()){
				r = iter.next();
				if (db.hasIngredient(ingredients.get(i), r.getName())){
					counter++;
				}
			}
			intermediate = counter;
			counter = 0;
			if (intermediate < least){
				least = intermediate;
			}
		}
		return least;
	}

	/**
	 * Returns an integer amount of the mean recipes per unique ingredient.
	 *
	 * @return mean of recieps per unique ingredient
	 */
	public int meanRecipes(){
		List<String> ingredients = uniqueIngredients();
		double recipes = 0;
		int mean = 0;
		Recipe r;
		for (int i = 0; i < ingredients.size(); i++){
			iter = db.iterator();
			while (iter.hasNext()){
				r = iter.next();
				if (db.hasIngredient(ingredients.get(i), r.getName())){
					recipes++;
				}
			}
		}
		mean = (int) Math.round(recipes / (double) ingredients.size());
		return mean;
	}

	/**
	 * Returns a list of the most common ingredient(s) based on how many times
	 * they appear in recipes.
	 *
	 * @return a list of the most common ingredient(s)
	 */
	public List<String> mostCommon(){
		List<String> ingredients = uniqueIngredients();
		List<String> mostCommon = new ArrayList<String>();
		int most = mostRecipes();
		int counter = 0;
		for (int i = 0; i < ingredients.size(); i++){
			iter = db.iterator();
			Recipe r;
			while (iter.hasNext()){
				r = iter.next();
				if (db.hasIngredient(ingredients.get(i), r.getName())){
					counter++;
				}
			}
			if (counter == most) {
				mostCommon.add(ingredients.get(i));
			}
			counter = 0;
		}
		return mostCommon;
	}

	/**
	 * Takes a list of ingredient(s), searches all recipes, and returns a list
	 * of all recipes that contain ALL ingredients in the given list.
	 *
	 * @param ingredients The ingredients to search for
	 * @return a List<String> of recipes containing the given ingredients
	 */
	public List<String> search(List<String> ingredients) {
		List<String> recipes = new ArrayList<String>();
		int counter = 0;
		Recipe r;
		iter = db.iterator();
		while (iter.hasNext()){
			r = iter.next();
			for (int i = 0; i < ingredients.size(); i++){
				if (db.hasIngredient(ingredients.get(i), r.getName())){
					counter++;
				}
			}
			if (counter == ingredients.size()){
				recipes.add(r.getName());
				counter = 0;
			}
			else {
				counter = 0;
			}
		}
		return recipes;
	}

}
