package com.elex_project.amarok.astro;

import java.math.BigDecimal;

/**
 * Defines the solar declination used in computing the sunrise/sunset.
 */
public class Zenith {
	/**
	 * Astronomical sunrise/set is when the sun is 18 degrees below the horizon. <br>
	 * Astronomical twilight (the sky is completely dark)
	 */
	public static final Zenith ASTRONOMICAL = new Zenith(108);
	/**
	 * Amateur astronomical twilight (the sky is dark enough for most astronomical observations)
	 */
	public static final Zenith AMATEUR_ASTRONOMICAL = new Zenith(105);
	/**
	 * Nautical sunrise/set is when the sun is 12 degrees below the horizon.<br>
	 * Nautical twilight (navigation using a sea horizon no longer possible)
	 */
	public static final Zenith NAUTICAL = new Zenith(102);

	/**
	 * Civil sunrise/set (dawn/dusk) is when the sun is 6 degrees below the horizon.<br>
	 * Civil twilight (one can no longer read outside without artificial illumination)
	 */
	public static final Zenith CIVIL = new Zenith(96);

	/**
	 * Official sunrise/set is when the sun is 50' below the horizon.<br>
	 * Sun's upper limb touches the horizon; atmospheric refraction accounted for
	 */
	public static final Zenith OFFICIAL = new Zenith(90.8333);

	/**
	 * Center of Sun's disk touches the horizon; atmospheric refraction accounted for
	 */
	public static final Zenith CENTER_OF_SUN_DISK = new Zenith(90.583);
	/**
	 * Sun's upper limb touches a mathematical horizon
	 */
	public static final Zenith MATHEMATICAL_UPPER_LIMB = new Zenith(90.25);
	/**
	 * Center of Sun's disk touches a mathematical horizon
	 */
	public static final Zenith MATHEMATICAL_CENTER = new Zenith(90);

	private final BigDecimal degrees;

	public Zenith(double degrees) {
		this.degrees = BigDecimal.valueOf(degrees);
	}

	public BigDecimal degrees() {
		return degrees;
	}
}
