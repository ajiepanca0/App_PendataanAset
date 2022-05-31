package com.about.asetdaerah_app.connection.Respon

import com.google.gson.annotations.SerializedName

data class ResponseAsetAlat(

	@field:SerializedName("hasil")
	val hasil: List<HasilItemAlat>
)

data class HasilItemAlat(

	@field:SerializedName("jumlahalat")
	val jumlahalat: String,

	@field:SerializedName("namaalat")
	val namaalat: String,

	@field:SerializedName("idasetalat")
	val idasetalat: String,

	@field:SerializedName("biayaalat")
	val biayaalat: String,

	@field:SerializedName("lainnyalat")
	val lainnyalat: String,

	@field:SerializedName("kondisialat")
	val kondisialat: String
)
