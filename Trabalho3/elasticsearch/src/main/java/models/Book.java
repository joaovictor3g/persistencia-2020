package models;

import org.springframework.data.elasticsearch.annotations.Document;
import java.util.List;

@Document(indexName = "books")
public class Book {
    private int id;
    private String title;
    private String publishYear;
    private List<String> authors;
    private String category;
    private float value;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublishYear() {
        return publishYear;
    }

    public void setPublishYear(String publishYear) {
        this.publishYear = publishYear;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", publishYear='" + publishYear + '\'' +
                ", authors=" + authors +
                ", category='" + category + '\'' +
                ", price=R$" + value +
                '}';
    }
}
