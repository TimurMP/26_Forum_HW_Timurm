package telran.forum.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Post  implements Comparable<Post>{
    String author;
    int postId;
    String title;
    String content;
    LocalDateTime date;
    int likes;

    public Post(String author, int postId, String title, String content) {
        this.author = author;
        this.postId = postId;
        this.title = title;
        this.content = content;
        this.date =  LocalDateTime.now();
    }

    public String getAuthor() {
        return author;
    }

    public int getPostId() {
        return postId;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public int getLikes() {
        return likes;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Post{" +
                "author='" + author + '\'' +
                ", postId=" + postId +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", date=" + date +
                ", likes=" + likes +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Post)) return false;
        Post post = (Post) o;
        return postId == post.postId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(postId);
    }

    public int addLike(){
        likes++;
        return likes;
    }

    @Override
    public int compareTo(Post o) {
        return date.compareTo(o.date);
    }


}
