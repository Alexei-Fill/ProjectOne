package com.SpEx7.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "NEWS")
@NamedNativeQueries( @NamedNativeQuery(name = "@INSERT_INTO_NEWS",
        query = "insert into NEWS (NEWS_BRIEF, NEWS_CONTENT, NEWS_DATE, NEWS_TITLE ) values (:brief, :content, :date_news, :title)"))
//        query = "insert into NEWS (NEWS_ID, NEWS_BRIEF, NEWS_CONTENT, NEWS_DATE, NEWS_TITLE ) values (NEWS_SEQUENCE.nextval, :brief, :content, :date_news, :title)"))
public class News implements Serializable {
    private int id;
    private String title;
    private String brief;
    private String content;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate date;

    @Id
    @Column(name = "NEWS_ID")
//    @SequenceGenerator(name = "newsGenerator", sequenceName = "NEWS_SEQUENCE", allocationSize = 1)
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "newsGenerator")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "NEWS_TITLE")
    @NotEmpty(message = "{err.empty}")
    @Size(max = 200, message = "{title.err.size}")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "NEWS_BRIEF")
    @Nullable
    @Size(max = 400, message = "{brief.err.size}")
    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    @Column(name = "NEWS_CONTENT")
    @NotEmpty(message = "{err.empty}")
    @Size(max = 2000, message = "{content.err.size}")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    @Column(name = "NEWS_DATE")
    @NotNull(message = "{err.empty}")

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public News() {
    }

    public News(int id, String title, String brief, String content, LocalDate date) {
        this.id = id;
        this.title = title;
        this.brief = brief;
        this.content = content;
        this.date = date;
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
