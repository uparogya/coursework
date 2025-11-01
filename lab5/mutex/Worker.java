/**
 * Stress test for LicenseManager that addresses
 * race conditions.
 */

public class Worker implements Runnable
{
  public static final int THREAD_COUNT = 100;
  public static final int RUN_COUNT = 25;
  
  private Manager licenseManager;
  
  public Worker(Manager licenseManager) {
    this.licenseManager = licenseManager;
  }
  
  public void run() {
    // alternate between sleeping and acquiring and releasing the permits
    int runCount = RUN_COUNT;
    
    try {
      while (runCount > 0) {
        Thread.sleep( (long) (Math.random() * 1000) );
        licenseManager.acquirePermit();
        Thread.sleep( (long) (Math.random() * 1000) );
        licenseManager.releasePermit();
        runCount--;
      }
      
    } catch (InterruptedException ie) { }
  }
  
  public static void main(String args[]) {
    Manager lm = new LicenseManager(5);
    
    Thread[] worker = new Thread[THREAD_COUNT];
    for (int i = 0; i < THREAD_COUNT; i++) {
      worker[i] = new Thread(new Worker(lm));
      worker[i].start();
    }
    
    try {
      for (int i = 0; i < THREAD_COUNT; i++) {
        worker[i].join();
      }
    }
    catch (InterruptedException ie) { }
    
    System.out.println("DONE\n");
      
  }
}
