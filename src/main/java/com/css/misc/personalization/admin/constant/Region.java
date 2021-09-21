package com.css.misc.personalization.admin.constant;

public enum Region {
	HKG("R1",Db.ALPHA),CHN("R2",Db.SHPD),TWN("R3",Db.ALPHA),MAC("R6",Db.ALPHA);
	public final String regionCode;
	public final Db db;
	Region(String regionCode,Db db) {
		this.regionCode=regionCode;
		this.db=db;
	}
	
}
