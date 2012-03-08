///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  RecipeDBApp.java
// File:             RecipeDatabase.java
// Semester:         Spring 2011
//
// Author:           Erin Rasmussen		ejrasmussen2@wisc.edu
// CS Login:         rasmusse
// Lecturer's Name:  Beck Hasti
// Lab Section:      Lecture 2
//
//
//                   STUDENTS WHO GET HELP FROM ANYONE OTHER THAN THEIR PARTNER
// Credits:          Xiaoming Shi for iterator method
//////////////////////////// 80 columns wide //////////////////////////////////

import java.util.*;
import java.lang.IllegalArgumentException;

/**
 * This class controls the creation and changing of a recipe database. It uses
 * an ArrayList and iterator to navigate.
 * 
 *
 * <p>Bugs: none known
 *
 * @author Erin Rasmussen
 */
public class RecipeDatabase {
	//A list of recipes
	private List<Recipe> recipes;
	//the total amount of recipes in a database
	private int num;
	//the iterator for lists of recipes
	private Iterator<Recipe> iter;


	/**
	 * Constructs an empty recipe database.
	 */
	public RecipeDatabase(){
		recipes = new ArrayList<Recipe>();
		num = 0;
	}

	/**
	 * Add a recipe with the given name n to the end of the database. 
	 * If a recipe with the name n is already in the database, just return.
	 *
	 * @param n The name of a recipe
	 */
	public void addRecipe(String n){
		if (n.equals(null)){
			throw new IllegalArgumentException();
		}
		iter = recipes.iterator();
		Recipe r;
		while (iter.hasNext()){
			r = iter.next();
			if (r.getName().equals(n)){
				return;
			}
		}
		recipes.add(new Recipe(n));
		num++;
	}

	/**
	 * Add the given ingredient i to the recipe with the given name n in the 
	 * database. If a recipe with the name n is not in the database throw a 
	 * java.lang.IllegalArgumentException. If i is already in the list of 
	 * ingredients associated with the recipe named n, just return. 
	 *
	 * @param i The ingredient to be added
	 * @param n The recipe to add the ingredient to
	 */
	public void addIngredient(String i, String n){
		if (n.equals(null) || i.equals(null)){
			throw new IllegalArgumentException();
		}
		iter = recipes.iterator();
		Recipe r;
		while (iter.hasNext()){
			r = iter.next();
			if (r.getName().equals(n)){
				if (!hasIngredient(i, n)){
					r.getIngredients().add(i);
					return;
				}
				else {
					return;
				}
			}
		}
		throw new IllegalArgumentException();

	}

	/**
	 *Return true if and only if a recipe with the name n is in the database. 
	 *
	 * @param n The name of a recipe
	 * @return whether or not the database has a recipe
	 */
	public boolean containsRecipe(String n){
		if (n.equals(null)){
			throw new IllegalArgumentException();
		}
		iter = recipes.iterator();
		Recipe r;
		while (iter.hasNext()){
			r = iter.next();
			if (r.getName().equals(n)){
				return true;
			}
		}
		return false;
	}

	/**
	 *Return true if and only if the ingredient i appears in at least one 
	 *recipe in the database.
	 *
	 * @param i The ingredient to search for
	 * @return whether or not an ingredient is in the database
	 */
	public boolean containsIngredient(String i){
		if (i.equals(null)){
			throw new IllegalArgumentException();
		}
		iter = recipes.iterator();
		while (iter.hasNext()){
			for (int j = 0; j < iter.next().getIngredients().size(); j++)
				if (iter.next().getIngredients().get(j).equals(i)){
					return true;
				}
		}
		return false;
	}

	/**
	 * Returns true if and only if the given ingredient i is in the recipe 
	 * with the given name n. If a recipe with the name n is not in the 
	 * database, return false.
	 *
	 * @param i an ingredient to search for
	 * @param n the recipe to search through
	 * @return whether or not a recipe contains an ingredient
	 */

	public boolean hasIngredient(String i, String n){
		if (n.equals(null) || i.equals(null)){
			throw new IllegalArgumentException();
		}
		iter = recipes.iterator();
		Recipe r;
		while (iter.hasNext()){
			r = iter.next();
			if (r.getName().equals(n)){
				for (int j = 0; j < r.getIngredients().size();
				j++){
					if (r.getIngredients().get(j).equals(i)){
						return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * Return the list of ingredients for the recipe with the given name n. 
	 * If a recipe with the name n is not in the database, return null.
	 *
	 * @param n the recipe to return the ingredients for
	 * @return a list of ingredients in a certain recipe
	 */
	public List<String> getIngredients(String n){
		if (n.equals(null)){
			throw new IllegalArgumentException();
		}
		iter = recipes.iterator();
		List<String> ingredients = new ArrayList<String>();
		boolean noMatch = true;
		Recipe r;
		while (iter.hasNext()){
			r = iter.next();
			if (r.getName().equals(n)){
				noMatch = false;
				for (int j = 0; j < r.getIngredients().size();
				j++){
					ingredients.add(r.getIngredients().get(j));
				}
			}
		}
		if (noMatch){
			return null;
		}
		return ingredients;
	}

	/**
	 * Return an Iterator over the Recipe objects in the database. The recipes 
	 * should be returned in the order they were added to the database 
	 * (resulting from the order in which they are in the text file). 
	 *
	 * @return an iterator of recipes
	 */
	public Iterator<Recipe> iterator(){
		return recipes.iterator();
	}

	/**Remove the recipe with the given name n from the database. If a recipe 
	 * with the name n is not in the database, return false; otherwise 
	 * (i.e., the removal is successful) return true. 
	 *
	 * @param n the recipe to be removed
	 * @return false if recipe doesn't exist, true if successful removal
	 */
	public boolean removeRecipe(String n){
		if (n.equals(null)){
			throw new IllegalArgumentException();
		}
		iter = recipes.iterator();
		Recipe r;
		while (iter.hasNext()){
			r = iter.next();
			if (r.getName().equals(n)){
				iter.remove();
				num--;
				return true;
			}
		}
		return false;
	}

	/**
	 * Return the number of recipes in this database.
	 *
	 * @return the amount of recipes in the database
	 */
	public int size(){
		return num;
	}





}
