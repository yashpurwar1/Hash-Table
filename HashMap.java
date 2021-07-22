package hashTable;

public class HashMap<K, V> {
	MapNode head;
	MapNode tail;
	
	/*
	 * Purpose : method to get value from LinkedList
	 */

	public V get(K key) {
		MapNode<K, V> myNode = search(key);
		return (myNode == null) ? null : myNode.getValue();
	}

	/*
	 * Purpose : Method to search the word in LinkedList is
	 */
	public MapNode<K, V> search(K key) {
		MapNode<K, V> currentNode = head;
		int position = 0;
		while (currentNode != null) {
			position++;
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
		MapNode<K, V> myNode = search(key);
		if (myNode == null) {
			myNode = new MapNode<>(key, value);
			this.append(myNode);
		} else
			myNode.setValue(value);
	}
	/*
	 * Purpose : Method to append node 
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

	@Override
	public String toString() {
		return "HashMapNodes{" + head + '}';
	}
}