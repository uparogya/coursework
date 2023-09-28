public class IsBinaryPalindrome {
    public static void main(String[] args) {

        //Checking for missing argument
        if(args.length != 1){
            System.err.println("One Argument Required!");
            System.exit(1);
        }

        int remainder = Integer.parseInt(args[0]);
        int quotient;
        boolean isPalindrome = true;
        int count = 0; //Number of digits in binary

        Stack <Integer> binaryStack = new ListStack<Integer>();
        Stack <Integer> reverseBinaryStack = new ListStack<Integer>();
        Stack <Integer> tempStack = new ListStack<Integer>();

        System.out.print("Reverse Binary Value of " + remainder + " = ");


        while (true){
            if(remainder == 1 || remainder == 0){ //For the last division
                reverseBinaryStack.push(remainder);
                tempStack.push(remainder);
                System.out.println(remainder);
                break;
            }
            count++;
            //Calculating quotient & remainder
            quotient = remainder % 2;
            remainder = remainder / 2;

            //Pushing the quotient into the reverse stack
            reverseBinaryStack.push(quotient);
            tempStack.push(quotient);
            System.out.print(quotient);
        }

        for (int i = 0; i < count+1; i++) {
            binaryStack.push(tempStack.pop());
        }

        for (int i = 0; i < count+1; i++) {
            if(binaryStack.pop() != reverseBinaryStack.pop()){
                isPalindrome = false;
                break;
            }
        }

        if(isPalindrome){
            System.out.println(Integer.parseInt(args[0]) + " Is Palindrome");
        }else{
            System.out.println(Integer.parseInt(args[0]) + " Is Not Palindrome");
        }
    }
}
