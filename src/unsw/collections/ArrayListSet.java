/**
 *
 */
package unsw.collections;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * An implementation of Set that uses an ArrayList to store the elements.
 *
 * @invariant All e in elements occur only once
 *
 * @author Robert Clifton-Everest
 *
 */
public class ArrayListSet<E> implements Set<E> {

    private ArrayList<E> elements;

    public ArrayListSet() {
        elements = new ArrayList<>();
    }

    @Override
    public void add(E e) {
        if (!elements.contains(e))
            elements.add(e);
    }

    @Override
    public void remove(E e) {
        elements.remove(e);
    }

    @Override
    public boolean contains(Object e) {
        return elements.contains(e);
    }

    @Override
    public int size() {
        return elements.size();
    }
    
    // determine if this set is a subset of another set
    @Override
    public boolean subsetOf(Set<?> other) {
    	
    	// we loop through all the elements in this set
    	for (E e: elements) {
    		
    		// if the other set doesn't contain this element, this set is not a subset of it
    		if (!other.contains(e)) {
    			return false;
    		}
    	}
    	
    	// if all elements were in the other set, it is a subset
        return true;
    }

    @Override
    public Iterator<E> iterator() {
        return elements.iterator();
    }

    @SuppressWarnings("null")
	@Override
    public Set<E> union(Set<? extends E> other) {
    	
    	// we create an empty union set which we will return
    	Set<E> result = null;
    	
    	// we loop through all the elements in our set
    	for (E e: elements) {
    		
    		// if the other set contains this element (and we haven't yet added it to union)
    		// then we add it to union
    		if (other.contains(e) && !result.contains(e)) {
    			result.add(e);
    		}
    	}

    	// we return this union set
        return result;
    }

    @Override
    public Set<E> intersection(Set<? extends E> other) {
    	
    	// we create an intersection set which we will return (and set it to equal our current set)
    	@SuppressWarnings("unchecked")
		Set<E> result = (Set<E>) elements;
    	
    	// we loop through all the elements in the other set
    	for (E e: other) {
    		
    		// if the intersection doesn't contain this element, we want to add it
    		if (!result.contains(e)) {
    			result.add(e);
    		}
    	}

    	// we then return this intersection set
        return result;
    }

    /**
     * For this method, it should be possible to compare all other possible sets
     * for equality with this set. For example, if an ArrayListSet<Fruit> and a
     * LinkedListSet<Fruit> both contain the same elements they are equal.
     * Similarly, if a Set<Apple> contains the same elements as a Set<Fruit>
     * they are also equal.
     */
    @SuppressWarnings("unchecked")
	@Override
    public boolean equals(Object other) {
    	
    	// we loop through all the elements in this set
    	for (E e: elements) {
    		
    		// if the other set doesn't contain this element, this set is not a subset of it
    		if (!((Set<?>) other).contains(e)) {
    			return false;
    		}
    	}
    	
    	// we loop through all the elements in this set
    	for (E e: (Set<E>)other) {
    		
    		// if the other set doesn't contain this element, this set is not a subset of it
    		if (!elements.contains(e)) {
    			return false;
    		}
    	}
    	
    	// if both have the same elements, they are equal
        return true;
    }

}
