package com.example.marcos.okhttptest

import android.app.Activity
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.view.View
import android.widget.*

import org.json.JSONArray
import org.json.JSONObject

import java.io.IOException

import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.json.JSONException
import java.lang.NullPointerException
import java.net.SocketTimeoutException
import kotlin.IllegalArgumentException
//import kotlin.NullPointerException
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    //VIEW
    private var buttonSendGET: Button? = null
    private var buttonSendPOST: Button? = null
    private var jsonTextField: TextView? = null
    private var input: EditText? = null
    private var cbEscolas: Spinner? = null

    //OKHTTP
    private var client: OkHttpClient? = null
    private var request: Request? = null
    private val http = OkHttpClass()

    //CLASSES TO MAP
    private val escolaList = ArrayList<Escola>()

    //JSON RETURN
    private var json: String? = null
    private var jsonresp = JSONArray()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonSendGET = findViewById<View>(R.id.buttonGET) as Button
        buttonSendPOST = findViewById<View>(R.id.buttonPOST) as Button
        jsonTextField = findViewById<View>(R.id.jsonField) as TextView
        input = findViewById<View>(R.id.urlInput) as EditText
        cbEscolas = findViewById<View>(R.id.comboBox) as Spinner

//        val escolaList = ArrayList<Escola>()

        buttonSendGET!!.setOnClickListener {
            jsonTextField!!.text = "Awaiting response from GET..."

            thread {
                try{
                    client = http.client
                    request = http.getRequest(input!!.text.toString())
                    json = http.GETurl(client, request)

                    Log.i("JSON", json)

                    this.runOnUiThread {
                        try {
                            jsonresp = org.json.JSONArray(json)
                            var s = ""
                            escolaList.clear()
                            for (i in 0 until jsonresp.length()) {
                                var r = Escola()
                                s += "Escola: " + jsonresp.getJSONObject(i).getString("escolaNome") + "\n" +
                                        "Latitude: " + jsonresp.getJSONObject(i).getString("latitude") + "\n" +
                                        "Longitude: " + jsonresp.getJSONObject(i).getString("longitude") + "\n\n"

                                r.setescolaNome(jsonresp.getJSONObject(i).getString("escolaNome"))
                                r.setlatitude(jsonresp.getJSONObject(i).getString("latitude"))
                                r.setlongitude(jsonresp.getJSONObject(i).getString("longitude"))


                                escolaList.add(r)
                            }

                            val escolaString = ArrayList<String>()
                            for (i in escolaList){
                                Log.i("TAG", i.getescolaNome())
                                escolaString.add(i.getescolaNome().toString())
                            }

                            jsonTextField!!.text = s

                            cbEscolas!!.adapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, escolaString)

                            cbEscolas!!.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                                override fun onNothingSelected(parent: AdapterView<*>?) {
                                    jsonTextField!!.text = "Selecione uma opção"
                                }

                                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
//                                    jsonTextField!!.text = escolaList[position].getescolaNome() + ", "+ escolaList[position].getlongitude()
                                }

                            }

                        }catch (e : JSONException){
                            jsonTextField!!.text = json
                        }catch (e : SocketTimeoutException){
                            jsonTextField!!.text = e.message
                        }
                    }

                }catch (e : IllegalArgumentException){
                    jsonTextField!!.text = e.message
                }
            }
        }

        buttonSendPOST!!.setOnClickListener {
            jsonTextField!!.text = "Awaiting response from POST..."
            thread {
                try{
                    client = http.client
                    json = "{\"escola\": null, \"usuario\": 1, \"pontuacao\": 5, \"foto\": \"none\"}"
                    json = http.POSTurl(client, json, "minha-merenda.herokuapp.com/ts830/create/avaliacao")

                    this.runOnUiThread {
                        jsonTextField!!.text = json;
                    }

                }catch (e : IllegalArgumentException){
                    jsonTextField!!.text = e.message
                }
            }
        }
    }

}
