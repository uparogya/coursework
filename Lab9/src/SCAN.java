public class SCAN implements DiskScheduler{
    private final int[] referenceString;
    private final int ip;
    public SCAN(int[] referenceString, int ip) {
        this.referenceString = referenceString;
        this.ip = ip;
    }

    @java.lang.Override
    public int serviceRequests() {
        int sum = 0;
        int current_position = ip;
        int access_count = 0;

        int movement = -1;
        int bench = -9999;

        while (access_count != referenceString.length) {
            int neg_index = 0;
            for(int i = 0; i < referenceString.length; i++){
                if(referenceString[i] == -1){
                    continue;
                }
                if (movement == 1 && referenceString[i] > current_position && referenceString[i] < bench){
                    bench = referenceString[i];
                    neg_index = i;
                }
                if (movement == -1 && referenceString[i] < current_position && referenceString[i] > bench){
                    bench = referenceString[i];
                    neg_index = i;
                }
            }

            if(bench == 9999){
                movement = -1;
                bench = -9999;
            } else if (bench == -9999) {
                movement = 1;
                bench = 9999;
            } else {
                sum += Math.abs(current_position - bench);
                current_position = bench;
                referenceString[neg_index] = -1;
                access_count++;
                if(movement == 1){
                    bench = 9999;
                }else{
                    bench = -9999;
                }
            }
        }

        return sum;
    }
}
