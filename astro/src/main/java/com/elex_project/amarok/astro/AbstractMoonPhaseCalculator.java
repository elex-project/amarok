package com.elex_project.amarok.astro;

import java.text.DateFormat;
import java.util.*;

abstract class AbstractMoonPhaseCalculator {
	/* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
	/* ~~~~~~~~~~~~~ EARTH ~~~~~~~~~~~~~ */
	/* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
	/** Eccentricity of Earth's orbit */
	static double ECCENTRICITY = 0.016718d;
	/** 1980 January */
	static double EPOCH = 2444238.5d;
	/** January 1, 1970 at 00:00:00 UTC */
	static double JULIAN_1_1_1970 = 2440587.5d;
	static double SECONDS_PER_DAY = 86400.0d;

	//
	/* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
	/* ~~~~~~~~~~~~~ SUN ~~~~~~~~~~~~~ */
	/* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
	/** Elliptic longitude of the Sun at epoch 1980.0 */
	static double SUN_ECLIPTIC_LONGITUDE_AT_EPOCH = 278.833540d;
	/** Elliptic longitude of the Sun at perigee */
	static double SUN_ECLIPTIC_LONGITUDE_AT_EPOCH_AT_PERIGREE = 282.596403d;
	static double SUN_ALTITUDE_UPPER_LIMB_TOUCHING_HORIZON = -0.833d;
	static double SUN_ALTITUDE_CIVIL_TWIGHLIGHT = -6.0d;

	//
	/* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
	/* ~~~~~~~~~~~~~ MOON ~~~~~~~~~~~~~ */
	/* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
	/** Moon's mean longitude at the epoch */
	static double MOONS_MEAN_LONGITUDE = 64.975464d;
	/** Mean longitude of the perigee at the epoch */
	static double MOONS_MEAN_LONGITUDE_AT_EPOCH_AT_PERIGREE = 349.383063d;
	/** (new Moon to new Moon) in days */
	static double SYNODIC_MONTH = 29.530587962962958d;
	static double MOONS_ALTITUDE_CENTER = 8 / 60;

	public abstract Moon calculate(Calendar calender);
	public abstract List<MoonEvent> getNextMoonEvents(Calendar upFromDate, int count, EnumSet<MoonEventType> includedTypes);

	public static class Moon {

		private final double _moonAge;
		private final double _fraction;

		public Moon(double moonAge, double fraction) {
			_moonAge = moonAge;
			_fraction = fraction;
		}

		public double getMoonAge() {
			return _moonAge;
		}

		public double getFraction() {
			return _fraction;
		}

		@Override
		public String toString() {
			return this.getClass().getSimpleName() + "[age=" + _moonAge + ",fraction=" + _fraction + "]";
		}

	}
	public enum MoonPhase {

		/***/
		WAXING_CRESCENT(MoonEventType.FIRST_QUARTER, 0d, 0.25d, 0, 7),
		/***/
		WAXING_GIBBOUS(MoonEventType.FULL_MOON, 0.25d, 0.5d, 7, 15),
		/***/
		WANING_GIBBOUS(MoonEventType.LAST_QUARTER, 0.5d, 0.75d, 15, 23),
		/***/
		WANING_CRESCENT(MoonEventType.NEW_MOON, 0.75d, 1d, 23, 31);

		private final MoonEventType _culminatingEvent;
		private final double _startFraction;
		private final double _endFraction;
		private final double _startAge;
		private final double _endAge;

		private MoonPhase(MoonEventType culminatingEvent, double startFraction, double endFraction, double startAge, double endAge) {
			_culminatingEvent = culminatingEvent;
			_startFraction = startFraction;
			_endFraction = endFraction;
			_startAge = startAge;
			_endAge = endAge;
		}

		public MoonEventType getCulminatingEventType() {
			return _culminatingEvent;
		}

		public static MoonPhase getMoonPhase(double moonFraction) {
			MoonPhase[] values = values();
			for (MoonPhase moonPhase : values) {
				if (moonFraction > moonPhase._startFraction && moonFraction < moonPhase._endFraction) {
					return moonPhase;
				}
			}
			throw new IllegalStateException("no moon-phase found for fraction '" + moonFraction + "'");
		}

		public static MoonPhase getMoonPhaseByAge(double moonAge) {
			MoonPhase[] values = values();
			for (MoonPhase moonPhase : values) {
				if (moonAge >= moonPhase._startAge && moonAge <= moonPhase._endAge) {
					return moonPhase;
				}
			}
			throw new IllegalStateException("no moon-phase found for fraction '" + moonAge + "'");
		}
	}
	public static class MoonEvent {

		private final MoonEventType _type;
		private final Date _date;

		public MoonEvent(MoonEventType type, Date date) {
			_type = type;
			_date = date;
		}

		public MoonEventType getType() {
			return _type;
		}

		public Date getDate() {
			return _date;
		}

		@Override
		public String toString() {
			return getType() + ": " + DateFormat.getDateInstance().format(getDate());
		}


	}
	public enum EventAllocation {
		IN_FUTURE, IN_PRESENT, IN_PAST;

		public static EventAllocation getEventAllocation(long now, long eventTime, long eventPhaseMinus, long eventPhasePlus) {
			long presentMin = eventTime - eventPhaseMinus;
			long presentMax = eventTime + eventPhasePlus;
			if (now < presentMin) {
				return IN_FUTURE;
			}
			if (now >= presentMin) {
				if (now <= presentMax) {
					return IN_PRESENT;
				}
			}
			return IN_PAST;
		}
	}
	public enum MoonEventType {

		NEW_MOON(0.0d) {
			@Override
			public MoonEventType opposite() {
				return FULL_MOON;
			}
		},
		FIRST_QUARTER(0.25d) {
			@Override
			public MoonEventType opposite() {
				return LAST_QUARTER;
			}
		},
		FULL_MOON(0.5d) {
			@Override
			public MoonEventType opposite() {
				return NEW_MOON;
			}
		},
		LAST_QUARTER(0.75d) {
			@Override
			public MoonEventType opposite() {
				return FIRST_QUARTER;
			}
		};

		private final double _fraction;

		private MoonEventType(double fraction) {
			_fraction = fraction;
		}

		/**
		 * @return value between 0..1
		 */
		public double getFraction() {
			return _fraction;
		}

		public String getDisplayName() {
			char[] chars = name().toCharArray();
			StringBuilder displayName = new StringBuilder(chars.length);
			boolean initial = true;
			for (char c : chars) {
				if (initial) {
					initial = false;
					displayName.append(c);
				} else {
					if (c == '_') {
						initial = true;
						displayName.append(' ');
					} else {
						displayName.append(Character.toLowerCase(c));
					}
				}
			}

			return displayName.toString();
		}

		public abstract MoonEventType opposite();

	}

	public static class MoonUtil  {

		public static Calendar newCalendar(Date date) {
			return newCalendar(date.getTime());
		}

		public static Calendar newCalendar(long timeInMillis) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTimeInMillis(timeInMillis);
			return calendar;
		}

		public static Calendar newCalendar(int year, int month, int day, int hour, int minutes, int seconds, TimeZone timeZone) {
			Calendar calendar = Calendar.getInstance(timeZone);
			calendar.set(year, month, day, hour, minutes, seconds);
			return calendar;
		}

		public static Calendar newCalendar(int year, int month, int day) {
			Calendar calendar = Calendar.getInstance();
			calendar.set(year, month, day, 0, 0, 0);
			return calendar;
		}

		public static double toJulian(Calendar calendar) {
			return toJulianFromUnixTimestamp(calendar.getTimeInMillis() / 1000);
		}

		public static double toModifiedJulian(Calendar calendar) {
			return toJulian(calendar) - 2400000.5d;

		}

		public static double toJulianFromUnixTimestamp(long unixSecs) {
			return (unixSecs / SECONDS_PER_DAY) + JULIAN_1_1_1970;
		}

		public static long toUnixTimestampFromJulian(double julianDate) {
			return (long) ((julianDate - JULIAN_1_1_1970) * SECONDS_PER_DAY);
		}

		public static Calendar toGregorian(double julianDate) {
			return newCalendar(toUnixTimestampFromJulian(julianDate) * 1000);
		}

		public static Date toGregorianDate(double julianDate) {
			return new Date(toUnixTimestampFromJulian(julianDate) * 1000);
		}

		public static double sinFromDegree(double x) {
			return Math.sin(Math.toRadians((x)));
		}

		public static double cosFromDegree(double x) {
			return Math.cos(Math.toRadians((x)));
		}

		public static double solveKeplerEquation(double m, double ecc) {
			double e, delta;
			double EPSILON = 1E-6;
			e = m = Math.toRadians(m);
			do {
				delta = e - ecc * Math.sin(e) - m;
				e -= delta / (1 - ecc * Math.cos(e));
			} while (Math.abs(delta) > EPSILON);
			return e;
		}

		public static double fixAngleDegrees(double deg) {
			double fixed = deg % 360d;
			if (fixed < 0) {
				fixed += 360d;
			}
			return fixed;
		}
	}
}
