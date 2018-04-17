package org.mehaexample.asdDemo.restModels;

import java.util.List;

public class StudentStatsObject {

	List<String> campus;

	/**
	 * 
	 */
	public StudentStatsObject() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param campus
	 */
	public StudentStatsObject(List<String> campus) {
		super();
		this.campus = campus;
	}

	/**
	 * @return the campus
	 */
	public List<String> getCampus() {
		return campus;
	}

	/**
	 * @param campus the campus to set
	 */
	public void setCampus(List<String> campus) {
		this.campus = campus;
	}
	
	
}
