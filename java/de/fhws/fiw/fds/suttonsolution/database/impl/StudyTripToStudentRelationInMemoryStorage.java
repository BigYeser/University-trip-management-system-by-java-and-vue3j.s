package de.fhws.fiw.fds.suttonsolution.database.impl;

import de.fhws.fiw.fds.sutton.server.database.IDatabaseAccessObject;
import de.fhws.fiw.fds.sutton.server.database.inmemory.AbstractInMemoryRelationStorage;
import de.fhws.fiw.fds.suttonsolution.database.DaoFactory;
import de.fhws.fiw.fds.suttonsolution.database.spi.IStudyTripStudentDao;
import de.fhws.fiw.fds.suttonsolution.models.Student;

public class StudyTripToStudentRelationInMemoryStorage
	extends AbstractInMemoryRelationStorage<Student>
	implements IStudyTripStudentDao
{
	@Override protected IDatabaseAccessObject<Student> getSecondaryStorage( )
	{
		return DaoFactory.getInstance( ).getStudentDao( );
	}

	@Override public void clearStorage( )
	{
		this.storage.clear();
	}
}