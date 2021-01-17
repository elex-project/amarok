package com.elex_project.amarok.astro;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.EnumSet;
import java.util.List;

/**
 * This is based on a 'do it in your head' algorithm by John Conway. In its current form, it's only
 * valid for the 20th and 21st centuries.
 *
 * See http://www.ben-daglish.net/moon.shtml
 */
@Deprecated
public class MoonPhaseCalculator extends AbstractMoonPhaseCalculator {

	@Override
	public Moon calculate(Calendar calendar) {
		double moonAge = calculateAge(calendar);
		return new Moon(moonAge, moonAge / SYNODIC_MONTH);
	}

	@Override
	public List<MoonEvent> getNextMoonEvents(Calendar calender, int count, EnumSet<MoonEventType> includedTypes) {
		List<MoonEvent> moonEventsData = new ArrayList<MoonEvent>();
		int countedEvents = 0;
		double moonAge = calculate(calender).getMoonAge();
		MoonPhase currentPhase = MoonPhase.getMoonPhaseByAge(moonAge);
		while (countedEvents < count) {
			calender.add(Calendar.HOUR_OF_DAY, 1);
			moonAge = calculate(calender).getMoonAge();
			if (MoonPhase.getMoonPhaseByAge(moonAge) != currentPhase) {
				if (includedTypes.contains(currentPhase.getCulminatingEventType())) {
					moonEventsData.add(new MoonEvent(currentPhase.getCulminatingEventType(), calender.getTime()));
					countedEvents++;
				}
				currentPhase = MoonPhase.getMoonPhaseByAge(moonAge);
			}
		}
		return moonEventsData;
	}

	private double calculateAge(Calendar cal) {
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		int day = cal.get(Calendar.DAY_OF_MONTH);

		double result = year % 100;
		result %= 19;
		if (result > 9) {
			result -= 19;
		}
		result = ((result * 11) % SYNODIC_MONTH) + month + day;
		if (month < 3) {
			result += 2;
		}
		result -= ((year < 2000) ? 4 : 8.3);
		result = Math.floor(result + 0.5) % SYNODIC_MONTH;
		result = (result < 0) ? result + SYNODIC_MONTH : result;
		return result;
	}

}
