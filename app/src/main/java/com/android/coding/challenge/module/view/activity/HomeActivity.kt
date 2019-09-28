package com.android.coding.challenge.module.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.android.coding.challenge.AppController
import com.android.coding.challenge.R
import com.android.coding.challenge.util.NetworkUtils
import kotlinx.android.synthetic.main.activity_home.*
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val inflater = fragment.findNavController().navInflater
        val graph = inflater.inflate(R.navigation.nav_graph_app)
        fragment.findNavController().graph = graph
        Navigation.findNavController(this, R.id.fragment).handleDeepLink(intent)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        Navigation.findNavController(this, R.id.fragment).handleDeepLink(intent)
    }
    override fun onSupportNavigateUp() =
        Navigation.findNavController(this, R.id.fragment).navigateUp()

    override fun setTitle(title: CharSequence?) {
        super.setTitle(title)
        toolbar.title = title
    }

    private val REWRITE_CACHE_CONTROL_INTERCEPTOR = Interceptor { chain ->
        val originalResponse = chain.proceed(chain.request())
        if (NetworkUtils(AppController.ApplicationContext).isConnected()) {
            val maxAge = 60 // read from cache for 1 minute
            originalResponse.newBuilder()
                .header("Cache-Control", "public, max-age=$maxAge")
                .build()
        } else {
            val maxStale = 60 * 60 * 24 * 28 // tolerate 4-weeks stale
            originalResponse.newBuilder()
                .header("Cache-Control", "public, only-if-cached, max-stale=$maxStale")
                .build()
        }
    }
}
