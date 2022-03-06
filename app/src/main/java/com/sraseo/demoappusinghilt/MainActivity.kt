package com.sraseo.demoappusinghilt

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.sraseo.State
import com.sraseo.demoappusinghilt.databinding.ActivityMainBinding
import com.sraseo.viewmodel.SignupViewModel
import com.sraseo.ytclient.model.Order
import com.sraseo.ytclient.model.ReqSignup
import com.sraseo.ytclient.model.ResSignup
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private val signupViewModel: SignupViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)
        signupViewModel.getData()
        binding.fab.setOnClickListener { view ->
            lifecycleScope.launchWhenCreated {
                signupViewModel.apiState.collect {
                    when (it) {
                        is State.Loading -> {
                            binding.progressBar.visibility = View.VISIBLE
                        }
                        is State.Success -> {
                            binding.progressBar.visibility = View.INVISIBLE
                            val order: Order = it.data as Order
                            Toast.makeText(baseContext, order.data.toString(), Toast.LENGTH_SHORT)
                                .show()
                        }
                        is State.Failure -> {
                            binding.progressBar.visibility = View.INVISIBLE
                        }
                        is State.Empty -> {
                            binding.progressBar.visibility = View.INVISIBLE
                        }
                    }

                }
            }
        }


        binding.fab1.setOnClickListener { view ->
            val reqSignup = ReqSignup("neeraj","ns@ns.com","test","admin","123456")
            signupViewModel.signUp(reqSignup).observe(this, Observer {
                when (it) {
                    is State.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }
                    is State.Success -> {
                        binding.progressBar.visibility = View.INVISIBLE
                        val resSignup: ResSignup = it.data as ResSignup
                        Toast.makeText(baseContext, resSignup.toString(), Toast.LENGTH_SHORT)
                            .show()
                    }
                    is State.Failure -> {
                        binding.progressBar.visibility = View.INVISIBLE
                    }
                    is State.Empty -> {
                        binding.progressBar.visibility = View.INVISIBLE
                    }
                }
            })
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}