package com.bitcode.volleydemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.bitcode.volleydemo.databinding.ActivityMainBinding
import com.bitcode.webservices2.User
import com.bitcode.webservices2.UsersResponse
import com.google.gson.Gson
import com.google.gson.JsonObject
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    //lateinit var volleyRequestQueue : RequestQueue

    var users = ArrayList<User>()
    var pageNumber = 1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        VolleySingleton.initRequestQueue(this)
        //volleyRequestQueue = Volley.newRequestQueue(this)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnStringRequest.setOnClickListener {
            stringRequestFun()
        }

        binding.btnJsonObjectRequest.setOnClickListener {
            jsonObjectRequestFun()
        }

        binding.btnCreateUser.setOnClickListener {
            addUser()
        }
    }

    private fun addUser() {

        var inputJsonObject = JSONObject()
        inputJsonObject.put("email", binding.edtUserEmail.text.toString())
        inputJsonObject.put("password", binding.edtPassword.text.toString())

        var jsonObjectRequestQueue = JsonObjectRequest(
            Request.Method.POST,
            "https://reqres.in/api/register",
            inputJsonObject,
            AddUserListener(),
            StringRequestErrorListener()
        )

        VolleySingleton.volleyRequestQueue!!.add(jsonObjectRequestQueue)
    }

    class AddUserListener : Response.Listener<JSONObject> {
        override fun onResponse(response: JSONObject) {
            Log.e("tag", response.toString())
        }
    }

    private fun jsonObjectRequestFun() {

        var volleyJsonObjectRequest = JsonObjectRequest(
            Request.Method.GET,
            "https://reqres.in/api/users?page=${pageNumber}",
            null,
            JsonObjectRequestSuccessListener(),
            StringRequestErrorListener()
        )

        //volleyRequestQueue.add(volleyJsonObjectRequest)
        VolleySingleton.volleyRequestQueue!!.add(volleyJsonObjectRequest)

        pageNumber++

    }


    inner class JsonObjectRequestSuccessListener : Response.Listener<JSONObject> {
        override fun onResponse(response: JSONObject?) {
            //Log.e("tag", response.toString())
            var usersResponse = Gson().fromJson<UsersResponse>(
                response.toString(),
                UsersResponse::class.java
            )

            users.addAll(usersResponse.users)

            Log.e("tag", "################################################")
            for(user in users) {
                Log.e("tag", user.toString())
            }
            Log.e("tag", "################################################")
        }
    }

    private fun stringRequestFun() {

        var volleyStringRequest = StringRequest(
            Request.Method.GET,
            "https://reqres.in/api/users?page=${pageNumber}",
            StringRequestSuccessListener(),
            StringRequestErrorListener()
        )

        //volleyRequestQueue.add(volleyStringRequest)
        VolleySingleton.volleyRequestQueue!!.add(volleyStringRequest)

        pageNumber++

    }

    inner class StringRequestSuccessListener : Response.Listener<String> {
        override fun onResponse(response: String?) {

            var usersResponse = Gson().fromJson<UsersResponse>(
                response!!,
                UsersResponse::class.java
            )

            users.addAll(usersResponse.users)

            Log.e("tag", "################################################")
            for(user in users) {
                Log.e("tag", user.toString())
            }
            Log.e("tag", "################################################")

        }
    }

    class StringRequestErrorListener : Response.ErrorListener {
        override fun onErrorResponse(error: VolleyError?) {
            Log.e("tag", "$error")
        }
    }
}