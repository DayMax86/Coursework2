import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomSortTest {

    @Test
    @DisplayName("calculate gaps functionality")
    void calculateGaps() {
        ArrayList<Double> values = new ArrayList<Double>();
        values.add(1d);
        values.add(3d);
        values.add(10d);
        values.add(20d);
        values.add(5d);

        System.out.println(CustomSort.calculateGaps(values));

    }

    @Test
    @DisplayName("calculate sort functionality")
    void sort() {
        ArrayList<Double> values = new ArrayList<Double>();
        values.add(13d);
        values.add(2d);
        values.add(100d);
        values.add(20d);
        values.add(5d);

        CustomSort cs = new CustomSort();
        cs.setValues(values);

    }


}
