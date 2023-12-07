import java.util.ArrayList;

public class CustomSort implements SortingInterface {
//    Your task is to implement this sorting algorithm via a class called CustomSort using the SortingInterface interface. The interface consists of the following methods:
//    - public void setValues(ArrayList<Double> values) - sets and sorts the values provided in ascending order
//    - public ArrayList<Integer> getGaps() - returns the gaps used by the sorting algorithm
//    - public void add(Double value) - adds a value to the sorted ArrayList in ascending order.
//    - public void remove(int index) - removes a value at the specified index from the sorted ArrayList.
//    - public void sort() - sorts the ArrayList in ascending order.
//
    ArrayList<Double> myValues = new ArrayList<>();

    public static ArrayList<Integer> calculateGaps(ArrayList<Double> values) {
        int n = values.size();
        ArrayList<Integer> gaps = new ArrayList<>();
        ArrayList<Integer> temp = new ArrayList<>();
        int gap = 1;
        int i = 2;

        while (gap < n) {
            temp.add(gap);
            gap = (int) ((Math.pow(2, i)) - 1);
            i++;
        }

        int k = temp.size() - 1;
        while (k >= 0) {
            gaps.add(temp.get(k));
            k--;
        }

        return gaps;
    }

    @Override
    public void setValues(ArrayList<Double> values) {
        myValues = values;
        sort();
    }

    @Override
    public ArrayList<Integer> getGaps() {


        return null;
    }

    @Override
    public void add(Double value) {

    }

    @Override
    public void remove(int index) {

    }

    @Override
    public void sort() {
        int n = myValues.size();
        ArrayList<Integer> gaps = calculateGaps(myValues);
        for (int gap : gaps) {
            for (int i = gap; i < n; i++) {
                Double temp;
                temp = myValues.get(i);
                int j;
                for (j = i ; j >= gap; j-=gap) {
                    if (myValues.get(j-gap) <= temp) {
                        break;
                    }
                    myValues.set(j, myValues.get(j-gap));
                }
                myValues.set(j, temp);
            }
        }
    }
}

//----------------------------------------------------------------------------------------------------------------------

interface SortingInterface {

    /**
     * Sets and sorts the values provided in ascending order.
     *
     * @param values the values to be sorted
     */
    public void setValues(ArrayList<Double> values);

    /**
     * Returns the gaps used by the sorting algorithm.
     *
     * @return The gaps used by the sorting algorithm to sort the ArrayList
     */
    public ArrayList<Integer> getGaps();

    /**
     * Adds a value to the sorted ArrayList in ascending order.
     *
     * @param value the double to be added to the array list
     */
    public void add(Double value);

    /**
     * Removes a value at the specified index from the sorted ArrayList.
     *
     * @param index the index of the double to be removed
     */
    public void remove(int index);

    /**
     * Sorts the ArrayList in ascending order.
     */
    public void sort();
}