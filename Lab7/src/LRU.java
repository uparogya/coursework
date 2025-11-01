/**
 * This class implements the LRU page-replacement strategy.
 *
 */

import java.util.List;
import java.util.ArrayList;

public class LRU extends ReplacementAlgorithm
{
    // LRU list of page frames
    private List<Integer> frameList;

    /**
     * @param pageFrameCount - the number of physical page frames
     */
    public LRU(int pageFrameCount) {
        super(pageFrameCount);

        frameList = new ArrayList<Integer>();
    }

    /**
     * Insert a page into a page frame.
     */
    public void insert(int pageNumber) {
        // insert pageNumber into frameList using LRU algorithm
        if(frameList.contains(pageNumber)){
            frameList.remove((Integer) pageNumber);
            frameList.add(0, pageNumber);
        }else{
            pageFaultCount++;
            if(frameList.size() >= pageFrameCount){
                frameList.remove(frameList.size()-1);
                frameList.add(0, pageNumber);
            }else{
                frameList.add(0, pageNumber);
            }
//            pageFrameCount++;
        }
    }
}