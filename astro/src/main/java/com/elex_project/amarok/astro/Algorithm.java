package com.elex_project.amarok.astro;

public enum Algorithm {
	/**
	 * Calculate topocentric solar position, i.e. the location of the sun on the sky for a certain point in time on a
	 * certain point of the Earth's surface.
	 * <p>
	 * This follows the SPA algorithm described in Reda, I.; Andreas, A. (2003): Solar Position Algorithm for Solar
	 * Radiation Applications. NREL Report No. TP-560-34302, Revised January 2008.
	 * <p>
	 *
	 * @see net.e175.klaus.solarpositioning.SPA
	 */
	SPA("SPA"),
	/**
	 * Calculate topocentric solar position, i.e. the location of the sun on the sky for a certain point in time on a
	 * certain point of the Earth's surface.
	 * <p>
	 * This follows the no. 3 algorithm described in Grena, 'Five new algorithms for the computation of sun position
	 * from 2010 to 2110', Solar Energy 86 (2012) pp. 1323-1337.
	 * <p>
	 *
	 * @see net.e175.klaus.solarpositioning.Grena3
	 */
	GRENA3("GRENA 3");

	private final String title;

	Algorithm(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return title;
	}
}
