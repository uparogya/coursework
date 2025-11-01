public class FCFS implements DiskScheduler{
    private final int[] referenceString;
    private final int ip;

    public FCFS(int[] referenceString, int ip) {
        this.referenceString = referenceString;
        this.ip = ip;
    }

    @java.lang.Override
    public int serviceRequests() {
        int current_position = ip;
        int sum = 0;
        for (int i = 0; i < referenceString.length; i++) {
            sum += Math.abs(current_position - referenceString[i]);
            current_position = referenceString[i];
        }

        return sum;
    }
}
