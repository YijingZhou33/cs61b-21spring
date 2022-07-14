package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeSLList {
    private static void printTimingTable(AList<Integer> Ns, AList<Double> times, AList<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeGetLast();
    }

    public static void timeGetLast() {
        SLList<Integer> sll = new SLList<>();
        AList<Integer> Ns = new AList<>();
        AList<Double> times = new AList<>();
        AList<Integer> opCounts = new AList<>();
        double pow = 0;

        for (int i = 1; i <= 64000; i += 1) {
            sll.addLast(i);
            Stopwatch sw = new Stopwatch();
            if (Math.pow(2, pow) == ((double) i / 1000) && pow < 10) {
                Ns.addLast(i);
                for (int j = 0; j < 10000; j += 1) {
                    sll.getLast();
                }
                times.addLast(sw.elapsedTime());
                opCounts.addLast(10000);
                pow += 1;
            }
        }

        printTimingTable(Ns, times, opCounts);
    }

}
