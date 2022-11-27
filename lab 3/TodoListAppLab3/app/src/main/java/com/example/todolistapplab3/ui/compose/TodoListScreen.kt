@file:OptIn(ExperimentalFoundationApi::class)

package com.example.todolistapplab3.ui.compose

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.todolistapplab3.R
import com.example.todolistapplab3.model.Item
import com.example.todolistapplab3.model.ItemViewModel
import com.example.todolistapplab3.ui.theme.TodoListAppLab3Theme
import java.util.*

@Composable
fun TodoListScreen() {
    val viewModel: ItemViewModel = viewModel()
    val context = LocalContext.current
    var showDialog by remember { mutableStateOf(false) }

    Scaffold(
        backgroundColor = MaterialTheme.colors.surface,
        floatingActionButton = {
            FloatingActionButton(
                onClick = {showDialog = true}
            )
            {
                Icon(Icons.Filled.Add, contentDescription = "")
            }
        },
        content = {
            if (showDialog){
                addItemDialog(context, dismissDialog = {showDialog = false}, {viewModel.add(it)})
            }
            LazyColumn(
                contentPadding = PaddingValues(
                    vertical = 8.dp,
                    horizontal = 8.dp
                )
            )
            {
                items(viewModel.itemList, key={item -> item.id}) { item ->
                    Items(item = item, context, {viewModel.delete(it)})
                }
            }
        }
    )
}

@Composable
fun addItemDialog(context: Context, dismissDialog:() -> Unit, addItem: (Item) -> Unit){
    var itemTextField by remember {
        mutableStateOf("")
    }
    var authorTextField by remember {
        mutableStateOf("")
    }

    AlertDialog(
        onDismissRequest = { dismissDialog},
        title={Text(text = stringResource(id = R.string.addItem), style = MaterialTheme.typography.h6)},
        text = {
            Column(modifier = Modifier.padding(top=20.dp)) {
                TextField(label = {Text(text=stringResource(id = R.string.itemName))}, value = itemTextField, onValueChange = {itemTextField=it})
                Spacer(modifier = Modifier.height(10.dp))
                TextField(label = {Text(text=stringResource(id = R.string.creatorName))},value = authorTextField, onValueChange = {authorTextField=it})
            }
        },
        confirmButton = {
            Button(onClick = {
                if(itemTextField.isNotEmpty()) {
                    val newID = UUID.randomUUID().toString();
                    addItem(Item(newID, itemTextField, authorTextField))
                    Toast.makeText(
                        context,
                        context.resources.getString(R.string.itemAdded),
                        Toast.LENGTH_SHORT
                    ).show()
                }
                dismissDialog()
            })
            {
                Text(text = stringResource(id = R.string.add))
            }
        },dismissButton = {
            Button(onClick = {
                dismissDialog()
            }) {
                Text(text = stringResource(id = R.string.cancel))
            }
        }
    )
}

@Composable
fun deleteItemDialog(context: Context, dismissDialog:() -> Unit, item: Item, deleteItem: (Item) -> Unit){
    AlertDialog(
        onDismissRequest = { dismissDialog},
        title={Text(text = stringResource(id = R.string.delete), style = MaterialTheme.typography.h6)},
        confirmButton = {
            Button(onClick = {
                deleteItem(item)
                Toast.makeText(
                    context,
                    context.resources.getString(R.string.deleteItem),
                    Toast.LENGTH_SHORT
                ).show()
                dismissDialog()
            })
            {
                Text(text = stringResource(id = R.string.yes))
            }
        },dismissButton = {
            Button(onClick = {
                dismissDialog()
            }) {
                Text(text = stringResource(id = R.string.no))
            }
        }
    )
}

@Composable
fun Items(item: Item, context: Context, deleteItem: (Item) -> Unit) {
    var showDeleteDialog by remember { mutableStateOf(false) }
    val checkedState = remember { mutableStateOf(false) }
    Row() {
        Card(
            elevation = 4.dp,
            shape = RoundedCornerShape(10.dp),
            backgroundColor = MaterialTheme.colors.primary,
            contentColor = MaterialTheme.colors.onPrimary,
            border = BorderStroke(2.dp, color = MaterialTheme.colors.primaryVariant),
            modifier = Modifier
                .padding(8.dp)
//                .fillMaxWidth()
                .width(350.dp)
                .combinedClickable(
                    onClick = {
                        Toast
                            .makeText(
                                context,
                                context.resources.getString(R.string.readmsg) + " " + item.itemName,
                                Toast.LENGTH_SHORT
                            )
                            .show()
                    },
                    onLongClick = { showDeleteDialog = true }
                )
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(text = item.itemName, style = MaterialTheme.typography.h6)
                Text(text = item.createdBy, style = MaterialTheme.typography.body1)
            }

        }
        Checkbox(
            checked = checkedState.value,
            modifier = Modifier.align(Alignment.CenterVertically),
            onCheckedChange = { checkedState.value = it }
        )
    }
    if (showDeleteDialog){
        deleteItemDialog(context, dismissDialog = {showDeleteDialog = false}, item, deleteItem)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TodoListAppLab3Theme {
        TodoListScreen()
    }
}
