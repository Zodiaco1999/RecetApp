package com.mintic22g1.recetapp.data.repositories

import com.mintic22g1.recetapp.data.models.UserModel
import kotlinx.coroutines.delay

class LoginRepository {

    suspend fun login(email: String, password: String) {
        delay(1000)
        if(email != "brayan@gmail.com" || password != "12345678")
            throw Exception("Credenciales Invalidas")
    }

    suspend fun signUp(email: String, password: String, name: String, gender: String) {
        if(email != "brayan@gmail.com")
            throw Exception("Registro Invalido")
    }

    suspend fun currentUser(): UserModel {
        return UserModel(
            "1", "Brayan Baez", "brayan@gmail.com", "Masculino", "https://img.freepik.com/vector-premium/dibujo-dibujos-animados-programador_29937-8176.jpg"
        )
    }
}