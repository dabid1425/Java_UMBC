
class Boat {
	private String shipName;
	private Position currentPosition;
	private String alignment;
	private String[] shipNames = { "Aircraft Carrier", "Battleship", "Cruiser", 
		"Destroyer", "Submarine"};
	private int[] sizes = { 5, 4, 3, 2, 3 };
	private int sizeOfShip;
	private boolean[] hitsOnBoat;
	
	public Boat(String shipName, Position startPosition, String alignment) {
		this.shipName = shipName;
		this.alignment = alignment;
		currentPosition = startPosition;
		int indexOfShip = 0;
		int index = 0;
		while(index < shipNames.length) {
			if(shipNames[index].equals(shipName)) {
				indexOfShip = index;
			}
			++index;
		}
		sizeOfShip = sizes[indexOfShip];
		hitsOnBoat = new boolean[sizeOfShip];
	}
	
	public String name() {
		return shipName;
	}
	
	public char abbreviation() {
		return (shipName.charAt(0));
	}
	
	public int size() {
		return sizeOfShip;
	}
	
	public boolean onBoat(Position check) {
		if(alignment.equals("horizontal")) {
			if(currentPosition.row() == check.row()) {
				if(currentPosition.column() <= check.column() && (currentPosition.column() + sizeOfShip-1) >= check.column()) 
					return true;
			}
		}
		else {
			if(currentPosition.column() == check.column()) {
				if(currentPosition.row() <= check.row() && (currentPosition.row() + sizeOfShip-1) >= check.row())
					return true;
			}
		}
		return false;
	}
	
	public boolean isHit(Position check) {
		if(onBoat(check)) {
			if(alignment.equals("horizontal"))
				return hitsOnBoat[(check.columnIndex() - currentPosition.columnIndex())];
			else
				return hitsOnBoat[(check.rowIndex() - currentPosition.rowIndex())];
		}
		return false;
	}
	
	public void hit(Position check) {
		if(onBoat(check)) {
			if(alignment.equals("horizontal")) 
				hitsOnBoat[(check.columnIndex() - currentPosition.columnIndex())] = true;
			else
				hitsOnBoat[(check.rowIndex() - currentPosition.rowIndex())] = true;
		}
	}
	
	public boolean sunk() {
		for(int i = 0; i < hitsOnBoat.length; ++i) {
			if(!hitsOnBoat[i] == true)
				return false;
		}
		return true;
	}
	
	public Position position() {
		return currentPosition;
	}
	
	public String direction() {
		return alignment;
	}
}
	
	
	
	
