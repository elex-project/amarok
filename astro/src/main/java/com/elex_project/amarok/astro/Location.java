package com.elex_project.amarok.astro;

import java.math.BigDecimal;

/**
 * Simple VO class to store latitude/longitude information.
 */
public class Location {
	/*
	static final double BUSAN_LONGITUDE = 35.198362;
	static final double BUSAN_LATITUDE = 129.053922;

	static final double SEOUL_LONGITUDE = 37.540705;
	static final double SEOUL_LATITUDE = 126.956764;

	public static double LEIPZIG_LATITUDE = 51.36d;
	public static double LEIPZIG_LONGITUDE = 12.38d;

	public static double KONGSFJORDEN_LATITUDE = 78.9651307d;
	public static double KONGSFJORDEN_LONGITUDE = 11.8569355d;
	 */
	public static final Location BUSAN = new Location(35.198362, 129.053922);
	public static final Location SEOUL = new Location(37.540705, 126.956764);

	private BigDecimal latitude;
	private BigDecimal longitude;

	/**
	 * Creates a new instance of <code>Location</code> with the given parameters.
	 *
	 * @param latitude
	 *            the latitude, in degrees, of this location. North latitude is positive, south negative.
	 * @param longitude
	 *            the longitude, in degrees of this location. East longitude is positive, west negative.
	 */
	public Location(String latitude, String longitude) {
		this.latitude = new BigDecimal(latitude);
		this.longitude = new BigDecimal(longitude);
	}

	/**
	 * Creates a new instance of <code>Location</code> with the given parameters.
	 *
	 * @param latitude
	 *            the latitude, in degrees, of this location. North latitude is positive, south negative.
	 * @param longitude
	 *            the longitude, in degrees, of this location. East longitude is positive, east negative.
	 */
	public Location(double latitude, double longitude) {
		this.latitude = new BigDecimal(latitude);
		this.longitude = new BigDecimal(longitude);
	}

	/**
	 * @return the latitude
	 */
	public BigDecimal getLatitude() {
		return latitude;
	}

	/**
	 * @return the longitude
	 */
	public BigDecimal getLongitude() {
		return longitude;
	}

	/**
	 * Sets the coordinates of the location object.
	 *
	 * @param latitude
	 *            the latitude, in degrees, of this location. North latitude is positive, south negative.
	 * @param longitude
	 *            the longitude, in degrees, of this location. East longitude is positive, east negative.
	 */
	public void setLocation(String latitude, String longitude) {
		this.latitude = new BigDecimal(latitude);
		this.longitude = new BigDecimal(longitude);
	}

	/**
	 * Sets the coordinates of the location object.
	 *
	 * @param latitude
	 *            the latitude, in degrees, of this location. North latitude is positive, south negative.
	 * @param longitude
	 *            the longitude, in degrees, of this location. East longitude is positive, east negative.
	 */
	public void setLocation(double latitude, double longitude) {
		this.latitude = new BigDecimal(latitude);
		this.longitude = new BigDecimal(longitude);
	}
}
