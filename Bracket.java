import java.util.Arrays;
import javax.swing.*;
import java.awt.*;
import java.awt.Dimension;
import java.awt.event.*;
import java.util.Scanner;
import java.io.*;

public class Bracket extends JPanel{

   private JPanel myPanel;
   private JLabel lblTitle, lblWinners, lblLosers, lblGrand;
   private JComboBox[] cmbPlayer = new JComboBox[31];
   private JButton btnSave, btnCancel;
   private String[] playerList;
   private static String Name, Name2;
   
   public Bracket(){
   
   // Creating the elements
      lblTitle = new JLabel("8 MAN BRACKET");
      lblGrand = new JLabel("Winner:  ");
      lblWinners = new JLabel("Winners Bracket");
      lblLosers = new JLabel("Losers Bracket");
      btnSave = new JButton("Save and Exit");
      btnSave.addActionListener(new SavePressed());
      btnCancel = new JButton("Cancel");
      btnCancel.addActionListener(new CancelPressed());
   // Combo Box
      int notEmpty = 0;
      for(int x = 0; x < elo.numPlayers; x++){
         if(!elo.allPlayers[x].getName().equalsIgnoreCase("EMPTY")){
            notEmpty++;
         }
      }
      notEmpty++;
      playerList = new String[notEmpty];
      System.out.println("" + notEmpty);
      int i = 1;
      playerList[0] = ("");
      for(int x = 0; x < elo.numPlayers; x++){
         if(!elo.allPlayers[x].getName().equalsIgnoreCase("EMPTY")){
            playerList[i] = elo.allPlayers[x].getName();
            i++;
         }
      }
      for(int x = 0; x < 31; x++){
         cmbPlayer[x] = new JComboBox(playerList);
      }
      
      setLayout(new GridBagLayout());
      GridBagConstraints gc = new GridBagConstraints();
      
   // Adding the Elements
      gc.weightx = 1;
      gc.weighty = 1;
      gc.gridx = 2;
      gc.gridy = 0;
      add(lblTitle, gc);
      lblTitle.setFont(new Font("Comic Sans", Font.PLAIN, 32));
      lblTitle.setForeground(Color.MAGENTA);
      
      gc.gridy = 1;
      add(lblWinners, gc);
      
      gc.gridx = 3;
      gc.anchor = GridBagConstraints.EAST;
      add(lblGrand, gc);
      
      gc.gridx = 4;
      gc.anchor = GridBagConstraints.WEST;
      add(cmbPlayer[30], gc);
      
      gc.anchor = GridBagConstraints.CENTER;
      gc.gridy = 8;
      gc.gridx = 2;
      add(lblLosers, gc);
      
      gc.gridx = 3;
      add(btnSave, gc);
      
      gc.gridx = 4;
      add(btnCancel, gc);
      
   // Column 1 Winners Bracket
      gc.gridx = 0;
      gc.weightx = 1;
      gc.weighty = 1;
      for(int x = 0; x < 8; x++){
         if(x % 2 == 0)
            gc.anchor = GridBagConstraints.SOUTH;
         else
            gc.anchor = GridBagConstraints.NORTH;
         gc.gridy = x + 1;
         add(cmbPlayer[x], gc);
      }
   // Column 1 Losers Bracket
      for(int x = 8; x < 12; x++){
         if(x % 2 == 0)
            gc.anchor = GridBagConstraints.SOUTH;
         else
            gc.anchor = GridBagConstraints.NORTH;
         gc.gridy = x + 1;
         add(cmbPlayer[x], gc);
      }
   // Column 2 Winners Bracket
      gc.gridx = 1;
      gc.anchor = GridBagConstraints.SOUTH;
      gc.gridy = 2;
      add(cmbPlayer[12], gc);
      gc.anchor = GridBagConstraints.NORTH;
      gc.gridy = 3;
      add(cmbPlayer[13], gc);
      gc.anchor = GridBagConstraints.SOUTH;
      gc.gridy = 6;
      add(cmbPlayer[14], gc);
      gc.anchor = GridBagConstraints.NORTH;
      gc.gridy = 7;
      add(cmbPlayer[15], gc);
   // Column 2 Losers Bracket
      for(int x = 16; x < 20; x++){
         if(x % 2 == 0)
            gc.anchor = GridBagConstraints.SOUTH;
         else
            gc.anchor = GridBagConstraints.NORTH;
         gc.gridy = x - 7;
         add(cmbPlayer[x], gc);
      }
   // Column 3 Winners Bracket
      gc.gridx = 2;
      gc.anchor = GridBagConstraints.SOUTH;
      gc.gridy = 4;
      add(cmbPlayer[20], gc);
      gc.anchor = GridBagConstraints.NORTH;
      gc.gridy = 5;
      add(cmbPlayer[21], gc);
   // Column 3 Losers Bracket
      gc.anchor = GridBagConstraints.SOUTH;
      gc.gridy = 10;
      add(cmbPlayer[22], gc);
      gc.anchor = GridBagConstraints.NORTH;
      gc.gridy = 11;
      add(cmbPlayer[23], gc);
   // Column 4 Winners Bracket
      gc.gridx = 3;
      gc.anchor = GridBagConstraints.SOUTH;
      gc.gridy = 4;
      add(cmbPlayer[24], gc);
      gc.anchor = GridBagConstraints.NORTH;
      gc.gridy = 5;
      add(cmbPlayer[25], gc);
   // Column 4 Losers Bracket
      gc.anchor = GridBagConstraints.SOUTH;
      gc.gridy = 10;
      add(cmbPlayer[26], gc);
      gc.anchor = GridBagConstraints.NORTH;
      gc.gridy = 11;
      add(cmbPlayer[27], gc);
   // Column 5 Grand Finals Set 2
      gc.gridx = 4;
      gc.anchor = GridBagConstraints.SOUTH;
      gc.gridy = 4;
      add(cmbPlayer[28], gc);
      gc.anchor = GridBagConstraints.NORTH;
      gc.gridy = 5;
      add(cmbPlayer[29], gc);
      
   
   
   }
   
   
   private class SavePressed implements ActionListener{
      private int Score, Score2, KA, KB, MA, MB, xp, yp;
      private double SA, SB, TA, TB, QA, QB, EA, EB;
      private String Name, Name2, temp;
      public void actionPerformed(ActionEvent e){
         int x = 0;
         for(int i = 0; i < 30; i = i + 2){
         // Finding matches
            if(x == 0){
               Name = (String)cmbPlayer[0].getSelectedItem();
               Name2 = (String)cmbPlayer[1].getSelectedItem();
               if(Name2.equalsIgnoreCase((String)cmbPlayer[12].getSelectedItem())){
                  temp = Name;
                  Name = Name2;
                  Name2 = temp;
               }
            }
            else if(x == 1){
               Name = (String)cmbPlayer[2].getSelectedItem();
               Name2 = (String)cmbPlayer[3].getSelectedItem();
               if(Name2.equalsIgnoreCase((String)cmbPlayer[13].getSelectedItem())){
                  temp = Name;
                  Name = Name2;
                  Name2 = temp;
               }
            }
            else if(x == 2){
               Name = (String)cmbPlayer[4].getSelectedItem();
               Name2 = (String)cmbPlayer[5].getSelectedItem();
               if(Name2.equalsIgnoreCase((String)cmbPlayer[14].getSelectedItem())){
                  temp = Name;
                  Name = Name2;
                  Name2 = temp;
               }
            }
            else if(x == 3){
               Name = (String)cmbPlayer[6].getSelectedItem();
               Name2 = (String)cmbPlayer[7].getSelectedItem();
               if(Name2.equalsIgnoreCase((String)cmbPlayer[15].getSelectedItem())){
                  temp = Name;
                  Name = Name2;
                  Name2 = temp;
               }
            }
            else if(x == 4){
               Name = (String)cmbPlayer[8].getSelectedItem();
               Name2 = (String)cmbPlayer[9].getSelectedItem();
               if(Name2.equalsIgnoreCase((String)cmbPlayer[16].getSelectedItem())){
                  temp = Name;
                  Name = Name2;
                  Name2 = temp;
               }
            }
            else if(x == 5){
               Name = (String)cmbPlayer[10].getSelectedItem();
               Name2 = (String)cmbPlayer[11].getSelectedItem();
               if(Name2.equalsIgnoreCase((String)cmbPlayer[18].getSelectedItem())){
                  temp = Name;
                  Name = Name2;
                  Name2 = temp;
               }
            }
            else if(x == 6){
               Name = (String)cmbPlayer[12].getSelectedItem();
               Name2 = (String)cmbPlayer[13].getSelectedItem();
               if(Name2.equalsIgnoreCase((String)cmbPlayer[20].getSelectedItem())){
                  temp = Name;
                  Name = Name2;
                  Name2 = temp;
               }
            }
            else if(x == 7){
               Name = (String)cmbPlayer[14].getSelectedItem();
               Name2 = (String)cmbPlayer[15].getSelectedItem();
               if(Name2.equalsIgnoreCase((String)cmbPlayer[21].getSelectedItem())){
                  temp = Name;
                  Name = Name2;
                  Name2 = temp;
               }
            }
            else if(x == 8){
               Name = (String)cmbPlayer[16].getSelectedItem();
               Name2 = (String)cmbPlayer[17].getSelectedItem();
               if(Name2.equalsIgnoreCase((String)cmbPlayer[22].getSelectedItem())){
                  temp = Name;
                  Name = Name2;
                  Name2 = temp;
               }
            }
            else if(x == 9){
               Name = (String)cmbPlayer[18].getSelectedItem();
               Name2 = (String)cmbPlayer[19].getSelectedItem();
               if(Name2.equalsIgnoreCase((String)cmbPlayer[23].getSelectedItem())){
                  temp = Name;
                  Name = Name2;
                  Name2 = temp;
               }
            }
            else if(x == 10){
               Name = (String)cmbPlayer[20].getSelectedItem();
               Name2 = (String)cmbPlayer[21].getSelectedItem();
               if(Name2.equalsIgnoreCase((String)cmbPlayer[24].getSelectedItem())){
                  temp = Name;
                  Name = Name2;
                  Name2 = temp;
               }
            }
            else if(x == 11){
               Name = (String)cmbPlayer[22].getSelectedItem();
               Name2 = (String)cmbPlayer[23].getSelectedItem();
               if(Name2.equalsIgnoreCase((String)cmbPlayer[26].getSelectedItem())){
                  temp = Name;
                  Name = Name2;
                  Name2 = temp;
               }
            }
            else if(x == 12){
               Name = (String)cmbPlayer[24].getSelectedItem();
               Name2 = (String)cmbPlayer[25].getSelectedItem();
               if(Name2.equalsIgnoreCase((String)cmbPlayer[28].getSelectedItem())){
                  temp = Name;
                  Name = Name2;
                  Name2 = temp;
               }
            }
            else if(x == 13){
               Name = (String)cmbPlayer[26].getSelectedItem();
               Name2 = (String)cmbPlayer[27].getSelectedItem();
               if(Name2.equalsIgnoreCase((String)cmbPlayer[25].getSelectedItem())){
                  temp = Name;
                  Name = Name2;
                  Name2 = temp;
               }
            }
            else if(x == 14){
               Name = (String)cmbPlayer[28].getSelectedItem();
               Name2 = (String)cmbPlayer[29].getSelectedItem();
               if(Name2.equalsIgnoreCase((String)cmbPlayer[30].getSelectedItem())){
                  temp = Name;
                  Name = Name2;
                  Name2 = temp;
               }
            }
            
            for(int y = 0; y < elo.numPlayers; y++){
               if(Name.equalsIgnoreCase(elo.allPlayers[y].getName()))
                  xp = y;
               else if(Name2.equalsIgnoreCase(elo.allPlayers[y].getName()))
                  yp = y;
            }
            // elo formula
            if(Name.equalsIgnoreCase(elo.allPlayers[xp].getName()) && Name2.equalsIgnoreCase(elo.allPlayers[yp].getName())){
               System.out.println("" + Name + " defeated " + Name2 + ".");
               Score = elo.allPlayers[xp].getScore(); Score2 = elo.allPlayers[yp].getScore();
               SA = Score; SB = Score2;
               MA = elo.allPlayers[xp].getMatches(); MB = elo.allPlayers[yp].getMatches();
               KA = 16 - MA; KB = 16 - MB;
               if(KA < 8)
                  KA = 8;
               if(KB < 8)
                  KB = 8;
               TA = SA/400; TB = SB/400;
               QA = Math.pow(10, TA); QB = Math.pow(10, TB);
               EA = QA/(QA + QB); EB = QB/(QB + QA);
               System.out.println("" + Name + "'s Old Score: " + Score + ".   " + Name2 + "'s Old Score: " + Score2);
               SA = SA + KA*(2 - EA);
               SB = SB - KB*(2 - EA);
               if(SA < 1)
                  SA = 1;
               if(SB < 1)
                  SB = 1;
               Score = (int) Math.round(SA); Score2 = (int) Math.round(SB);
               MA++; MB++;
               elo.allPlayers[xp].setMatches(MA); elo.allPlayers[yp].setMatches(MB);
               elo.allPlayers[xp].setScore(Score); elo.allPlayers[yp].setScore(Score2);
               System.out.println("" + Name + "'s New Score: " + Score + ".   " + Name2 + "'s New Score: " + Score2);
               System.out.println("" + Name + " Played " + MA + " Games.   " + Name2 + " Played " + MB + " Games.");
               System.out.println();
               System.out.println();
            }
            x++;
         }
         elo.frame.setVisible(false);
         elo.frame.dispose();
      }
   }
   
   private class CancelPressed implements ActionListener{
      public void actionPerformed(ActionEvent e){
         elo.frame.setVisible(false);
         elo.frame.dispose();
      }
   }
   
        
}