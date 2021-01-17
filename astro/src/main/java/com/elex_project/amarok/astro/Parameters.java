package com.elex_project.amarok.astro;

import com.elex_project.abraxas.Timez;
import net.e175.klaus.solarpositioning.DeltaT;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.GregorianCalendar;

public final class Parameters {

	private Algorithm algorithm = Algorithm.SPA;
	private double latitude, longitude;
	private double elevation = 0d;
	private double deltaT = 70.86d;
	private double pressure = 1013.25d;
	private double temperature = 20d;

	public Parameters(double latitude, double longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public Algorithm getAlgorithm() {
		return algorithm;
	}

	public void setAlgorithm(Algorithm algorithm) {
		this.algorithm = algorithm;
	}

	public double getLatitude() {
		return latitude;
	}

	/**
	 * Observer's latitude, in degrees (negative south of equator).
	 *
	 * @param latitude
	 */
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	/**
	 * Observer's longitude, in degrees (negative west of Greenwich).
	 *
	 * @param longitude
	 */
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getElevation() {
		return elevation;
	}

	public void setElevation(double elevation) {
		this.elevation = elevation;
	}

	public double getDeltaT() {
		return deltaT;
	}

	/**
	 * Difference between earth rotation time and terrestrial time (or Universal Time and Terrestrial Time),
	 * in seconds. See <a href ="http://asa.usno.navy.mil/SecK/DeltaT.html">http://asa.usno.navy.mil/SecK/DeltaT.html</a>.
	 * For the year 2015, a reasonably accurate default would be 68.
	 *
	 * @param deltaT
	 */
	public void setDeltaT(double deltaT) {
		this.deltaT = deltaT;
	}

	/**
	 * Estimate Delta T for the given Calendar. This is based on Espenak and Meeus, "Five Millennium Canon of
	 * Solar Eclipses: -1999 to +3000" (NASA/TP-2006-214141).
	 *
	 * @param date
	 * @see DeltaT#estimate(GregorianCalendar)
	 */
	public void setDeltaT(LocalDateTime date) {
		this.deltaT = DeltaT.estimate(Timez.toCalendar(date, ZoneId.systemDefault()));
	}

	public double getPressure() {
		return pressure;
	}

	/**
	 * Annual average local pressure, in millibars (or hectopascals).
	 * Used for refraction correction of zenith angle.
	 * If unsure, 1000 is a reasonable default.
	 *
	 * @param pressure
	 */
	public void setPressure(double pressure) {
		this.pressure = pressure;
	}

	public double getTemperature() {
		return temperature;
	}

	/**
	 * Annual average local temperature, in degrees Celsius.
	 * Used for refraction correction of zenith angle.
	 * @param temperature
	 */
	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}
}
