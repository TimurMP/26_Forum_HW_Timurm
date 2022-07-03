package telran.forum.model;

import java.time.LocalDate;
import java.util.Arrays;

public class ForumImpl implements Forum {

    Post posts[];
    int size;

    public ForumImpl() {
        posts = new Post[0];
    }

    @Override
    public boolean addPost(Post post) {
        //TODO: add verification
        if (getPostById(post.getPostId())!=null){
            return false;
        }
        int index =  Arrays.binarySearch(posts, 0, posts.length, post);
        index = index < 0 ? -index - 1 : index;
        Post[] postCopy = new Post[size+1];
        System.arraycopy(posts, 0, postCopy,0, index);
        System.arraycopy(posts, index, postCopy, index+1, posts.length-index);
        postCopy[index] = post;
        size++;
        posts = postCopy;



        return true;
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
        Post pattern = new Post(null, postId, null,null);
        for (int i = 0; i < posts.length; i++) {
            if (posts[i].equals(pattern)){
                System.out.println("Get Post by Id returned: "  + posts[i]);
                return posts[i];
            }

        }
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
        return size;
    }

    @Override
    public void printPosts() {
        for (int i = 0; i < posts.length; i++) {
            System.out.println(posts[i]);

        }

    }
}
