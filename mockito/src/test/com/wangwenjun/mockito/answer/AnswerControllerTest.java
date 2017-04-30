package com.wangwenjun.mockito.answer;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;

import javax.servlet.http.HttpServletRequest;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

/***************************************
 * @author:Alex Wang
 * @Date:2017/4/30
 * QQ:532500648
 * QQ交流群:286081824
 ***************************************/
public class AnswerControllerTest {

    @Mock
    private AnswerService answerService;

    @Mock
    private HttpServletRequest request;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        when(answerService.getTemperature(anyString(), anyString())).thenAnswer(this::answers);
    }

    @Test
    public void testGetTemperatureWithLanzhouAndPM() {
        when(request.getParameter("location")).thenReturn("Lanzhou");
        when(request.getParameter("slot")).thenReturn("PM");

        AnswerController controller = new AnswerController();
        controller.setAnswerService(answerService);

        String temperature = controller.getAnswer(request);
        assertThat(temperature, equalTo("29.3"));
    }


    @Test
    public void testGetTemperatureWithLanzhouAndAM() {
        when(request.getParameter("location")).thenReturn("Lanzhou");
        when(request.getParameter("slot")).thenReturn("AM");

        AnswerController controller = new AnswerController();
        controller.setAnswerService(answerService);

        String temperature = controller.getAnswer(request);
        assertThat(temperature, equalTo("27.5"));
    }

    private String answers(InvocationOnMock invocationOnMock) {
        Object[] arguments = invocationOnMock.getArguments();
        if (arguments[0].equals("Lanzhou") && arguments[1].equals("AM")) {
            return "27.5";
        } else if (arguments[0].equals("Lanzhou") && arguments[1].equals("PM")) {
            return "29.3";
        } else if (arguments[0].equals("Guangzhou") && arguments[1].equals("AM")) {
            return "31.5";
        } else if (arguments[0].equals("Guangzhou") && arguments[1].equals("PM")) {
            return "39.8";
        } else {
            return "0.0";
        }
    }
}