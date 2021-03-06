/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.lite.api.impl;

import org.openmrs.api.impl.BaseOpenmrsService;
import org.openmrs.module.lite.api.LiteService;
import org.openmrs.module.lite.api.db.LiteDao;

public class LiteServiceImpl extends BaseOpenmrsService implements LiteService {
	
	private LiteDao dao;
	
	public void setDao(LiteDao dao) {
		this.dao = dao;
	}
	
	public LiteDao getDao() {
		return dao;
	}
}
