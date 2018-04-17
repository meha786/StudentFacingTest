package PublicServiceTest;

import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;
import junit.framework.Assert;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mehaexample.asdDemo.alignWebsite.PublicFacing;
import org.mehaexample.asdDemo.dao.alignprivate.StudentsDao;
import org.mehaexample.asdDemo.dao.alignpublic.*;
import org.mehaexample.asdDemo.enums.*;
import org.mehaexample.asdDemo.model.alignprivate.Students;
import org.mehaexample.asdDemo.model.alignpublic.*;
import org.mehaexample.asdDemo.restModels.StudentSerachCriteria;
import org.mehaexample.asdDemo.restModels.StudentStatsObject;
import org.mehaexample.asdDemo.restModels.TopUnderGradSchools;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

public class serviceTest {

	public static PublicFacing publicFacing;
	public static StudentsPublicDao studentsPublicDao;
	public static UndergraduatesPublicDao undergraduatesPublicDao;
	public static WorkExperiencesPublicDao workExperiencesPublicDao;
	public static MultipleValueAggregatedDataDao multipleValueAggregatedDataDao;
	private static MultipleValueAggregatedDataDao dataDao;
	SingleValueAggregatedDataDao SdataDao;
	SingleValueAggregatedData data;
	public static StudentsDao studentsDao;
	public static final String LIST_OF_STUDENTS_STATES = "ListOfStudentsStates";
	private static final String TOTAL_GRADUATED_STUDENTS = "TotalGraduatedStudents";
	private static final String TOTAL_CURRENT_STUDENTS = "TotalCurrentStudents";
	private static final String TOTAL_STUDENTS = "TotalStudents";
	private static final String TOTAL_STUDENTS_DROPPED_OUT = "TotalStudentsDroppedOut";
	private static final String TOTAL_STUDENTS_GOT_JOB = "TotalStudentsGotJob";
	private static final String TOTAL_STUDENTS_IN_BOSTON = "TotalStudentsInBoston";
	private static final String TOTAL_STUDENTS_IN_SEATTLE = "TotalStudentsInSeattle";
	private static final String TOTAL_STUDENTS_IN_SILICON_VALLEY = "TotalStudentsInSiliconValley";
	private static final String TOTAL_STUDENTS_IN_CHARLOTTE = "TotalStudentsInCharlotte";
	private static final String TOTAL_MALE_STUDENTS = "TotalMaleStudents";
	private static final String TOTAL_FEMALE_STUDENTS = "TotalFemaleStudents";
	private static final String TOTAL_FULL_TIME_STUDENTS = "TotalFullTimeStudents";
	private static final String TOTAL_PART_TIME_STUDENTS = "TotalPartTimeStudents";
	private static final String TOTAL_STUDENTS_WITH_SCHOLARSHIP = "TotalStudentsWithScholarship";
	MultipleValueAggregatedData multipleValueAggregatedData;
	StudentSerachCriteria studentSerachCriteria;
	StudentStatsObject studentStatsObject;
	StudentStatsObject studentStatsObjectEmpty;
	Students newStudent;
	DataCount state;


	@BeforeClass
	public static void init() {
		publicFacing = new PublicFacing();
		studentsPublicDao = new StudentsPublicDao();
		undergraduatesPublicDao = new UndergraduatesPublicDao();
		workExperiencesPublicDao = new WorkExperiencesPublicDao();
		multipleValueAggregatedDataDao = new MultipleValueAggregatedDataDao();
		dataDao = new MultipleValueAggregatedDataDao();
		studentsDao = new StudentsDao();
	}

	@Before
	public void setup() {

		StudentsPublic studentsPublic1 = new StudentsPublic(21, 2015, true);
		StudentsPublic studentsPublic2 = new StudentsPublic(22, 2015, true);
		StudentsPublic studentsPublic3 = new StudentsPublic(23, 2015, true);
		StudentsPublic studentsPublic4 = new StudentsPublic(24, 2016, true);



		studentsPublicDao.createStudent(studentsPublic1);
		studentsPublicDao.createStudent(studentsPublic2);
		studentsPublicDao.createStudent(studentsPublic3);
		studentsPublicDao.createStudent(studentsPublic4);

		UndergraduatesPublic undergraduatesPublic1 =
				new UndergraduatesPublic(21, "cs", "WSU");
		UndergraduatesPublic undergraduatesPublic2 =
				new UndergraduatesPublic(22, "cs", "WSU");
		UndergraduatesPublic undergraduatesPublic3 =
				new UndergraduatesPublic(23, "cs", "WSU");
		UndergraduatesPublic undergraduatesPublic4 =
				new UndergraduatesPublic(24, "agriculture", "NEU");
		UndergraduatesPublic undergraduatesPublic5 =
				new UndergraduatesPublic(21, "agriculture", "NEU");

		undergraduatesPublicDao.createUndergraduate(undergraduatesPublic1);
		undergraduatesPublicDao.createUndergraduate(undergraduatesPublic2);
		undergraduatesPublicDao.createUndergraduate(undergraduatesPublic3);
		undergraduatesPublicDao.createUndergraduate(undergraduatesPublic4);
		undergraduatesPublicDao.createUndergraduate(undergraduatesPublic5);


		WorkExperiencesPublic workExperiencesPublic1 = new WorkExperiencesPublic(21, "lululemon");
		WorkExperiencesPublic workExperiencesPublic2 = new WorkExperiencesPublic(22, "lululemon");
		WorkExperiencesPublic workExperiencesPublic3 = new WorkExperiencesPublic(23, "lululemon");
		WorkExperiencesPublic workExperiencesPublic4 = new WorkExperiencesPublic(24, "blackrock");
		WorkExperiencesPublic workExperiencesPublic5 = new WorkExperiencesPublic(21, "blackrock");

		workExperiencesPublicDao.createWorkExperience(workExperiencesPublic1);
		workExperiencesPublicDao.createWorkExperience(workExperiencesPublic2);
		workExperiencesPublicDao.createWorkExperience(workExperiencesPublic3);
		workExperiencesPublicDao.createWorkExperience(workExperiencesPublic4);
		workExperiencesPublicDao.createWorkExperience(workExperiencesPublic5);

		state = new DataCount("WA",2);

		List<String> coopList = new ArrayList<>();
		List<String> degreeList = new ArrayList<>();
		List<String> schoolList = new ArrayList<>();
		List<String> yestList = new ArrayList<>();
		List<String> campus = new ArrayList<>();
		List<String> campusList = new ArrayList<>();


		coopList.add("lululemon");
		degreeList.add("cs");
		schoolList.add("WSU");
		yestList.add("2015");

		campus.add("SEATTLE");
		campus.add("BOSTON");
		campus.add("CHARLOTTE");
		campus.add("SILICON_VALLEY");

		studentSerachCriteria = new StudentSerachCriteria(coopList, degreeList, schoolList, yestList, "0", "1");

		studentStatsObject = new StudentStatsObject(campus);
		studentStatsObjectEmpty = new StudentStatsObject(campusList);

		multipleValueAggregatedData = new MultipleValueAggregatedData("WA",2);
		multipleValueAggregatedData.setAnalyticTerm(LIST_OF_STUDENTS_STATES);

	}

	@After
	public void deleteForDuplicateDatabase() {
		studentsPublicDao.deleteStudentByPublicId(21);
		studentsPublicDao.deleteStudentByPublicId(22);
		studentsPublicDao.deleteStudentByPublicId(23);
		studentsPublicDao.deleteStudentByPublicId(24);
	}

//	@SuppressWarnings("unchecked")
//	@Test
//	public void getHigestEducation() {
//		JSONObject education = new JSONObject();
//		Response res = publicFacing.getListOfHighestEducation();
//		//Assert.assertEquals(education.toString(), res.getEntity());
//		Assert.assertEquals("{\"bachelors\":\"25.609756\",\"master of it\":\"25.609756\",\"master of science\":\"24.390244\",\"masters\":\"24.390244\"}", res.getEntity());
//		Assert.assertEquals(200, res.getStatus());
//	}


//	@SuppressWarnings("unchecked")
//	@Test
//	public void getState() {
//		JSONObject state = new JSONObject();
//		Response res = publicFacing.getListOfState();
//		//Assert.assertEquals(state.toString(), res.getEntity());
//		Assert.assertEquals("{\"ma\":\"51.219513\",\"wa\":\"48.780487\"}", res.getEntity());
//		System.out.println(res.getEntity());
//		Assert.assertEquals(200, res.getStatus());
//	}

	@Test
	public void getListOfStatesTest(){
		// add data
		MultipleValueAggregatedData wa = new MultipleValueAggregatedData();
		wa.setAnalyticTerm(MultipleValueAggregatedDataDao.LIST_OF_STUDENTS_STATES);
		wa.setAnalyticKey("WA");
		wa.setAnalyticValue(20);

		MultipleValueAggregatedData ma = new MultipleValueAggregatedData();
		ma.setAnalyticTerm(MultipleValueAggregatedDataDao.LIST_OF_STUDENTS_STATES);
		ma.setAnalyticKey("MA");
		ma.setAnalyticValue(21);

		// update in database
		List<MultipleValueAggregatedData> listOfStudentsStates = new ArrayList<>();
		listOfStudentsStates.add(wa);
		listOfStudentsStates.add(ma);
		dataDao.saveOrUpdateList(listOfStudentsStates);

		// call the service method
		Response resp = publicFacing.getListOfState();

		Assert.assertEquals(200, resp.getStatus());   

		// clear the database
		dataDao.deleteListOfBachelorDegrees();
		Assert.assertTrue(dataDao.getTopFiveListOfBachelorDegrees().isEmpty());
	}

	@Test
	public void getHigestEducationsTest(){
		// add data
		MultipleValueAggregatedData wa = new MultipleValueAggregatedData();
		wa.setAnalyticTerm(MultipleValueAggregatedDataDao.LIST_OF_DEGREES);
		wa.setAnalyticKey("Masters");
		wa.setAnalyticValue(20);

		MultipleValueAggregatedData ma = new MultipleValueAggregatedData();
		ma.setAnalyticTerm(MultipleValueAggregatedDataDao.LIST_OF_DEGREES);
		ma.setAnalyticKey("Bachelors");
		ma.setAnalyticValue(21);

		// update in database
		List<MultipleValueAggregatedData> listOfDegree = new ArrayList<>();
		listOfDegree.add(wa);
		listOfDegree.add(ma);
		dataDao.saveOrUpdateList(listOfDegree);

		// call the service method
		Response resp = publicFacing.getListOfHighestEducation();

		Assert.assertEquals(200, resp.getStatus());

		// clear the database
		dataDao.deleteListOfBachelorDegrees();
		Assert.assertTrue(dataDao.getTopFiveListOfBachelorDegrees().isEmpty());
	}

	@Test
	public void getUndergradPercentTest(){
		// add data
		MultipleValueAggregatedData wa = new MultipleValueAggregatedData();
		wa.setAnalyticTerm(MultipleValueAggregatedDataDao.LIST_OF_BACHELOR_DEGREES);
		wa.setAnalyticKey("CS");
		wa.setAnalyticValue(20);

		MultipleValueAggregatedData ma = new MultipleValueAggregatedData();
		ma.setAnalyticTerm(MultipleValueAggregatedDataDao.LIST_OF_BACHELOR_DEGREES);
		ma.setAnalyticKey("IT");
		ma.setAnalyticValue(21);

		// update in database
		List<MultipleValueAggregatedData> listOfMajor = new ArrayList<>();
		listOfMajor.add(wa);
		listOfMajor.add(ma);
		dataDao.saveOrUpdateList(listOfMajor);

		// call the service method
		Response resp = publicFacing.getListOfUndergradMajorPercent();

		Assert.assertEquals(200, resp.getStatus());

		// clear the database
		dataDao.deleteListOfBachelorDegrees();
		Assert.assertTrue(dataDao.getTopFiveListOfBachelorDegrees().isEmpty());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void getAllSchools() {
		List<String> schools = new ArrayList<>();
		schools.add("WSU");
		schools.add("NEU");
		Response res = publicFacing.getAllSchools();
		List response = (List) res.getEntity();
		Assert.assertEquals(schools, response);
		Assert.assertEquals(200, res.getStatus());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void getTopSchools() {
		List<String> schools = new ArrayList<>();
		schools.add("WSU");
		schools.add("NEU");

		JSONArray result = new JSONArray();
		for (String school : schools) {
			result.put(school);
		}
		Response res = publicFacing.getUndergradSchools(3);
		String response = (String) res.getEntity();
		Assert.assertEquals(result.toString(), response);
		Assert.assertEquals(200, res.getStatus());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void getTopCoops() {
		List<String> coops = new ArrayList<>();
		coops.add("lululemon");
		coops.add("blackrock");
		JSONArray result = new JSONArray();
		for (String coop : coops) {
			result.put(coop);
		}
		Response res = publicFacing.getTopCoops(3);
		String response = (String) res.getEntity();
		Assert.assertEquals(result.toString(), response);
		Assert.assertEquals(200, res.getStatus());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void getTopMajor() {
		List<String> undergradMajor = new ArrayList<>();
		undergradMajor.add("cs");
		undergradMajor.add("agriculture");
		JSONArray result = new JSONArray();
		for (String major : undergradMajor) {
			result.put(major);
		}
		Response res = publicFacing.getUndergradDegrees(3);
		String response = (String) res.getEntity();
		Assert.assertEquals(result.toString(), response);
		Assert.assertEquals(200, res.getStatus());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void getTopYear() {
		List<Integer> years = new ArrayList<>();
		years.add(2015);
		years.add(2016);

		JSONArray result = new JSONArray();
		for (Integer year : years) {
			result.put(Integer.toString(year));
		}
		Response res = publicFacing.getTopGraduationYears(3);
		String response = (String) res.getEntity();
		Assert.assertEquals(result.toString(), response);
		Assert.assertEquals(200, res.getStatus());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void getAllUndergard() {
		List<String> undergrads = new ArrayList<>();
		undergrads.add("cs");
		undergrads.add("agriculture");
		Response res = publicFacing.getAllUndergradDegrees();
		List response = (List) res.getEntity();
		Assert.assertEquals(200, res.getStatus());
		Assert.assertEquals(undergrads, response);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void getEnrollment() {

		SingleValueAggregatedData fulltime = new SingleValueAggregatedData();
		fulltime.setAnalyticKey(TOTAL_FULL_TIME_STUDENTS);
		fulltime.setAnalyticValue(23);

		SingleValueAggregatedData parttime = new SingleValueAggregatedData();
		parttime.setAnalyticKey(TOTAL_PART_TIME_STUDENTS);
		parttime.setAnalyticValue(23);

		Response res = publicFacing.getEnrollmentStatus();
        Assert.assertEquals(200, res.getStatus());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void getCampus() {
		SingleValueAggregatedData boston = new SingleValueAggregatedData();
		boston.setAnalyticKey(TOTAL_STUDENTS_IN_BOSTON);
		boston.setAnalyticValue(23);

		SingleValueAggregatedData seattle = new SingleValueAggregatedData();
		seattle.setAnalyticKey(TOTAL_STUDENTS_IN_SEATTLE);
		seattle.setAnalyticValue(23);

		SingleValueAggregatedData charllot = new SingleValueAggregatedData();
		charllot.setAnalyticKey(TOTAL_STUDENTS_IN_CHARLOTTE);
		charllot.setAnalyticValue(23);

		SingleValueAggregatedData siliconvalley = new SingleValueAggregatedData();
		siliconvalley.setAnalyticKey(TOTAL_STUDENTS_IN_SILICON_VALLEY);
		siliconvalley.setAnalyticValue(23);

		Response res = publicFacing.getCampusData();
        Assert.assertEquals(200, res.getStatus());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void getGraduationTest() {

		SingleValueAggregatedData graduate = new SingleValueAggregatedData();
		graduate.setAnalyticKey(TOTAL_GRADUATED_STUDENTS );
		graduate.setAnalyticValue(23);

		SingleValueAggregatedData notGraduate = new SingleValueAggregatedData();
		notGraduate.setAnalyticKey(TOTAL_STUDENTS_DROPPED_OUT);
		notGraduate.setAnalyticValue(23);

		Response res = publicFacing.getGraduation();
        Assert.assertEquals(200, res.getStatus());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void searchStudent() {

		Response res = publicFacing.searchStudent(studentSerachCriteria);
		String response = (String) res.getEntity();
		Assert.assertEquals(200, res.getStatus());
	}


	@SuppressWarnings("unchecked")
	@Test
	public void getScholarshipTest() {

		SingleValueAggregatedData scholar = new SingleValueAggregatedData();
		scholar.setAnalyticKey(TOTAL_STUDENTS_WITH_SCHOLARSHIP);
		scholar.setAnalyticValue(23);

		SingleValueAggregatedData total = new SingleValueAggregatedData();
		total.setAnalyticKey(TOTAL_STUDENTS);
		total.setAnalyticValue(23);

		Response res = publicFacing.getScholarshipData();
        Assert.assertEquals(200, res.getStatus());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void getGenderTest() {

		SingleValueAggregatedData wa = new SingleValueAggregatedData();
		wa.setAnalyticKey(TOTAL_FEMALE_STUDENTS);
		wa.setAnalyticValue(23);

		SingleValueAggregatedData ma = new SingleValueAggregatedData();
		ma.setAnalyticKey(TOTAL_MALE_STUDENTS);
		ma.setAnalyticValue(23);

		Response res = publicFacing.getGender();
        Assert.assertEquals(200, res.getStatus());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void getTotalGraduateTest() {
		SingleValueAggregatedData ma = new SingleValueAggregatedData();
		ma.setAnalyticKey(TOTAL_GRADUATED_STUDENTS);
		ma.setAnalyticValue(23);
		Response res = publicFacing.getTotalGraduates();
		Assert.assertEquals(200, res.getStatus());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void getTotalStudent1Test() {
		Response res = publicFacing.getTotalStudents1();
		Assert.assertEquals(200, res.getStatus());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void getUndergraduatepercentTest() {
		Response res = publicFacing.getListOfUndergradMajorPercent();
		Assert.assertEquals(200, res.getStatus());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void getTotalStudentTest() {
		Response res = publicFacing.getTotalStudents(studentStatsObject);
		Assert.assertEquals(200, res.getStatus());
	}



	@SuppressWarnings("unchecked")
	@Test
	public void getTotalStudentEmptyListTest() {
		Response res = publicFacing.getTotalStudents(studentStatsObjectEmpty);
		Assert.assertEquals(200, res.getStatus());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void getAllGradYearsTest() {
		Response res = publicFacing.getAllGradYears();
		Assert.assertEquals(200, res.getStatus());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void getAllCoopCompaniesTest() {
		Response res = publicFacing.getAllCoopCompanies();
		Assert.assertEquals(200, res.getStatus());
	}
}