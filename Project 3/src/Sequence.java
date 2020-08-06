/*
* File: Sequence.java
* Author: John Kucera
* Date: April 23, 2019
* Purpose: This java program contains 3 public methods. The first one computes
* the values of a sequence 0 1 2 5 12 29... in iterative method. The second one
* computes the same thing using recursion. The third method returns the efficiency
* counter left behind by previous calls for either of these two computation
* methods.
*/

public class Sequence {
    // Variable initialization
    private static int eff;
    private static int num;

    // Iterative Method
    public static int computeIterative(int input) {
        // Variable initialize
        eff = 0;
        int n1 = 1;
        int n2 = 0;
        
        // Getting result
        switch (input) {
            case 0:
                return 0;
            case 1:
                return 1;
            default:
                for (int i = 1; i < input; i++) {
                    num = (n1 * 2) + n2;
                    n2 = n1;
                    n1 = num;
                    eff++;
                }
                return num;
        }// end of switch
    } // end of method
    
    // Real Recursive Method (for clearing efficiency value)
    public static int computeRecursive(int input) {
        eff = 0;
        return recursive(input);
    } // end of method
    
    //Recursive Method
    public static int recursive(int input) {
        eff++;
        if (input == 0) {
            return 0;
        }
        else if (input == 1) {
            return 1;
        }
        else {
            num = (2 * recursive(input - 1)) + recursive(input - 2);
            return num;
        }
    } // end of method
    
    // Efficiency Counter
    public static int getEfficiency() {
        return eff;
    }
} // end of class
