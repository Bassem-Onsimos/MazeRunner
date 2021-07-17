
package Controller;

import java.util.ArrayList;
import java.util.List;

public class CareTaker {
   private List<Memento> mementoList = new ArrayList<>();

   public void add(Memento score){
      mementoList.add(score);
   }

   public Memento get(int index){
      return mementoList.get(index);
   }
   
}