package telran.forum.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import telran.forum.model.Forum;
import telran.forum.model.ForumImpl;
import telran.forum.model.Post;

import static org.junit.jupiter.api.Assertions.*;

class ForumImplTest {
    Forum forum;
    Post[] facebook;

    @BeforeEach
    void setUp() {
        forum = new ForumImpl();
        facebook = new Post[5];
        facebook[0] = new Post("User1", 1, "Title1", "Hello1");
        facebook[1] = new Post("User2", 2, "Title2", "Hello2");
        facebook[2] = new Post("User3", 3, "Title3", "Hello3");
        facebook[3] = new Post("User4", 4, "Title4", "Hello4");
        facebook[4] = new Post("User5", 5, "Title5", "Hello5");


        for (int i = 0; i < facebook.length; i++) {
            forum.addPost(facebook[i]);


        }

    }

    @Test
    void addPost() {
        Post tester = new Post("User-Test", 6, "Test", "Bye");

        assertTrue(forum.addPost(tester));
        assertEquals(tester, forum.getPostById(6));
        assertEquals(facebook.length+1, forum.size());
        assertFalse(forum.addPost(tester));
        forum.printPosts();
    }

    @Test
    void removePost() {
        assertFalse(forum.removePost(10));
        assertTrue(forum.removePost(3));
        assertNull(forum.getPostById(3));
        assertEquals(facebook.length-1, forum.size());


        forum.printPosts();

    }

    @Test
    void updatePost() {
    }

    @Test
    void getPostById() {
        assertEquals(facebook[2], forum.getPostById(3));
        assertNull(forum.getPostById(10));
    }

    @Test
    void getPostsByAuthor() {
    }

    @Test
    void testGetPostsByAuthor() {
    }

    @Test
    void size() {
    }
}