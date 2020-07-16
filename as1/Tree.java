import java.util.*;
public class Tree{
  //variables
  private node root;
  private LinkedList<node> leafNodes;
  private int maxdepth;
  private GameBoard state;
  private int nodeCount;
  public Tree(int depthset,GameBoard currentState){
    maxdepth = depthset;
    nodeCount = 1; //1 for the root.
    leafNodes = new LinkedList<node>();
    state = currentState;
    TreeCreation();
    minMax();
  }
  public void TreeCreation(){
    root = new node(null,state);
    treeAdd(root,0);
  }
  public void treeAdd(node current,int recursivedepth){
    if(recursivedepth==maxdepth){
      leafNodes.add(current);
      return;
    }
    if(current.getChildrenAmount()==0){
      recursivedepth++; //maybe a problem if
    }
    GameBoard temp = current.getGame();
    int turn = current.getTurn();
    if(turn==0){
      turn = 1;
    }
    else{
      turn = 0;
    }
    for(int i=0;i<7;i++){
      LinkedList<Integer> list = temp.returnInput();
      GameBoard newTemp = new GameBoard(list,turn); //creating the new game board with the list.
      if(newTemp.moveLegal(i)){ //column is not full
        newTemp.makeMove(i);
        node newChild = new node(current,newTemp);
        nodeCount++; //adds another node to the total count;
        current.addChild(newChild);
        treeAdd(newChild,recursivedepth); //keeps the recursion going.
      }
      else{ //if the column is full
        continue;
      }
    }

  }
  //does the static evaluation on the leaf nodes of the tree.
  public void Evaluation(){
    ListIterator it = leafNodes.listIterator();
    while(it.hasNext()){
      node temp = (node)it.next();
      temp.setEvaluation();
    }
  }
  public void minMax(){
    boolean it = false;
    if(GameInfo.player==0){
       it = true;
    }
    play output = root.minmax(maxdepth-1,it);
    root.setScore(output.getEval());
    root.setBest(output.getMove());

  }
  public int getMinMax(){
    return root.getScore();
  }
  public int getBestMove(){
    return root.returnBest();
  }
  public void returnCount(){
    System.out.println("perft "+maxdepth+" "+nodeCount);
  }
}
