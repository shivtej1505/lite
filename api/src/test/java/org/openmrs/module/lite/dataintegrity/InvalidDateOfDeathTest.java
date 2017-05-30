package org.openmrs.module.lite.dataintegrity;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.openmrs.Patient;
import org.openmrs.module.dataintegrity.rule.RuleResult;
import org.openmrs.test.BaseModuleContextSensitiveTest;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class InvalidDateOfDeathTest extends BaseModuleContextSensitiveTest {
	
	private static final String STANDARD_DATASET_XML = "standardTestDataset.xml";
	
	@Mock
	InvalidDateOfDeath invalidDateOfDeath;
	
	@Before
	public void initialize() throws Exception {
		executeDataSet(STANDARD_DATASET_XML);
		invalidDateOfDeath = new InvalidDateOfDeath();
	}
	
	@Test
	public void EvaluateTestNonEmptyPatientList() {
		/*Logger logger = Logger.getLogger("logger");
		List<Patient> patients = Context.getPatientService().getAllPatients();
		for (Patient patient : patients)
			logger.info(patient.getPerson().getDeathDate() + "");*/
		List<RuleResult<Patient>> list = invalidDateOfDeath.evaluate();
		assertEquals(2, list.size());
	}
}
