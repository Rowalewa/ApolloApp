package com.example.apollo.models

class User {
    var email: String = ""
    var pass: String = ""
    var userid: String = "" // unique column identifier user

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