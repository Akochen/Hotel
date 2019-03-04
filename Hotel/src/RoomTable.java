
class RoomTable {
	int roomArray[][];
	
	/**
	 * Default contructor
	 * Puts rooms into the table and sets them all as unfilled (-1)
	 */
	public RoomTable() {
		//0: room num, 1: address
		roomArray = new int[100][2];
		for(int i = 0; i < roomArray.length; i++) {
			roomArray[i][0] = i+1;
			roomArray[i][1] = -1;
		}
	}
	
	/**
	 * Links a room to the person filling it
	 * @param i the room to be filled
	 * @param a the address of the start of the chain of the person filling the room
	 */
	public void fillRoom(int i, int a) {
		if(roomArray[i-1][1] == -1) {
			roomArray[i-1][1] = a;
		} else {
			roomArray[getEmptyRoom()][1] = a;
		}
	}
	
	/**
	 * Sets a room to empty
	 * @param i The room to empty
	 */
	public void emptyRoom(int i) {
		roomArray[i][1] = -1;
	}
	
	/**
	 * Gets the next empty room
	 * @return the first empty room
	 */
	public int getEmptyRoom() {
		int e = -1;
		for(int i = 0; i < roomArray.length; i++) {
			if(roomArray[i][1] == -1) {
				e=i+1;
				break;
			}
		}
		return e;
	}
	
	/**
	 * @return returns the table as a string
	 */
	public String toString() {
		String r= "\nROOMS\n__________\n";
		r += String.format("%1$-4s", "Sub") +"|" + String.format("%1$-4s", "Add#") + "|\n----|----|\n"; 
		for(int i = 0; i < roomArray.length; i++) {
			r += String.format("%1$-4s", roomArray[i][0]) +"|" + String.format("%1$-4s", roomArray[i][1]) + "|"
					+ "\n";
		}
		return r;
	}
}
