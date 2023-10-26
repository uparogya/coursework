/**
 * Example Bridges Program
 */
import bridges.connect.Bridges;
import bridges.base.SLelement;
public class BridgesDemo {

    public static void main(String[] args) throws Exception{

        //create the Bridges object

        // Use YOUR API Sha1 Key and user name.
        Bridges bridges = new Bridges(0, "au0304", "450444019629");

        //create two singly-linked elements
        SLelement<String> sle0 = new SLelement<String>("H", "");
        SLelement<String> sle1 = new SLelement<String>("E", "");
        SLelement<String> sle2 = new SLelement<String>("L", "");
        SLelement<String> sle3 = new SLelement<String>("L", "");
        SLelement<String> sle4 = new SLelement<String>("O", "");
        SLelement<String> sle5 = new SLelement<String>("World", "");

        // create a singly-linked list
        // by adding sle1 as sle0's next element
        sle0.setNext(sle1);
        sle1.setNext(sle2);
        sle2.setNext(sle3);
        sle3.setNext(sle4);
        sle4.setNext(sle5);

        //edit some visual properties of the two elements
        sle0.getVisualizer().setColor("red");
        sle1.getVisualizer().setColor("green");
        sle2.getVisualizer().setColor("blue");
        sle3.getVisualizer().setColor("yellow");
        sle4.getVisualizer().setColor("pink");
        sle5.getVisualizer().setColor("black");
        sle5.getVisualizer().setSize(25);

        //pass the first element of the list
        bridges.setDataStructure(sle0);

        // visualize the list
        bridges.visualize();

    }
}