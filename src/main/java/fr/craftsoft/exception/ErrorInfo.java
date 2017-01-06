package fr.craftsoft.exception;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.xml.bind.annotation.XmlRootElement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by ayr on 02/01/17.
 */

public class ErrorInfo {
    private static final DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss.SSS");

    private String error;
    private String message;
    private String url;
    private String date;


    public ErrorInfo(){
    }


    public ErrorInfo(Throwable throwable) {
        this.error = getRootCause(throwable).getMessage();
        this.message = throwable.getMessage();
        this.date = dateFormat.format(new Date());

    }

    public ErrorInfo(Throwable throwable, String url) {
        this.error = getRootCause(throwable).getMessage();
        this.message = throwable.getMessage();
        this.url = url;
        this.date = dateFormat.format(new Date());
    }

    public static DateFormat getDateFormat() {
        return dateFormat;
    }

    public String getError() {
        return error;
    }

    public String getUrl() {
        return url;
    }

    public String getMessage() {
        return message;
    }

    public String getDate() {
        return date;
    }

    private Throwable getRootCause(Throwable throwable) {
        if (throwable.getCause() != null)
            return getRootCause(throwable.getCause());
        return throwable;
    }

    @Override
    public String toString() {
        return "ErrorInfo{" +
                "error='" + error + '\'' +
                ", message='" + message + '\'' +
                ", url='" + url + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}