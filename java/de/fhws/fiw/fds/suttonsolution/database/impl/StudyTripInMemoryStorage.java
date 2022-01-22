package de.fhws.fiw.fds.suttonsolution.database.impl;

import de.fhws.fiw.fds.sutton.server.database.inmemory.AbstractInMemoryStorage;
import de.fhws.fiw.fds.suttonsolution.database.spi.IStudyTripDao;
import de.fhws.fiw.fds.suttonsolution.models.StudyTrip;

public class StudyTripInMemoryStorage
	extends AbstractInMemoryStorage<StudyTrip>
	implements IStudyTripDao
{
	@Override public void clearStorage( )
	{
		this.storage.clear( );
	}
}