
object ApplicationId{
    const val id = "com.jemmycalak.searchgithub"
}

object Release {
    const val versionCode = 1
    const val versionName = "1.0.0"
}

object Versions{

    const val compileSdk = 29
    const val buildTools = "29.0.2"
    const val minSdk = 16
    const val targetSdk = 28

    const val buildGradle = "3.6.0"

    const val kotlin = "1.3.61"
    const val coroutines = "1.1.1"
    const val safeArgs = "2.1.0-alpha01"

    const val koin = "2.0.1"
    const val appCompat = "1.1.0"
    const val nav = "2.0.0"

    const val core = "1.0.1"
    const val activity = "1.0.0"
    const val fragment = "1.1.0"
    const val constraintLayout = "1.1.3"
    const val material = "1.2.0-alpha06"
    const val gson = "2.8.5"
    const val okHttp = "3.12.1"
    const val retrofit = "2.6.1"

    const val lifecycle = "2.1.0-alpha04"
    const val shimmer = "0.5.0"
    const val glide = "4.9.0"
    const val glideProcessor = "4.8.0"
    const val room = "2.1.0"
}


object Libraries {
    // KOIN
    const val koin = "org.koin:koin-android:${Versions.koin}"
    const val koinCore = "org.koin:koin-core:${Versions.koin}"
    const val koinViewModel = "org.koin:koin-android-viewmodel:${Versions.koin}"

    const val gson = "com.google.code.gson:gson:${Versions.gson}"
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofitGsonConverter = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    const val httpLoggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okHttp}"

    const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    const val glideProcessor = "com.github.bumptech.glide:compiler:${Versions.glideProcessor}"

    const val roomCompiler = "androidx.room:room-compiler:${Versions.room}"
    const val roomRunTime = "androidx.room:room-runtime:${Versions.room}"
    const val roomKtx = "androidx.room:room-ktx:${Versions.room}"

    const val shimmer = "com.facebook.shimmer:shimmer:${Versions.shimmer}"
}

object AndroidLibraries {
    // KOTLIN
    const val kotlinCoroutineAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
    // ANDROID
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    const val coreKtx = "androidx.core:core-ktx:${Versions.core}"
    const val core = "androidx.core:core:${Versions.core}"
    const val constraintLayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val lifecycleViewModel =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    const val lifecycleExtensions = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycle}"
    const val activity = "androidx.activity:activity:${Versions.activity}"
    const val activityKtx = "androidx.activity:activity-ktx:${Versions.activity}"
    const val fragment = "androidx.fragment:fragment:${Versions.fragment}"
    const val fragmentKtx = "androidx.fragment:fragment-ktx:${Versions.fragment}"
    const val navigation = "androidx.navigation:navigation-ui-ktx:${Versions.nav}"
    const val navigationFrag = "androidx.navigation:navigation-fragment-ktx:${Versions.nav}"
    const val matrialDesign ="com.google.android.material:material:${Versions.material}"

}


object KotlinLibraries {
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    const val kotlinCoroutineCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
}


object Modules {
    const val navigation = ":navigation"
    const val common = ":common"
    const val model = ":data:model"
    const val local = ":data:local"
    const val remote = ":data:remote"
    const val repository = ":data:repository"
    const val listrepository = ":features:listrepository"
    const val widgets = ":features:widgets"
    const val auth = ":features:auth"
}


object Database {
    const val DB_NAME = "\"jemmycalak.github.db\""
    const val DB_VERSION = "1"
}