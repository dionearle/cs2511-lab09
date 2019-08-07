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
    
    /**
     * Determine if this set is a subset of another set.
     *
     * @param other The possible super set.
     * @return subset - Whether or not the subset relation holds.
     * @post subset if and only if (forall e. contains(e) => other.contains(e))
     */
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

    /**
     * Return a new set that is the union of this set and the given set
     *
     * @param other The other set operand.
     * @return result - A new set that is the union of these two sets.
     * @post for all e in result, contains(e) or other.contains(e)
     */
	@Override
    public Set<E> union(Set<? extends E> other) {
        
        // we create an union set which we will return (and set it to equal our current set)
		ArrayListSet<E> result = new ArrayListSet<>();
     	for (E e: elements) {
        	result.add(e);
        }
         	
         // we loop through all the elements in the other set
         for (E e: other) {
         		
         	// if the union doesn't contain this element, we want to add it
         	if (!result.contains(e)) {
         		result.add(e);
         	}
         }

         // we then return this union set
         return result;
    }

	/**
     * Return a new set that is the intersection of this set and the given set
     *
     * @param other The other set operand.
     * @return result - A new set that is the intersection of these two sets.
     * @post for all e in result, contains(e) and other.contains(e)
     */
    @Override
    public Set<E> intersection(Set<? extends E> other) {
    	
    	// we create an empty intersection set which we will return
    	ArrayListSet<E> result = new ArrayListSet<>();
    	
    	// we loop through all the elements in our set
    	for (E e: elements) {
    		
    		// if the other set contains this element (and we haven't yet added it to intersection)
    		// then we add it to intersection
    		if (other.contains(e) && !result.contains(e)) {
    			result.add(e);
    		}
    	}

    	// we return this intersection set
        return result;
    }

    /**
     * For this method, it should be possible to compare all other possible sets
     * for equality with this set. For example, if an ArrayListSet<Fruit> and a
     * LinkedListSet<Fruit> both contain the same elements they are equal.
     * Similarly, if a Set<Apple> contains the same elements as a Set<Fruit>
     * they are also equal.
     */
	@Override
    public boolean equals(Object other) {
		
		// if object is null it cannot be equal
		if (other == null) {
			return false;
		}
		
		// if they are the same object then they must be equal
		if (elements == other) {
			return true;
		}
		
		// if the other object isn't a set of some form
		// they can't be equal
		if (!(other instanceof Set<?>)) {
			return false;
		}
		
		ArrayListSet<?> otherSet = (ArrayListSet<?>) other;
		
		Iterator<?> otherIterator = otherSet.iterator();
		while (otherIterator.hasNext()) {
			if (!(elements.contains(otherIterator.next()))) return false;
		}
    	
    	// we loop through all the elements in this set
    	for (E e: elements) {
    		
    		// if the other set doesn't contain this element, this set is not a subset of it
    		if (!(otherSet.contains(e))) {
    			return false;
    		}
    	} 
    	
    	// if both are a subset of each other, they are equal
        return true;
    }

}
