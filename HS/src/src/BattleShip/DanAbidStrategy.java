

import java.util.ArrayList;
public class DanAbidStrategy extends ComputerBattleshipPlayer {
	private Position pos;
	private Position newPos;
	private int turns;
	private boolean StopChecking;
	private int searchMethodCounter;
	private int horizontalSearchMethodCounter;
	private int sizeOfBoat;
	private boolean StopCheckingLeft;
	private boolean StopCheckingRight;
	private int hitCountTillSunk;
	private int sunkCount=0;
	private boolean StopCheckingDown;
	private boolean savethatPositionloop;
	private Position AdjacentposVertOnefound;
	private Position AdjacentposHortOnefound;
	private Position AdjacentposHortTwofound;
	private Position AdjacentposVertTwofound;
	private ArrayList<Position> savethatPosition=new ArrayList<Position>(1);
	private char verticalIndex;
	private Position lastPosHit;
	private int savedPosIndex;
	boolean okToInitialized;
	private int horizontalIndex;
	DanAbidStrategy(){	
		lastPosHit=new Position('a',1);
		initializeVariables();
	}

	private void initializeVariables(){
		horizontalIndex=-3;
		verticalIndex=0x61;
		turns=0;
		hitCountTillSunk=1;
		savedPosIndex=-1;
		savethatPosition=new ArrayList<Position>(1);
		searchMethodCounter=0;
		horizontalSearchMethodCounter=0;
		StopChecking=false;
		StopCheckingDown=false;
		StopCheckingLeft=false;
		StopCheckingRight=false;
		savethatPositionloop=false;
		AdjacentposVertOnefound=null;
		AdjacentposVertTwofound=null;
		AdjacentposHortOnefound=null;
		AdjacentposHortTwofound=null;
		savethatPosition=new ArrayList<Position>(1);
		okToInitialized=false;
	}
	private Position savedPosition(){
		if(savethatPositionloop==false){
			for(;savedPosIndex<savethatPosition.size();){
				pos=savethatPosition.get(savedPosIndex);
				return Search(savethatPosition.get(savedPosIndex),mygrid.boatInitial(savethatPosition.get(savedPosIndex)));
			}
		}
		savethatPositionloop=true;
		savedPosIndex--;
		boolean empty=false;
		while(empty==false){
			horizontalIndex+=4;
			if(horizontalIndex>10){
				horizontalIndex-=11;
				if(horizontalIndex==0){
					horizontalIndex+=4;
				}
				verticalIndex++;
				if(verticalIndex>0x6A){
					verticalIndex=0x61;
					horizontalIndex=3;
				}
			}
			newPos=new Position(verticalIndex,horizontalIndex);
			empty=mygrid.empty(newPos);
		}
		return pos=new Position(verticalIndex,horizontalIndex);

	}
	private boolean CanIGoLeft(Position pos){
		if(pos.column()==1){
			return false;
		}
		return true;
	}
	private boolean CanIGoDown(Position pos){
		if(pos.row()=='j'){
			return false;
		}
		return true;
	}
	private boolean CanIGoUp(Position pos){
		if(pos.row()=='a'){
			return false;
		}
		return true;
	}
	private Position horizontalSearch(Position hitPos,char boatInitial){
		horizontalSearchMethodCounter++;
		if(horizontalSearchMethodCounter>1){
			if(StopCheckingLeft==false){
				if(!mygrid.hit(AdjacentposHortOnefound)){
					StopCheckingLeft=true;
					return AdjacentposHortTwofound=new Position(pos.row(),pos.column()+1);				
				}else if(mygrid.boatInitial(pos)!=mygrid.boatInitial(AdjacentposHortOnefound)){
					StopCheckingLeft=true;
					boolean addpos=true;
					for(int i=0;i<savethatPosition.size();i++){
						if(mygrid.boatInitial(savethatPosition.get(i))==mygrid.boatInitial(AdjacentposHortOnefound)){
							addpos=false;
						}
					}
					if(addpos){
						savethatPositionloop=false;
						savethatPosition.add(AdjacentposHortOnefound);
					}
					return AdjacentposHortOnefound=new Position(pos.row(),pos.column()+1);
				}else{
					if(CanIGoLeft(AdjacentposHortOnefound)){
						AdjacentposHortOnefound=new Position(AdjacentposHortOnefound.row(),AdjacentposHortOnefound.column()-1);
						if(mygrid.empty(AdjacentposHortOnefound
								)||mygrid.boatInitial(AdjacentposHortOnefound)==mygrid.boatInitial(hitPos)){
							return AdjacentposHortOnefound;
						}else{
							StopCheckingLeft=true;
							return AdjacentposHortTwofound=new Position(hitPos.row(),hitPos.column()+1);
						}

					}else{
						StopCheckingLeft=true;
						return AdjacentposHortTwofound=new Position(pos.row(),pos.column()+1);
					}

				}	
			}else if(StopCheckingRight==false){
				if(AdjacentposHortTwofound==null){
					return AdjacentposHortTwofound=new Position(pos.row(),pos.column()+1);
				}
				if(!mygrid.hit(AdjacentposHortTwofound)){
					return AdjacentposHortTwofound=new Position(pos.row(),pos.column()+1);
				}else if(mygrid.boatInitial(pos)!=mygrid.boatInitial(AdjacentposHortTwofound)){
					boolean addpos=true;
					for(int i=0;i<savethatPosition.size();i++){
						if(mygrid.boatInitial(savethatPosition.get(i))==mygrid.boatInitial(AdjacentposHortTwofound)){
							addpos=false;
						}
					}
					if(addpos){
						savethatPositionloop=false;
						savethatPosition.add(AdjacentposHortTwofound);
					}
					return AdjacentposHortTwofound=new Position(pos.row(),pos.column()+1);
				}else{
					return AdjacentposHortTwofound=new 
					Position(AdjacentposHortTwofound.row(),AdjacentposHortTwofound.column()+1);
				}	
			}
		}else{
			if(StopCheckingLeft==false){
				if(CanIGoLeft(pos)){
					AdjacentposHortOnefound=new Position(pos.row(),pos.column()-1);
					if(mygrid.empty(AdjacentposHortOnefound)
							||mygrid.boatInitial(AdjacentposHortOnefound)==mygrid.boatInitial(hitPos)){
						return AdjacentposHortOnefound;
					}else{
						StopCheckingLeft=true;
						AdjacentposHortTwofound=new Position(pos.row(),pos.column()+1);
						return AdjacentposHortTwofound;
					}

				}else{
					StopCheckingLeft=true;
					return AdjacentposHortTwofound=new Position(pos.row(),pos.column()+1);
				}
			}else{
				return AdjacentposHortTwofound=new Position(pos.row(),pos.column()+1);
			}
		}
		return AdjacentposHortOnefound;
	}
	private Position Search(Position hitPos,char boatInitial){
		if(boatInitial=='A'){
			sizeOfBoat=5;
		}else if(boatInitial=='B'){
			sizeOfBoat=4;
		}else if(boatInitial=='C'){
			sizeOfBoat=3;
		}else if(boatInitial=='S'){
			sizeOfBoat=3;
		}else if(boatInitial=='D'){
			sizeOfBoat=2;
		}			
		searchMethodCounter++;
		if(searchMethodCounter>1){
			if(StopChecking==false){
				if(!mygrid.hit(AdjacentposVertOnefound)){
					StopChecking=true;
					if(CanIGoDown(hitPos)){
						AdjacentposVertTwofound=new Position(hitPos.rowIndex()+1,hitPos.columnIndex());
						if(mygrid.empty(AdjacentposVertTwofound)
								||mygrid.boatInitial(AdjacentposVertTwofound)==mygrid.boatInitial(hitPos)){
							return AdjacentposVertTwofound;
						}else{
							StopCheckingDown=true;
							return horizontalSearch(hitPos, mygrid.boatInitial(pos));
						}
					}else{
						StopCheckingDown=true;
						return horizontalSearch(hitPos, mygrid.boatInitial(hitPos));
					}
				}else if(mygrid.boatInitial(hitPos)!=mygrid.boatInitial(AdjacentposVertOnefound)){
					StopChecking=true;
					boolean addpos=true;
					for(int i=0;i<savethatPosition.size();i++){
						if(mygrid.boatInitial(savethatPosition.get(i))
								==mygrid.boatInitial(AdjacentposVertOnefound)){
							addpos=false;
						}
					}
					if(addpos){
						savethatPositionloop=false;
						savethatPosition.add(AdjacentposVertOnefound);
					}
					if(CanIGoDown(hitPos)){
						AdjacentposVertTwofound=new Position(hitPos.rowIndex()+1,hitPos.columnIndex());
						if(mygrid.empty(AdjacentposVertTwofound)
								||mygrid.boatInitial(AdjacentposVertTwofound)==mygrid.boatInitial(hitPos)){
							return AdjacentposVertTwofound;
						}else{
							StopCheckingDown=true;
							return horizontalSearch(hitPos, mygrid.boatInitial(hitPos));
						}
						
					}else{
						StopCheckingDown=true;
						return horizontalSearch(hitPos, mygrid.boatInitial(hitPos));
					}
				}else{
					hitCountTillSunk++;
					if(hitCountTillSunk==sizeOfBoat){
						hitCountTillSunk=1;
						sunkCount++;
						searchMethodCounter=0;
						horizontalSearchMethodCounter=0;
						AdjacentposVertOnefound=null;
						AdjacentposVertTwofound=null;
						AdjacentposHortOnefound=null;
						AdjacentposHortTwofound=null;
						StopChecking=false;
						StopCheckingDown=false;
						StopCheckingLeft=false;
						StopCheckingRight=false;
						if(savethatPosition.size()==0){
							savethatPositionloop=true;
						}
						if(savethatPositionloop==true){
							savethatPositionloop=false;
							boolean empty=false;
							while(empty==false){
								horizontalIndex+=4;
								if(horizontalIndex>10){
									horizontalIndex-=11;
									if(horizontalIndex==0){
										horizontalIndex+=4;
									}
									verticalIndex++;
									if(verticalIndex>0x6A){
										verticalIndex=0x61;
										horizontalIndex=3;
									}
								}
								newPos=new Position(verticalIndex,horizontalIndex);
								empty=mygrid.empty(newPos);
							}
							return pos=new Position(verticalIndex,horizontalIndex);
						}else{
							savedPosIndex++;
							return savedPosition();
						}
					}
					if(CanIGoUp(hitPos)){
						if(searchMethodCounter>1){
							if(CanIGoUp(AdjacentposVertOnefound)){
								AdjacentposVertOnefound=
									new Position(AdjacentposVertOnefound.rowIndex()-1
											,AdjacentposVertOnefound.columnIndex());
								if(mygrid.empty(AdjacentposVertOnefound)
										||mygrid.boatInitial(AdjacentposVertOnefound)==mygrid.boatInitial(hitPos)){
									return AdjacentposVertOnefound;
								}else{
									StopChecking=true;
									if(CanIGoDown(hitPos)){
										AdjacentposVertTwofound=
											new Position(hitPos.rowIndex()+1
													,hitPos.columnIndex());
										if(mygrid.empty(AdjacentposVertTwofound)
												||mygrid.boatInitial
												(AdjacentposVertTwofound)==mygrid.boatInitial(hitPos)){
											return AdjacentposVertTwofound;
										}else{
											StopCheckingDown=true;
											return horizontalSearch(hitPos, mygrid.boatInitial(hitPos));
										}
									}
								}

							}else{
								AdjacentposVertTwofound=new Position(hitPos.rowIndex()+1,hitPos.columnIndex());
								StopChecking=true;
								if(mygrid.empty(AdjacentposVertTwofound)
										||mygrid.boatInitial(AdjacentposVertTwofound)==mygrid.boatInitial(hitPos)){
									return AdjacentposVertTwofound;
								}else{
									StopCheckingDown=true;
									return horizontalSearch(hitPos, mygrid.boatInitial(hitPos));
								}
							}
						}else{
							AdjacentposVertOnefound=new Position(hitPos.rowIndex()-1,hitPos.columnIndex());
							if(mygrid.empty(AdjacentposVertOnefound)
									||mygrid.boatInitial(AdjacentposVertOnefound)==mygrid.boatInitial(hitPos)){
								return AdjacentposVertOnefound;
							}else{
								StopChecking=true;
								if(CanIGoDown(hitPos)){

								}
							}
						}
					}else{
						StopChecking=true;
					}
				}
			}else if(StopCheckingDown==false){
				if(!mygrid.hit(AdjacentposVertTwofound)){
					StopCheckingDown=true;
					return horizontalSearch(hitPos,mygrid.boatInitial(hitPos));
				}else if(mygrid.boatInitial(hitPos)!=mygrid.boatInitial(AdjacentposVertTwofound)){
					StopCheckingDown=true;
					boolean addpos=true;
					for(int i=0;i<savethatPosition.size();i++){
						if(mygrid.boatInitial(savethatPosition.get(i))
								==mygrid.boatInitial(AdjacentposVertTwofound)){
							addpos=false;
						}
					}
					if(addpos){
						savethatPositionloop=false;
						savethatPosition.add(AdjacentposVertTwofound);
					}
					return horizontalSearch(hitPos,mygrid.boatInitial(hitPos));
				}else{
					hitCountTillSunk++;
					if(hitCountTillSunk==sizeOfBoat){
						hitCountTillSunk=1;
						sunkCount++;
						horizontalSearchMethodCounter=0;
						searchMethodCounter=0;	
						AdjacentposVertOnefound=null;
						AdjacentposVertTwofound=null;
						AdjacentposHortOnefound=null;
						AdjacentposHortTwofound=null;
						StopChecking=false;
						StopCheckingDown=false;
						StopCheckingLeft=false;
						StopCheckingRight=false;
						if(savethatPosition.size()==0){
							savethatPositionloop=true;
						}
						if(savethatPositionloop==true){
							savethatPositionloop=false;
							boolean empty=false;
							while(empty==false){
								horizontalIndex+=4;
								if(horizontalIndex>10){
									horizontalIndex-=11;
									if(horizontalIndex==0){
										horizontalIndex+=4;
									}
									verticalIndex++;
									if(verticalIndex>0x6A){
										verticalIndex=0x61;
										horizontalIndex=3;
									}
								}
								newPos=new Position(verticalIndex,horizontalIndex);
								empty=mygrid.empty(newPos);
							}
							return pos=new Position(verticalIndex,horizontalIndex);
						}else{
							savedPosIndex++;
							return savedPosition();
						}
					}
					if(CanIGoDown(hitPos)){
						if(searchMethodCounter>1){
							if(CanIGoDown(AdjacentposVertTwofound)){
								AdjacentposVertTwofound=
									new Position(AdjacentposVertTwofound.rowIndex()+1
											,AdjacentposVertTwofound.columnIndex());
								if(mygrid.empty(AdjacentposVertTwofound)
										||mygrid.boatInitial(AdjacentposVertOnefound)==mygrid.boatInitial(hitPos)){
									return AdjacentposVertTwofound;
								}else{
									StopCheckingDown=true;
									return horizontalSearch(hitPos,mygrid.boatInitial(hitPos));
								}
								
							}
						}
					}else{
						StopCheckingDown=true;
						return horizontalSearch(hitPos,mygrid.boatInitial(hitPos));
					}
				}
			}else if(StopCheckingLeft==false){
				if(!mygrid.hit(AdjacentposHortOnefound)){
					StopCheckingLeft=true;
					return horizontalSearch(hitPos,mygrid.boatInitial(hitPos));
				}else if(mygrid.boatInitial(hitPos)!=mygrid.boatInitial(AdjacentposHortOnefound)){
					StopCheckingLeft=true;
					boolean addpos=true;
					for(int i=0;i<savethatPosition.size();i++){
						if(mygrid.boatInitial(savethatPosition.get(i)
								)==mygrid.boatInitial(AdjacentposHortOnefound)){
							addpos=false;
						}
					}
					if(addpos){
						savethatPositionloop=false;
						savethatPosition.add(AdjacentposHortOnefound);
					}
					return horizontalSearch(hitPos,mygrid.boatInitial(hitPos));
				}else{
					hitCountTillSunk++;
					if(hitCountTillSunk==sizeOfBoat){
						hitCountTillSunk=1;
						sunkCount++;
						horizontalSearchMethodCounter=0;
						searchMethodCounter=0;
						AdjacentposVertOnefound=null;
						AdjacentposVertTwofound=null;
						AdjacentposHortOnefound=null;
						AdjacentposHortTwofound=null;
						StopChecking=false;
						StopCheckingDown=false;
						StopCheckingLeft=false;
						StopCheckingRight=false;
						if(savethatPosition.size()==0){
							savethatPositionloop=true;
						}
						if(savethatPositionloop==true){
							savethatPositionloop=false;
							boolean empty=false;
							while(empty==false){
								horizontalIndex+=4;
								if(horizontalIndex>10){
									horizontalIndex-=11;
									if(horizontalIndex==0){
										horizontalIndex+=4;
									}
									verticalIndex++;
									if(verticalIndex>0x6A){
										verticalIndex=0x61;
										horizontalIndex=3;
									}
								}
								newPos=new Position(verticalIndex,horizontalIndex);
								empty=mygrid.empty(newPos);
							}
							return pos=new Position(verticalIndex,horizontalIndex);
						}else{
							savedPosIndex++;
							return savedPosition();
						}
					}
					return horizontalSearch(hitPos,mygrid.boatInitial(hitPos));
				}
			}else if(StopCheckingRight==false){
				if(mygrid.boatInitial(hitPos)!=mygrid.boatInitial(AdjacentposHortTwofound)){
					StopCheckingRight=true;
					boolean addpos=true;
					for(int i=0;i<savethatPosition.size();i++){
						if(mygrid.boatInitial(savethatPosition.get(i)
								)==mygrid.boatInitial(AdjacentposHortTwofound)){
							addpos=false;
						}
					}
					if(addpos){
						savethatPositionloop=false;
						savethatPosition.add(AdjacentposHortTwofound);
					}
					return savedPosition();
				}else{
					hitCountTillSunk++;
					if(hitCountTillSunk==sizeOfBoat){
						hitCountTillSunk=1;
						sunkCount++;
						horizontalSearchMethodCounter=0;
						searchMethodCounter=0;
						AdjacentposVertOnefound=null;
						AdjacentposVertTwofound=null;
						AdjacentposHortOnefound=null;
						AdjacentposHortTwofound=null;
						StopChecking=false;
						StopCheckingDown=false;
						StopCheckingLeft=false;
						StopCheckingRight=false;
						if(savethatPosition.size()==0){
							savethatPositionloop=true;
						}
						if(savethatPositionloop==true){
							savethatPositionloop=false;
							boolean empty=false;
							while(empty==false){
								horizontalIndex+=4;
								if(horizontalIndex>10){
									horizontalIndex-=11;
									if(horizontalIndex==0){
										horizontalIndex+=4;
									}
									verticalIndex++;
									if(verticalIndex>0x6A){
										verticalIndex=0x61;
										horizontalIndex=3;
									}
								}
								newPos=new Position(verticalIndex,horizontalIndex);
								empty=mygrid.empty(newPos);
							}
							return pos=new Position(verticalIndex,horizontalIndex);
						}else{
							savedPosIndex++;
							return savedPosition();
						}
					}
					return horizontalSearch(hitPos,mygrid.boatInitial(hitPos));

				}
			}
		}else{
			if(CanIGoUp(hitPos)){
				AdjacentposVertOnefound=new Position(hitPos.rowIndex()-1,hitPos.columnIndex());
				if(mygrid.empty(AdjacentposVertOnefound)
						||mygrid.boatInitial(AdjacentposVertOnefound)==mygrid.boatInitial(hitPos)){
					return AdjacentposVertOnefound;
				}else{
					StopChecking=true;		
					if(CanIGoDown(hitPos)){
						AdjacentposVertTwofound=new Position(hitPos.rowIndex()+1,hitPos.columnIndex());
						if(mygrid.empty(AdjacentposVertTwofound)
								||mygrid.boatInitial(AdjacentposVertTwofound)==mygrid.boatInitial(hitPos)){
							return AdjacentposVertTwofound;
						}else{
							StopCheckingDown=true;
							if(CanIGoLeft(hitPos)){
								AdjacentposHortOnefound=new Position(hitPos.rowIndex(),hitPos.columnIndex()-1);
								if(mygrid.empty(AdjacentposHortOnefound)
										||mygrid.boatInitial(AdjacentposHortOnefound)==mygrid.boatInitial(hitPos)){
									horizontalSearchMethodCounter++;
									return AdjacentposHortOnefound;
								}else{
									StopCheckingLeft=true;
									horizontalSearchMethodCounter++;
									return AdjacentposHortTwofound=new Position(hitPos.rowIndex(),hitPos.columnIndex()+1);
								}
							}else{
								StopCheckingLeft=true;
								horizontalSearchMethodCounter++;
								return AdjacentposHortTwofound=new Position(hitPos.rowIndex(),hitPos.columnIndex()+1);
							}
						}
					}else{
						StopCheckingDown=true;
						if(CanIGoLeft(hitPos)){
							AdjacentposHortOnefound=new Position(hitPos.rowIndex(),hitPos.columnIndex()-1);
							if(mygrid.empty(AdjacentposHortOnefound)
									||mygrid.boatInitial(AdjacentposHortOnefound)==mygrid.boatInitial(hitPos)){
								horizontalSearchMethodCounter++;
								return AdjacentposHortOnefound;
							}else{
								StopCheckingLeft=true;
								horizontalSearchMethodCounter++;
								return AdjacentposHortTwofound=new Position(hitPos.rowIndex(),hitPos.columnIndex()+1);
							}
						}else{
							StopCheckingLeft=true;
							horizontalSearchMethodCounter++;
							return AdjacentposHortTwofound=new Position(hitPos.rowIndex(),hitPos.columnIndex()+1);
						}					
					}
				}
			}else{
				StopChecking=true;		
				if(CanIGoDown(hitPos)){
					AdjacentposVertTwofound=new Position(hitPos.rowIndex()+1,hitPos.columnIndex());
					if(mygrid.empty(AdjacentposVertTwofound)
							||mygrid.boatInitial(AdjacentposVertTwofound)==mygrid.boatInitial(hitPos)){
						return AdjacentposVertTwofound;
					}else{
						StopCheckingDown=true;
						if(CanIGoLeft(hitPos)){
							AdjacentposHortOnefound=new Position(hitPos.rowIndex(),hitPos.columnIndex()-1);
							if(mygrid.empty(AdjacentposHortOnefound)
									||mygrid.boatInitial(AdjacentposHortOnefound)==mygrid.boatInitial(hitPos)){								
								horizontalSearchMethodCounter++;
								return AdjacentposHortOnefound;
							}else{
								StopCheckingLeft=true;
								horizontalSearchMethodCounter++;
								return AdjacentposHortTwofound=new Position(hitPos.rowIndex(),hitPos.columnIndex()+1);
							}
						}else{
							StopCheckingLeft=true;
							horizontalSearchMethodCounter++;
							return AdjacentposHortTwofound=new Position(hitPos.rowIndex(),hitPos.columnIndex()+1);
						}
					}
				}else{
					StopCheckingDown=true;
					if(CanIGoLeft(hitPos)){
						AdjacentposHortOnefound=new Position(hitPos.rowIndex(),hitPos.columnIndex()-1);
						if(mygrid.empty(AdjacentposHortOnefound)
								||mygrid.boatInitial(AdjacentposHortOnefound)==mygrid.boatInitial(hitPos)){
							horizontalSearchMethodCounter++;
							return AdjacentposHortOnefound;
						}else{
							StopCheckingLeft=true;
							horizontalSearchMethodCounter++;
							return AdjacentposHortTwofound=new Position(hitPos.rowIndex(),hitPos.columnIndex()+1);
						}
					}else{
						StopCheckingLeft=true;
						horizontalSearchMethodCounter++;
						return AdjacentposHortTwofound=new Position(hitPos.rowIndex(),hitPos.columnIndex()+1);
					}
				}
			}
		}
		return null;
	}
	public String playerName(){	
		return "Dan Abid";		
	}
	public Position shoot(){
		if((!mygrid.hit(lastPosHit)&&okToInitialized)){
			initializeVariables();
			okToInitialized=false;
		}
		turns++;
		if(turns>1){
			if(mygrid.hit(pos)){
				okToInitialized=true;
				lastPosHit=pos;
				return Search(pos,mygrid.boatInitial(pos));
			}else{
				hitCountTillSunk=1;
				boolean empty=false;
				while(empty==false){
					horizontalIndex+=4;
					if(horizontalIndex>10){
						horizontalIndex-=11;
						if(horizontalIndex==0){
							horizontalIndex+=4;
						}
						verticalIndex++;
						if(verticalIndex>0x6A){
							verticalIndex=0x61;
							horizontalIndex=3;
						}
					}
					newPos=new Position(verticalIndex,horizontalIndex);
					empty=mygrid.empty(newPos);
				}
				return pos=new Position(verticalIndex,horizontalIndex);
			}
		}else{
			horizontalIndex+=4;
			pos=new Position(verticalIndex,horizontalIndex);
		}
		return pos;
	}
	public static void main(String[] args) {
	}
}


