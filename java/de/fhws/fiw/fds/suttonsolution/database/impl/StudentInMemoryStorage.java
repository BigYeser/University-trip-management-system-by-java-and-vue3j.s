package de.fhws.fiw.fds.suttonsolution.database.impl;

import de.fhws.fiw.fds.sutton.server.database.inmemory.AbstractInMemoryStorage;
import de.fhws.fiw.fds.suttonsolution.database.spi.IStudentDao;
import de.fhws.fiw.fds.suttonsolution.models.Student;

public class StudentInMemoryStorage
	extends AbstractInMemoryStorage<Student>
	implements IStudentDao
{
	@Override public void clearStorage( )
	{
		this.storage.clear( );
	}
}