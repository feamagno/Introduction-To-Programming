public class Lucas {

    public Lucas(){
    }

    public long lucasSeq(long n, long a, long b){

        if (n == 0){
            return 0;
        }

        else if (n == 1){
            return 1;
        }

        else {
            return a*lucasSeq(n-1, a, b) + b*lucasSeq(n-2,a,b);
        }
        
    }

    public long sumLucasSeq(long n, long a, long b, long d){
        long sum = 0;
        for (long i = n; i >= 1; i-- ){
            long f = lucasSeq(i,a,b);
            if (f%d == 0){
                sum += f;
            }
        }

        return sum;

    }

}
