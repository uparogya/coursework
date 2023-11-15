public class ComparableExample
{
    public static void main(String[] args) {
        // in the Java api, the String class
        // implements the Comparable interface

        String str1 = "apple";
        String str2 = "banana";
        String str3 = "cherry";

        // some examples of how to call compareTo():

        System.out.println(str1 + " compared to " + str2 + " = " + str1.compareTo(str2));
        System.out.println(str2 + " compared to " + str3 + " = " + str2.compareTo(str3));
        System.out.println(str1 + " compared to " + str3 + " = " + str1.compareTo(str3));

        System.out.println(str2 + " compared to " + str1 + " = " + str2.compareTo(str1));
        System.out.println(str3 + " compared to " + str2 + " = " + str3.compareTo(str2));
        System.out.println(str3 + " compared to " + str1 + " = " + str3.compareTo(str1));

        System.out.println(str1 + " compared to " + str1 + " = " + str1.compareTo(str1));
        System.out.println(str2 + " compared to " + str2 + " = " + str2.compareTo(str2));
        System.out.println(str3 + " compared to " + str3 + " = " + str3.compareTo(str3));
    }
}