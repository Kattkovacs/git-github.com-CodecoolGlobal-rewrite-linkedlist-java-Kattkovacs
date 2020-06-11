package com.codecool.linkedlist;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SinglyLinkedListTest {

    SinglyLinkedList listUnderTest;

    public SinglyLinkedListTest() {
        this.listUnderTest = new SinglyLinkedList();
    }

    @Test
    @Order(1)
    void insert_elementAtFirstIndex_canBeAccessedByIndex() {
        listUnderTest.insert(0, 123);

        assertEquals(123, listUnderTest.get(0));
    }

    @Test
    @Order(2)
    void insert_elementToNegativeIndex_throwsIndexOutOfBoundException() {
        assertThrows(IndexOutOfBoundsException.class, ()-> listUnderTest.insert(-1, 123));
    }

    @Test
    @Order(3)
    void insert_multipleElementAtLastIndex_receivedInSameOrder() {
        listUnderTest.insert(0, 123);
        listUnderTest.insert(1, 234);
        listUnderTest.insert(2, 345);

        assertEquals(123, listUnderTest.get(0));
        assertEquals(234, listUnderTest.get(1));
        assertEquals(345, listUnderTest.get(2));
    }

    @Test
    @Order(4)
    void insert_multipleElementAtFirstIndex_receivedInReversedOrder() {
        listUnderTest.insert(0, 123);
        listUnderTest.insert(0, 234);
        listUnderTest.insert(0, 345);

        assertEquals(345, listUnderTest.get(0));
        assertEquals(234, listUnderTest.get(1));
        assertEquals(123, listUnderTest.get(2));
    }

    @Test
    @Order(5)
    void insert_multipleElementAtDifferentIndices_receivedAtProperIndex() {
        listUnderTest.insert(0, 123);
        listUnderTest.insert(0, 234);
        listUnderTest.insert(0, 345);
        listUnderTest.insert(1, 456);

        assertEquals(345, listUnderTest.get(0));
        assertEquals(456, listUnderTest.get(1));
        assertEquals(234, listUnderTest.get(2));
        assertEquals(123, listUnderTest.get(3));
    }

    @Test
    @Order(6)
    void get_indexBiggerThanSizeOfList_throwsIndexOutOfBoundException() {
        listUnderTest.insert(0, 123);
        listUnderTest.insert(1, 234);
        listUnderTest.insert(2, 345);

        assertThrows(IndexOutOfBoundsException.class, () -> listUnderTest.get(10));
    }

    @Test
    @Order(7)
    void delete_elementFromFirstIndex_remainedElementsShiftedAccordingly() {
        fillListWithElements();

        listUnderTest.delete(0);

        assertEquals(234, listUnderTest.get(0));
        assertEquals(345, listUnderTest.get(1));
    }

    @Test
    @Order(8)
    void delete_elementFromMiddle_remainedElementsShiftedAccordingly() {
        // given
        fillListWithElements();

        // when
        listUnderTest.delete(2);

        // then
        assertEquals(123, listUnderTest.get(0));
        assertEquals(234, listUnderTest.get(1));
        assertEquals(456, listUnderTest.get(2));
        assertEquals(567, listUnderTest.get(3));
        assertEquals(678, listUnderTest.get(4));

        // when
        listUnderTest.delete(3);

        assertEquals(123, listUnderTest.get(0));
        assertEquals(234, listUnderTest.get(1));
        assertEquals(456, listUnderTest.get(2));
        assertEquals(678, listUnderTest.get(3));
    }

    @Test
    @Order(9)
    void indexOf_existingElement_returnsWithItsIndex() {
        fillListWithElements();

        assertEquals(0, listUnderTest.indexOf(123));
        assertEquals(3, listUnderTest.indexOf(456));
        assertEquals(5, listUnderTest.indexOf(678));
    }

    @Test
    @Order(10)
    void indexOf_elementNotInList_returnsMinusOne() {
        fillListWithElements();

        assertEquals(-1, listUnderTest.indexOf(444));
        assertEquals(-1, listUnderTest.indexOf(888));
        assertEquals(-1, listUnderTest.indexOf(666));
    }

    @Test
    @Order(11)
    void size_emptyList_returnsZero() {
        assertEquals(0, listUnderTest.size());
    }

    @Test
    @Order(12)
    void size_nonEmptyList_returnsAmountOfInsertedElements() {
        fillListWithElements();
        assertEquals(6, listUnderTest.size());
    }

    private void fillListWithElements() {
        listUnderTest.insert(0, 123);
        listUnderTest.insert(1, 234);
        listUnderTest.insert(2, 345);
        listUnderTest.insert(3, 456);
        listUnderTest.insert(4, 567);
        listUnderTest.insert(5, 678);
    }
}
