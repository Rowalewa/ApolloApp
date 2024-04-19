package com.example.apollo.models

class User {
    private var email: String = ""
    private var pass: String = ""
    private var userid: String = "" // unique column identifier user

    constructor(
        email: String,
        pass: String,
        userid: String
    ){
        this.email = email
        this.pass = pass
        this.userid = userid
    }

    constructor()
}