package com.company;


import java.util.ArrayList;
import java.util.Collections;

public class BracketTree {
    TeamNode root;
    public static ArrayList<String> path = new ArrayList<String>();

    // TeamNode class
    static class TeamNode {
        String teamName;
        TeamNode left, right;
        //Constructor
        TeamNode(String data) {
            this.teamName = data;
            this.left = null;
            this.right = null;
        }
    }

    // Function to build the bracket from the given input
    public TeamNode BuildBracket(ArrayList<String> arr, TeamNode root,
                                 int i) {
        // Base case for recursion
        if (i < arr.size()) {
            TeamNode temp = new TeamNode(arr.get(i));
            root = temp;

            // insert left child
            root.left = BuildBracket(arr, root.left,
                    2 * i + 1);

            // insert right child
            root.right = BuildBracket(arr, root.right,
                    2 * i + 2);
        }
        return root;
    }

    //Function to count all the teams in the Bracket
    int countTeams(TeamNode root) {
        //Base case
        if (root == null)
            return (0);

        return (1 + countTeams(root.left) + countTeams(root.right));
    }

    //Function to determine if the bracket is fully filled out or not
    boolean isBracketComplete(TeamNode root){
        //127 is the number of nodes in a fully filled bracket
        if (countTeams(root) == 127){
            return true;
        }
        else{
            return false;
        }
    }

   //Function to return the champion of the bracket
    TeamNode findChampion(TeamNode root){
        //Champion is just the root teamNode of the tree
        if (isBracketComplete(root)){
            return root;
        }else{
            return null;
        }

    }

    //Function to first determine if a path is available
    public  boolean hasPathToVicorty(TeamNode root, String team)
    {
        // if root is NULL
        // there is no path
        if (root==null)
            return false;

        // if the right child team does not equal the team we are looking for then that is opponent that
        // the champion beat, so add it to the path
        if (root.right != null && !root.right.teamName.equalsIgnoreCase(team)) {
            path.add(root.right.teamName);
        }
        // if the left child team does not equal the team we are looking for then that is opponent that
        // the champion beat, so add it to the path
        if (root.left != null && !root.left.teamName.equalsIgnoreCase(team)) {
            path.add(root.left.teamName);
        }
        // if the team has been found and it is the furthest occurrence(first round)
        // return true
        if (root.teamName.equalsIgnoreCase(team) && root.left == null && root.right == null)
            return true;

        // else check whether the required node lies
        // in the left subtree or right subtree of
        // the current node
        if (hasPathToVicorty(root.left, team) ||
                hasPathToVicorty(root.right, team))
            return true;

        // remove current node if it is not in right or left subtree of current node
        path.remove(path.size()-1);
        return false;
    }

    public void getPathToVoctory(TeamNode root, String team)
    {
        if(isBracketComplete(root)){

            // if team is found then print the path
            if (hasPathToVicorty(root, team))
            {
                //Reverse the array to get the path in the right order
                Collections.reverse(path);
                for (int i=0; i<path.size()-1; i++){
                    System.out.print(path.get(i)+"-> ");
                }
                //Print the last team without the arrow
                System.out.print(path.get(path.size() - 1));
                System.out.println("");
            }


            //  team is not in the bracket
            else
                System.out.println("No Path");
        }else{
            System.out.println("Cannot get champ path, Bracket not complete");
        }
    }

}
