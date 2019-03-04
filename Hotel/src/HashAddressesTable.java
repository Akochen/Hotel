
class HashAddressesTable {
	String hashArray[][];
	
	/**
	 * Default constructor
	 * Sets up array with proper sizes and fills the subscripts
	 */
	public HashAddressesTable() {
		//0: subscript 1: name, 2: key
		hashArray = new String [113][3];
		
		for(int i = 0; i < hashArray.length; i++) {
			hashArray[i][0] = i+1+"";
			hashArray[i][1] = "";
			hashArray[i][2] = "";
		}
	}
	
	/**
	 * Inserts a name into the hash address table
	 * @param in The name to put in the table
	 * @return The slot of the name, or -1 if the table is filled
	 */
	public int insert(String in) {
		int p = findEmptySlot();
		if(p != -1) {
			hashArray[p][1] = in;
			hashArray[p][2] = (int) Math.ceil(Math.random()*113)+"";
		}
		return p;
	}
	
	/**
	 * Removes a name from the hash address table
	 * @param name The name to remove
	 * @return The slot of the name, or -1 if the name is not found
	 */
	public int delete(String name) {
		int pos = -1;
		for(int i = 0; i < hashArray.length; i++) {
			if(hashArray[i][1].equals(name)) {
				hashArray[i][1] = "";
				hashArray[i][2] = "";
				pos = i;
			}
		}
		return pos;
	}
	
	/**
	 * Finds the first empty slot
	 * @return The first empty slot, or -1 if the table is filled
	 */
	private int findEmptySlot() {
		int i = -1;
		
		for(int j = 0; j < hashArray.length; j++) {
			if(hashArray[j][1].equals("")) {
				i = j;
				break;
			}
		}
		
		return i;
	}
	
	/**
	 * Returns the size of the array of Hash Addresses
	 * @return the size of the array of Hash Addresses
	 */
	public int getSize() {return hashArray.length;}
	
	/**
	 * Checks if a specific slot is filled
	 * @param i The slot to check
	 * @return True if the slot is full, False if the slot is empty
	 */
	public boolean isSlotFilled(int i) {
		if(hashArray[i][1].equals("")) {
			return false;
		} else {
			return true;
		}
	}
	
	/**
	 * Returns the name of the guest in the specified slot
	 * @param slot The slot to get the name from
	 * @return The name of the guest
	 */
	public String getName(int slot) {return hashArray[slot][1];}
	
	/**
	 * Gets the key of the guest in the specified slot
	 * @param slot The slot to get the key from
	 * @returnThe key of the guest
	 */
	public int getKey(int slot) {return Integer.parseInt(hashArray[slot][2]);}
	
	public int getKeyByName(String name) {
		int r = -1;
		
		for(int i = 0; i < hashArray.length; i++) {
			if(hashArray[i][1].equals(name)) {
				r = Integer.parseInt(hashArray[i][2]);
			}
		}
		return r;
	}
	
	/**
	 * @return returns the table as a string
	 */
	public String toString() {
		String r= "\nHASH ADDRESSES\n__________________________________\n";
		r += String.format("%1$-3s", "Sub") +"|" + String.format("%1$-25s", "Name") + "|" + String.format("%1$-3s", "key|") + "\n---|-------------------------|---|\n"; 
		for(int i = 0; i < hashArray.length; i++) {
			r += String.format("%1$-3s", hashArray[i][0]) +"|" + String.format("%1$-25s", hashArray[i][1]) + "|" + String.format("%1$-3s", hashArray[i][2]) + "|" 
					+ "\n";
		}
		return r;
	}
}
