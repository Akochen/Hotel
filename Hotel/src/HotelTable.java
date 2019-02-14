
public class HotelTable {
	private String[][] hotelArray;
	
	/**
	 * Default Constructor
	 * Sets up the subscript and default fwd and bkwd chains and sets the full codes to 0
	 */
	public HotelTable() {
		//0: subscript, 1: name, 2: room#, 3: fwd-chain, 4: bkwd-chain, 5: full code
		hotelArray = new String [114][6];
		
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
	 * @return
	 */
	public int insert(String name, int key, int room) {
		int b = -1;
		if(getNextEmpty() != -1) {
			if(hotelArray[key][1].equals("")) {
				//is inserted in native spot
				hotelArray[key][1] = name;
				hotelArray[key][2] = room+"";
				hotelArray[key][3] = 0+"";
				hotelArray[key][4] = -1+"";
				hotelArray[key][5] = 1+"";
			}else if(!hotelArray[key][1].equals("") && Integer.parseInt(hotelArray[key][0]) == key) {
				//native spot is full so it's inserted in first open spot as foreigner
				int n = getNextEmpty();
				hotelArray[n][1] = name;
				hotelArray[n][2] = room+"";
				hotelArray[n][3] = -1+"";
				hotelArray[n][4] = key+"";
				hotelArray[n][5] = 1+"";
			}
			/*else if(!hotelArray[key][1].equals("") && Integer.parseInt(hotelArray[key][3]) != 0) {
				//move foreigner
				int n = getNextEmpty();
				hotelArray[n][1] = hotelArray[key][1];
				hotelArray[n][2] = hotelArray[key][2];
				hotelArray[key][3] = hotelArray[key][3];
				hotelArray[key][4] = hotelArray[key][4];
				hotelArray[n][5] = hotelArray[key][5];
				
			}*/
		}
		return b;
	}

	
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
	
	private int getNextLink(int link) {
		return 1;
	}
	
	/**
	 * @return returns the table as a string
	 */
	public String toString() {
		String r = "\nHOTEL TABLE\n";
		
		for(int i = 0; i < hotelArray.length; i++) {
			r += hotelArray[i][0] + ", Name: " + hotelArray[i][1] + ", Room Num: " +  hotelArray[i][2] + 
					", Fwd_Chain: " +  hotelArray[i][3] +  ", Bkwd_Chain: " +  hotelArray[i][4] + ", Full Code: " +  hotelArray[i][5] 
					+ "\n";
		}
		return r;
	}
	
}
