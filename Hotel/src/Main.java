import javax.swing.plaf.synth.SynthStyle;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Create tables
		HashAddressesTable hash = new HashAddressesTable();
		RoomTable room = new RoomTable();
		HotelTable hotel = new HotelTable(hash);
		//Fill hash address table
		fillHashTable(hash);
		//Fill hotel table and assign rooms
		fillHotelTable(hotel, hash, room);
		
		print(hotel, hash, room);
		list(hotel);
		System.out.println("CHECK_INS/CHECK_OUTS/LOCATES");
		check_out( "Foster, Kevin", hotel, hash, room);
		check_out( "Weedy, Mark David", hotel, hash, room);
		check_out( "Fields, Janet", hotel, hash, room);
		check_in("Gupta, Naresh", 92, hotel, hash, room);
		check_in("Johnson, Carol", 100, hotel, hash, room);
		check_in("Grubbs, Frank", 55, hotel, hash, room);
		check_out( "Gupta, Naresh", hotel, hash, room);
		check_out( "Mayall, John", hotel, hash, room);
		locate("Haley, Roger", hotel);
		locate("Mix, Gary", hotel);
		locate("Jam, Jim", hotel);
		check_in("Goodman, Eric", 55, hotel, hash, room);
		check_out( "Fields, Janet", hotel, hash, room);
		locate("Goodman, Eric", hotel);
		check_in("Fields, Janet", 55, hotel, hash, room);
		check_in("Goodman, Eric", 55, hotel, hash, room);
		check_out( "Johnson, Carol", hotel, hash, room);
		check_out( "Fisher, Eddie", hotel, hash, room);
		check_out( "Farmer, Jim", hotel, hash, room);
		System.out.println("");
		list(hotel);
		print(hotel, hash, room);
	}
	
	private static void fillHashTable(HashAddressesTable t) {
		t.insert("Adames, Winter");
		t.insert("Adams, Carl");
		t.insert("Alummootil, Tony");
		t.insert("Barreca, Nancy");
		t.insert("Bristol, Phillip");
		t.insert("Cardiello, Brian");
		t.insert("Chung, Darren");
		t.insert("Concepcion, Diony");
		t.insert("Cui, Hui Liang");
		t.insert("David, Bunmi");
		t.insert("Dilawari, Shonila");
		t.insert("Ehrlinger, Frederick");
		t.insert("Famodimu, Gideon");
		t.insert("Glynn, Debra");
		t.insert("Inoa, Jose");
		t.insert("Lam, Shirley");
		t.insert("Lau, Ka Kin");
		t.insert("Matos, Fanny");
		t.insert("Meyer, Richard");
		t.insert("Nunez, Gregory");
		t.insert("Richardson, Enerolisa");
		t.insert("Rodriquez, Dennis");
		t.insert("Saleem, Amir");
		t.insert("Salzman, Brian");
		t.insert("Siddiqui, Moudud");
		t.insert("Smith, Mellinda");
		t.insert("Tessema, Yikum");
		t.insert("Woldmichael, Yodit");
		t.insert("Akron, Jennifer");
		t.insert("Arriaza, Esperanza");
		t.insert("Chin, Li-Ching");
		t.insert("Chong, Kevin");
		t.insert("Delva, Edson");
		t.insert("Hughes, John");
		t.insert("Abraham, Mathews");
		t.insert("Alvarez, Marjorie");
		t.insert("Bennettson, Christine");
		t.insert("Cameau, Hans");
		t.insert("Corbett, Dennis");
		t.insert("Critchlow, Amenia");
		t.insert("Diaz, Brendaly");
		t.insert("Escalante, Martin");
		t.insert("Fleury, Melinda");
		t.insert("Ford, Thomas");
		t.insert("Josefsberg, Richard");
		t.insert("Kelly, James");
		t.insert("Langebek, Michele");
		t.insert("Myrthil, Jean");
		t.insert("Nasser, Muhammed");
		t.insert("Nguyen, Linh");
		t.insert("Niefeld, Tracy");
		t.insert("Padilla, Karl");
		t.insert("Scneider, Ross");
		t.insert("Jackson, Ltanya");
		t.insert("John, Susan");
		t.insert("Kutschera, Thomas");
		t.insert("Lada, Anna");
		t.insert("Makkar, Vibhor");
		t.insert("Rodriguez, Jessica");
		t.insert("Sewell, Andrew");
		t.insert("Thorne, Peter");
		t.insert("Shah, Syed");
		t.insert("Small, Tracy");
		t.insert("Steiner, Jared");
		t.insert("Tierney, Jeremy");
		t.insert("Tsai, James");
		t.insert("Wallace, Lorna");
		t.insert("Williams, William");
		t.insert("Yair, Yeud");
		t.insert("Atique, Kazi");
		t.insert("Burke, Grace-Ann");
		t.insert("Farook, Mohammed");
		t.insert("Feng, Feng Nian");
		t.insert("Hong, Robert");
		t.insert("James, Christopher");
		t.insert("Lopez, Emmanuel");
		t.insert("Mathew, Jiji");
		t.insert("Naseer, Muhammed");
		t.insert("Resmini, James");
		t.insert("Rommel, Gerald");
		t.insert("Abraham, Mathews");
		t.insert("Barger, Christopher");
		t.insert("Farrell, Daniel");
		t.insert("Hubbard, Kwame");
		t.insert("Mathew, Jiji");
		t.insert("Nguyen, Linh");
		t.insert("Foster, Kevin");
		t.insert("Weedy, Mark David");
		t.insert("Fields, Janet");
		t.insert("Mayall, John");
		t.insert("Haley, Roger");
		t.insert("Mix, Gary");
		t.insert("Goodman, Eric");
		t.insert("Johnson, Carol");
		t.insert("Fisher, Eddie");
		t.insert("Farmer, Jim");
		t.insert("Minor, Sharon");
		t.insert("Iacono, Lynn");
		t.insert("Bernstein, Leonard");
		t.insert("Mehta, Zubin");
	}
	
	public static void fillHotelTable(HotelTable h, HashAddressesTable t, RoomTable r) {
		for(int i = 0; i < t.getSize(); i++) {
			if(!t.getName(i).equals("")) {
				h.insert(t.getName(i), t.getKey(i), r.getEmptyRoom());
				r.fillRoom(r.getEmptyRoom(), t.getKey(i));
			}
		}
	}
	
	private static void check_in(String name, int room, HotelTable h, HashAddressesTable t, RoomTable r) {
		t.insert(name);
		h.insert(name, t.getKeyByName(name), r.getEmptyRoom());
		if(room == -1) {
			r.fillRoom(r.getEmptyRoom(), t.getKeyByName(name));
		} else {
			r.fillRoom(room, t.getKeyByName(name));
		}
	}
	
	private static void check_out(String name, HotelTable h, HashAddressesTable t, RoomTable r) {
		if(h.getRoomByName(name) == -1) {
			System.out.println("ERROR: " + name + " does not exist! CODE:1");
		} else {
			r.emptyRoom(h.getRoomByName(name));
			
			if(h.delete(name) == -1) {
				System.out.println("ERROR: " + name +" does not exist! CODE:2");
			} else if(t.delete(name) == -1) {;
				System.out.println("ERROR: " + name +" does not exist! CODE:3");
			} else {
				System.out.println("Guest " + name + " succesfully checked out!");
			}
		}
	}
	
	private static void print(HotelTable hotel, HashAddressesTable hash, RoomTable room) {
		System.out.println(hotel.toString() + "\n" + hash.toString() + "\n" + room.toString());
	}
	
	private static void list(HotelTable hotel) {
		System.out.println(hotel.listRooms());
	}
	
	private static void locate(String name, HotelTable hotel) {
		if(hotel.getRoomByName(name) != -1) {
			System.out.println(name + " is in room " + hotel.getRoomByName(name));
		} else {
			System.out.println("ERROR: Unable to locate guest! CODE:3");
		}
	}
}
