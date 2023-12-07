import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomSortTest {

    ArrayList<Double> values = new ArrayList<>();
    CustomSort cs = new CustomSort();

    @BeforeEach
    void setValuesForTests() {
        values.add(13d);
        values.add(2d);
        values.add(100d);
        values.add(20d);
        values.add(5d);
        cs.setValues(values);
    }


    @Test
    @DisplayName("calculate gaps functionality")
    void calculateGaps() {
        //System.out.println(CustomSort.calculateGaps(values));
    }

    @Test
    @DisplayName("calculate sort functionality")
    void sort() {
//  cs.setValues(values);

    }


    @ParameterizedTest
    @DisplayName("Add element and sort")
    @ValueSource(doubles = {21d})
    void insertElement(Double value) {
        cs.add(value);
        ArrayList<Double> target = new ArrayList<>();
        target.add(2d);
        target.add(5d);
        target.add(13d);
        target.add(20d);
        target.add(21d);
        target.add(100d);

        System.out.println(cs.myValues);
        System.out.println(target);
        assertEquals(cs.myValues, target);
    }

    @ParameterizedTest
    @DisplayName("Remove element")
    @ValueSource(ints = {0, 1, 2, 3, 4})
    void removeElement(int index) {
        System.out.println(cs.myValues);
        cs.remove(index);
        System.out.println(cs.myValues);
        assertEquals(4, cs.myValues.size());
    }

}
