package de.fhws.fiw.fds.suttonsolution.database;

import de.fhws.fiw.fds.suttonsolution.database.impl.*;
import de.fhws.fiw.fds.suttonsolution.database.spi.*;

public class DaoFactory
{
	private static DaoFactory INSTANCE;

	public static final DaoFactory getInstance( )
	{
		if ( INSTANCE == null )
		{
			INSTANCE = new DaoFactory( );
		}

		return INSTANCE;
	}

	private final IUserDao userDao;

	private final IStudyTripDao studyTripDao;

	private final IStudentDao studentDao;

	private final IStudyTripStudentDao studyTripStudentDao;

	public DaoFactory( )
	{
		this.userDao = new UserDaoInMemoryStorage( );
		this.studyTripDao = new StudyTripInMemoryStorage( );
		this.studentDao = new StudentInMemoryStorage( );
		this.studyTripStudentDao = new StudyTripToStudentRelationInMemoryStorage( );
	}

	public IUserDao getUserDao( )
	{
		return userDao;
	}

	public IStudyTripDao getStudyTripDao( )
	{
		return studyTripDao;
	}

	public IStudentDao getStudentDao( )
	{
		return studentDao;
	}

	public IStudyTripStudentDao getStudyTripStudentDao( )
	{
		return studyTripStudentDao;
	}
}