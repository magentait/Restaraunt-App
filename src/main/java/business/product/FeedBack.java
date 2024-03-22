package business.product;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FeedBack {
    private int mark;
    private String text;

    public FeedBack() {
        mark = 0;
        text = "";
    }

    public FeedBack(@JsonProperty("mark") int mark, @JsonProperty("text") String text) {
        this.mark = mark;
        this.text = text;
    }

    public int getRating() {
        return mark;
    }

    public void setRating(int mark) {
        this.mark = mark;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void display() {
        System.out.println("Оценка: «" + mark + "»\n " + text);
    }
}
