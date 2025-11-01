/**
 * DiskScheduler.java
 *
 * An interface for a disk scheduler algorithm.
 */

public interface DiskScheduler
{
    /**
     * Service the requests
     * @return the amount of head movement for the particular algorithm
     */
    public int serviceRequests();
}