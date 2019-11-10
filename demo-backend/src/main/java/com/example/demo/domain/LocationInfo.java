package com.example.demo.domain;

public class LocationInfo {

	private int coordinateX;
	private int coordinateY;
	private String headingDirection;

	public LocationInfo(int coordinateX, int coordinateY, String headingDirection) {
		this.coordinateX = coordinateX;
		this.coordinateY = coordinateY;
		this.headingDirection = headingDirection;
	}

	public int getCoordinateX() {
		return coordinateX;
	}

	public void setCoordinateX(int coordinateX) {
		this.coordinateX = coordinateX;
	}

	public int getCoordinateY() {
		return coordinateY;
	}

	public void setCoordinateY(int coordinateY) {
		this.coordinateY = coordinateY;
	}

	public String getHeadingDirection() {
		return headingDirection;
	}

	public void setHeadingDirection(String headingDirection) {
		this.headingDirection = headingDirection;
	}

}
