package com.kisaragi.app.requests;

public class UpdateStoreRequest {
	private int id;
	private StoreDTO newData;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public StoreDTO getNewData() {
		return newData;
	}
	public void setNewData(StoreDTO newData) {
		this.newData = newData;
	}
	public UpdateStoreRequest(int id, StoreDTO newData) {
		super();
		this.id = id;
		this.newData = newData;
	}
}
