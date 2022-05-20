package com.about.asetdaerah_app.connection.Respon

import com.google.gson.annotations.SerializedName

data class ResponseDataAdmin(

	@field:SerializedName("hasil")
	val hasil: List<HasilItem>
)

data class HasilItem(

	@field:SerializedName("notelp")
	val notelp: String,

	@field:SerializedName("password")
	val password: String,

	@field:SerializedName("nip")
	val nip: String,

	@field:SerializedName("nama")
	val nama: String
)
