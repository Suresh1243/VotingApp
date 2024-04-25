package com.voting.appClass

import android.app.Application

class AppClass : Application() {
    companion object {
        val list = arrayListOf<String>().apply {
            add("Conservative and Unionist Party")
            add("Labour Party")
            add("Liberal Democrats")
            add("Green Party of England and Wales")
            add("Brexit Party")
            add("Independent")
            add("Scottish National Party")
            add("UKIP")
            add("Plaid Cymru")
            add("Yorkshire Party")
            add("Christian Peoples Alliance")
            add("Official Monster Raving Loony Party")
            add("Scottish Green Party")
            add("Social Democratic Party")
            add("Liberal Party")
            add("Alliance Party of Northern Ireland")
        }
    }
}