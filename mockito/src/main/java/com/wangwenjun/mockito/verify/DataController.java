package com.wangwenjun.mockito.verify;

import javax.servlet.http.HttpServletRequest;

/***************************************
 * @author:Alex Wang
 * @Date:2017/4/30
 * QQ:532500648
 * QQ交流群:286081824
 ***************************************/
public class DataController {

    private DataService dataService;

    public String saveOrUpdate(HttpServletRequest request) {
        String type = request.getParameter("type");
        if (type.equals("ADD")) {
            dataService.save(new Data());
            return "add";
        } else if (type.equals("UPDATE")) {
            dataService.update(new Data());
            return "update";
        } else {
            throw new RuntimeException("Not support the type [" + type + "]");
        }
    }


    public void calcRate(HttpServletRequest request){
        String[] rates = request.getParameterValues("rate");
        for(String rate:rates){
            dataService.calc(rate);
        }
    }

    public void setDataService(DataService dataService) {
        this.dataService = dataService;
    }



}