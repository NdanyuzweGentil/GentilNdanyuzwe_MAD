package com.example.todolistapplab3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.todolistapplab3.model.ItemViewModel
import com.example.todolistapplab3.ui.compose.TodoListScreen
import com.example.todolistapplab3.ui.theme.TodoListAppLab3Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TodoListAppLab3Theme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    TodoListScreenSetup()
                }
            }
        }
    }

    @Composable
    fun TodoListScreenSetup(){
        TodoListScreen()
    }
}

