/**
 * Determines if two strands or DNA or RNA are palindromic sequences
 */

import java.util.Scanner;

public class PalindromicSequence
{
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String strand1;
		String strand2;

		Stack <Character> stack = new ListStack<Character>();
		Queue <Character> queue = new ListQueue<Character>();

		boolean isPalindrome = true;

		do {
			System.out.print("Enter the first strand:");

			strand1 = scan.nextLine();
			
			System.out.print("Enter the second strand:");

			strand2 = scan.nextLine();
			
			if (strand1.length() != strand2.length()) {
				System.out.println("Both strands must be the same length");
				continue;
			}

			for (char character: strand1.toCharArray()) {
				stack.push(character);
			}
			for (char character: strand2.toCharArray()) {
				queue.add(character);
			}
			for (int i = 0; i < strand1.length(); i++) {
				if(stack.pop() != queue.remove()){
					isPalindrome = false;
				}
			}
			System.out.print("The strands " + strand1 + " and " + strand2 + " are ");
			if(isPalindrome){
				System.out.println("palindromic.\n");
			}else {
				System.out.println("not palindromic.\n");
			}
		}
		while (strand1.length() != 0);
		
		scan.close();
	}
}