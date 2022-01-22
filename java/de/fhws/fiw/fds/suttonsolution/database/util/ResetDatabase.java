package de.fhws.fiw.fds.suttonsolution.database.util;

import de.fhws.fiw.fds.suttonsolution.database.DaoFactory;

public class ResetDatabase
{
	public void reset( )
	{
		DaoFactory.getInstance( ).getStudyTripDao( ).clearStorage( );
		DaoFactory.getInstance( ).getStudyTripStudentDao( ).clearStorage( );
		DaoFactory.getInstance( ).getStudentDao( ).clearStorage( );
		DaoFactory.getInstance( ).getUserDao( ).clearStorage( );
	}
}