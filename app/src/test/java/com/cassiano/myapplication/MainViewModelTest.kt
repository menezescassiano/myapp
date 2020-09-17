package com.cassiano.myapplication

import com.cassiano.myapplication.home.model.Recipe
import com.cassiano.myapplication.home.view.viewmodel.MainViewModel
import com.cassiano.myapplication.repository.DataRepository
import io.mockk.clearAllMocks
import io.mockk.mockk
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(application = ApplicationTest::class)
class MainViewModelTest {

    private lateinit var viewModelMock: MainViewModel
    private lateinit var recipe: Recipe
    private var list = ArrayList<Recipe>()
    private val repository: DataRepository = mockk(relaxed = true)

    @Before
    fun init() {
        clearAllMocks()

        viewModelMock = MainViewModel(repository)
        recipe = Recipe(
            "516 kcal",
            "47 g",
            "There’s nothing like the simple things in life - the smell of freshly cut grass, sitting outside on a nice sunny day, spending time with friends and family. Well here is a recipe that delivers simple culinary pleasures - some nice fresh fish with a crispy crust, crunchy potato wedges and some delightfully sweet sugar snap peas flavoured with cooling mint. Slip into something comfortable and relax into a delicious dinner!",
            0,
            "8 g",
            "with Sweet Potato Wedges and Minted Snap Peas",
            "533143aaff604d567f8b4571",
            "https://img.hellofresh.com/f_auto,q_auto/hellofresh_s3/image/533143aaff604d567f8b4571.jpg",
            "Crispy Fish Goujons",
            "43 g",
            "https://img.hellofresh.com/f_auto,q_auto,w_300/hellofresh_s3/image/533143aaff604d567f8b4571.jpg",
            "PT35M"
        )

        list.add(recipe)
        viewModelMock.list = list
    }

    @Test
    fun `checks if the list of recipes is not empty`() {
        viewModelMock.run {
            Assert.assertFalse(list.isEmpty())
        }
    }

    @Test
    fun `checks if the name of the recipe is correct`() {
        Assert.assertEquals(viewModelMock.list[0].name, "Crispy Fish Goujons")
    }

    @Test
    fun `checks if the thumb url of the recipe is correct`() {
        Assert.assertEquals(
            viewModelMock.list[0].thumb,
            "https://img.hellofresh.com/f_auto,q_auto,w_300/hellofresh_s3/image/533143aaff604d567f8b4571.jpg"
        )
    }
}
