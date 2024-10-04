package CubeSolver;
import java.util.Arrays;


public class CubeSim {
    public static void main(String[] args){
        final String[][] SOLVESTATE = {{"WBO00","WBR01","WGR02","WGO03"},{"GOW10","GRW11","GRY12","GOY13"},{"OBY20","OBW21","OGW22","OGY23"},
        {"BOY30","BRY31","BRW32","BOW33"},{"RBW40","RBY41","RGY42","RGW43"},{"YGO50","YGR51","YBR52","YBO53"}};

        String[][] cubeState = {{"WBO00","WBR01","WGR02","WGO03"},{"GOW10","GRW11","GRY12","GOY13"},{"OBY20","OBW21","OGW22","OGY23"},
        {"BOY30","BRY31","BRW32","BOW33"},{"RBW40","RBY41","RGY42","RGW43"},{"YGO50","YGR51","YBR52","YBO53"}};

        String[][] userCube = {{"WBO00","WBR01","WGR02","WGO03"},{"GOW10","GRW11","GRY12","GOY13"},{"OBY20","OBW21","OGW22","OGY23"},
        {"BOY30","BRY31","BRW32","BOW33"},{"RBW40","RBY41","RGY42","RGW43"},{"YGO50","YGR51","YBR52","YBO53"}}; //DEFAULT
        
        // String[][] userCube = {{"WBO00","WBR01","OGW22","OGY23"},{"GOY13","GOW10","GRW11","GRY12"},{"OBY20","OBW21","YGO50","YGR51"},
        // {"BOY30","BRY31","BRW32","BOW33"},{"RBW40","RBY41","WGR02","WGO03"},{"RGY42","RGW43","YBR52","YBO53"}}; // this is f
        
        // String[][] userCube = {{"WBO00", "GOW10", "YBO53", "GRY12",},{ "RGY42", "OBY20", "GOY13", "RBY41",},{"WBR01", "OBW21", "YGR51", "YBR52"},
        // {"RBW40", "GRW11", "OGW22", "BOW33"}, {"WGO03", "RGW43", "YGO50", "BOY30"},{"BRY31", "OGY23", "WGR02", "BRW32"}}; //rfdrff
        
        
        
        //----------------------------------------------- Start scramble below
        
        
        
        String scramble = "RfRfdFFDRFDRFrDfDFFDR";
        StringScramble(scramble, userCube);
        System.out.println(printCube(userCube));
        
        int count = 0;
        while(!bogoSolver(userCube,cubeState,SOLVESTATE)){
            count++;
            if(count > 999999  && count % 1000000 == 0){
                System.out.println(count);
            }
        }
        
        // for(int i = 0; i < 4; i++){
        //     userCube = PARITY; //congratulations, you just made a pointer you goon
        //     bogoSolver(userCube,cubeState,SOLVESTATE);
        // }

        
    }
    
    
    //for loop -> 20, check against SOLVESTATE every turn, random number generator 0 to 5, switch case correlating num to move
    //boolean solved, while loop(false)
    public static boolean bogoSolver(String[][] userCube, String[][] cubeState, String[][] SOLVESTATE){ 
        //userCube is cube input/reset state, 
        //cubeState is the copy to interact with
        int bogo;
        String solve = "";
        reconstructCube(cubeState, userCube);
        // System.out.println(printCube(cubeState)+"\n"); //debug
        for(int i = 0; i < 14; i++){
            if(Arrays.deepEquals(cubeState, SOLVESTATE)){
                System.out.println("\n" + printCube(cubeState));
                System.out.println(solve);
                return true;
            }
            
            bogo = (int)(Math.random()*6);
            switch (bogo) {
                case 0:
                    r(cubeState);
                    solve += "R ";
                    if(Arrays.deepEquals(cubeState, SOLVESTATE)){
                        System.out.println("\n" + printCube(cubeState));
                        System.out.println(solve);
                        return true;
                    }
                    break;
                case 1:
                    rPrime(cubeState);
                    solve += "R' "; //s
                    if(Arrays.deepEquals(cubeState, SOLVESTATE)){
                        System.out.println("\n" + printCube(cubeState));
                        System.out.println(solve);
                        return true;
                    }
                    break;
                    
                case 2:
                    f(cubeState);
                    solve += "F ";
                    if(Arrays.deepEquals(cubeState, SOLVESTATE)){
                        System.out.println("\n" + printCube(cubeState));
                        System.out.println(solve);
                        return true;
                    }
                    break;
                case 3:
                    fPrime(cubeState);
                    solve += "F' "; //x
                    if(Arrays.deepEquals(cubeState, SOLVESTATE)){
                        System.out.println("\n" + printCube(cubeState));
                        System.out.println(solve);
                        return true;
                    }
                    break;
                    
                case 4:
                    d(cubeState);
                    solve += "D ";
                    if(Arrays.deepEquals(cubeState, SOLVESTATE)){
                        System.out.println("\n" + printCube(cubeState));
                        System.out.println(solve);
                        return true;
                    }
                    break;
                case 5:
                    dPrime(cubeState);
                    solve += "D' "; // m
                    if(Arrays.deepEquals(cubeState, SOLVESTATE)){
                        System.out.println("\n" + printCube(cubeState));
                        System.out.println(solve);
                        // System.out.println(simplifySolve(solve));
                        return true;
                    }
                    break;
                }
                // System.out.println(solve+"\n"); //debug
                // System.out.println(printCube(cubeState)+"\n"); //debug
            }
            // cubeState = userCube; //debug
            // System.out.println("reset cube (should be f) \n" + printCube(cubeState)+"\n"); //debug
            return false;
        }
        
        
        public static void r(String[][] cubeState){
        //top right face
        String temp = cubeState[0][1];
        cubeState[0][1] = cubeState[1][1];
        cubeState[1][1] = cubeState[5][1];
        cubeState[5][1] = cubeState[3][1];
        cubeState[3][1] = temp;
        
        //bottom right face
        temp = cubeState[0][2];
        cubeState[0][2] = cubeState[1][2];
        cubeState[1][2] = cubeState[5][2];
        cubeState[5][2] = cubeState[3][2];
        cubeState[3][2] = temp;
        
        //side
        temp = cubeState[4][0];
        cubeState[4][0] = cubeState[4][3];
        cubeState[4][3] = cubeState[4][2];
        cubeState[4][2] = cubeState[4][1];
        cubeState[4][1] = temp;
    }
    
    // String[][] cubeState = {{"WBO00","WBR01","WGR02","WGO03"},{"GOW10","GRW11","GRY12","GOY13"},{"OBY20","OBW21","OGW22","OGY23"},
    //{"BOY30","BRY31","BRW32","BOW33"},{"RBW40","RBY41","RGY42","RGW43"},{"YGO50","YGR51","YBR52","YBO53"}};
    
    public static void rPrime(String[][] cubeState){
        //top right face
        String temp = cubeState[0][1];
        cubeState[0][1] = cubeState[3][1];
        cubeState[3][1] = cubeState[5][1];
        cubeState[5][1] = cubeState[1][1];
        cubeState[1][1] = temp;
        
        //bottom right face
        temp = cubeState[0][2];
        cubeState[0][2] = cubeState[3][2];
        cubeState[3][2] = cubeState[5][2];
        cubeState[5][2] = cubeState[1][2];
        cubeState[1][2] = temp;
        
        //side
        temp = cubeState[4][0];
        cubeState[4][0] = cubeState[4][1];
        cubeState[4][1] = cubeState[4][2];
        cubeState[4][2] = cubeState[4][3];
        cubeState[4][3] = temp;
    }
    
    // String[][] cubeState = {{"WBO00","WBR01","WGR02","WGO03"},{"GOW10","GRW11","GRY12","GOY13"},{"OBY20","OBW21","OGW22","OGY23"},
    //{"BOY30","BRY31","BRW32","BOW33"},{"RBW40","RBY41","RGY42","RGW43"},{"YGO50","YGR51","YBR52","YBO53"}};
    
    public static void f(String[][] cubeState){
        //top left corner
        String temp = cubeState[0][3];
        cubeState[0][3] = cubeState[2][3];
        cubeState[2][3] = cubeState[5][1];
        cubeState[5][1] = cubeState[4][3];
        cubeState[4][3] = temp;
        
        //front face
        temp = cubeState[1][0];
        cubeState[1][0] = cubeState[1][3];
        cubeState[1][3] = cubeState[1][2];
        cubeState[1][2] = cubeState[1][1];
        cubeState[1][1] = temp;
        
        //top right corner
        temp = cubeState[0][2];
        cubeState[0][2] = cubeState[2][2];
        cubeState[2][2] = cubeState[5][0];
        cubeState[5][0] = cubeState[4][2];
        cubeState[4][2] = temp;
    }
    
    // String[][] cubeState = {{"WBO00","WBR01","WGR02","WGO03"},{"GOW10","GRW11","GRY12","GOY13"},{"OBY20","OBW21","OGW22","OGY23"},
    //{"BOY30","BRY31","BRW32","BOW33"},{"RBW40","RBY41","RGY42","RGW43"},{"YGO50","YGR51","YBR52","YBO53"}};
    
    public static void fPrime(String[][] cubeState){ // FIX
        //top left corner
        String temp = cubeState[0][3];
        cubeState[0][3] = cubeState[4][3];
        cubeState[4][3] = cubeState[5][1];
        cubeState[5][1] = cubeState[2][3];
        cubeState[2][3] = temp;
        
        //front face
        temp = cubeState[1][0];
        cubeState[1][0] = cubeState[1][1];
        cubeState[1][1] = cubeState[1][2];
        cubeState[1][2] = cubeState[1][3];
        cubeState[1][3] = temp;
        
        //top right corner
        temp = cubeState[0][2];
        cubeState[0][2] = cubeState[4][2];
        cubeState[4][2] = cubeState[5][0];
        cubeState[5][0] = cubeState[2][2];
        cubeState[2][2] = temp;
    }
    
    // String[][] cubeState = {{"WBO00","WBR01","WGR02","WGO03"},{"GOW10","GRW11","GRY12","GOY13"},{"OBY20","OBW21","OGW22","OGY23"},
    //{"BOY30","BRY31","BRW32","BOW33"},{"RBW40","RBY41","RGY42","RGW43"},{"YGO50","YGR51","YBR52","YBO53"}};
    
    public static void d(String[][] cubeState){
        //left face
        String temp = cubeState[1][3];
        cubeState[1][3] = cubeState[2][0];
        cubeState[2][0] = cubeState[3][1];
        cubeState[3][1] = cubeState[4][2];
        cubeState[4][2] = temp;
        
        //bottom left
        temp = cubeState[5][0];
        cubeState[5][0] = cubeState[5][3];
        cubeState[5][3] = cubeState[5][2];
        cubeState[5][2] = cubeState[5][1];
        cubeState[5][1] = temp;
        
        //right face
        temp = cubeState[1][2];
        cubeState[1][2] = cubeState[2][3];
        cubeState[2][3] = cubeState[3][0];
        cubeState[3][0] = cubeState[4][1];
        cubeState[4][1] = temp;
    }
    
    // String[][] cubeState = {{"WBO00","WBR01","WGR02","WGO03"},{"GOW10","GRW11","GRY12","GOY13"},{"OBY20","OBW21","OGW22","OGY23"},
    //{"BOY30","BRY31","BRW32","BOW33"},{"RBW40","RBY41","RGY42","RGW43"},{"YGO50","YGR51","YBR52","YBO53"}};
    
    public static void dPrime(String[][] cubeState){
        //left face
        String temp = cubeState[1][3];
        cubeState[1][3] = cubeState[4][2];
        cubeState[4][2] = cubeState[3][1];
        cubeState[3][1] = cubeState[2][0];
        cubeState[2][0] = temp;
        
        //bottom left
        temp = cubeState[5][0];
        cubeState[5][0] = cubeState[5][1];
        cubeState[5][1] = cubeState[5][2];
        cubeState[5][2] = cubeState[5][3];
        cubeState[5][3] = temp;
        
        //right face
        temp = cubeState[1][2];
        cubeState[1][2] = cubeState[4][1];
        cubeState[4][1] = cubeState[3][0];
        cubeState[3][0] = cubeState[2][3];
        cubeState[2][3] = temp;
    }

    public static String printCube(String[][]cubeState){
        String printCube = "";
        
        printCube += "    " + cubeState[0][0].charAt(0) + " " + cubeState[0][1].charAt(0) + "\n";
        printCube += "    " + cubeState[0][3].charAt(0) + " " + cubeState[0][2].charAt(0) + "\n";
        
        printCube += cubeState[2][1].charAt(0) + " " + cubeState[2][2].charAt(0) + " " +
        cubeState[1][0].charAt(0) + " " + cubeState[1][1].charAt(0) + " " + 
        cubeState[4][3].charAt(0) + " " + cubeState[4][0].charAt(0) + "\n";
        
        printCube += cubeState[2][0].charAt(0) + " " + cubeState[2][3].charAt(0) + " " +
        cubeState[1][3].charAt(0) + " " + cubeState[1][2].charAt(0) + " " +
        cubeState[4][2].charAt(0) + " " + cubeState[4][1].charAt(0) + "\n";
        
        printCube += "    " + cubeState[5][0].charAt(0) + " " + cubeState[5][1].charAt(0) + "\n";
        printCube += "    " + cubeState[5][3].charAt(0) + " " + cubeState[5][2].charAt(0) + "\n";
        
        printCube += "    " + cubeState[3][0].charAt(0) + " " + cubeState[3][1].charAt(0) + "\n";
        printCube += "    " + cubeState[3][3].charAt(0) + " " + cubeState[3][2].charAt(0); 
        return printCube;
        
    }
    
    public static void reconstructCube(String[][] cubeState, String[][] userCube){
        for(int i = 0; i < 6; i++){ //yeah Im gonna hardcode this
            for(int j = 0; j < 4; j++){
                cubeState[i][j] = userCube[i][j];
            }
        }
    }
    
    public static void StringScramble(String scramble, String[][]userCube){
        for(int i = 0; i <scramble.length(); i++){
            if(scramble.charAt(i) == 'R'){
                r(userCube);
            }
            if(scramble.charAt(i) == 'r'){
                rPrime(userCube);
            }
            if(scramble.charAt(i) == 'F'){
                f(userCube);
            }
            if(scramble.charAt(i) == 'f'){
                fPrime(userCube);
            }
            if(scramble.charAt(i) == 'D'){
                d(userCube);
            }
            if(scramble.charAt(i) == 'd'){
                dPrime(userCube);
            }
        }
    }
    
    // public static String simplifySolve(String solve){
        //     //if tree
        //     //f r d f(x) r(s) d(m)
        //     String efficientSolve = "";
        //     solve.replace(" ", "");
        //     for(int i = 0; i<solve.length()-1; i++){
            //         if(solve.charAt(i) == solve.charAt(i+1)){
                //             efficientSolve += solve.charAt(i)+ "2 ";
                //         }
                //         if(solve.charAt(i) == 'F' && solve.charAt(i+1) == 'x'){
                    //             i+=2;
                    //         }
                    //         if(solve.charAt(i) == 'x' && solve.charAt(i+1) == 'F'){
                        //             i+=2;
                        //         }
                        //         if(solve.charAt(i) == 'R' && solve.charAt(i+1) == 's'){
                            //             i+=2;
                            //         }
                            //         if(solve.charAt(i) == 's' && solve.charAt(i+1) == 'R'){
                                //             i+=2;
                                //         }
                                //         if(solve.charAt(i) == 'D' && solve.charAt(i+1) == 'm'){
    //             i+=2;
    //         }
    //         if(solve.charAt(i) == 'm' && solve.charAt(i+1) == 'D'){
        //             i+=2;
        //         }
        //         else{
            //            if(solve.charAt(i) == 'x'){
                //             efficientSolve += "F' ";
                //            }
                //            if(solve.charAt(i) == 's'){
                    //             efficientSolve += "R' ";
                    //            }
                    //            if(solve.charAt(i) == 'm'){
                        //             efficientSolve += "D' ";
                        //            }
                        //         }
                        
                        //     }
                        //     return efficientSolve;
                        // }

                        // String princub = "";
                        // for(int i = 0; i<6; i++){
                        //     for(int j = 0; j< 4; j++){
                        //         princub += userCube[i][j] + " ";
                        //     }
                        // }
                        // System.out.println(princub);
                    }