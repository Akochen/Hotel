
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Create tables
		HotelTable hotel = new HotelTable();
		HashAddressesTable hash = new HashAddressesTable();
		RoomTable room = new RoomTable();
		//Fill hash address table
		fillHashTable(hash);
		//Fill hotel table and assign rooms
		fillHotelTable(hotel, hash, room);

		System.out.println(hotel.toString() + "\n" + hash.toString() + "\n" + room.toString() + "\n" + hash.isSlotFilled(113));
//		hash.delete("Adames, Winter");
//		System.out.println(hotel.toString() + "\n" + hash.toString() + "\n" + room.toString() + "\n" + hash.isSlotFilled(113));
//		hash.insert("kek");
//		System.out.println(hotel.toString() + "\n" + hash.toString() + "\n" + room.toString() + "\n" + hash.isSlotFilled(113));
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
		t.insert("Fester, Kevin");
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
}
