package com.challenge.dto;

import com.google.gson.annotations.SerializedName;

public class Generation3 {
	public Emerald emerald;
	
	@SerializedName("firered-leafgreen")
	public DefaultDetailDefShiny fireredLeafgreen;
	
	@SerializedName("ruby-sapphire")
	public DefaultDetailDefShiny rubySapphire;
	
}
