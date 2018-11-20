import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Dinglemouse {
    private static int cap;                              //=capacity of Lift
    private static int direction;                        //+1=UP, -1=DOWN
    private static int floor;                            //current floor
    private static HashMap<Integer, Integer> peopleInLift;      //array of numbers(people in Lift)
    private static int numberFloors;                     //=queues.length
    private static int amountPeopleInLift;               //0...cap

    public static void main(String[] args) {
        final int[][] queues = {
                new int[]{1,1}, // G
                new int[]{5,0}, // 1
                new int[0], // 2
                new int[0], // 3
                new int[0], // 4
                new int[]{6}, // 5
                new int[]{5}, // 6
                new int[0], // 7
        };
        final int[] result = Dinglemouse.theLift(queues,1);
        System.out.println(Arrays.toString(result));

        final int[][] queues2 = {
                new int[]{3,2,2}, // G
                new int[]{2}, // 1
                new int[]{1,1}, // 2
                new int[0], // 3
                new int[]{5,5}, // 4
                new int[0], // 5
                new int[]{3}, // 6
        };
        final int[] result2 = Dinglemouse.theLift(queues2,3);
        System.out.println(Arrays.toString(result2));

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
        System.out.println(Arrays.toString(result3));

        final int[][] queues4 = {
                new int[]{1,2}, // G
                new int[]{2,0,0,2}, // 1
                new int[]{1,1,1}, // 2
        };
        final int[] result4 = Dinglemouse.theLift(queues4,2);
        System.out.println(Arrays.toString(result4));

    }

    public static int[] theLift(final int[][] queues, final int capacity) {
        cap=capacity;
        direction = 1;
        floor = -1;
        peopleInLift = new HashMap<>();
        numberFloors = queues.length;
        ArrayList<Integer> liftStops = new ArrayList<>();
        liftStops.add(0);
        amountPeopleInLift = 0;

        int[] numberPeopleExist = new int[numberFloors];
        for (int i = 0; i < numberFloors; i++) {
            numberPeopleExist[i] = queues[i].length;
        }

        while (Arrays.stream(numberPeopleExist).max().getAsInt() != 0 || amountPeopleInLift > 0) {
            changeFloor();

            leaveLift(liftStops);

            enterLift(queues, liftStops, numberPeopleExist);
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

    private static void leaveLift(ArrayList<Integer> liftStops) {
        if (peopleInLift.get(floor) != null) {
            amountPeopleInLift -= peopleInLift.get(floor);
            peopleInLift.remove(floor);
            liftStops.add(floor);
        }
    }

    private static void enterLift(int[][] queues, ArrayList<Integer> liftStops, int[] numberPeopleExist) {
        for (int i = 0; i < queues[floor].length; i++) {
            if (queues[floor][i] < 0) continue;
            if (direction > 0 && queues[floor][i] < floor) break;
            if (direction < 0 && queues[floor][i] > floor) break;
            if (liftStops.get(liftStops.size() - 1) != floor) liftStops.add(floor);
            if (amountPeopleInLift == cap) break;

            if (peopleInLift.get(queues[floor][i]) == null)
                peopleInLift.put(queues[floor][i], 1);
            else
                peopleInLift.put(queues[floor][i], peopleInLift.get(queues[floor][i]) + 1);
            queues[floor][i] = -1;
            numberPeopleExist[floor]--;
            amountPeopleInLift++;
        }
    }
}
