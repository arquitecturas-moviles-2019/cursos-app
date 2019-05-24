package com.arquitecturasmoviles.asado;

import android.support.test.espresso.action.ViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class RegisterIUTest {
    private String name = "jack";
    private String surname = "Sparrow";
    private String email = "jack_sparrrow@correo.com";
    private String password = "123456";
    private String passwordConfirm = "123456";

    @Rule
    public ActivityTestRule<RegisterActivity> testRule =
            new ActivityTestRule<>(RegisterActivity.class);

    @Test
    public void registerUser(){
        onView(withId(R.id.name))
                .perform(typeText(name), ViewActions.closeSoftKeyboard());

        onView(withId(R.id.surname))
                .perform(typeText(surname), ViewActions.closeSoftKeyboard());

        onView(withId(R.id.email))
                .perform(typeText(email), ViewActions.closeSoftKeyboard());

        onView(withId(R.id.password))
                .perform(typeText(password), ViewActions.closeSoftKeyboard());

        onView(withId(R.id.passwordConfirm))
                .perform(typeText(passwordConfirm), ViewActions.closeSoftKeyboard());

        onView(withId(R.id.email_sign_up_button)).perform(click());


//            intended(hasComponent(MyCoursesAndEventsActivity.class.getName()));
//            Intents.release();

    }
}