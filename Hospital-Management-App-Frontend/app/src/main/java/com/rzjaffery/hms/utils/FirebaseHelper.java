package com.rzjaffery.hms.utils;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseHelper {

    // This will point to the "users" node in Firebase Realtime Database
    public static DatabaseReference getUsersReference() {
        return FirebaseDatabase.getInstance().getReference("users");
    }
}
