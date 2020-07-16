import java.util.*;
public class node{ //purpose of the class is to be a node of the tree.
  //variables
  private node parent;
  private boolean isFirst; //is a check saying whether it is the first child of a parent allowing for it know whether it should call up to it's parent during the evalution phase.
  private int score;
  private GameBoard gamestate;
  private int turn;
  private int bestMove = 0;
  private LinkedList<node> childrenlist;
  public node(node parentint,GameBoard Gameboard){
    isFirst=false;
    parent = parentint; //setting the parents of the node. if root of the tree this value will be null
  if(parent!=null){
    if(parentint.getTurn()==0){
      turn = 1;
    }
    else{
      turn =0;
    }
    gamestate = Gameboard;
  }
  else{
    turn = GameInfo.player;
    gamestate = Gameboard; //defaults to zero as 0 is the first player always. If player is first or second will only change if the desired score is negative or positive.
  }

    childrenlist = new LinkedList<node>(); //initialising the new children list.
  }
  public void addChild(node child){
    int size = childrenlist.size();
    if(size==0){
      child.setFirst(true);
    }
    childrenlist.add(child);
  }
  public void setScore(int scoreint){
    score = scoreint;//when setting the evaluation score.
  }
  public int getScore(){
    return score;
  }
  public void setFirst(boolean value){
    isFirst = value;
  }
  public int getTurn(){
    return turn;
  }
  public play minmax(int depth,boolean max){

    if(depth==0){
      play temp = new play(gamestate.Evaluation(),gamestate.returnLastMove());
      return temp; //returns the static evaluation.
    }
    if(max == true){ //meaning that the current node is the maximising palyer.
      play maxEval = new play(-2000,0);//setting max evaluation at really small number as default.
      ListIterator it = childrenlist.listIterator();
      while(it.hasNext()){
        node temp = (node)it.next();
        play evaluation = temp.minmax(depth-1,false); //swaps to false as it is gonig to be the other players turn.
        if(depth==3){
      }
        if(evaluation.getEval()>maxEval.getEval()){
          maxEval = evaluation;
        }
        }
        return maxEval;

    }
  else{
    play minEval = new play(2000,0);//setting max evaluation at really small number as default.
    ListIterator it = childrenlist.listIterator();
    while(it.hasNext()){
      node temp = (node)it.next();
      play evaluation = temp.minmax(depth-1,true); //swaps to false as it is gonig to be the other players turn.
      if(depth==3){
    }
      if(evaluation.getEval()<minEval.getEval()){
        minEval = evaluation;
      }
      }
      return minEval;
    }

}
  /*
  public void minmax(){
    //goes through a while loop of all the children and picking the lowest or highest depending on whether turn is 1 or 0.
    int size = childrenlist.size();
    ListIterator it = childrenlist.listIterator();
    int currentMax;
    int best;
    int currentMin;
    if(size>0){//e.g there is children to check
      if(turn==1){ //gets the minimum as that's the best option for player2.
        node temp = (node)it.next();
        currentMin = temp.getScore();
        best = temp.returnBest();
        while(it.hasNext()){
          temp = (node)it.next();
          int tempint = temp.getScore();
          if(tempint<currentMin){
            currentMin = tempint;
            best = temp.returnBest();
          }
        }
        setScore(currentMin);
        setBest(best);
      }
      else{ //turn ==0 gets the maximum value as it's the best for player 1.
        node temp = (node)it.next();
        currentMax = temp.getScore();
        best = temp.returnBest();
        while(it.hasNext()){
          temp = (node)it.next();
          int tempint = temp.getScore();
          if(tempint>currentMax){
            currentMax = tempint;
            best = temp.returnBest();

          }
        }
        setBest(best);
        setScore(currentMax);
      }
  }
    if(parent!=null&&isFirst){ //e.g not root.
      parent.checkChildEvaluation();
    }
    else{
      return;
    }
  }
  */
  public void setEvaluation(){
    setScore(gamestate.Evaluation());//gets the static evaluation is only for leaf nodes.
    setBest(gamestate.returnLastMove());
  }
  public int getChildrenAmount(){
    return childrenlist.size();
  }
  public GameBoard getGame(){
    return gamestate;
  }
  public int returnBest(){
    return bestMove;
  }
  public void setBest(int best){
    bestMove = best;
  }
}
