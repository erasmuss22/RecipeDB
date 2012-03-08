///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  RecipeDBApp.java
// File:             Recipe.java
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
 * The Recipe class is used to represent a recipe that keeps track of its name
 * and a list of ingredients.
 * 
 * @author Beck Hasti, CS 367 instructor, copyright 2011
 */
public class Recipe {
    private String name;                // the recipe name      
    private List<String> ingredients;   // the list of ingredients
    
    /**
     * Constructs a recipe with the given name and an empty list of ingredients.
     * 
     * @param name the name of this recipe
     */
    public Recipe(String name)     {
        this.name = name;
        this.ingredients = new ArrayList<String>();
    }
    
    /**
     * Return the name of this recipe.
     * 
     * @return the name of the recipe
     */
    public String getName() { 
        return name;
    }
    
    /**
     * Return the list of ingredients for this recipe.
     * 
     * @return the list of ingredients
     */
    public List<String> getIngredients() {
        return ingredients;
    }
}
