package uz.tokhir.mockitotestingdemo.business;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SomeBusinessImplMockTest {

    @Test
    void findTheGreatestFromAllData_basicScenario() {
        DataService dataServiceMock = mock(DataService.class);
        //  dataServiceMock.retrieveAllData() => new int[] {25,15,5}
        when(dataServiceMock.retrieveAllData()).thenReturn(new int[] {25,15,5});
        SomeBusinessImpl business = new SomeBusinessImpl(dataServiceMock);
        assertEquals(25,business.findTheGreatestFromAllData());
    }

    @Test
    void findTheGreatestFromAllData_OneValue() {
        DataService dataServiceMock = mock(DataService.class);
        //  dataServiceMock.retrieveAllData() => new int[] {35}
        when(dataServiceMock.retrieveAllData()).thenReturn(new int[] {35});
        SomeBusinessImpl business = new SomeBusinessImpl(dataServiceMock);
        assertEquals(35,business.findTheGreatestFromAllData());
    }

}