package com.jhoglas.pethelthcontrol.ui.register

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import com.jhoglas.domain.repository.GenderRepository
import com.jhoglas.domain.repository.PetRepository
import com.jhoglas.domain.repository.RaceRepository
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class RegisterScreenKtTest{

    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var viewModel: RegisterViewModel
    private lateinit var genderRepository: GenderRepository
    private lateinit var raceRepository: RaceRepository
    private lateinit var petRepository: PetRepository

    private lateinit var navController: TestNavHostController

    @Before
    fun setUp() {
        genderRepository = mockk()
        raceRepository = mockk()
        petRepository = mockk()

        viewModel = RegisterViewModel(
            genderRepository = genderRepository,
            raceRepository = raceRepository,
            petRepository = petRepository
        )
    }

    private fun setContent() {
        composeTestRule.setContent {
            navController = TestNavHostController(
                context = ApplicationProvider.getApplicationContext()
            )
            RegisterScreen(
                navController = navController
            )
        }
    }



    @Test
    fun testRegisterScreen_enable_save_button_isDisabled() = runTest {
        setContent()

        composeTestRule.onNodeWithTag("SAVE_BUTTON").assertIsDisplayed()
        composeTestRule.onNodeWithTag("SAVE_BUTTON").assertIsNotEnabled()

    }

    @Test
    fun testRegisterScreen_enable_save_button_isEnable() = runTest {
        setContent()

        composeTestRule.waitUntil {
            composeTestRule.onAllNodesWithTag("loading_field").fetchSemanticsNodes().isEmpty()
        }

        composeTestRule.onNodeWithTag("PET_NAME").performTextInput("pet_name")
        composeTestRule.onNodeWithTag("PET_SURNAME").performTextInput("pet_surname")
        composeTestRule.onNodeWithTag("PET_GENDER").performClick()
        composeTestRule.waitUntil {
            composeTestRule.onNodeWithTag("PET_GENDER_BOTTOM_SHEET").isDisplayed()
        }
        composeTestRule.onNodeWithTag("PET_GENDER_BOTTOM_SHEET_0").performClick()
        composeTestRule.onNodeWithTag("PET_RACE").performClick()
        composeTestRule.waitUntil {
            composeTestRule.onNodeWithTag("PET_RACE_BOTTOM_SHEET").isDisplayed()
        }
        composeTestRule.onNodeWithTag("PET_RACE_BOTTOM_SHEET_0").performClick()
        composeTestRule.onNodeWithTag("PET_DATE").performTextInput("10022023")
        composeTestRule.onNodeWithTag("PET_WEIGHT").performTextInput("1105")
        composeTestRule.onNodeWithTag("PET_HAIR_COLOR").performTextInput("black")
        composeTestRule.onNodeWithTag("PET_HAIR_TYPE").performTextInput("long")

        composeTestRule.onNodeWithTag("SAVE_BUTTON").assertIsDisplayed()
        composeTestRule.onNodeWithTag("SAVE_BUTTON").assertIsEnabled()
    }

    @Test
    fun testRegisterScreen_enable_save_button_is_disabled_when_one_field_is_empty() = runTest {
        setContent()

        composeTestRule.waitUntil {
            composeTestRule.onAllNodesWithTag("loading_field").fetchSemanticsNodes().isEmpty()
        }

        composeTestRule.onNodeWithTag("PET_NAME").performTextInput("pet_name")
        composeTestRule.onNodeWithTag("PET_SURNAME").performTextInput("pet_surname")
        composeTestRule.onNodeWithTag("PET_GENDER").performClick()
        composeTestRule.waitUntil {
            composeTestRule.onNodeWithTag("PET_GENDER_BOTTOM_SHEET").isDisplayed()
        }
        composeTestRule.onNodeWithTag("PET_GENDER_BOTTOM_SHEET_0").performClick()
        composeTestRule.onNodeWithTag("PET_RACE").performClick()
        composeTestRule.waitUntil {
            composeTestRule.onNodeWithTag("PET_RACE_BOTTOM_SHEET").isDisplayed()
        }
        composeTestRule.onNodeWithTag("PET_RACE_BOTTOM_SHEET_0").performClick()
        composeTestRule.onNodeWithTag("PET_DATE").performTextInput("")
        composeTestRule.onNodeWithTag("PET_WEIGHT").performTextInput("1105")
        composeTestRule.onNodeWithTag("PET_HAIR_COLOR").performTextInput("black")
        composeTestRule.onNodeWithTag("PET_HAIR_TYPE").performTextInput("long")

        composeTestRule.onNodeWithTag("SAVE_BUTTON").assertIsDisplayed()
        composeTestRule.onNodeWithTag("SAVE_BUTTON").assertIsNotEnabled()
    }
}