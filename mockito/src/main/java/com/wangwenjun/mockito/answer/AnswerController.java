package com.wangwenjun.mockito.answer;

import javax.servlet.http.HttpServletRequest;

/***************************************
 * @author:Alex Wang
 * @Date:2017/4/30
 * QQ:532500648
 * QQ交流群:286081824
 ***************************************/
public class AnswerController {

    private AnswerService answerService;

    public String getAnswer(HttpServletRequest request){
        String location = request.getParameter("location");
        String timeSlot = request.getParameter("slot");

        return answerService.getTemperature(location,timeSlot);
    }

    public void setAnswerService(AnswerService answerService) {
        this.answerService = answerService;
    }
}