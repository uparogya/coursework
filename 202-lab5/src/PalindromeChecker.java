import java.util.Scanner;

public class PalindromeChecker
{
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String message = null;

		do {
			System.out.println("Enter a string:");

			message = scan.nextLine();
			int charCount = 0;
			boolean isPalindrome = true;

			Stack <Character> tempStack = new ListStack<Character>();
			Stack <Character> stack1 = new ListStack<Character>();
			Stack <Character> stack2 = new ListStack<Character>();

			for (char character: message.toCharArray()) {
				tempStack.push(character);
				stack1.push(character);
				charCount++;
			}

			for (int i = 0; i < charCount; i++) {
				stack2.push(tempStack.pop());
			}

			for (int i = 0; i < charCount; i++) {
				if (stack1.pop() != stack2.pop()){
					isPalindrome = false;
					break;
				}
			}
			if(isPalindrome){
				System.out.println("Entered String Is Palindrome");
			}else{
				System.out.println("Entered String Is Not A Palindrome");
			}

		}
		while (message.length() != 0);
		
		scan.close();
	}
}
