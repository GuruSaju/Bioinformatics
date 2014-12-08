import java.util.LinkedList;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * An implementation of a cache that uses a linked list data structure to store
 * any type of generic objects.
 * 
 * 
 * @author Adam Renak
 *
 */
public class Cache<T> {

	private int maxCapacity;
	private LinkedList<T> cache;
	private int hits, references;

	/**
	 * Constructor: creates an empty Linked list
	 * 
	 * @param maxCapacity:	maximum amount of objects that can be stored in our cache.
	 * 
	 */
	public Cache(int maxCapacity) {

		this.maxCapacity = maxCapacity;

		cache = new LinkedList<T>();
		hits = 0;
		references = 0;
	}

	/**
	 * Gets specified object from desired cache. NoSuchElementException thrown
	 * if object is not in cache.
	 * 
	 * @param object:	desired object to be obtained from cache
	 * @return object:	desired object to be obtained from cache
	 */
	public T getObject(T object) {
		boolean found = cache.contains(object);

		if (!found) {

			throw new NoSuchElementException("Object not found in cache");
		}

		else {
			return object;
		}
	}

	/**
	 * Adds object to front of cache
	 * 
	 * @param object:	object that is added to cache
	 */
	public void addObject(T object) {

		cache.addFirst(object);
	}

	/**
	 * Removes last object from a full cache
	 * 
	 * @return object:	object removed from cache
	 */
	public T removeObject() {
				
		T object = cache.removeLast();
		
		return object;
	}
	
	/**
	 * Removes object from current location in cache and places in the front.
	 * 
	 * @param object:  object to be moved to the front of the cache.
	 */
	public void write(T object) {
		cache.remove(object);
		addObject(object);
	}

	/**
	 * Searches cache for object. If object is found, it is moved to the front.
	 * If not found, object is added to the front. If object is not found and
	 * cache is full, then last element in the cache is removed and object is
	 * added to the front.
	 * 
	 * @param object:	object searched for in cache
	 * @return found:	true if object is in cache, false if not.
	 */
	public boolean search(T object) {
		
		boolean found = cache.contains(object);
		
		if (!found ) {
			if ( cache.size() >= maxCapacity) {
			removeObject();
			}
			addObject(object);
		}
		else {
			write(object);
			hits++;			
		}
		
		references++;
		return found;
	}

	/**
	 * Removes all objects from cache.
	 */
	public void clearCache() {

		cache = new LinkedList<T>();
	}
	
	/**
	 * @return  number of entries in the cache.
	 */
	public int size() {
		return cache.size();
	}
	
	/**
	 * @return hits:	number of hits in cache.
	 */
	public int getHits() {
		return hits;
	}
	
	/**
	 * @return references:	number of references to cache.
	 */
	public int getReferences() {
		return references;
	}

}
