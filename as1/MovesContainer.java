import java.util.*;
public class MovesContainer{ //purpose is to track all the possible moves
  //variables:
  private LinkedList<Moves> moveslist;
  private Moves temp;
  private int player;
  private LinkedList<Moves> currentMoves;
  public MovesContainer(int playerint){
    player = playerint; //whether it's recording player 1 or player 0 possible moves.
    moveslist = new LinkedList<Moves>();
    temp = new Moves();//current tracked move.
    currentMoves = new LinkedList<Moves>();
  }
  public void newLine(){ //when new diagnoal or whatever is being explored it resets the temp or puts it in linkedlist if it makes a partial line.
    ListIterator it =currentMoves.listIterator();
    Moves li;
    while(it.hasNext()){
      li = (Moves)it.next();
      if(li.isPos()){
        moveslist.add(li);//adds li to the finished moves list.
      }
    }
    currentMoves.clear();
    temp = null;
    temp = new Moves();
    currentMoves.add(temp);
  }
  public void add(int value){ //adds to the list depending on the value;
    ListIterator it =currentMoves.listIterator();
    if(value==player){
      if(temp.isPostOwner()){
        Moves temp2 = new Moves(temp.getPosFree()); //initialises teh new move is the free spots after the owner on the previous one.
        temp = temp2;
        currentMoves.add(temp);
        it =currentMoves.listIterator();
        Moves li;
        while(it.hasNext()){
          li = (Moves)it.next();
          li.addOwner();
        }
      }
    }
    else if(value==2){
      Moves li;
      while(it.hasNext()){
        li = (Moves)it.next();
        li.addFree();
      }
    }
    else{ //e.g value belongs to opponnent then check if there is a possible move and if so then add to linkedlist else reset temp.
      newLine();
    }
  }
  public int getTotal(){ //returns total eval\
    newLine();
    ListIterator it = moveslist.listIterator();
    Moves li;
    int total = 0;
    while(it.hasNext()){
      li = (Moves)it.next();
      if(li.isPos()){
      if(li.getValue()>500){
        return 1000;
      }
      total += li.getValue();
    }
    }
    return total;
  }
}
