package com.itis.android.myweatherapp.utils


data class Five<A, B, C, D, E>(
        var first: A? = null,
        var second: B? = null,
        var third: C? = null,
        var fourth: D? = null,
        var fiveth: E? = null) {

    companion object {
        fun <A, B, C, D, E> create(a: A, b: B, c: C, d: D, e: E): Five<A, B, C, D, E> = Five(a, b, c, d, e)

        fun <A, B, C, D, E> zipFunc(): io.reactivex.functions.Function5<A, B, C, D, E, Five<A, B, C, D, E>> = io.reactivex.functions.Function5 { a, b, c, d, e ->
            create(a, b, c, d, e)
        }
    }
}
