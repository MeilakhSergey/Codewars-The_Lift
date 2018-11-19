import java.util.ArrayList;
import java.util.Arrays;

public class Dinglemouse {
    private static int cap;
    private static boolean isGoingUp;

    public static void main(String[] args) {
        final int[][] queues = {
                new int[0], // G
                new int[0], // 1
                new int[]{5,5,5}, // 2
                new int[0], // 3
                new int[0], // 4
                new int[0], // 5
                new int[0], // 6
        };
        final int[] result = Dinglemouse.theLift(queues,5);
        System.out.println(result.toString());

        final int[][] queues2 = {
                new int[0], // G
                new int[0], // 1
                new int[]{1,1}, // 2
                new int[0], // 3
                new int[0], // 4
                new int[0], // 5
                new int[0], // 6
        };
        final int[] result2 = Dinglemouse.theLift(queues,5);
        System.out.println(result2.toString());
    }

    public static int[] theLift(final int[][] queues, final int capacity) {
        cap=capacity;
        isGoingUp = true;
        ArrayList<Integer> lift = new ArrayList<>();
        int[] numberPeopleExist = new int[queues.length];
        for (int i = 0; i < queues.length; i++) {
            numberPeopleExist[i] = queues[i].length;
        }
        while (Arrays.stream(numberPeopleExist).max().getAsInt() != 0) {

        }

        return new int[0];
    }
}
