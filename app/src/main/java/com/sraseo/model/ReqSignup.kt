package com.sraseo.ytclient.model

data class ReqSignup(
    var name: String,
    var email: String,
    var tokenid: String,
    var usertype: String,
    var version: String,
    var studiocode: String,
)