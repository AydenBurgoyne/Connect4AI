import java.util.Scanner;  // Import the Scanner class
import java.util.*;
public class Interface{
    public static void main(String args[]){
      //variables:
      Tree tree;
      LinkedList<Integer> inputs = new LinkedList<Integer>();
      int player = 0; //if player 1 player will equal zero etc.
      GameBoard state = new GameBoard(player);

    while(true){
      Scanner scanner = new Scanner(System.in);  // Create a Scanner object
      String input = scanner.nextLine();
      String [] parts = input.split(" ");
      switch(parts[0]){
        case "name":
          System.out.println("enginename-c3303000");

          break;
        case "isready": //e.g only responds when talking about it.
        player = Integer.parseInt(parts[2]);
        GameInfo.player = player;
          System.out.println("readyok");
          break;
        case "position":
        LinkedList<Integer> temp = new LinkedList<Integer>();
        inputs = temp;
        if(parts.length==3){

          String [] pos = parts[2].split("");
          for(int i = 0;i<pos.length;i++){
            inputs.add(Integer.parseInt(pos[i]));
          }
          }
          break;
        case "go":
        if(inputs.size()==0){
          GameBoard gametemp = new GameBoard(player);
          state = gametemp;
        }
        else{
          GameBoard gametemp = new GameBoard(inputs,player);
          state = gametemp;
        }
        int pdepth = 4;
        tree = new Tree(pdepth,state);
        System.out.println("bestmove "+tree.getBestMove()+" "+tree.getMinMax());
        break;
        case "quit":
        System.out.println("quiting");
        break;
      case "perft":
        int depth = Integer.parseInt(parts[1]);
        LinkedList<Integer> templink = new LinkedList<Integer>();
        GameBoard gametemp = new GameBoard(templink,player);
        Tree depthTree = new Tree(depth,gametemp);
        depthTree.returnCount();
        break;
      }
    }
    }

}
