package org.openmrs.module.lite.dataintegrity;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.openmrs.Patient;
import org.openmrs.api.context.Context;
import org.openmrs.module.dataintegrity.DataIntegrityRule;
import org.openmrs.module.dataintegrity.rule.RuleDefinition;
import org.openmrs.module.dataintegrity.rule.RuleResult;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InvalidDateOfDeath implements RuleDefinition<Patient> {
	private SessionFactory sessionFactory;

	@Override
	public List<RuleResult<Patient>> evaluate() {
		Criteria criteria = getSession().createCriteria(Patient.class, "patient");
		criteria.add(Restrictions.isNotNull("deathDate"));
		criteria.add(Restrictions.eq("voided", false));
		criteria.add(Restrictions.gt("deathDate", new Date()));

		List<Patient> patientList = criteria.list();
		return patientToRuleResultTransformer(patientList);
	}

	private List<RuleResult<Patient>> patientToRuleResultTransformer(List<Patient> patients) {
        List<RuleResult<Patient>> ruleResults = new ArrayList<>();
        for (Patient patient : patients) {
            RuleResult<Patient> ruleResult = new RuleResult<>();
            ruleResult.setActionUrl("");
            ruleResult.setNotes("Patient with invalid date");
            ruleResult.setEntity(patient);
            ruleResults.add(ruleResult);
        }
        return ruleResults;
    }

	public DataIntegrityRule getRule() {
		DataIntegrityRule rule = new DataIntegrityRule();
		rule.setRuleCategory("patient");
		rule.setHandlerConfig("java");
		rule.setHandlerClassname(getClass().getName());
		rule.setRuleName("Invalid Date of Death");
		rule.setUuid("e0e6cb8d-8492-4bed-bf3f-08a3ecf3bedb");
		return rule;
	}

	public Session getSession() {
		return Context.getRegisteredComponent("sessionFactory", SessionFactory.class).getCurrentSession();
	}

	/*private org.hibernate.Session getCurrentSession() {
		try {
			return sessionFactory.getCurrentSession();
		}
		catch (NoSuchMethodError ex) {
			try {
				Method method = sessionFactory.getClass().getMethod("getCurrentSession", null);
				return (org.hibernate.Session)method.invoke(sessionFactory, null);
			}
			catch (Exception e) {
				System.out.println("Failed to get the hibernate session:" + e.getMessage());
				e.printStackTrace();
			}
		}
		return null;
	}*/

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}
