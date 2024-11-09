package utn.methodology

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import utn.methodology.domain.entities.Usuario

class UserCreateTest {
    private lateinit var userRepository: UserRepository

    @Before
    fun setUp() {
        userRepository = MockUserRepository()
    }

    @Test
    fun create_user_should_returns_201() {
        val validUser = Usuario(
            uuid = "123456",
            name = "Lionel Messi",
            username = "leomessi",
            email = "leo.messi@example.com",
            password = "barcabarca"
        )
        userRepository.save(validUser)
        val savedUser = userRepository.findByUsername("leomessi")
        assertNotNull(savedUser)
        assertEquals(validUser.username, savedUser?.username)
    }

    @Test
    fun create_user_should_returns_400() {
        val invalidUser = Usuario(
            uuid = "654321",
            name = "Cristiano Ronaldo",
            username = "cristiano",
            email = "",
            password = "madridmadrid"
        )
        try {
            userRepository.save(invalidUser)
            fail("Expected an IllegalArgumentException to be thrown")
        } catch (e: IllegalArgumentException) {
            assertEquals("Email cannot be empty", e.message)
        }
        val notSavedUser = userRepository.findByUsername("cristiano")
        assertNull(notSavedUser)
    }

    interface UserRepository {
        fun save(user: Usuario)
        fun findByUsername(username: String): Usuario?
        fun clean()
    }

    class MockUserRepository : UserRepository {
        private val users = mutableListOf<Usuario>()

        override fun save(user: Usuario) {
            if (user.email.isEmpty()) {
                throw IllegalArgumentException("Email cannot be empty")
            }
            users.add(user)
        }

        override fun findByUsername(username: String): Usuario? {
            return users.find { it.username == username }
        }

        override fun clean() {
            users.clear()
        }
    }
}
