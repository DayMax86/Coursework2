import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SortedLinkedListTest {
    SortedLinkedList L = new SortedLinkedList();

    @BeforeEach
    void beforeEach() {
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
