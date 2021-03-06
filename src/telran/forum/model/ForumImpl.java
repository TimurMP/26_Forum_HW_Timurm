package telran.forum.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.function.Predicate;

public class ForumImpl implements Forum {

    Post[] posts;
    int size;

    public ForumImpl() {
        posts = new Post[0];
    }

    @Override
    public boolean addPost(Post post) {
        if (getPostById(post.getPostId()) != null) {
            return false;
        }
        int index = Arrays.binarySearch(posts, 0, posts.length, post);
        index = index < 0 ? -index - 1 : index;
        Post[] postCopy = new Post[size + 1];
        System.arraycopy(posts, 0, postCopy, 0, index);
        System.arraycopy(posts, index, postCopy, index + 1, posts.length - index);
        postCopy[index] = post;
        size++;
        posts = postCopy;


        return true;
    }

    @Override
    public boolean removePost(int postId) {
        Post pattern = new Post(null, postId, null, null);
        for (int i = 0; i < posts.length; i++) {
            if (pattern.equals(posts[i])) {
                Post[] postCopy = new Post[size - 1];
                System.arraycopy(posts, 0, postCopy, 0, i);
                System.arraycopy(posts, i + 1, postCopy, i, postCopy.length - i);
                size--;
                posts = postCopy;
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean updatePost(int postID, String newContent) {
        Post post = getPostById(postID);
        if (post == null) {
            return false;
        }
        post.setContent(newContent);
        return true;
    }

    @Override
    public Post getPostById(int postId) {
        Post pattern = new Post(null, postId, null, null);
        for (int i = 0; i < posts.length; i++) {
            if (posts[i].equals(pattern)) {
                System.out.println("Get Post by Id returned: " + posts[i]);
                return posts[i];
            }

        }
        return null;
    }

    @Override
    public Post[] getPostsByAuthor(String author) {
        return findPostsByPredicate(post -> post.getAuthor() == author, posts);

    }

    @Override
    public Post[] getPostsByAuthor(String author, LocalDate dateFrom, LocalDate dateTo) {
        Post[] res = getPostsByAuthor(author);
        return findPostsByPredicate(post -> post.getDate().isAfter(dateFrom.atStartOfDay()) &&
                post.getDate().isBefore(LocalDateTime.of(dateTo, LocalTime.MAX)), res);
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
        System.out.println();

    }


    private Post[] findPostsByPredicate(Predicate<Post> predicate, Post[] arr) {
        Post[] res = new Post[size];
        int j = 0;
        for (int i = 0; i < posts.length; i++) {
            if (predicate.test(posts[i])) {
                res[j++] = posts[i];
            }


        }
        System.out.println("Predicate Array: ");
        for (int i = 0; i < res.length; i++) {
            System.out.println(res[i]);

        }
        System.out.println();
        return Arrays.copyOf(res, j);
    }
}
