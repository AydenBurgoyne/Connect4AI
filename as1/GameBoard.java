import java.util.*;
public class GameBoard{
    //variables:
    int[][] gameboard = new int[7][6];
    int score;
    int lastMove;
    LinkedList<Integer> inputlist = new LinkedList<Integer>();
    int turn;
    public GameBoard(int turn){ //default
      score = 999;
      this.turn = turn;
      //initialise by setting all the values to 2 where two is the value given to an unselected spot.
      for(int column = 0;column<7;column++){
        for(int row = 0;row<6;row++){
          gameboard[column][row] = 2; //all values in gameboard given 2.
        }
      }
    }
    public GameBoard(LinkedList<Integer> input,int turn){ //when the gameboard state is inputted.
      this.turn = turn;
      for(int column = 0;column<7;column++){
        for(int row = 0;row<6;row++){
          gameboard[column][row] = 2; //all values in gameboard given 2.
        }
      }
      //doing inputs:
      //for loop goes through all the inputs given to the gameboard, it is assumed that the inputs will fit.
      inputlist = input;
      ListIterator it = input.listIterator();
      int player = 0;
      while(it.hasNext()){
        Integer column = (Integer)it.next();
        //getting the column from the input
        //finding the first free spot in the column
        for(int row = 5;row!=-1;row--){
          if(gameboard[column][row]==2){
            gameboard[column][row]= player;
            break;
          }
        }
        if(player==0){
          player = 1;
        }
        else{
          player = 0;
        }

      }
    }
    //methods
    //adding
    public void makeMove(int column){
      for(int row = 5;row!=-1;row--){
        if(gameboard[column][row]==2){
          gameboard[column][row]=turn;
          lastMove = column;
          inputMove(column);
          return;
        }
      }
    }
    public boolean moveLegal(int column){
      for(int row = 5;row!=-1;row--){
        if(gameboard[column][row]==2){
          return true; //a space has been found in the column.
        }
      }
      return false; //if no suitable position can be found.
    }
    public LinkedList<Integer> returnInput(){ //returns clone of the linkedlist.
      return (LinkedList<Integer>)inputlist.clone();
    }
  public int returnLastMove(){
    return lastMove;
  }
  public int Evaluation(){
    //determining if there is a draw.
      //do a bunch of shit that returns the static evaluation.
      //first check the amount of diagnoal 4s still possible.
      int b = 0;
      MovesContainer player = new MovesContainer(0);
      MovesContainer opponent = new MovesContainer(1);
      for(int i =0;i<6;i++){ //for loop is for going down the rows of the matrix.
        player.newLine();
        opponent.newLine();
        int Playercount = 0;
        int OpponentCount = 0;
        for(int g = i;g<6;g++){
          if(b<7){
            break;
          }
          int value = gameboard[b][g];
          player.add(value);
          opponent.add(value);
          if(value==0){
            Playercount++;
            OpponentCount = 0;
          }
          else if(value==1){
            Playercount = 0;
            OpponentCount++;
          }
          else if(value==2){
            Playercount = 0;
            OpponentCount = 0;
          }
          if(Playercount>3){
            return 1000;
          }
          else if(OpponentCount>3){
            return -1000;
          }
        }
        b++;
      }
      //------------------------------------Same but for columns
      for(int i =0;i<7;i++){ //for loop is for going down the rows of the matrix.
        player.newLine();
        opponent.newLine();
        int Playercount = 0;
        int OpponentCount = 0;
        int a = 0;
        for(int g = i;g<7;g++){
          if(a<6){
            break;
          }
          int value = gameboard[g][a];
          player.add(value);
          opponent.add(value);
          if(value==0){
            Playercount++;
            OpponentCount = 0;
          }
          else if(value==1){
            Playercount = 0;
            OpponentCount++;
          }
          else if(value==2){
            Playercount = 0;
            OpponentCount = 0;
          }
          if(Playercount>3){
            return 1000;
          }
          else if(OpponentCount>3){
            return -1000;
          }
          a++;
        }
      }
      //checking the amount of 4s left on the columns.
      for(int column =0;column<7;column++){
        player.newLine();
        opponent.newLine();
        int Playercount = 0;
        int OpponentCount = 0;
        for(int row = 0;row<6;row++){
          int value = gameboard[column][row];
          player.add(value);
          opponent.add(value);
          if(value==0){
            Playercount++;
            OpponentCount = 0;
          }
          else if(value==1){
            Playercount = 0;
            OpponentCount++;
          }
          else if(value==2){
            Playercount = 0;
            OpponentCount = 0;
          }
          if(Playercount>3){
            return 1000;
          }
          else if(OpponentCount>3){
            return -1000;
          }
        }
      }
      //checking the amount of 4s left of the rows
      for(int row =0;row<6;row++){
        player.newLine();
        opponent.newLine();
        int Playercount = 0;
        int OpponentCount = 0;
        for(int column = 0;column<7;column++){
          int value = gameboard[column][row];
          player.add(value);
          opponent.add(value);
          if(value==0){
            Playercount++;
            OpponentCount = 0;
          }
          else if(value==1){
            Playercount = 0;
            OpponentCount++;
          }
          else if(value==2){
            Playercount = 0;
            OpponentCount = 0;
          }
          if(Playercount>3){
            return 1000;
          }
          else if(OpponentCount>3){
            return -1000;
          }

        }
      }
      boolean draw = true;
      for(int i = 0;i<7;i++){
        if(moveLegal(i)){  //if any move if legal than not a draw
          draw = false;
          break;
        }
      }
      if(draw){
        return 0;
      }
      //deciding the value;
        score = (player.getTotal()) - (opponent.getTotal()); //reason being is because if opponent has less lines and player is player 1 then they are winning and should have a bigger score.
      return score;
   }
    private void inputMove(int column){
      inputlist.add(column);
    }


    //print out move list.
}
