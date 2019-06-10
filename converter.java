/*
 * This class contains various methods for converting numbers written 
 * using different number systems: binary, decimal, hexadecimal. 
 * The decimal numbers are represented using int type. 
 * The binary and hexadecimal numbers are represented using binary and hexadecimal strings.
 * 
 * The valid strings representing binary numbers are formatted as:
 * "0bBB...BB" where BB...BB is a sequence of 1 to 31 binary characters. 
 * Binary characters are 0, 1.
 * 
 * The valid strings representing hexadecimal numbers are formatted as:
 * "0xHH...HH" where HH...HH is a sequence of 1 to 8 hexadecimal characters. 
 * Hexadecimal characters are 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, A, B, C, D, E, F.
 * 
 * @author sonamtailor
 */

public class Converter {
	
	/**
	 * Converts binary strings to decimal numbers. 
	 * More specifically given a valid string representing a binary number 
	 * returns a non-negative decimal integer with the same value.
	 * @param binary - the binary string to be converted
	 * @return the decimal number equal in value to the binary string passed as the parameter.
	 * @throws IllegalArgumentException - if the binary string passed to the function is invalid.
	 */
	public static int binaryToDecimal(String binary) throws IllegalArgumentException{
		//validation
		if (binary == null || binary.isEmpty()) {
			throw new IllegalArgumentException("Invalid Binary Sequence.");
		}
		if (!binary.substring(0, 2).equals("0b")) { //assures that first two characters are "0b"
			throw new IllegalArgumentException("Invalid Binary Sequence.");
		}
		for (int i  = 2; i<binary.length(); i++) {
			if (!(binary.charAt(i)=='0' || binary.charAt(i)=='1')) { //assures that characters are either 0 or 1
				throw new IllegalArgumentException("Invalid Binary Sequence.");
			}
		}
		binary = binary.substring(2); //stores new string of characters excluding the "0b"
		if (binary.length()<1 || binary.length()>31) { //check to make sure the string is of valid length
			throw new IllegalArgumentException("Invalid Binary Sequence.");
		}
		return binaryToDecimal(binary, binary.length(),0); //calls recursive function
	}
	
	/*
	 * This recursive function is a helper method for the binary to decimal conversion.
	 * Base Case: if length is 0 
	 * @param binary- the binary string to be converted.
	 * @param length- the length of the binary string that has not yet been converted.
	 * @return 0 if the length is 0
	 * @return power function value added to remaining binaryToDecimal calls
	 */
/*
	private static int binaryToDecimal(String binary, int length) {
		if (length == 0) { //base case
			return 0;
		}
 		int value = binary.charAt(length-1) - '0'; //reads characters starting from right to left 
 		
 		//2- the powers of the base for binary number system
 		//binary.length()-length because the rightmost has the smallest power 
 		//example- last element in binary sequence would be 2 to the power of 0 (length-length)
		return(value*pow(2,binary.length()-length) + binaryToDecimal(binary, length-1)); 
		
	}
*/
	//Alernative code 
	private static int binaryToDecimal(String binary, int length, int index) {
		if (length == 0) {
			return 0;
		}
		int value = binary.charAt(index) - '0';
		return(value*(pow(2,length-1)) + binaryToDecimal(binary, length-1, index+1));
		
	}

	
	/* 
	 * Recursive function to calculate powers of num1 (base) to the power of num2.
	 * Varying recursive calls depending on whether num2 is odd or even. Written in class. 
	 * @param num1 - base integer
	 * @param num2 - int power raised to 
	 * @returns num1 to the power of num2
	 */
	private static int pow(int num1, int num2) {
		if (num2<= 0) { //base case
			return 1;
		}
		if (num2%2 == 0) { //even power
			int temp = pow(num1, num2/2);
			return temp*temp;
		}
		else { //odd power
			int temp = pow(num1, num2/2);
			return temp*temp*num1;
		}
	}
	
	/**
	 * Converts decimal numbers to binary strings. 
	 * More specifically given a non-negative decimal integer 
	 * returns the string representing the binary number with the same value.
	 * @param decimal - the decimal number to be converted
	 * @return the binary string equal in value to the decimal
	 *  number passed as the parameter or null if the decimal is negative.
	 */
	
	public static String decimalToBinary(int decimal) {
		if (decimal<0) { //if decimal is negative return null
			return null;
		}

		return decimalToBinHelp(decimal);
	}
	
	/*
	 * Recursive function to calculate the binary value of a decimal number.
	 * @param decimal - the decimal number to be converted
	 * @return 0b if the decimal value is equal to 0
	 * @return decimalToBinary call on decimal integer division by 2. Sum with decimal%2. 
	 * 
	 */
	private static String decimalToBinHelp(int decimal) {
		if (decimal == 0) { //base case
			return "0b";
		}
		else {
			return(decimalToBinary(decimal/2) + decimal%2); //decimal%2 is the symbol at position one in the binary sequence 
		}
	}
	
	/**
	 * Converts binary strings to hexadecimal strings. 
	 * More specifically given a valid string representing a binary number 
	 * returns the string representing the hexadecimal number with the same value. 
	 * @param binary - the binary string to be converted
	 * @return the hexadecimal string equal in value to the binary string passed 
	 * as the parameter or null if the binary string is not valid.
	 */
	public static String binaryToHex(String binary) {
		if (binary == null || binary.isEmpty()) { //null check
			return null;
		}
		
		if (!binary.substring(0, 2).equals("0b")) { //assures first two characters are "0b"
			return null;
		}
		for (int i  = 2; i<binary.length(); i++) { //assures valid binary characters "0" or "1"
			if (!(binary.charAt(i)=='0' || binary.charAt(i)=='1')) {
				return null; 
			}
		}
		binary = binary.substring(2); //stores new string of characters excluding the "0b"
		
		if (binary.length()<1 || binary.length()>31) { //assures valid length
			return null;
		}
		while (binary.length()%4!=0){ //pad with 0's until sequence length is a multiple of 4
			binary = "0"+binary;	
		}
		return binaryToHex(binary, binary.length());
	}
	
	/*
	 * Recursive function to calculate the hex value of a binary string.
	 * @param binary - the binary string to be converted
	 * @param length - the length of the binary string that has not yet been converted
	 * @return the binaryToHex call on binary with length 4 less than previous call - appended to a string which is returned  
	 */
	private static String binaryToHex(String binary, int length) {
		if (length == 0) { //base case
			return "0x";
		}
		String bin = binary.substring(length-4,length); //store a substring of 4 characters
		String val = ""; //empty string to append hex values to
		
		//search the substring 
		switch (bin){
		case "0000": val = "0"; break;
		case "0001": val = "1"; break;
		case "0010": val = "2"; break;
		case "0011": val = "3"; break;
		case "0100": val = "4"; break;
		case "0101": val = "5"; break;
		case "0110": val = "6"; break;
		case "0111": val = "7"; break;
		case "1000": val = "8"; break;
		case "1001": val = "9"; break;
		case "1010": val = "A"; break;
		case "1011": val = "B"; break;
		case "1100": val = "C"; break; 
		case "1101": val = "D"; break;
		case "1110": val = "E"; break;
		case "1111": val = "F"; break;		
		}
		return binaryToHex(binary, length-4) + val; //length-4 for the next 4 characters (reads from right end to left)
	}
	
	/**
	 * Converts hexadecimal strings to binary strings. 
	 * More specifically given a valid string representing a hexadecimal number 
	 * returns the string representing the binary number with the same value.
	 * @param hex- the hexadecimal string to be converted
	 * @return the binary string equal in value to the hexadecimal string passed as the parameter or null 
	 * if the hexadecimal string is not valid.
	 */
	public static String hexToBinary(String hex) {
		if (hex == null || hex.isEmpty()) { // null check
			return null;
		}
		if (!hex.substring(0, 2).equals("0x")) { //checks to see if first 2 characters are "0x"
			return null;
		}
		String tester = "0123456789ABCDEF";
		for (int i  = 2; i<hex.length(); i++) {
			if (tester.indexOf(hex.charAt(i))==-1) { //checks to see if each character of the passed string is a valid hexadecimal value
				return null; 
			}
		}
		
		hex = hex.substring(2); //stores new string of characters excluding the "0x"
		if (hex.length()<1 || hex.length()>8) { //hexadecimal length check 
			return null;
		}
			
		return hexToBinary(hex, hex.length());
	}
	
	/*
	 * Recursive function to convert hexadecimal string to binary string
	 * @param hex - the hexadecimal string to be converted
	 * @param length - the length of the hexadecimal string that has not yet been converted
	 * @return hexToDecimal call to hex and length -1 appended to a string which is returned 
	 */
	private static String hexToBinary(String hex, int length) {
		if (length == 0) { //base case
			return "0b";
		}
		String val = ""; //empty string to append binary values 
		
		//hex char search 
		switch (hex.charAt(length-1)){ //reading from right to left 
		case '0': val = "0000"; break;
		case '1': val = "0001"; break;
		case '2': val = "0010"; break;
		case '3': val = "0011"; break;
		case '4': val = "0100"; break;
		case '5': val = "0101"; break;
		case '6': val = "0110"; break;
		case '7': val = "0111"; break;
		case '8': val = "1000"; break;
		case '9': val = "1001"; break;
		case 'A': val = "1010"; break;
		case 'B': val = "1011"; break;
		case 'C': val = "1100"; break; 
		case 'D': val = "1101"; break;
		case 'E': val = "1110"; break;
		case 'F': val = "1111"; break;		
		}
		return (hexToBinary(hex, length-1) + val); //append to begin of string 
	}

}