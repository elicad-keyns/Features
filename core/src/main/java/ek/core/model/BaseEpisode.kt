package ek.core.model

import com.google.gson.annotations.SerializedName

data class BaseEpisode (
	@SerializedName("info")
	val info : Info,
	@SerializedName("results")
	val results : List<Episode>
)