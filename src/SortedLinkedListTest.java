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

    @Test
    @DisplayName("Get first method (ascending)")
    void getFirstAscending() {
        L.orderAscending();
        assertEquals("bravo", L.getFirst().getString());
    }

    @Test
    @DisplayName("Get first method (descending)")
    void getFirstDescending() {
        L.orderDescending();
        assertEquals("zulu", L.getFirst().getString());
    }

    @Test
    @DisplayName("Remove first element")
    void removeFirstMethod() {
        L.removeFirst();
        assertEquals("charlie", L.first.getString());
    }

    @Test
    @DisplayName("Remove last element")
    void removeLastMethod() {
        L.removeLast();
        assertEquals("x-ray", L.last.getString());
    }

    @ParameterizedTest
    @DisplayName("Remove element using valid index")
    @ValueSource(ints = {0, 5, 8})
    void removeWithValidIndex(int i) {
        L.remove(i);
        L.print();
        assertEquals(8, L.size());
    }

    @ParameterizedTest
    @DisplayName("Remove element using invalid index")
    @ValueSource(ints = {-1, 50})
    void removeWithInvalidIndex(int i) {
        L.remove(i);
        L.print();
        assertEquals(9, L.size());
    }

    @ParameterizedTest
    @DisplayName("Remove element using string")
    @ValueSource(strings = {"bravo", "zulu", "foxtrot"})
    void removeWithString(String s) {
        L.remove(s);
        L.print();
        assertEquals(8, L.size());
    }

    @Test
    @DisplayName("Clear entire list")
    void removeAll() {
        L.removeAll(L.size());
        assertEquals(0, L.size());
    }

    @Test
    @DisplayName("Order list (ascending)")
    void orderAscending() {

        SortedLinkedList lAsc = new SortedLinkedList();
        lAsc.order = SortedLinkedList.OrderStatus.ASCENDING;
        lAsc.add("apple");
        lAsc.add("banana");
        lAsc.add("lychee");
        lAsc.add("cherry");
        lAsc.add("blackcurrant");
        lAsc.add("persimmon");
        lAsc.add("yam");
        lAsc.print();

        SortedLinkedList L2 = new SortedLinkedList();
        L2.order = SortedLinkedList.OrderStatus.DESCENDING;
        L2.add("apple");
        L2.add("banana");
        L2.add("lychee");
        L2.add("cherry");
        L2.add("blackcurrant");
        L2.add("persimmon");
        L2.add("yam");
        L2.orderAscending();
        L2.print();

        assertEquals(lAsc.get(0).getString(), L2.get(0).getString());
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
