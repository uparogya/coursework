/**
 * Implementation of the Manager interface that
 * permits a number of licenses.
 */

import java.util.concurrent.locks.ReentrantLock;

public class LicenseManager implements Manager
{

    private ReentrantLock mutex;

    // the number of available permits
    private int permits;
  
    public LicenseManager(int permits) {
        if (permits < 0)
            throw new IllegalArgumentException();
        this.permits = permits;

        mutex = new ReentrantLock();

    }
   
    public boolean acquirePermit() {
        boolean rv = false;

        mutex.lock();

        try{
            if (permits > 0) {
                permits--;
      
                rv = true;
            }
        }
        finally {
            mutex.unlock();
        }

    return rv;
    }
  
    public void releasePermit() {
        mutex.lock();
        
        try {
            permits++;
        }
        finally {
            mutex.unlock();
        }
    }
}
  
