import  java.lang.Iterable;
/*
 * This is a simple encryption program, it takes input from the user through
 * the standard input stream, checks if the input data is correct and 
 * encrypts it using cipher shift encryption also known as caeser shift
 * Please note that this is not at production level
 * It has been written by a Computer science student.
 */

/**
 *
 * @author Dawood Morris F Kaundama : He is a Computer Science Student at University of the People.
 */
public class Cryptography {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // declare some variables taht we might find useful
        String plainText = "";
        System.out.println("Welcome! We gonna do some kind of Caeser Cipher.\n");
        System.out.println("Especially the simple Shift Cipher. \nYou gonna have to provide a string of characters "
                + "or a sentence. \nThen we gonna encrypt it so that it is unreadable by intruders!");
        System.out.println("Please enter your string:");
        plainText = TextIO.getln();
        //just to confirm that the string entered successfully, show the user
        System.out.println("You entered the text:  \"" + plainText + "\".\n");
        System.out.println("Please wait while we encrypt your text...");
        
        // The alphabet acts as our basic reference when we are working with the plain text from the user
        char[] alphabetLetters = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
        
        // The alphabetic numerals act likewise the alphabet and works simultaneously with it, especially usefull
        // when we will be translating the string to their corresponding numerals or digits in the alphabet.
        int[] alphaNumerals = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25};
        
       String lwcsPlainText = plainText.toLowerCase(); // if the we received a mixed case string 
        
        
        // an array to store translated numeric string 
        
        int[] translatedNumStr = new int[lwcsPlainText.length()];
        int tNumStrCt = 0; // spot count for translatedNumStr
        
        char[] iterableString = new char[lwcsPlainText.length()];
        int itrbleCt = 0; // fot iterable spot count
        
        // to convert the entered string into itarable array-like
        for ( int i = 0; i < lwcsPlainText.length(); i++){   
        iterableString[itrbleCt] = lwcsPlainText.charAt(i); // write to iterableString[]
        itrbleCt++; // at this end this variable holds 6
                } //end for
        
        // Here letterFrmPT stands for letterFromPlainText , one letter from the entered string at a time and
        // compare it to ech and every letter in our alphabet, if we find a match, we keep the corresponding integer from the
        // alphaNumerals and append the integer to another numeric  string
        for (char letterFrmPT : iterableString){
            for ( int index = 0; index < alphabetLetters.length && index < alphaNumerals.length; index++){
                if( letterFrmPT == alphabetLetters[index]){
                    translatedNumStr[tNumStrCt] = alphaNumerals[index];
                    tNumStrCt++;
                    break; // if we find a match break out of the loop to process another value from iterableString[]
                }else{
                  translatedNumStr[tNumStrCt] = 0; // reset if not equal  
                }
            }// end for
        }// end for each loop
        
        // if the above code is right  then we have something like this in memory
        // translatedNumStr[] = {'12','4','8','12','23','16','13','12','11' };
        // of course according to the input data from user
        
        
        int[] shifted = cipherShiftEncrypt(translatedNumStr);
        
        System.out.println("Seccess! We have successfully encrypted your text!");
        System.out.println("Warning: To Decrypt it you need an decryption key!");
        System.out.println("Do you want to see the encryption key? \\(Press 1 for YES and 0 for NO\\) \\[1/0\\]: \n");
        boolean answer;
        
        answer = TextIO.getlnBoolean();
        if(answer == true){
            System.out.println("The encryption key is: 3");
        } else{
            System.out.println("************************************************************");
            System.out.println("****************** Okay. I got you!*************************");
            System.out.println("************************************************************");
        } // end if
        
        System.out.println("The cipher digit is: \n");
        
        for ( int i = 0; i < shifted.length;i++){
            System.out.print(" " + shifted[i] + "");
        } // end for
        
        System.out.println();
        System.out.println();
        
        
        // now translate the digital string back to letters
        
        int xCount = 0; 
        int strCount = 0;
        char[] str = new char[shifted.length];
        
        for( int x : shifted){
            
            for ( int iCount = 0; iCount < alphabetLetters.length && iCount < alphaNumerals.length; iCount++){
                
                if ( x == alphaNumerals[iCount]){
                    str[strCount] = alphabetLetters[iCount];
                    strCount++;
                }// end for
            }// end for
        }//
        
        System.out.println("The cipher text is: ");
        System.out.println();
        System.out.println();
        
        for ( int i = 0; i < str.length; i++){
            
            
            System.out.print(""+str[i]+"");
            
        }// enf for
        
        System.out.println();
        System.out.println();
        System.out.println("**************<<<<<<< Thank You! >>>>>>*******************");
            
        
    } // end main 
    
    
         /*************************************************************************************
         *  FUNCTION DEFINITION
         * Now Comes the actual encryption:
         * We now are about to encrypt the string entered by the user using Cipher Shift
         * with 3 as the encryption key, the algorithm or the function that maps the idea is 
         * f(p) = p + k mod26.
         * We will need to improve the program in the future to implement affine cipher encryption. 
         * the function expects an array as its parameters
         ***************************************************************************************/
    
    public static int[] cipherShiftEncrypt(int[] numbers ){
            
            int encryptKey = 3; // encryption key which stands as k in f(p) = p + k mod26
                               // p is one of the entegers from the parameters
                               
            // declare some variables
            int shiftedDigit = 0;
            int numCount = 0; // index updater in an array
            int[] encryptedNums = new int[numbers.length];
            
            
            // use for each loop for conveniences
            for (int digit : numbers){
                shiftedDigit = digit + encryptKey;
                if(shiftedDigit >= 26)
                    shiftedDigit = (digit + encryptKey) % 26; // this will either cause the shifting to reset to a or 0 in the alphabet and the alphaNumerals
                encryptedNums[numCount] = shiftedDigit;
                numCount++;
            }// end for each loop
            
            return encryptedNums;
            
            
        }// end cipherShiftEncypt
    
} // end class Cryptography  => CipherShiftEncryption

