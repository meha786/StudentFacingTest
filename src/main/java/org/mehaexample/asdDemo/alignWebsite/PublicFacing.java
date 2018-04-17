package org.mehaexample.asdDemo.alignWebsite;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONObject;
import org.json.JSONArray;
import org.mehaexample.asdDemo.dao.alignpublic.*;
import org.mehaexample.asdDemo.model.alignpublic.*;
import org.mehaexample.asdDemo.restModels.StudentSerachCriteria;
import org.mehaexample.asdDemo.restModels.StudentStatsObject;
import org.mehaexample.asdDemo.restModels.TopCoopsNumber;
import org.mehaexample.asdDemo.restModels.TopGraduationYearsNumber;
import org.mehaexample.asdDemo.restModels.TopUnderGradDegreesNumber;
import org.mehaexample.asdDemo.restModels.TopUnderGradSchools;

@Path("")
public class PublicFacing {
	UndergraduatesPublicDao undergraduatesPublicDao = new UndergraduatesPublicDao();
	WorkExperiencesPublicDao workExperiencesPublicDao = new WorkExperiencesPublicDao();
	StudentsPublicDao studentsPublicDao = new StudentsPublicDao();
	SingleValueAggregatedDataDao singleValueAggregatedDataDao = new SingleValueAggregatedDataDao();
	MultipleValueAggregatedDataDao multipleValueAggregatedDataDao = new MultipleValueAggregatedDataDao();

	/**
	 * Request 1
	 * This is the function to get top n undergraduate schools
	 *
	 * @param topUnderGradSchools
	 * @return List of n top undergraduate schools
	 * @throws SQLException
	 */
	@POST
	@Path("/undergradschools")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUndergradSchools(int topUnderGradSchools) {
		List<TopUndergradSchools> undergrad;
		JSONArray result = new JSONArray();

		try {
			undergrad = undergraduatesPublicDao.getTopUndergradSchools(topUnderGradSchools);
			for (TopUndergradSchools school : undergrad) {
				JSONObject schoolJson = new JSONObject(school);
				result.put(schoolJson.get("undergradSchool"));
			}
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e).build();
		}

		return Response.status(Response.Status.OK).entity(result.toString()).build();
	}

	/**
	 * Request 2
	 * This is the function to get top coops.
	 * The body should be in the JSON format like below:
	 * <p>
	 * http://localhost:8080/alignWebsite/webapi/public-facing/top-coops
	 *
	 * @return List of n top coops
	 */
	@POST
	@Path("/coops")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTopCoops(int topCoopsNumber) {
		List<TopCoops> coops;
		JSONArray result = new JSONArray();

		try {
			coops = workExperiencesPublicDao.getTopCoops(topCoopsNumber);
			for (TopCoops coop : coops) {
				JSONObject coopJson = new JSONObject(coop);
				result.put(coopJson.get("coop"));
			}
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e).build();
		}

		return Response.status(Response.Status.OK).entity(result.toString()).build();
	}

	/**
	 * Request 3
	 * This is the function to get top undergraduate majors.
	 * The body should be in the JSON format like below:
	 * <p>
	 * http://localhost:8080/alignWebsite/webapi/public-facing/top-undergraddegrees
	 *
	 * @return List of n top undergraduate degrees
	 */
	@POST
	@Path("undergradmajors")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUndergradDegrees(int topUnderGradMajorsNumber) {
		List<TopUndergradDegrees> degrees;
		JSONArray result = new JSONArray();

		try {
			degrees = undergraduatesPublicDao.getTopUndergradDegrees(topUnderGradMajorsNumber);
			for (TopUndergradDegrees degree : degrees) {
				JSONObject degreeJson = new JSONObject(degree);
				result.put(degreeJson.get("undergradDegree"));
			}
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e).build();
		}

		return Response.status(Response.Status.OK).entity(result.toString()).build();
	}

	/**
	 * Request 4
	 * This is the function to get top graduation years.
	 * The body should be in the JSON format like below:
	 * <p>
	 * http://localhost:8080/alignWebsite/webapi/public-facing/top-graduationyears
	 *
	 * @return List of n top graduation years
	 */
	@POST
	@Path("graduationyears")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTopGraduationYears(int topGraduationYearsNumber) {
		List<TopGradYears> gradYears;
		JSONArray result = new JSONArray();

		try {
			gradYears = studentsPublicDao.getTopGraduationYears(topGraduationYearsNumber);
			for (TopGradYears year : gradYears) {
				JSONObject yearJson = new JSONObject(year);
				result.put(Integer.toString((int) yearJson.get("graduationYear")));
			}
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e).build();
		}

		return Response.status(Response.Status.OK).entity(result.toString()).build();
	}

	/**
	 * Request 5
	 * This is a function to get all undergrad schools
	 * <p>
	 * http://localhost:8080/alignWebsite/webapi/public-facing/all-schools
	 *
	 * @return List of UnderGradSchools
	 */
	@GET
	@Path("/undergradschools")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllSchools() {
		List<String> allUnderGradSchools;

		try {
			allUnderGradSchools = undergraduatesPublicDao.getListOfAllSchools();

		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e).build();
		}

		return Response.status(Response.Status.OK).entity(allUnderGradSchools).build();
	}

		/**
	 * This is the function to get all graduate years.
	 * 
	 * @return List of all graduate years
	 */
	@GET
	@Path("graduationyears")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllGradYears(){
		List<Integer> years;
		JSONArray result = new JSONArray();
		try {
			years = studentsPublicDao.getListOfAllGraduationYears();

			if (years == null) {
				return Response.status(Response.Status.NOT_FOUND).entity("No graduation years are found").build();
			} 

			for(Integer year : years){
				result.put(Integer.toString(year));
			}
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e).build();
		}

		return Response.status(Response.Status.OK).entity(result.toString()).build();
	}

	/**
	 * Request 6
	 * This is a function to get list of ALL Coop companies
	 * 	 
	 * @return List of all Coops
	 */
	@GET
	@Path("/coops")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllCoopCompanies() {
		List<String> listOfAllCoopCompanies;
		try {
			listOfAllCoopCompanies = workExperiencesPublicDao.getListOfAllCoopCompanies();

			if (listOfAllCoopCompanies == null) {
				return Response.status(Response.Status.NOT_FOUND).entity("No COOPS are found").build();
			} 

		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e).build();
		}

		return Response.status(Response.Status.OK).entity(listOfAllCoopCompanies).build();
	} 
	
	/**
	 * Request 7
	 * This is the function to get all undergraduate degrees.
	 * The body should be in the JSON format like below:
	 * <p>
	 * http://localhost:8080/alignWebsite/webapi/public-facing/all-undergraddegrees
	 *
	 * @return List of all undergraduate degrees
	 */
	@GET
	@Path("undergradmajors")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllUndergradDegrees() {
		List<String> degrees;

		try {
			degrees = undergraduatesPublicDao.getListOfAllUndergraduateDegrees();

		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e).build();
		}

		return Response.status(Response.Status.OK).entity(degrees).build();
	}

	/**
	 * Request 10
	 * This is the function to search for students
	 * <p>
	 * http://localhost:8080/alignWebsite/webapi/public-facing/students
	 *
	 * @return the list of student profiles matching the fields.
	 */
	@POST
	@Path("students")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response searchStudent(StudentSerachCriteria studentSerachCriteria) {
		Map<String, List<String>> searchCriteriaMap = new HashMap<>();
		List<StudentsPublic> studentList;
		JSONObject finalObj = new JSONObject();
		JSONArray resultArray = new JSONArray();
		int quantity = 0;
		int begin = 0;
		int end = 20;

		try{
			if (studentSerachCriteria.getCoops() != null && studentSerachCriteria.getCoops().size() > 0){
					searchCriteriaMap.put("coop", studentSerachCriteria.getCoops());
			}
			if (studentSerachCriteria.getUndergraddegree() != null && studentSerachCriteria.getUndergraddegree().size() > 0){
					searchCriteriaMap.put("undergradDegree", studentSerachCriteria.getUndergraddegree());
			}
			if (studentSerachCriteria.getUndergradschool() != null && studentSerachCriteria.getUndergradschool().size() > 0){
					searchCriteriaMap.put("undergradSchool", studentSerachCriteria.getUndergradschool());
			}
			if (studentSerachCriteria.getGraduationyear() != null && studentSerachCriteria.getGraduationyear().size() > 0){
					searchCriteriaMap.put("graduationYear", studentSerachCriteria.getGraduationyear());
			}
			if (studentSerachCriteria.getEndindex() != null) {
				end = Integer.valueOf(studentSerachCriteria.getEndindex());
			}
			if (studentSerachCriteria.getBeginindex() != null) {
				begin = Integer.valueOf(studentSerachCriteria.getBeginindex());
			}
		} catch (Exception e){
			return Response.status(Response.Status.BAD_REQUEST).entity(e).build();
		}

		try {
			quantity = studentsPublicDao.getPublicFilteredStudentsCount(searchCriteriaMap);
			studentList = studentsPublicDao.getPublicFilteredStudents(searchCriteriaMap, 1, 9999);

			finalObj.put("quantity", quantity);

			if(end > quantity && begin < quantity){
				studentList = studentList.subList(begin, quantity);
			} else if (end < quantity && begin < quantity){
				studentList = studentList.subList(begin, end);
			}			

			for(StudentsPublic student : studentList){
				String undergradDegree = "No degree";
				String undergradSchool = "No school";
				String coop = "No coop";
				if(student.getWorkExperiences().size() > 0){
					coop = student.getWorkExperiences().get(0).getCoop();
				}
				if(student.getUndergraduates().size() > 0){
					undergradDegree = student.getUndergraduates().get(0).getUndergradDegree();
					undergradSchool = student.getUndergraduates().get(0).getUndergradSchool();
				}

				JSONObject jsonObj = new JSONObject();
				jsonObj.put("graduationyear", Integer.toString(student.getGraduationYear()));
				jsonObj.put("coop", coop);
				jsonObj.put("undergraddegree", undergradDegree);
				jsonObj.put("undergradschool", undergradSchool);

				resultArray.put(jsonObj);
			}

			finalObj.put("students", resultArray);

		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e).build();
		}

		return Response.status(Response.Status.OK).entity(finalObj.toString()).build();
	}

	// Request 11
	@GET
	@Path("gender")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getGender() {
		JSONObject jsonObj = new JSONObject();
		try {
			int numberOfMale = singleValueAggregatedDataDao.getTotalMaleStudents();
			int numberOfFemale = singleValueAggregatedDataDao.getTotalFemaleStudents();

			int totalCount = numberOfMale + numberOfFemale;
			
			if(totalCount == 0){
				totalCount = 1;
			}
			
			float malepercent = (float) (((float) numberOfMale/(float) totalCount) * 100.00);
			float femalepercent = (float) (((float) numberOfFemale/(float) totalCount) * 100.00);

			jsonObj.put("male", Float.toString(malepercent));
			jsonObj.put("female", Float.toString(femalepercent));

		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e).build();
		}
		return Response.status(Response.Status.OK).entity(jsonObj.toString()).build();
	}

	// // Request 12
	//    @GET
	//    @Path("race")
	//    @Consumes(MediaType.APPLICATION_JSON)
	//    @Produces(MediaType.APPLICATION_JSON)
	//    public Response getRace(){
	//		List<DataCount> race;
	//		JSONObject resultObj = new JSONObject();
	//		int totalCount = 0;
	//
	//        try {
	//            race = multipleValueAggregatedDataDao.getListOfRacesCount();
	//
	//            for(int i=0; i<race.size();i++){
	//    			totalCount += race.get(i).getDataValue();
	//            }
	//			
	//			if(totalCount == 0){
	//				totalCount = 1;
	//			}
	//
	//    		for(int i=0; i<race.size();i++){
	//    			float percent = (float) (( (float) race.get(i).getDataValue()/(float) totalCount ) * 100.00);
	//    			resultObj.put(race.get(i).getDataKey().toLowerCase(), Float.toString(percent));
	//    		}
	//        } catch (Exception e) {
	//            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e).build();
	//        }
	//		return Response.status(Response.Status.OK).entity(resultObj.toString()).build();
	//    }

	// Request 14
	@GET
	@Path("enrollment")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEnrollmentStatus() {
		JSONObject jsonObj = new JSONObject();
		try {
			int numberOfFulltime = singleValueAggregatedDataDao.getTotalFullTimeStudents();
			int numberOfPartTime = singleValueAggregatedDataDao.getTotalPartTimeStudents();
			int totalCount = numberOfFulltime + numberOfPartTime;
			
			if(totalCount == 0){
				totalCount = 1;
			}
			
			float fulltimepercent = (float) (((float) numberOfFulltime/(float) totalCount) * 100.00);
			float parttimepercent = (float) (((float) numberOfPartTime/(float) totalCount) * 100.00);

			jsonObj.put("full-time", Float.toString(fulltimepercent));
			jsonObj.put("part-time", Float.toString(parttimepercent));
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e).build();
		}
		return Response.status(Response.Status.OK).entity(jsonObj.toString()).build();
	}

	// Request 15
	@GET
	@Path("graduation")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getGraduation() {
		JSONObject jsonObj = new JSONObject();
		float graduatedpercent = 0;
		float terminatedpercent = 0;
		try {
			int numberOfGraduated = singleValueAggregatedDataDao.getTotalGraduatedStudents();
			int numberOfTerminated = singleValueAggregatedDataDao.getTotalDroppedOutStudents();
			int totalCount = numberOfGraduated + numberOfTerminated;

			if(totalCount == 0){
				totalCount = 1;
			}
			
			if(numberOfGraduated > 0 && numberOfTerminated > 0){
				graduatedpercent = (float) (((float) numberOfGraduated/(float) totalCount) * 100.00);
				terminatedpercent = (float) (((float) numberOfTerminated/(float) totalCount) * 100.00);
			}

			jsonObj.put("graduated", Float.toString(graduatedpercent));
			jsonObj.put("terminated", Float.toString(terminatedpercent));

		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e).build();
		}
		return Response.status(Response.Status.OK).entity(jsonObj.toString()).build();
	}

	// Request 16
	@GET
	@Path("state")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getListOfState() {
		List<DataCount> state;
		JSONObject resultObj = new JSONObject();
		int totalCount = 0;
		try{
			state = multipleValueAggregatedDataDao.getListOfStudentsStatesCount();

			for(int i=0; i<state.size();i++){
				totalCount += state.get(i).getDataValue();
			}
			
			if(totalCount == 0){
				totalCount = 1;
			}

			for(int i=0; i<state.size();i++){
				float percent = (float) (( (float) state.get(i).getDataValue()/(float) totalCount ) * 100.00);
				resultObj.put(state.get(i).getDataKey().toLowerCase(), Float.toString(percent));
			}

		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e).build();
		}
		return Response.status(Response.Status.OK).entity(resultObj.toString()).build();
	}

	// Request 17
	@GET
	@Path("campus")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCampusData() {
		JSONObject jsonObj = new JSONObject();
		try {
			int studentInBoston = singleValueAggregatedDataDao.getTotalStudentsInBoston();
			int studentInSeattle = singleValueAggregatedDataDao.getTotalStudentsInSeattle();
			int studentInCharlotte = singleValueAggregatedDataDao.getTotalStudentsInCharlotte();
			int studentInSiliconValley = singleValueAggregatedDataDao.getTotalStudentsInSiliconValley();
			int totalCount = studentInBoston + studentInSeattle + studentInCharlotte + studentInSiliconValley;
			
			if(totalCount == 0){
				totalCount = 1;
			}
			
			float bostonpercent = (float) (((float) studentInBoston/(float) totalCount) * 100.00);
			float seattlepercent = (float) (((float) studentInSeattle/(float) totalCount) * 100.00);
			float charlottepercent = (float) (((float) studentInCharlotte/(float) totalCount) * 100.00);
			float siliconvalleypercent = (float) (((float) studentInSiliconValley/(float) totalCount) * 100.00);

			jsonObj.put("boston", Float.toString(bostonpercent));
			jsonObj.put("seattle", Float.toString(seattlepercent));
			jsonObj.put("charlotte", Float.toString(charlottepercent));
			jsonObj.put("siliconvalley", Float.toString(siliconvalleypercent));

		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e).build();
		}
		return Response.status(Response.Status.OK).entity(jsonObj.toString()).build();
	}

	// Request 18
	@GET
	@Path("scholarship")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getScholarshipData(){;
	JSONObject jsonObj = new JSONObject();
	try {
		int studentWithScholarship = singleValueAggregatedDataDao.getTotalStudentsWithScholarship();
		int studentWithoutScholarship = singleValueAggregatedDataDao.getTotalStudents();
		int totalCount = studentWithScholarship + studentWithoutScholarship ;
		
		if(totalCount == 0){
			totalCount = 1;
		}
			
		float scholarshippercent = (float) (((float) studentWithScholarship/(float) totalCount) * 100.00);
		float nonepercent = (float) (((float) studentWithoutScholarship/(float) totalCount) * 100.00);
		jsonObj.put("scholarship", Float.toString(scholarshippercent));
		jsonObj.put("none", Float.toString(nonepercent));
	} catch (Exception e) {
		return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e).build();
	}
	return Response.status(Response.Status.OK).entity(jsonObj.toString()).build();
	}

	// Request 19
	@GET
	@Path("undergradmajor-percent")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getListOfUndergradMajorPercent() {
		List<DataCount> major;
		JSONObject resultObj = new JSONObject();
		int totalCount = 0;
		try{
			major = multipleValueAggregatedDataDao.getListOfUndergraduateMajorsCount();

			for(int i=0; i<major.size();i++){
				totalCount += major.get(i).getDataValue();
			}
			
			if(totalCount == 0){
				totalCount = 1;
			}

			for(int i=0; i<major.size();i++){
				float percent = (float) (( (float) major.get(i).getDataValue()/(float) totalCount ) * 100.00);
				resultObj.put(major.get(i).getDataKey().toLowerCase(), Float.toString(percent));
			}

		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e).build();
		}
		return Response.status(Response.Status.OK).entity(resultObj.toString()).build();
	}

	// Request 20
	@GET
	@Path("highest-education")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getListOfHighestEducation(){
		List<DataCount> education;
		JSONObject resultObj = new JSONObject();
		int totalCount = 0;
		try {
			education = multipleValueAggregatedDataDao.getListOfHighestDegreesCount();
			for(int i=0; i<education.size();i++){
				totalCount += education.get(i).getDataValue();
			}
			
			if(totalCount == 0){
				totalCount = 1;
			}

			for(int i=0; i<education.size();i++){
				float percent = (float) (( (float) education.get(i).getDataValue()/(float) totalCount ) * 100.00);
				resultObj.put(education.get(i).getDataKey().toLowerCase(), Float.toString(percent));
			}
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e).build();
		}
		return Response.status(Response.Status.OK).entity(resultObj.toString()).build();
	}

	// Machine Learning API
	@GET
	@Path("stats/graduates")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTotalGraduates() {
		JSONObject jsonObj = new JSONObject();
		try {
			int totalGraduateNumber = singleValueAggregatedDataDao.getTotalGraduatedStudents();
			jsonObj.put("graduates", Integer.toString(totalGraduateNumber));
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e).build();
		}
		return Response.status(Response.Status.OK).entity(jsonObj.toString()).build();    	    	
	}

	// Machine Learning API
	@GET
	@Path("stats/total-student-count")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTotalStudents1() {
		JSONObject jsonObj = new JSONObject();
		try {
			int totalGraduateNumber = singleValueAggregatedDataDao.getTotalStudents();
			jsonObj.put("studentcount", Integer.toString(totalGraduateNumber));
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e).build();
		}
		return Response.status(Response.Status.OK).entity(jsonObj.toString()).build();    	
	}

	// Machine Learning API
	@POST
	@Path("stats/student-count")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTotalStudents(StudentStatsObject input) {
		JSONObject jsonObj = new JSONObject();
		List<String> campus = new ArrayList<String>();
		int total = 0;
		try {
			if(input.getCampus().size() < 1){
				campus.add("SEATTLE");
				campus.add("BOSTON");
				campus.add("CHARLOTTE");
				campus.add("SILICON_VALLEY");
				input.setCampus(campus);
			}

			if(input.getCampus().contains("SEATTLE")){
				total+= singleValueAggregatedDataDao.getTotalStudentsInSeattle();
			}
			if(input.getCampus().contains("BOSTON")){
				total+= singleValueAggregatedDataDao.getTotalStudentsInBoston();
			}
			if(input.getCampus().contains("CHARLOTTE")){
				total+= singleValueAggregatedDataDao.getTotalStudentsInCharlotte();
			}
			if(input.getCampus().contains("SILICON_VALLEY")){
				total+= singleValueAggregatedDataDao.getTotalStudentsInSiliconValley();
			}
			jsonObj.put("studentcount", Integer.toString(total));
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e).build();
		}
		return Response.status(Response.Status.OK).entity(jsonObj.toString()).build();    	
	}
}
