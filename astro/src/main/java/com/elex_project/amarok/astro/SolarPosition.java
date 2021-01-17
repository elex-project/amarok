package com.elex_project.amarok.astro;

import com.elex_project.abraxas.Timez;
import lombok.Getter;
import lombok.ToString;
import net.e175.klaus.solarpositioning.AzimuthZenithAngle;
import net.e175.klaus.solarpositioning.Grena3;
import net.e175.klaus.solarpositioning.SPA;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.GregorianCalendar;

/**
 * 태양 위치 계산
 *
 * @author email@elex.pe.kr
 * @since 2018.08.03
 */
@Getter
@ToString
public final class SolarPosition implements ICalc {

	/**
	 * 특정 시간 기준으로 계산
	 *
	 * @param time Observer's local date and time.
	 * @return
	 */
	@Deprecated
	public static SolarPosition estimate(
			@NotNull GregorianCalendar time, @NotNull Parameters parameters) {
		if (parameters.getAlgorithm() == Algorithm.SPA) {
			return calcWithSPA(time, parameters);
		} else {
			return calcWithGrena3(time, parameters);
		}
	}

	/**
	 *
	 * @param time Observer's local date and time.
	 * @param parameters
	 * @return
	 */
	public static SolarPosition estimate(
			@NotNull LocalDateTime time, @NotNull Parameters parameters) {
		if (parameters.getAlgorithm() == Algorithm.SPA) {
			return calcWithSPA(Timez
					.toCalendar(time, ZoneId.systemDefault()), parameters);
		} else {
			return calcWithGrena3(Timez
					.toCalendar(time, ZoneId.systemDefault()), parameters);
		}
	}

	@Contract("_, _ -> new")
	private static @NotNull SolarPosition calcWithSPA(
			@NotNull GregorianCalendar time, @NotNull Parameters parameters) {
		return new SolarPosition(
				SPA.calculateSolarPosition(time,
						parameters.getLatitude(), parameters.getLongitude(),
						parameters.getElevation(), parameters.getDeltaT(),
						parameters.getPressure(), parameters.getTemperature())
				, time);
	}

	@Contract("_, _ -> new")
	private static @NotNull SolarPosition calcWithGrena3(
			@NotNull GregorianCalendar time, @NotNull Parameters parameters) {
		return new SolarPosition(
				Grena3.calculateSolarPosition(time,
						parameters.getLatitude(), parameters.getLongitude(),
						parameters.getDeltaT(),
						parameters.getPressure(), parameters.getTemperature())
				, time);
	}


	private final LocalDateTime dateTime;
	/**
	 * 방위각
	 */
	private final double azimuth; // 방위각
	/**
	 * 고도
	 */
	private final double altitude; // 고도

	private SolarPosition(@NotNull AzimuthZenithAngle pos,
	                      @NotNull GregorianCalendar date) {
		this.azimuth = pos.getAzimuth();
		this.altitude = 90 - pos.getZenithAngle();
		this.dateTime = Timez.toLocalDateTime(date);
	}

}
