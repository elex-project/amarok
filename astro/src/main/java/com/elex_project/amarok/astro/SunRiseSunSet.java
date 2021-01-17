package com.elex_project.amarok.astro;

import com.elex_project.abraxas.Timez;
import lombok.Getter;
import lombok.ToString;
import net.e175.klaus.solarpositioning.SPA;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.GregorianCalendar;

/**
 * 일출 / 일몰 계산
 *
 * @author email@elex.pe.kr
 * @since 2018.08.03
 */
@Getter
@ToString
public final class SunRiseSunSet implements ICalc {
	private static final String TAG = SunRiseSunSet.class.getSimpleName();

	/**
	 * 일출 / 일몰 시간 계산
	 *
	 * @param calendar
	 * @return
	 */
	@Contract("_, _ -> new")
	@Deprecated
	public static @NotNull SunRiseSunSet estimate(
			@NotNull GregorianCalendar calendar, @NotNull Parameters parameters) {
		return new SunRiseSunSet(
				SPA.calculateSunriseTransitSet(calendar,
						parameters.getLatitude(), parameters.getLongitude(), parameters.getDeltaT()));
		//return new SunRiseSunSet(calendar,
		//		new SunriseSunsetCalculator(
		//				new Location(parameters.getLatitude(), parameters.getLongitude()), TimeZone.getDefault().getID()));
	}

	public static @NotNull SunRiseSunSet estimate(
			@NotNull LocalDateTime date, @NotNull Parameters parameters) {
		return new SunRiseSunSet(
				SPA.calculateSunriseTransitSet(Timez.toCalendar(date, ZoneId.systemDefault()),
						parameters.getLatitude(), parameters.getLongitude(), parameters.getDeltaT()));
	}

	private final LocalDateTime sunRise, sunSet, sunTransit;
	private final Duration lengthOfDay;

	private SunRiseSunSet(GregorianCalendar @NotNull [] calcResult) {
		this.sunRise = Timez.toLocalDateTime(calcResult[0]);
		this.sunTransit = Timez.toLocalDateTime(calcResult[1]);
		this.sunSet = Timez.toLocalDateTime(calcResult[2]);
		this.lengthOfDay = Duration.between(sunRise, sunSet);
	}

}
