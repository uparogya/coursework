/**
 * Interface for a license manager
 */

public interface Manager
{
  /** 
   * Return true if able to acquire a permit, false otherwise.
   */
  public boolean acquirePermit();
  
  /**
   * Release a permit
   */
  public void releasePermit();
}
  