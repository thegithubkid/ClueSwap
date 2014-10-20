package clueGame;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class ComputerPlayer extends Player{
	
	private char lastRoomVisited;
	
	public ComputerPlayer(){
		
	}
	public ComputerPlayer(String name, String color, int row, int col){
		super();
		this.name=name;
		setPlayerColor(color);
		this.colorString=color;
		this.row=row;
		this.col=col;
		//this.boardCell=
	}
	@Override
	public BoardCell pickLocation(Set<BoardCell> targets){
		BoardCell nextLocation=null;
		//pick random target number
		int randomCardNum = (int)(Math.random()*targets.size());
		int i=0;
		//look for random target number
		for(BoardCell cell: targets){
			if(cell.isRoom()){
				RoomCell room=(RoomCell)cell;
				if(room.getInitial()!=lastRoomVisited){
					nextLocation=cell;
					this.lastRoomVisited=room.getInitial();
					break;
				}
				if(i == randomCardNum){
					nextLocation = cell;
				}
			}else if(i == randomCardNum){
				nextLocation = cell;
			}
			i++;
		}
		
		return nextLocation;
	}
	public String[] createSuggestion(HashMap<String, Card> allCards, Board board){
		
		ArrayList<Card> alreadyGuessedCards = new ArrayList<Card>();
		ArrayList<Card> personCards = new ArrayList<Card>();
		ArrayList<Card> weaponCards = new ArrayList<Card>();
		
		String[] suggestions= new String[3];
		
		for(Card card: myCards)
		{
			System.out.println("mycard ....." +card.getName());
			if(card.getCardType() == CardType.ROOM)
			{
				
			}
			else if(!alreadyGuessedCards.contains(card))
			{
				alreadyGuessedCards.add(card);
			}
			
		}
		for(Card card: shownCards)
		{
			System.out.println("shown card ....." +card.getName());
			if(card.getCardType() == CardType.ROOM)
			{
				
			}
			else if(!alreadyGuessedCards.contains(card))
			{
				alreadyGuessedCards.add(card);
			}
		}
		
		Iterator it = allCards.entrySet().iterator();
	    while (it.hasNext()) 
	    {
	        Map.Entry pairs = (Map.Entry)it.next();
	        
		        if(((Card) pairs.getValue()).getCardType() == CardType.PERSON)
		        {
		        	personCards.add((Card) pairs.getValue());
		        }
		        else if(((Card) pairs.getValue()).getCardType() == CardType.WEAPON)
		        {
		        	weaponCards.add((Card) pairs.getValue());
		        }
		       //System.out.println(pairs.getKey() + " = " + pairs.getValue());
		        it.remove(); 
	       
	    }
	    
	    for(int i=0; i<alreadyGuessedCards.size(); i++)
	    {
	    	System.out.println("********" + alreadyGuessedCards.get(i).getName());
	    	
	    }
	    for(int i=0; i<shownCards.size(); i++)
	    {
	    	System.out.println("------------" + shownCards.get(i).getName());
	    	
	    }
	    
	   
	    for(int j=0; j<alreadyGuessedCards.size(); j++)
	    {
	    	
	    	 for(int i=0; i<personCards.size(); i++)
	    	 {
	    		
	    		if(personCards.get(i).getName().equals(alreadyGuessedCards.get(j).getName()))
	    		{
	    			
	    			System.out.println("removing " + personCards.get(i).getName());
	    			personCards.remove(i);
	    			System.out.println("after remove");
	    			
	    			
	    		}
	    		
	    	}
	    }
	    for(int i=0; i<weaponCards.size(); i++)
	    {
	    	for(Card card2: alreadyGuessedCards)
	    		if(weaponCards.get(i).getName().equals(card2.getName()))
	    		{
	    			weaponCards.remove(weaponCards.get(i));
	    			break;
	    		}
	    }
	    int randomCardNum = (int)(Math.random()*personCards.size());
		suggestions[0] = personCards.get(randomCardNum).getName();
		
		randomCardNum = (int)(Math.random()*personCards.size());
		suggestions[1] = weaponCards.get(randomCardNum).getName();
		
		char roomInitial = board.getRoomCellAt(row, col).getInitial();
		switch(roomInitial)
		{
			case 'L':
				suggestions[2] = "Library";
				break;
			case 'R':
				suggestions[2] = "Billiard Room";
				break;
			case 'S':
				suggestions[2] = "Study";
				break;
			case 'K':
				suggestions[2] = "Kitchen";
				break;
			case 'D':
				suggestions[2] = "DiningRoom";
				break;
			case 'O':
				suggestions[2] = "Lounge";
				break;
			case 'B':
				suggestions[2] = "Ballroom";
				break;
			case 'C':
				suggestions[2] = "Conservatory";
				break;
			case 'H':
				suggestions[2] = "Hall";
				break;
			case 'X':
				suggestions[2] = "Closet";
				break;
			default:
				System.out.println("problem in creatSuggestion");
				break;
		}
	    
		return suggestions;
		
	}
	public void setLastRoomVisited(char room){
		lastRoomVisited=room;
	}
}
