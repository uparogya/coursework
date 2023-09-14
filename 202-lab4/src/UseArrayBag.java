/*
* Arogya Upadhyaya & William Myers
* */

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UseArrayBag {
    public static void main(String[] args) {
        List <String> ArrayBag = new ArrayList<String>();
        ArrayBag.add("Apple");
        ArrayBag.add("Banana");
        ArrayBag.add("Cherry");
        ArrayBag.add("Donut");
        ArrayBag.add("Eggs");

        //PRINTING THE STUFFS
//        for (int i = 0; i < ArrayBag.size(); i++){
//            System.out.println(ArrayBag.get(i));
//        }

        Iterator<String> itr = ArrayBag.iterator();
        while (itr.hasNext()){
            System.out.println(itr.next());
        }
    }
}
