import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Optional;

public class Dinglemouse {
    private static int cap;                              //=capacity of Lift
    private static int direction;                        //+1=UP, -1=DOWN
    private static int floor;                            //current floor
    private static HashMap<Integer, Integer> peopleInLift;      //array of numbers(people in Lift)
    private static int numberFloors;                     //=queues.length

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
        final int[] result = Dinglemouse.theLift(queues,1);
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
        final int[] result2 = Dinglemouse.theLift(queues2,5);
        System.out.println(result2.toString());

        final int[][] queues3 = {
                new int[0], // G
                new int[]{6,5,2}, // 1
                new int[]{4}, // 2
                new int[0], // 3
                new int[]{0,0,0}, // 4
                new int[0], // 5
                new int[0], // 6
                new int[]{3,6,4,5,6}, // 7
                new int[0], // 8
                new int[]{1,10,2}, // 9
                new int[]{1,4,3,2}, // 10
        };
        final int[] result3 = Dinglemouse.theLift(queues3,5);
        System.out.println(result3.toString());

    }

    public static int[] theLift(final int[][] queues, final int capacity) {
        cap=capacity;
        direction = 1;
        floor = -1;
        peopleInLift = new HashMap<>();
        numberFloors = queues.length;
        ArrayList<Integer> liftStops = new ArrayList<>();
        liftStops.add(0);

        int[] numberPeopleExist = new int[numberFloors];
        for (int i = 0; i < numberFloors; i++) {
            numberPeopleExist[i] = queues[i].length;
        }

        while (Arrays.stream(numberPeopleExist).max().getAsInt() != 0 || peopleInLift.size() > 0) {
            changeFloor();

            if (peopleInLift.get(floor) != null) {
                peopleInLift.remove(floor);
                liftStops.add(floor);
            }

            for (int i = 0; i < queues[floor].length; i++) {
                if (peopleInLift.size() == cap) break;
                if (queues[floor][i] < 0) continue;
                if (direction > 0 && queues[floor][i] < floor) break;
                if (direction < 0 && queues[floor][i] > floor) break;
                if (peopleInLift.get(queues[floor][i]) == null)
                    peopleInLift.put(queues[floor][i], 1);
                else
                    peopleInLift.put(queues[floor][i], peopleInLift.get(queues[floor][i]) + 1);
                queues[floor][i] = -1;
                numberPeopleExist[floor]--;
                if (liftStops.get(liftStops.size() - 1) != floor) liftStops.add(floor);
            }
        }
        if (liftStops.get(liftStops.size() - 1) != 0) liftStops.add(0);
        return liftStops.stream().mapToInt(i -> i).toArray();
    }

    private static void changeFloor() {
        floor += direction;
        if (floor == numberFloors) {
            floor -= 1;
            direction *= -1;
        }
        if (floor == -1) {
            floor = 0;
            direction *= -1;
        }
    }
}
