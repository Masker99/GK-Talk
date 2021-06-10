package com.rookie.gktalk.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class ContentDto implements Comparable<ContentDto>{
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date date;

    public void setDate(Date date){
        this.date = date;
    }

    public Date getDate(){
        return this.date;
    }

    @Override
    public int compareTo(ContentDto contentDto) {
        int result = this.date.compareTo(contentDto.getDate());

        if( result < 0){
            return 1;
        }else if (result > 0){
            return -1;
        }

        return 0;
    }

}
