package telran.forum.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import telran.forum.model.Forum;
import telran.forum.model.ForumImpl;
import telran.forum.model.Post;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ForumImplTest {
    Forum forum;
    Post[] facebook;

    @BeforeEach
    void setUp() {
        forum = new ForumImpl();
        facebook = new Post[8];
        facebook[0] = new Post("User1", 1, "Title1", "Hello1");
        facebook[1] = new Post("User2", 2, "Title2", "Hello2");
        facebook[2] = new Post("User3", 3, "Title3", "Hello3");
        facebook[3] = new Post("User4", 4, "Title4", "Hello4");
        facebook[4] = new Post("User1", 5, "Title5", "Hello5");
        facebook[5] = new Post("User5", 7, "User5-1", "Hello7");
        facebook[6] = new Post("User5", 8, "User5-2", "Hello8");
        facebook[7] = new Post("User5", 9, "User5-3", "Hello9");

        for (int i = 0; i < facebook.length; i++) {
            forum.addPost(facebook[i]);


        }

    }

    @Test
    void addPost() {
        Post tester = new Post("User-Test", 6, "Test", "Bye");

        assertTrue(forum.addPost(tester));
        assertEquals(tester, forum.getPostById(6));
        assertEquals(facebook.length + 1, forum.size());
        assertFalse(forum.addPost(tester));
        forum.printPosts();
    }

    @Test
    void removePost() {
        assertFalse(forum.removePost(10));
        assertTrue(forum.removePost(3));
        assertNull(forum.getPostById(3));
        assertEquals(facebook.length - 1, forum.size());

        forum.printPosts();

    }

    @Test
    void updatePost() {
        assertTrue(forum.updatePost(4, "This is an updated content"));
        assertEquals("This is an updated content", forum.getPostById(4).getContent());
        forum.printPosts();

    }

    @Test
    void getPostById() {
        assertEquals(facebook[2], forum.getPostById(3));
        assertNull(forum.getPostById(10));
    }

    @Test
    void getPostsByAuthor() {
        LocalDateTime time1 = LocalDateTime.of(2022, 5, 15, 5, 5);
        LocalDateTime time2 = LocalDateTime.of(2022, 5, 16, 5, 5);
        facebook[0].setDate(time1);
        facebook[4].setDate(time2);
        Post[] actual = forum.getPostsByAuthor("User1");
        Arrays.sort(actual);
        Post[] expected = {facebook[0], facebook[4]};
        assertArrayEquals(expected, actual);

    }

    @Test
    void testGetPostsByAuthorTimeRange() {
//        facebook[5] = new Post("User5", 7, "User5-1", "Hello7");
//        facebook[6] = new Post("User5", 8, "User5-2", "Hello8");
//        facebook[7] = new Post("User5", 9, "User5-3", "Hello9");

        LocalDateTime from = LocalDateTime.of(2022, 1, 15, 5, 5);
        LocalDateTime to = LocalDateTime.of(2022, 6, 16, 5, 5);
        LocalDateTime time1 = LocalDateTime.of(2022, 5, 15, 5, 5);
        LocalDateTime time2 = LocalDateTime.of(2021, 5, 16, 5, 5);
        LocalDateTime time3 = LocalDateTime.of(2022, 5, 5, 5, 5);
        facebook[5].setDate(time1);
        facebook[6].setDate(time2);
        facebook[7].setDate(time3);
        Post[] actual = forum.getPostsByAuthor("User6", from.toLocalDate(), to.toLocalDate());
        Arrays.sort(actual);
        Post[] expected = {facebook[7], facebook[5]};
        assertArrayEquals(expected, actual);

    }

    @Test
    void size() {
        assertEquals(8, forum.size());
    }

    @Test

    void Likes() {
        facebook[0].addLike();
        facebook[0].addLike();
        facebook[0].addLike();
        assertEquals(3, facebook[0].getLikes());
        for (int i = 0; i < 250; i++) {
            facebook[5].addLike();

        }
        assertEquals(250, facebook[5].getLikes());

        System.out.println(facebook[5].getLikes());


    }
}