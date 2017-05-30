package org.openmrs.module.lite.dataintegrity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.openmrs.Patient;
import org.openmrs.api.context.Context;
import org.openmrs.module.dataintegrity.rule.RuleResult;
import org.openmrs.test.BaseModuleContextSensitiveTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.logging.Logger;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:**/moduleApplicationContext.xml" })
public class InvalidDateOfDeathTest extends BaseModuleContextSensitiveTest {
	
	private static final String STANDARD_DATASET_XML = "standardTestDataset.xml";
	
	@Mock
	InvalidDateOfDeath invalidDateOfDeath;
	
	Logger logger = Logger.getLogger("logger");
	
	@Before
	public void initialize() throws Exception {
		executeDataSet(STANDARD_DATASET_XML);
		invalidDateOfDeath = new InvalidDateOfDeath();
	}
	
	@Test
	public void EvaluateTestNonEmptyPatientList() {
		List<Patient> patients = Context.getPatientService().getAllPatients();
		for (Patient patient : patients)
			logger.info(patient.getPerson().getDeathDate() + "");
		
		List<RuleResult<Patient>> list = invalidDateOfDeath.evaluate();
		assertEquals(2, list.size());
	}
}
