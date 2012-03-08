import java.util.*;

public class IngredientIterator {
	private List<String> ingredients;  
    private int num;
    private int current;
     
    public IngredientIterator(List<String> ingredients, int num) {
        this.ingredients = ingredients;
        this.num = num;
        this.current = 0;
    }

    public boolean hasNext() {
    	return current < num;
    }
    

    public String next() {
    	if (!hasNext()){
    		throw new NoSuchElementException();
    	}
    	String s = ingredients.get(current);
    	current++;
    	return s;
    }
 
    public void remove() {
        throw new UnsupportedOperationException();
    }
}
