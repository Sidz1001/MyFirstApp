package com.example.myfirstapp.screens.products

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.myfirstapp.data.ProductViewModel
import com.example.myfirstapp.models.Product
import com.example.myfirstapp.navigation.ROUTE_UPDATE_PRODUCTS

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductListScreen(navController: NavHostController){
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {Text("PRODUCT LIST")},
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.Magenta,
                    titleContentColor = Color.White
                )
            )
        }
    ) {
        innerpadding ->
        val context = LocalContext.current
        val myproductViewModel = ProductViewModel(navController, context)
        val product = remember { mutableStateOf(Product()) }
        val products = remember { mutableStateListOf<Product>() }

        val isPreview = androidx.compose.ui.platform.LocalInspectionMode.current
        LaunchedEffect(Unit) {
            if (!isPreview) {
                myproductViewModel.allProducts(product, products)
            }
        }

        LazyColumn(
            modifier = Modifier
                .padding(innerpadding)
                .fillMaxSize()
                .padding(16.dp)
        ) {
            items(products){ productItem ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(6.dp)
                ) {
                    //image preview
                    AsyncImage(
                        model = productItem.imageUrl,
                        contentDescription = "product",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(180.dp)
                    )
                    Text(
                        text = productItem.name,
                        fontSize = 28.sp
                    )
                    Text(
                        text = "PRICE :Ksh.${productItem.price}"
                    )
                    Text(
                        text = productItem.description
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(10.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Button(onClick = {
                            myproductViewModel.deleteProduct(productItem.id)
                        }) {
                            Text(text = "Delete")
                        }
                        Button(onClick = {
                            navController.navigate(ROUTE_UPDATE_PRODUCTS + "/${productItem.id}")
                        }) {
                            Text(text = "Update")
                        }
                    }

                }
            }


        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun ProductListScreenPreview(){
    ProductListScreen(navController = rememberNavController())
}