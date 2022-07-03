package telran.forum.model;

import java.time.LocalDate;

public class ForumImpl implements Forum {

    @Override
    public boolean addPost(Post post) {
        return false;
    }

    @Override
    public boolean removePost(int postId) {
        return false;
    }

    @Override
    public boolean updatePost(int postID, String newContent) {
        return false;
    }

    @Override
    public Post getPostById(int postId) {
        return null;
    }

    @Override
    public Post[] getPostsByAuthor(String author) {
        return new Post[0];
    }

    @Override
    public Post[] getPostsByAuthor(String author, LocalDate dateFrom, LocalDate dateTo) {
        return new Post[0];
    }

    @Override
    public int size() {
        return 0;
    }
}
