package com.cassiano.myapplication

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.cassiano.myapplication.home.view.viewmodel.RecipeDetailViewModel
import com.cassiano.myapplication.utils.ResourceManager
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(application = ApplicationTest::class)
class RecipeDetailViewModelTest {

    private lateinit var viewModelMock: RecipeDetailViewModel
    private lateinit var context: Context
    private var resourceManager = ResourceManager(context = ApplicationProvider.getApplicationContext())

    @Before
    fun init() {
        viewModelMock = RecipeDetailViewModel(resourceManager)
        context = ApplicationProvider.getApplicationContext()
    }

    @Test
    fun `check if fav icon is set correctly`() {
        viewModelMock.run {
            isFavorite.set(true)
            setFavIcon()

            assertThat(
                Shadows.shadowOf(favIcon.get()).createdFromResId,
                `is`(Shadows.shadowOf(context.getDrawable(R.drawable.ic_fav)).createdFromResId)
            )
        }
    }

    @Test
    fun `check if unfav icon is set correctly`() {
        viewModelMock.run {
            isFavorite.set(false)
            setFavIcon()

            assertThat(
                Shadows.shadowOf(favIcon.get()).createdFromResId,
                `is`(Shadows.shadowOf(context.getDrawable(R.drawable.ic_unfav)).createdFromResId)
            )
        }
    }
}
