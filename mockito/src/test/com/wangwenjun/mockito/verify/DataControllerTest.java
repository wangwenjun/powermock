package com.wangwenjun.mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.http.HttpServletRequest;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.*;

/***************************************
 * @author:Alex Wang
 * @Date:2017/4/30
 * QQ:532500648
 * QQ交流群:286081824
 ***************************************/
public class DataControllerTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private DataService dataService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testDataControllerAdd() {
        DataController controller = new DataController();
        controller.setDataService(dataService);

        when(request.getParameter("type")).thenReturn("ADD");
        doNothing().when(dataService).save(new Data());

        String result = controller.saveOrUpdate(request);
        assertThat(result, equalTo("add"));

        verify(dataService, only()).save(isA(Data.class));
        verify(dataService, never()).update(isA(Data.class));
    }

    @Test
    public void testDataControllerUpdate() {
        DataController controller = new DataController();
        controller.setDataService(dataService);

        when(request.getParameter("type")).thenReturn("UPDATE");
        doNothing().when(dataService).update(new Data());

        String result = controller.saveOrUpdate(request);
        assertThat(result, equalTo("update"));

        verify(dataService, only()).update(isA(Data.class));
        verify(dataService, never()).save(isA(Data.class));
    }

    @Test(expected = RuntimeException.class)
    public void testDataControllerException() {
        DataController controller = new DataController();
        controller.setDataService(dataService);

        when(request.getParameter("type")).thenReturn("UNKNOWN");
        doNothing().when(dataService).update(new Data());
        doNothing().when(dataService).save(new Data());

        controller.saveOrUpdate(request);

        fail("The process should throw a exception at previous place.");
    }

    @Test
    public void testDataControllerCalcRate() {
        DataController controller = new DataController();
        controller.setDataService(dataService);

        String[] rates = {"1", "2", "3", "4"};

        when(request.getParameterValues("rate")).thenReturn(rates);
        doNothing().when(dataService).calc(isA(String.class));

        controller.calcRate(request);

        verify(dataService, times(4)).calc(isA(String.class));
    }

}