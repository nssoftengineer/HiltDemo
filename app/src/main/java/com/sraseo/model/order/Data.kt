package com.sraseo.ytclient.model

import java.math.BigInteger


data class Data (

	val transictionid : BigInteger,
	val userid : BigInteger,
	val channelid : String,
	val orderid : Int,
	val channelname : String,
	val category : String,
	val persubcription : Double,
	val totalamount : Double,
	val leftamount : Double,
	val studiocode : Int,
	val perlike : Double,
	val pervideo : Double,
	val gst : Double,
	val offer : Int,
	val totallike : Int,
	val totalsubscription : Int,
	val totalvideo : Int,
	val totallikeamount : Int,
	val totalsubcribeamount : Int,
	val totalvideoamount : Int,
	val orderdate : String,
	val currentlike : Int,
	val currentsubscription : Int,
	val currentvideoview : Int,
	val video : List<Video>
)