package com.omstu.kvantorium.presentation.screens

import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.omstu.kvantorium.domain.datacontracts.model.NewsDataModel
import com.omstu.kvantorium.domain.datacontracts.model.UserRegisterDataModel
import com.omstu.kvantorium.presentation.screens.mainscreen.MainFragment
import com.omstu.kvantorium.presentation.screens.newsscreen.NewsFragment
import com.omstu.kvantorium.presentation.screens.notificationscreen.NotificationFragment
import com.omstu.kvantorium.presentation.screens.onboardingscreen.OnboardingFragment
import com.omstu.kvantorium.presentation.screens.profile.ProfileFragment
import com.omstu.kvantorium.presentation.screens.registrationscreens.singuppasswordscreen.SingUpPasswordFragment
import com.omstu.kvantorium.presentation.screens.registrationscreens.singuppersonaldatascreen.SingUpPersonalDataFragment
import com.omstu.kvantorium.presentation.screens.registrationscreens.singupusernamescreen.SingUpUserNameFragment
import com.omstu.kvantorium.presentation.screens.singinscreen.SingInFragment
import com.omstu.kvantorium.presentation.screens.unauthorizedusermainscreen.UnauthorizedUserMainFragment
import com.omstu.kvantorium.presentation.screens.updateemailscreen.UpdateEmailFragment
import com.omstu.kvantorium.presentation.screens.updatephone.UpdatePhoneFragment
import com.omstu.kvantorium.presentation.screens.updateprofilescreen.UpdateProfileFragment

object Screens {
    fun getOnboardingFragment() = FragmentScreen { OnboardingFragment() }
    fun getUnauthorizedUserMainFragment() = FragmentScreen { UnauthorizedUserMainFragment() }
    fun getSingUpUsernameFragment() = FragmentScreen { SingUpUserNameFragment() }
    fun getSingUpPersonalDataFragment(firstName: String, lastName: String) =
        FragmentScreen {
            SingUpPersonalDataFragment.newInstance(
                UserRegisterDataModel(
                    userFirstName = firstName,
                    userLastName = lastName
                )
            )
        }

    fun getSingUpPasswordFragment(user: UserRegisterDataModel) =
        FragmentScreen {
            SingUpPasswordFragment.newInstance(user = user)
        }

    fun getMainFragment() = FragmentScreen { MainFragment() }
    fun getSingInFragment() = FragmentScreen { SingInFragment() }
    fun getNewsFragment(news: NewsDataModel) = FragmentScreen {
        NewsFragment.newInstance(news)
    }

    fun getProfileFragment() = FragmentScreen {
        ProfileFragment()
    }

    fun getUpdateProfileScreenFragment() = FragmentScreen {
        UpdateProfileFragment()
    }

    fun getPhoneUpdateFragment() = FragmentScreen {
        UpdatePhoneFragment()
    }

    fun getEmailUpdateFragment() = FragmentScreen {
        UpdateEmailFragment()
    }

    fun getNotificationFragment() = FragmentScreen {
        NotificationFragment()
    }
}