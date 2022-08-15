package com.mintic22g1.recetapp.data.repositories

import android.graphics.Bitmap
import android.graphics.drawable.AnimatedImageDrawable
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.mintic22g1.recetapp.data.models.UserModel
import com.mintic22g1.recetapp.utils.USER_COLLECTION
import kotlinx.coroutines.delay
import kotlinx.coroutines.tasks.await
import java.io.ByteArrayOutputStream

class LoginRepository(private val authService: FirebaseAuth,
                      private val fireStoreService: FirebaseFirestore, private val storageService: FirebaseStorage) {

    suspend fun login(email: String, password: String) {
        try {
            authService.signInWithEmailAndPassword(email, password).await()
        } catch (e: FirebaseAuthException) {
            throw Exception(e.message)
        }
    }

    suspend fun signUp(email: String, password: String, name: String, gender: String) {
        try {
            authService.createUserWithEmailAndPassword(email, password).await()
            val user = authService.currentUser!!
            val profileUpdate = userProfileChangeRequest {
                displayName = name
            }
            user.updateProfile(profileUpdate).await()
            val userInfo = hashMapOf(
                "gender" to gender
            )
            fireStoreService.collection(USER_COLLECTION).document(user.uid)
                .set(userInfo).await()

        } catch (e: FirebaseAuthException) {
            throw Exception(e.message)
        }
    }

    suspend fun currentUser(): UserModel? {
        val user = authService.currentUser
        if (user != null) {
            val info = fireStoreService.collection(USER_COLLECTION).document(user.uid).get().await()
            var image: String? = null
            if(user.photoUrl != null) {
                image = user.photoUrl.toString()
            }
            return UserModel(
                user.uid, user.displayName!!, user.email!!, info.get("gender").toString(), image
            )
        }
        return null
    }

    suspend fun logOut(): UserModel? {
        authService.signOut()
        return null
    }

    suspend fun uploadPhoto(image: Bitmap): UserModel? {
        val user = authService.currentUser
        if(user != null) {
            val ref = storageService.reference
            val imageRef = ref.child("${user.uid}.jpg")
            val baos: ByteArrayOutputStream = ByteArrayOutputStream()
            image.compress(Bitmap.CompressFormat.JPEG, 100, baos)
            imageRef.putBytes(baos.toByteArray()).await()
            val url = imageRef.downloadUrl.await()
            val profileUpdate = userProfileChangeRequest {
                photoUri = url
            }
            user.updateProfile(profileUpdate).await()
        }
        return this.currentUser()
    }
}