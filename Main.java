import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        // order of input: n (length of array), N (number of samples), c (max cut off), i (cutoff increment)
        int n = Integer.parseInt(args[0]);
        int N = Integer.parseInt(args[1]);
        int c = Integer.parseInt(args[2]);
        int i = Integer.parseInt(args[3]);
        Path path = Path.of("benchmark.csv");
        // column names
        try {
            Files.writeString(path, "cutoff mergesort\n");
        } catch (IOException err) {
            err.printStackTrace();
        }
        ArrayList<long[]> reses = new ArrayList<long[]>();

        for (int g = 0; g < N; g++) {
            int[] originArr = new int[n];

            for (int h = 0; h < c; ) {
                long[] res = new long[2];
                res[0] = h;
                for (int f = 0; f < n; f++) {
                    originArr[f] = (int) (Math.random() * 1000000);
                }
                // merge sort
                long start = System.nanoTime();
                SortingAlgo.MergeSorted(Arrays.copyOf(originArr, n), 0, n, h);
                long stop = System.nanoTime();
                res[1] = stop - start;

                reses.add(res);

                h += i;
            }
        }
        for (int j = 0; j < reses.size(); j++) {
            try {
                Files.writeString(
                    path,
                    String.format("%d %d\n", reses.get(j)[0], reses.get(j)[1]),
                    StandardOpenOption.APPEND
                );
            } catch (IOException err) {
                err.printStackTrace();
            }
        }
    }
}
