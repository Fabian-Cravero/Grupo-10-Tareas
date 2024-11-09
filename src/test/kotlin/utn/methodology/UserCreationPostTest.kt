package utn.methodology

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import utn.methodology.domain.entities.Post

class UserCreationPostTest {
    private lateinit var postRepository: PostRepository

    @Before
    fun setUp() {
        postRepository = MockPostRepository()
    }

    @Test
    fun create_post_should_returns_201() {
        val validPost = Post(
            uuid = "123456",
            uuidUser = "leomessi",
            text = "ehh fulbo",
            date = "2024-11-08"
        )
        postRepository.save(validPost)
        val savedPost = postRepository.findByUuid("123456")
        assertNotNull(savedPost)
        assertEquals(validPost.uuidUser, savedPost?.uuidUser)
    }

    @Test
    fun create_post_should_returns_400() {
        val invalidPost = Post(
            uuid = "654321",
            uuidUser = "cristianoronaldo",
            text = "",
            date = "2024-11-08"
        )
        try {
            postRepository.save(invalidPost)
            fail("Expected an IllegalArgumentException to be thrown")
        } catch (e: IllegalArgumentException) {
            assertEquals("Post cannot be empty", e.message)
        }
        val notSavedPost = postRepository.findByUuid("654321")
        assertNull(notSavedPost)
    }

    interface PostRepository {
        fun save(post: Post)
        fun findByUuid(uuid: String): Post?
        fun clean()
    }

    class MockPostRepository : PostRepository {
        private val posts = mutableListOf<Post>()

        override fun save(post: Post) {
            if (post.text.isEmpty()) {
                throw IllegalArgumentException("Post cannot be empty")
            }
            posts.add(post)
        }

        override fun findByUuid(uuid: String): Post? {
            return posts.find { it.uuid == uuid }
        }

        override fun clean() {
            posts.clear()
        }
    }
}
