package com.SpEx7.entity;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

public class News implements Serializable {
    private int id;
    private String title;
    private String brief;
    private String content;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NotEmpty(message = "{err.empty}")
    @Size(max = 200, message = "{title.err.size}")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Nullable
    @Size(max = 400, message = "{brief.err.size}")
    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    @NotEmpty(message = "{err.empty}")
    @Size(max = 2000, message = "{content.err.size}")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    @NotNull(message = "{err.empty}")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public News() {
    }

    @Override
    public String toString() {
        return "News{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", brief='" + brief + '\'' +
                ", content='" + content + '\'' +
                ", date=" + date +
                '}';
    }
}
