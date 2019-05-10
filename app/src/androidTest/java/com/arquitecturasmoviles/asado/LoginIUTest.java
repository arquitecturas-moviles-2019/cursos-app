package com.arquitecturasmoviles.asado;

import android.support.test.espresso.action.ViewActions;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.rule.ActivityTestRule;

import androidx.test.espresso.intent.Intents;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;

@RunWith(AndroidJUnit4.class)
public class LoginIUTest {
    private String email = "jack@correo.com";
    private String password = "123456";

        @Rule
        public ActivityTestRule<LoginActivity> testRule =
                new ActivityTestRule<>(LoginActivity.class);

        @Test
        public void loginUser(){
            onView(withId(R.id.email))
                    .perform(typeText(email), ViewActions.closeSoftKeyboard());

            onView(withId(R.id.password))
                    .perform(typeText(password), ViewActions.closeSoftKeyboard());

            onView(withId(R.id.email_sign_in_button)).perform(click());

//            intended(hasComponent(MyCoursesAndEventsActivity.class.getName()));
//            Intents.release();
        }
}

