
class HotelTable {
	private String[][] hotelArray;
	private HashAddressesTable hashTable;
	
	/**
	 * Default Constructor
	 * Sets up the subscript and default fwd and bkwd chains and sets the full codes to 0
	 */
	public HotelTable(HashAddressesTable h) {
		//0: subscript, 1: name, 2: room#, 3: fwd-chain, 4: bkwd-chain, 5: full code
		hotelArray = new String [114][6];
		hashTable = h;
		
		//empty chain
		hotelArray[0][0] = "0";
		hotelArray[0][1] = "";
		hotelArray[0][2] = "";
		hotelArray[0][3] = "1";
		hotelArray[0][4] = hotelArray.length-1+"";
		hotelArray[0][5] = "";
		//first slot
		hotelArray[1][0] = "1";
		hotelArray[1][1] = "";
		hotelArray[1][2] = "";
		hotelArray[1][3] = "2";
		hotelArray[1][4] = "-1";
		hotelArray[1][5] = "0";
		//last slot
		hotelArray[113][0] = hotelArray.length-1+"";
		hotelArray[hotelArray.length-1][1] = "";
		hotelArray[hotelArray.length-1][2] = "";
		hotelArray[hotelArray.length-1][3] = "-1";
		hotelArray[hotelArray.length-1][4] = hotelArray.length-2+"";
		hotelArray[hotelArray.length-1][5] = "0";
		//
		for(int i = 2; i < hotelArray.length-1; i++) {
			hotelArray[i][0] = i+"";
			hotelArray[i][1] = "";
			hotelArray[i][2] = "";
			hotelArray[i][3] = (i+1)+"";
			hotelArray[i][4] = (i-1)+"";
			hotelArray[i][5] = 0+"";
		}
	}
	
	/**
	 * Inserts an entry into the hotel table
	 * @param name The name of the guest
	 * @param key The native position of the entry
	 * @param room The room the guest is staying in
	 * @return an array of 2 ints. 
	 * 		0: The slot the entry is inserted to, or -1 if the table is full
	 * 		1: The probe count
	 */
	public int[] insert(String name, int key, int room) {
		int b[] = {-1, 1};
		if(getNextEmpty() != -1) {
			if(hotelArray[key][1].equals("")) { //Name is empty
				//is inserted in native spot
				hotelArray[key][1] = name;
				hotelArray[key][2] = room+"";
				hotelArray[key][3] = 0+"";
				hotelArray[key][4] = 0+"";
				hotelArray[key][5] = 1+"";
				b[0]=key;
				b[1]=1;
				//System.out.println(name + " is native");
			} else if (!hotelArray[key][1].equals("")) {//if name is not empty
				if(hashTable.getKeyByName(name) != key) { 
					//Native spot is filled by foreigner so foreigner is moved and native is inserted
						String pName = hotelArray[key][1] + "";
						String pRoom = hotelArray[key][2];
						b[0] = key;
						hotelArray[key][1] = name+"";
						hotelArray[key][2] = room+"";
						hotelArray[key][3] = 0+""; 
						hotelArray[key][4] = 0+"";
						hotelArray[key][5] = 1+"";
						b[1] = 1 + insert(pName + "", hashTable.getKeyByName(pName), Integer.parseInt(pRoom))[1];
						//System.out.println(name + " replaced a foreigner");
				} else {
					//native spot is filled by native so it's inserted in first open spot as foreigner
					int n = getNextEmpty();
					hotelArray[n][1] = name + ""; 
					hotelArray[n][2] = room+"";
					hotelArray[n][3] = 0+"";
					hotelArray[n][4] = getFinalForward(key)+"";
					hotelArray[n][5] = 1+"";
					hotelArray[getFinalForward(key)][3] = n+"";
					b[0] = n;
					b[1] = 2;
				}
			}
		}
		repairEmptyChain();
		return b;
	}
	
	/**
	 * Removes a guest from the hotel
	 * @param name The name of the guest to be removed
	 * @return The slot the guest was stored in, or -1 if no guest is found
	 */
	public int[] delete(String name) {
		int[] b = new int[2];
		b[0] = getNameByChain(name)[0];
		b[1] = getNameByChain(name)[1];
		
		/*
		for(int i = 0; i < hotelArray.length; i++) {
			if(hotelArray[i][1].equals(name)) {
				b[0] = i;
				break;
			}
		}
		*/
		
		if(b[0] != -1) {
			if(hotelArray[b[0]][3].equals(0+"") && hotelArray[b[0]][4].equals(0+"")) { //first and only link
				hotelArray[b[0]][1] = "";
				hotelArray[b[0]][2] = "";
				hotelArray[b[0]][5] = 0+"";
			} else if(!hotelArray[b[0]][3].equals(0+"") && !hotelArray[b[0]][4].equals(0+"")) {//located between two or more links
				hotelArray[Integer.parseInt(hotelArray[b[0]][4])][3] = 0+"";
				hotelArray[Integer.parseInt(hotelArray[b[0]][3])][4] = 0+"";
				hotelArray[b[0]][1] = "";
				hotelArray[b[0]][2] = "";
				hotelArray[b[0]][5] = 0+"";
			} else if(hotelArray[b[0]][3].equals(0+"") && !hotelArray[b[0]][4].equals(0+"")) {//located as final link in chain with multiple links
				hotelArray[Integer.parseInt(hotelArray[b[0]][4])][3] = 0+"";
				hotelArray[b[0]][1] = "";
				hotelArray[b[0]][2] = "";
				hotelArray[b[0]][5] = 0+""; 
			} else {//located as first link in chain with multiple links
				//move next link to native position
				hotelArray[b[0]][1] = hotelArray[Integer.parseInt(hotelArray[b[0]][3])][1];
				hotelArray[b[0]][2] = hotelArray[Integer.parseInt(hotelArray[b[0]][3])][2];
				hotelArray[b[0]][3] = hotelArray[Integer.parseInt(hotelArray[b[0]][3])][3];
				hotelArray[b[0]][4] = hotelArray[Integer.parseInt(hotelArray[b[0]][3])][4];
				hotelArray[b[0]][5] = hotelArray[Integer.parseInt(hotelArray[b[0]][3])][5];
				//delete old position of moved link
				hotelArray[Integer.parseInt(hotelArray[b[0]][3])][1] = "";
				hotelArray[Integer.parseInt(hotelArray[b[0]][3])][2] = "";
				hotelArray[Integer.parseInt(hotelArray[b[0]][3])][5] = 0+"";
			}
		}
		repairEmptyChain();
		return b;
	}
	
	private int[] getNameByChain(String name) {
		int[] i = {hashTable.getKeyByName(name), 1};
		if(i[0] != -1) {
			if(hotelArray[i[0]][1].equals(name)) {
			} else {
				while(Integer.parseInt(hotelArray[i[0]][3]) != 0) {
					i[0] = Integer.parseInt(hotelArray[i[0]][3]);
					i[1]++;
				}
			}
		}
		return i;
	}
	
	/**
	 * Gets the final forward_chain link
	 * @param link The final link in the specified forward chain
	 */
	private int getFinalForward(int link) {
		int r = link;
		int p = r;
		r = Integer.parseInt(hotelArray[p][3]);
		while(r != -1 && r!= 0) {
			p = r;
			r = Integer.parseInt(hotelArray[p][3]);
		}
		return p;
	}
	
	/**
	 * Gets the next empty slot to put a guest
	 * @return the next empty slot or -1 if the table is full
	 */
	private int getNextEmpty() {
		int slot = -1;
		
		for(int i = 1; i < hotelArray.length; i++) {
			if(hotelArray[i][1].equals("")) {
				slot = i;
				break;
			}
		}
		return slot;
	}

	
	/**
	 * @return returns the table as a string
	 */
	public String toString() {
		String r = "\nHOTEL TABLE\n_____________________________________________\n";
		r += String.format("%1$-3s", "Sub") +"|" + String.format("%1$-25s", "Name") + "|" + String.format("%1$-3s", "Rm#") + "|" + String.format("%1$-3s", "Fwd") + "|" 
				+ String.format("%1$-3s", "Bwd") + "|" + String.format("%1$-2s", "FC") + "|\n---|-------------------------|---|---|---|--|\n"; 
		for(int i = 0; i < hotelArray.length; i++) {
			r += String.format("%1$-3s", hotelArray[i][0]) + "|" + String.format("%1$-25s", hotelArray[i][1]) + "|" + String.format("%1$-3s", hotelArray[i][2]) + "|" + 
					String.format("%1$-3s", hotelArray[i][3]) + "|" +  String.format("%1$-3s", hotelArray[i][4]) + "|" + String.format("%1$-2s", hotelArray[i][5]) 
					+ "|" + "\n";
		}
		return r;
	}
	
	/**
	 * Relinks the empty chain after changes to the table
	 */
	private void repairEmptyChain() {
		int b = -1;
		int old = -1;
		for(int i = hotelArray.length-1; i>=0; i--) {
			if(hotelArray[i][1].equals("")) {
				hotelArray[i][3] = old +"";
				old = i;
			}
		}
		old = -1;
		for(int i = 1; i < hotelArray.length; i++) {
			if(hotelArray[i][1].equals("")) {
				hotelArray[i][4] = old +"";
				old = i;
			}
		}
	}
	
	/**
	 * Finds the room of a guest using their name
	 * @param name The name of the guest
	 * @return The room of the guest
	 */
	public int getRoomByName(String name) {
		int room = -1;
		
		for(int i = 1; i < hotelArray.length; i++) {
			if(hotelArray[i][1].equals(name)) {
				room = Integer.parseInt(hotelArray[i][2]);
			}
		}
		return room;
	}
	
	public int[] getRoomByNameForLocate(String name) {
		int i[] = new int[2];
		i[1] = -1;
		i[0] = 1;
		if(hashTable.getKeyByName(name) != -1) {
			int key1 = Integer.parseInt(hotelArray[hashTable.getKeyByName(name)][3]);
			i[1] = hashTable.getKeyByName(name);
			while(key1 != 0 && key1 != -1) {
				key1 = Integer.parseInt(hotelArray[key1][3]);
				i[0]++;
				if(key1 == 0) {
					break;
				}else {
					i[1] = key1;
				}
			}
			i[1] = Integer.parseInt(hotelArray[i[1]][2]);
			//System.out.println("Probes for locating " + name + ": " + i);
			return i;
		} else {
			//System.out.println("Probes for locating " + name + ": " + i);
			return i;
		}
	}
	
	/**
	 * returns number of empty cells
	 * DEBUGGING only
	 * @return the number of empty cells
	 */
	public int getEmpty() {
		int e = 0;
		for(int i = 0; i < hotelArray.length; i++) {
			if(hotelArray[i][1].equals("")) {
				e++;
			}
		}
		return e;
	}
	
	/**
	 * Lists all rooms and who's in them
	 * @return A string containing a list of all rooms
	 */
	public String listRooms() {
		String list = "LIST\n_____________________________\n";
		list += String.format("%1$-24s", "Name") +"|" + String.format("%1$-3s","Rm#") + "|\n------------------------|---|\n";
		for(int i = 0; i < hotelArray.length; i++) {
			for(int j = 0; j < hotelArray.length; j++) {
				if(hotelArray[j][2].equals(i+"")) {
					list += String.format("%1$-24s", hotelArray[j][1]) +"|" + String.format("%1$-3s", hotelArray[j][2]) + "|\n";
				}
			}
		}
		return list;
	}
}
