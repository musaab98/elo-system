import java.util.Calendar;

public class Player
{
   private String name;
   private int score;
   private int matches;

   public Player(String nm, int sc, int ms)
   {
      setName(nm);
      setScore(sc);
      setMatches(ms);
   }

   public void setName(String nm) {
      name = nm;
   }
   public void setScore(int sc) {
      score = sc;
   }
   public void setMatches(int ms) {
      matches = ms;
   }
   

   public String getName(){
      return name;
   }
   public int getScore(){
      return score;
   }
   public int getMatches(){
      return matches;
   }

}
