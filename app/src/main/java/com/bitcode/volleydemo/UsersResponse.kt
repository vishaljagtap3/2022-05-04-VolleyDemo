package com.bitcode.webservices2

import com.google.gson.annotations.SerializedName

data class UsersResponse (
    @SerializedName("page")
    var pageNumber : Int,

    @SerializedName("per_page")
    var usersPerPage : Int,

    @SerializedName("total")
    var userCount : Int,

    @SerializedName("total_pages")
    var totalPages : Int = 0,

    @SerializedName("data")
    var users : ArrayList<User>
) {
    override fun toString(): String {
        //return "page = $page per_page = $per_page total = $total total_pages = $total_pages"
        return "page = $pageNumber per_page = $usersPerPage total = $userCount total_pages = $totalPages"
    }
}

/*
class UsersResponse {

    */
/*var page : Int = 0
    var per_page : Int = 0
    var total : Int = 0
    var total_pages : Int = 0*//*


    @SerializedName("page")
    var pageNumber : Int = 0

    @SerializedName("per_page")
    var usersPerPage : Int = 0

    @SerializedName("total")
    var userCount : Int = 0

    @SerializedName("total_pages")
    var totalPages : Int = 0

    override fun toString(): String {
        //return "page = $page per_page = $per_page total = $total total_pages = $total_pages"
        return "page = $pageNumber per_page = $usersPerPage total = $userCount total_pages = $totalPages"
    }
}*/
