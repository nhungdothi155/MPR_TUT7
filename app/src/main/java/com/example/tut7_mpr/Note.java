package com.example.tut7_mpr;

import java.io.Serializable;

public class Note implements Serializable {
  private int id;
  private String content;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


}
