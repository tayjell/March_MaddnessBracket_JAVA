package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ArrayList<String> bracketArray = new ArrayList<>();
        ArrayList<String> bracketHeaders = new ArrayList<>(Arrays.asList("Champion:", "Finals:", "Final Four:", "Elite Eight:", "Sweet Sixteen:", "Round Of 32:", "Round Of 64:", ""));


        //Get filled out bracket from bracket Data file
        try {
            Scanner readSccanner = new Scanner(new File("./BracketData.txt"));
            while (readSccanner.hasNextLine()) {
                String line = readSccanner.nextLine();
                if (bracketHeaders.contains(line) != true) {
                    //System.out.println(line.strip());
                    bracketArray.add(line);
                }
            }
            readSccanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //Create bracket instance and load the bracket with data from the file.
        BracketTree marchMadBracket = new BracketTree();
        marchMadBracket.root = marchMadBracket.BuildBracket(bracketArray, marchMadBracket.root, 0);

        while(true){
            Scanner inputScanner = new Scanner(System.in);

            System.out.print("Enter a command: ");
            String input = inputScanner.next();


            //Handle the input
            if(input.equalsIgnoreCase("isComp")) {
                boolean compResult = marchMadBracket.isBracketComplete(marchMadBracket.root);
                if (compResult == true){
                    System.out.println("Bracket is complete!");
                }else{
                    System.out.println("Bracket is not complete");
                }
            }else if (input.equalsIgnoreCase("champ")){
                BracketTree.TeamNode champNode = marchMadBracket.findChampion(marchMadBracket.root);
                if (champNode == null){
                    System.out.println("No Champion found. Bracket is not complete.");
                }else{
                    System.out.println("Your champion is: " + champNode.teamName);
                }
            }
            else if (input.equalsIgnoreCase("pathToVictory")){
                marchMadBracket.getPathToVoctory(marchMadBracket.root,"Duke");
            }
            else{
                System.out.println("That option is not available. Please try again with one of these values:");
                System.out.println("iscomp");
                System.out.println("champ");
                System.out.println("pathtovictory");
            }

        }



    }
}
