package uz.tokhir.mockitotestingdemo.list;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

public class ListTest {

    @Test
    void simpleTest() {
        List listMock = mock(List.class);
        // listMock.size() => 3
        when(listMock.size()).thenReturn(3);
        assertEquals(3,listMock.size());
    }

    @Test
    void multipleReturns() {
        List listMock = mock(List.class);
        // listMock.size() => 3
        when(listMock.size()).thenReturn(1).thenReturn(2);
        assertEquals(1,listMock.size());
        assertEquals(2,listMock.size());
        assertEquals(2,listMock.size());
        assertEquals(2,listMock.size());
    }

    @Test
    void specificParameters() {
        List listMock = mock(List.class);
        // listMock.size() => 3
        when(listMock.get(0)).thenReturn("SomeString");
        when(listMock.get(1)).thenReturn("Java");
        assertEquals("SomeString",listMock.get(0));
        assertEquals("Java",listMock.get(1));
        assertEquals(null,listMock.get(3));
    }

    @Test
    void genericParameters() {
        List listMock = mock(List.class);
        when(listMock.get(Mockito.anyInt())).thenReturn("SomeOtherString");
        assertEquals("SomeOtherString",listMock.get(0));
        assertEquals("SomeOtherString",listMock.get(1));
        assertEquals("SomeOtherString",listMock.get(3));
    }
}
