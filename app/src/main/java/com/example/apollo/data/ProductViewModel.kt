@file:Suppress("DEPRECATION")

package com.example.apollo.data



import android.app.ProgressDialog
import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.navigation.NavHostController
import com.example.apollo.models.Product
import com.example.apollo.models.Upload
import com.example.apollo.navigation.ROUTE_ADD_PRODUCT
import com.example.apollo.navigation.ROUTE_HOME
import com.example.apollo.navigation.ROUTE_LOGIN
import com.example.apollo.navigation.ROUTE_VIEW_PRODUCT
import com.example.apollo.navigation.ROUTE_VIEW_UPLOAD_SCREEN
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage

class ProductViewModel(var navController: NavHostController, var context: Context) {
    private var authRepository: AuthViewModel = AuthViewModel(navController, context)
    private var progress: ProgressDialog

    init {
        if (!authRepository.isloggedin()) {
            navController.navigate(ROUTE_LOGIN)
        }
        progress = ProgressDialog(context)
        progress.setTitle("Saving \uD83D\uDCBE")
        progress.setMessage("Please wait...")
    }


    fun saveProduct(productName: String, productQuantity: String, productPrice: String) {
        val id = System.currentTimeMillis().toString()
        val productData = Product(productName, productQuantity, productPrice, id)
        val productRef = FirebaseDatabase.getInstance().getReference().child("Products/$id")
        progress.show()
        productRef.setValue(productData).addOnCompleteListener {
            progress.dismiss()
            if (it.isSuccessful) {
                Toast.makeText(context, "Saving successful", Toast.LENGTH_SHORT).show()
                navController.navigate(ROUTE_ADD_PRODUCT)
            } else {
                Toast.makeText(context, "ERROR: ${it.exception!!.message}", Toast.LENGTH_SHORT).show()
                navController.navigate(ROUTE_HOME)
            }
        }
    }

    fun viewProducts(
        product: MutableState<Product>,
        products: SnapshotStateList<Product>
    ): SnapshotStateList<Product> {
        val ref = FirebaseDatabase.getInstance().getReference().child("Products")

//        progress.show()
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
//                progress.dismiss()
                products.clear()
                for (snap in snapshot.children) {
                    val value = snap.getValue(Product::class.java)
                    product.value = value!!
                    products.add(value)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show()
            }
        })
        return products
    }

    fun deleteProduct(id: String) {
        val delRef = FirebaseDatabase.getInstance().getReference().child("Products/$id")
//        progress.show()
        delRef.removeValue().addOnCompleteListener {
//            progress.dismiss()
            if (it.isSuccessful) {
                Toast.makeText(context, "Product deleted", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, it.exception!!.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun updateProduct(name: String, quantity: String, price: String, id: String) {
        val updateRef = FirebaseDatabase.getInstance().getReference().child("Products/$id")
//        progress.show()
        val updateData = Product(name, quantity, price, id)
        updateRef.setValue(updateData).addOnCompleteListener {
//            progress.dismiss()
            if (it.isSuccessful) {
                Toast.makeText(context, "Update successful", Toast.LENGTH_SHORT).show()
                navController.navigate(ROUTE_VIEW_PRODUCT)
            } else {
                Toast.makeText(context, it.exception!!.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun saveProductWithImage(productName:String,
                             productQuantity:String,
                             productPrice:String,
                             filePath: Uri
    ){
        val id = System.currentTimeMillis().toString()
        val storageReference = FirebaseStorage.getInstance().getReference().child("Uploads/$id")

        storageReference.putFile(filePath).addOnCompleteListener{
            progress.show()
            if (productName.isBlank() || productPrice.isBlank() || productQuantity.isBlank()){
                progress.dismiss()
                Toast.makeText(context, "Fill all the fields please", Toast.LENGTH_LONG).show()
                navController.navigate(ROUTE_ADD_PRODUCT)
            } else if (it.isSuccessful){
                progress.dismiss()
                // Proceed to store other data into the db
                storageReference.downloadUrl.addOnSuccessListener {
                    val imageUrl = it.toString()
                    val houseData = Upload(
                        productName,
                        productQuantity,
                        productPrice,
                        imageUrl,id
                    )
                    val dbRef = FirebaseDatabase.getInstance().getReference().child("Uploads/$id")
                    dbRef.setValue(houseData)
                    Toast.makeText(context, "Upload successful", Toast.LENGTH_SHORT).show()
                    navController.navigate(ROUTE_VIEW_UPLOAD_SCREEN)
                }
            }else{
                progress.dismiss()
                Toast.makeText(context, it.exception!!.message, Toast.LENGTH_SHORT).show()
            }
        }

    }

    fun viewUploads(
        upload:MutableState<Upload>,
        uploads:SnapshotStateList<Upload>)
    : SnapshotStateList<Upload> {
        val ref = FirebaseDatabase.getInstance().getReference().child("Uploads")
        progress = ProgressDialog(context)
        progress.setTitle("Loading ")
        progress.setMessage("Please wait...")

        progress.show()
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                progress.dismiss()
                uploads.clear()
                for (snap in snapshot.children){
                    val value = snap.getValue(Upload::class.java)
                    upload.value = value!!
                    uploads.add(value)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show()
            }
        }
        )
        return uploads
    }

}
