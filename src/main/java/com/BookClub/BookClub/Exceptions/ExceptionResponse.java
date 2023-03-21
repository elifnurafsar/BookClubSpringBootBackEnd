package com.BookClub.BookClub.Exceptions;

import java.sql.Timestamp;
import java.util.Date;

public class ExceptionResponse {

    private Timestamp timesStamp;
    private String message;
    private String detail;

    public ExceptionResponse(){
        Date date = new Date();
        this.timesStamp = new Timestamp(date.getTime());
        this.message = "no messages specified";
        this.detail = "no details given";
    }

    public ExceptionResponse(String s){
        Date date = new Date();
        this.timesStamp = new Timestamp(date.getTime());
        this.message = s;
        this.detail = "no details given";
    }

    public ExceptionResponse(String s1, String s2){
        Date date = new Date();
        this.timesStamp = new Timestamp(date.getTime());
        this.message = s1;
        this.detail = s2;
    }

    public Timestamp getTimesStamp() {
        return timesStamp;
    }

    public String getMessage() {
        return message;
    }

    public String getDetail() {
        return detail;
    }
}
