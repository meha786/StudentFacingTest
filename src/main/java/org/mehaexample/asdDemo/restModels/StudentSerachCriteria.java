package org.mehaexample.asdDemo.restModels;

import java.util.List;

public class StudentSerachCriteria {
	List<String> coops;
	List<String> undergraddegree;
	List<String> undergradschool;
	List<String> graduationyear;
	String beginindex;
	String endindex;
	
	/**
	 * @return the beginindex
	 */
	public String getBeginindex() {
		return beginindex;
	}

	/**
	 * @param beginindex the beginindex to set
	 */
	public void setBeginindex(String beginindex) {
		this.beginindex = beginindex;
	}

	/**
	 * @return the endindex
	 */
	public String getEndindex() {
		return endindex;
	}

	/**
	 * @param endindex the endindex to set
	 */
	public void setEndindex(String endindex) {
		this.endindex = endindex;
	}

	/**
	 * 
	 */
	public StudentSerachCriteria() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param coops
	 * @param undergraddegree
	 * @param undergradschool
	 * @param graduationyear
	 * @param beginindex
	 * @param endindex
	 */
	public StudentSerachCriteria(List<String> coops,
			List<String> undergraddegree, List<String> undergradschool,
			List<String> graduationyear, String beginindex, String endindex) {
		super();
		this.coops = coops;
		this.undergraddegree = undergraddegree;
		this.undergradschool = undergradschool;
		this.graduationyear = graduationyear;
		this.beginindex = beginindex;
		this.endindex = endindex;
	}

	public List<String> getCoops() {
		return coops;
	}

	public void setCoops(List<String> coops) {
		this.coops = coops;
	}

	public List<String> getUndergraddegree() {
		return undergraddegree;
	}

	public void setUndergraddegree(List<String> undergraddegree) {
		this.undergraddegree = undergraddegree;
	}

	public List<String> getUndergradschool() {
		return undergradschool;
	}

	public void setUndergradschool(List<String> undergradschool) {
		this.undergradschool = undergradschool;
	}

	public List<String> getGraduationyear() {
		return graduationyear;
	}

	public void setGraduationyear(List<String> graduationyear) {
		this.graduationyear = graduationyear;
	}
}
