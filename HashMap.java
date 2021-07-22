package hashTable;

import java.util.ArrayList;

public class HashMap<K, V> {
	MapNode<K, V> head;
	MapNode<K, V> tail;
	private final int numOfBuckets;
	ArrayList<MapNode<K, V>> bucketArray;

	public HashMap() {
		this.numOfBuckets = 10;
		this.bucketArray = new ArrayList<>(numOfBuckets);
		// Create empty LinkedLists
		for (int i = 0; i < numOfBuckets; i++)
			this.bucketArray.add(null);
	}

	/*
	 * Purpose : method to get value from LinkedList using index number
	 */

	public V get(K key) {
		int index = this.getBucketIndex(key);
		if (this.bucketArray.get(index) == null)
			return null;
		MapNode<K, V> myNode = search(key);
		return (myNode == null) ? null : myNode.getValue();
	}

	/*
	 * Purpose : Method to search the word in LinkedList is
	 * 
	 * @param : key to search
	 *
	 */

	public MapNode<K, V> search(K key) {
		MapNode<K, V> currentNode = head;
		while (currentNode != null) {
			if (currentNode.getKey().equals(key)) {
				return currentNode;
			}
			currentNode = currentNode.getNext();
		}

		return currentNode;
	}

	/*
	 * Purpose : Method to add key and value to hash table 
	 */
	public void add(K key, V value) {
		int index = this.getBucketIndex(key);
		MapNode<K, V> myNode = this.bucketArray.get(index);
		if (myNode == null) {
			myNode = new MapNode<>(key, value);
			this.bucketArray.set(index, myNode);
		}
		myNode = search(key);
		if (myNode == null) {
			myNode = new MapNode<>(key, value);
			this.append(myNode);
		} else
			myNode.setValue(value);
	}

	public int getBucketIndex(K key) {
		int hashCode = Math.abs(key.hashCode());
		int index = hashCode % numOfBuckets;
		return index;
		
	}

	/*
	 * Purpose : Method to append value to Linked List
	 */

	private void append(MapNode<K, V> myNode) {
		if (this.head == null)
			this.head = myNode;
		if (this.tail == null)
			this.tail = myNode;
		else {
			this.tail.setNext(myNode);
			this.tail = myNode;
		}
	}
	
	public void remove(K key) {
		MapNode<K, V> currentNode = head;
		MapNode<K, V> previousNode = null;
		while (currentNode != null && currentNode.getKey().equals(key)) {
			head = currentNode.getNext();
		}
		while (currentNode != null && !(currentNode.getKey().equals(key))) {
			previousNode = currentNode;
			currentNode = currentNode.getNext();
		}
		if (currentNode != null)
			previousNode.next = currentNode.next;
		if (currentNode == null)
			System.out.println("Word not found");
	}

	@Override
	public String toString() {
		return "{" + head + '}';
	}
}