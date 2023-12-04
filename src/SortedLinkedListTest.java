import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

public class SortedLinkedListTest {
    SortedLinkedList L = new SortedLinkedList();
    SortedLinkedList.OrderStatus O = SortedLinkedList.OrderStatus.ASCENDING;

    @BeforeEach
    void beforeEach() {
        O = SortedLinkedList.OrderStatus.ASCENDING;
        L.add(new Node("bravo"));
        L.add(new Node("charlie"));
        L.add(new Node("echo"));
        L.add(new Node("delta"));
        L.add(new Node("x-ray"));
        L.add(new Node("golf"));
        L.add(new Node("foxtrot"));
        L.add(new Node("zulu"));
        L.add(new Node("hotel"));
    }

    @Test
    @DisplayName("Add to start of list")
    void addStart() {
        L.add(new Node("alpha"));
        assert (Objects.equals(L.getFirst().getString(), "alpha"));
    }

    /*
    @DisplayName("Get using index")
    @ParameterizedTest
    @ValueSource(ints = {0, 3, 8, 20, -1}, strings = {"alpha", "charlie", "zulu", "", ""})
    void getWithIndex(int index, String nodeName) {
        assertEquals(L.get(index).getString(), nodeName);
    }
*/
    @Test
    @DisplayName("Get using index (not paramaterised)")
    void getWithIndex() {
        assertEquals("bravo", L.get(0).getString());
        assertEquals("foxtrot", L.get(4).getString());
        assertEquals("zulu", L.get(8).getString());
        //assertNull(L.get(-2).getString());
    }

    @DisplayName("Check for presence of a string (true)")
    @ParameterizedTest
    @ValueSource(strings = {"x-ray", "charlie", "zulu"})
    void seeIfPresentTrue(String string) {
        L.print();
        assertTrue(L.isPresent(string));
    }

    @DisplayName("Check for presence of a string (false)")
    @ParameterizedTest
    @ValueSource(strings = {"xray", " ", "", "1234"})
    void seeIfPresentFalse(String string) {
        L.print();
        assertFalse(L.isPresent(string));
    }

    /*
    @Test
    @DisplayName("Add to reverse list") {
        void addStartReverse () {
            list.orderDescending();
            list.add(new Node("aaaa"));
            assert 3 rd node is aaaa
            assert 2 nd is geoff
            assert 1 st is max
        }

        @ParameterizedTest
        @ValueSource()
        void add (Node node){

        }
    }*/
}
