package com.project.whattowatch.ui.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.gson.Gson
import com.project.whattowatch.common.data.MovieModel
import com.project.whattowatch.common.repository.MovieRepository
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.powermock.api.mockito.PowerMockito
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner
import kotlin.random.Random


@RunWith(PowerMockRunner::class)
@PrepareForTest(
    fullyQualifiedNames = [
        "com.project.whattowatch.*",
        "android.content.SharedPreferences"]
)
class MainViewModelTest {
    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @Rule
    @JvmField
    val test = TestCoroutineRule()

    lateinit var viewModel: MainViewModel

    private val movieRepository = PowerMockito.mock(MovieRepository::class.java)

    @Before
    fun setup() {
        viewModel = MainViewModel(movieRepository)
    }

    var gson = Gson()

    @ExperimentalCoroutinesApi
    @Test
    @Throws(Exception::class)
    fun `return popular movie response after hit endpoint`() {
        test.runBlockingTest {
            var result: List<MovieModel>? = listOf(MovieModel(399566, "Godzilla vs. Kong", "In a time when monsters walk the Earth, humanity’s fight for its future sets Godzilla and Kong on a collision course that will see the two most powerful forces of nature on the planet collide in a spectacular battle for the ages.", "/pgqgaUx1cJb5oZQQ5v0tNARCeBp.jpg",7.3f, "2021-03-24"))
            var expected = """[{"id":399566,"title":"Godzilla vs. Kong","overview":"In a time when monsters walk the Earth, humanity’s fight for its future sets Godzilla and Kong on a collision course that will see the two most powerful forces of nature on the planet collide in a spectacular battle for the ages.","poster_path":"/pgqgaUx1cJb5oZQQ5v0tNARCeBp.jpg","vote_average":7.3,"release_date":"2021-03-24"}]"""
            Assert.assertEquals(expected, gson.toJson(result))
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    @Throws(Exception::class)
    fun `return upcoming movie response after hit endpoint`() {
        test.runBlockingTest {
            var result: List<MovieModel>? = listOf(MovieModel(399566, "Godzilla vs. Kong", "In a time when monsters walk the Earth, humanity’s fight for its future sets Godzilla and Kong on a collision course that will see the two most powerful forces of nature on the planet collide in a spectacular battle for the ages.", "/pgqgaUx1cJb5oZQQ5v0tNARCeBp.jpg",7.3f, "2021-03-24"))
            var expected = """[{"id":399566,"title":"Godzilla vs. Kong","overview":"In a time when monsters walk the Earth, humanity’s fight for its future sets Godzilla and Kong on a collision course that will see the two most powerful forces of nature on the planet collide in a spectacular battle for the ages.","poster_path":"/pgqgaUx1cJb5oZQQ5v0tNARCeBp.jpg","vote_average":7.3,"release_date":"2021-03-24"}]"""
            Assert.assertEquals(expected, gson.toJson(result))
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    @Throws(Exception::class)
    fun `return top rated movie response after hit endpoint`() {
        test.runBlockingTest {
            var result: List<MovieModel>? = listOf(MovieModel(399566, "Godzilla vs. Kong", "In a time when monsters walk the Earth, humanity’s fight for its future sets Godzilla and Kong on a collision course that will see the two most powerful forces of nature on the planet collide in a spectacular battle for the ages.", "/pgqgaUx1cJb5oZQQ5v0tNARCeBp.jpg",7.3f, "2021-03-24"))
            var expected = """[{"id":399566,"title":"Godzilla vs. Kong","overview":"In a time when monsters walk the Earth, humanity’s fight for its future sets Godzilla and Kong on a collision course that will see the two most powerful forces of nature on the planet collide in a spectacular battle for the ages.","poster_path":"/pgqgaUx1cJb5oZQQ5v0tNARCeBp.jpg","vote_average":7.3,"release_date":"2021-03-24"}]"""
            Assert.assertEquals(expected, gson.toJson(result))
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    @Throws(Exception::class)
    fun `return now playing movie response after hit endpoint`() {
        test.runBlockingTest {
            var result: List<MovieModel>? = listOf(MovieModel(399566, "Godzilla vs. Kong", "In a time when monsters walk the Earth, humanity’s fight for its future sets Godzilla and Kong on a collision course that will see the two most powerful forces of nature on the planet collide in a spectacular battle for the ages.", "/pgqgaUx1cJb5oZQQ5v0tNARCeBp.jpg",7.3f, "2021-03-24"))
            var expected = """[{"id":399566,"title":"Godzilla vs. Kong","overview":"In a time when monsters walk the Earth, humanity’s fight for its future sets Godzilla and Kong on a collision course that will see the two most powerful forces of nature on the planet collide in a spectacular battle for the ages.","poster_path":"/pgqgaUx1cJb5oZQQ5v0tNARCeBp.jpg","vote_average":7.3,"release_date":"2021-03-24"}]"""
            Assert.assertEquals(expected, gson.toJson(result))
        }
    }

    @Test
    fun `return open dialog category`() {
        viewModel.actionType.value = MainViewModel.OPEN_DIALOG_CATEGORY
        Assert.assertEquals(MainViewModel.OPEN_DIALOG_CATEGORY, viewModel.actionType.value)
    }

    @Test
    fun `return adapter category` () {
        val i = Random.nextInt(1, 4)

        when (i) {
            1 -> assertEquals(MainViewModel.POPULAR, "Popular")
            2 -> assertEquals(MainViewModel.UPCOMING, "Upcoming")
            3 -> assertEquals(MainViewModel.TOP_RATED, "Top Rated")
            4 -> assertEquals(MainViewModel.NOW_PLAYING, "Now Playing")
        }
    }
}