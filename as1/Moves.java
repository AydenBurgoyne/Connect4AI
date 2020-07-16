import java.util.*;

public class Moves{
  //variables
  private int freecount;
  private int ownercount;
  private int postOwnerfree;
  private boolean ownerAllowed;

  public Moves(){
    ownerAllowed = true;
    freecount = 0;
    ownercount =0;
    postOwnerfree = 0;
  }
  public Moves(int freec){
    ownerAllowed = true;
    freecount = freec;
    ownercount =0;
    postOwnerfree = 0;
  }

  public void addFree(){
    if(ownercount>0){ //checks whether the owner has started being counted
      ownerAllowed=false;
    }
    if(ownerAllowed==false){ //started to count owner so is considered postOwnerFree.This will be used to add to the next Move if it's from the owner. E.g it could possibly use those free spots.
      postOwnerfree++;
    }
    else if(ownerAllowed){ //e.g have not started counting owner than regular free spots.
    freecount++;
    }

  }
  public void addOwner(){
    if(ownerAllowed){
    ownercount++;
  }
  else{
    postOwnerfree++;
  }
  }
  public int getFree(){
    return freecount;
  }
  public int getOwner(){
    return ownercount;
  }
  public boolean isPos(){ //returns whether it's possible to make a line of 4
    if ((freecount+ownercount+postOwnerfree)>=4){
      return true;
    }
    else{
      return false;
    }
  }
  public int getPosFree(){
    return postOwnerfree;
  }
  public boolean isPostOwner(){
    return ownerAllowed;
  }
  public int getValue(){
    if(isPos()){
    if(ownercount==1){
      return 1;
    }
    else if(ownercount==2){
      return 5;
    }
    else if(ownercount==3){
      return 10;
    }
    else if(ownercount>3){
      return 1000;
    }
    else{
      return 0;
    }
  }
  else{
    return 0;
  }
}
}
