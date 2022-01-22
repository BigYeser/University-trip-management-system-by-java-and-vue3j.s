package de.fhws.fiw.fds.suttonsolution.database.util;

import de.fhws.fiw.fds.sutton.server.api.security.User;
import de.fhws.fiw.fds.suttonsolution.database.DaoFactory;
import de.fhws.fiw.fds.suttonsolution.models.Student;
import de.fhws.fiw.fds.suttonsolution.models.StudyTrip;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class InitializeDatabase
{
	private final List<User> users = Arrays.asList(
		new User( "braunp", "secret", "lecturer" ),
		new User( "weberk", "secret", "lecturer" ),
		new User( "muellerp", "secret", "admin" ),
		new User( "muellerl", "secret", "lecturer" )
	);

	private final List<StudyTrip> studyTrips = Arrays.asList(
		new StudyTrip(
			"India Gateway Program 2022",
			LocalDate.of( 2022, 2, 15 ),
			LocalDate.of( 2022, 3, 1 ),
			"Christ University",
			"Bangalore",
			"India",
			true ),

		new StudyTrip(
			"Indienreise 2000",
			LocalDate.of( 2000, 1, 10 ),
			LocalDate.of( 2000, 2, 10 ),
			"University of Mumbai",
			"Mumbai",
			"India",
			true ),

		new StudyTrip(
			"Frankreich 2020",
			LocalDate.of( 2021, 6, 1 ),
			LocalDate.of( 2021, 6, 30 ),
			"FHWS",
			"Paris",
			"Frankreich",
			true ),

		new StudyTrip(
			"Würzburg 2021",
			LocalDate.of( 2021, 5, 1 ),
			LocalDate.of( 2021, 5, 30 ),
			"FHWS",
			"Würzburg",
			"Deutschland",
			false ),

		new StudyTrip(
			"Würzburg 2022",
			LocalDate.of( 2022, 1, 1 ),
			LocalDate.of( 2022, 1, 30 ),
			"FHWS",
			"Würzburg",
			"Deutschland",
			false ),

		new StudyTrip(
			"Schweinfurt 2021",
			LocalDate.of( 2021, 2, 1 ),
			LocalDate.of( 2021, 2, 25 ),
			"FHWS",
			"Schweinfurt",
			"Deutschland",
			false )
	);

	private final List<Student> students = Arrays.asList(
		new Student( "Max", "Mustermann", "max.mustermann@fhws.de", "BIN", 2, 1111 ),
		new Student( "James", "Bond", "james.bond@fhws.de", "BEC", 3, 2222 ),
		new Student( "Erika", "Mustermann", "erika.musterfrau@fhws.de", "BWI", 4, 3333 ),
		new Student( "Harry", "Potter", "harry.potter@fhws.de", "MIS", 1, 4444 ),
		new Student( "Abdulruman", "Rihawei", "Abdulruman.Rihawei@fhws.de", "MIS", 1, 5555 )

	);

	public void initialize( )
	{
		populateUsers( );
		populateStudyTrips( );
		populateStudents( );
		populateStudyTripStudents( );
	}

	private void populateUsers( )
	{
		this.users.forEach( this::populateUser );
	}

	private void populateUser( final User user )
	{
		DaoFactory.getInstance( ).getUserDao( ).create( user );
	}

	private void populateStudyTrips( )
	{
		this.studyTrips.forEach( this::populateStudyTrip );
	}

	private void populateStudyTrip( final StudyTrip studyTrip )
	{
		DaoFactory.getInstance( ).getStudyTripDao( ).create( studyTrip );
	}

	private void populateStudents( )
	{
		this.students.forEach( this::populateStudent );
	}

	private void populateStudent( final Student student )
	{
		DaoFactory.getInstance( ).getStudentDao( ).create( student );
	}

	private void populateStudyTripStudents( )
	{
		populateStudyTripStudent( 1, this.students.get( 0 ) );
		populateStudyTripStudent( 1, this.students.get( 1 ) );
		populateStudyTripStudent( 1, this.students.get( 2 ) );

		populateStudyTripStudent( 2, this.students.get( 0 ) );
		populateStudyTripStudent( 2, this.students.get( 1 ) );
		populateStudyTripStudent( 2, this.students.get( 3 ) );

		populateStudyTripStudent( 3, this.students.get( 0 ) );
		populateStudyTripStudent( 3, this.students.get( 1 ) );
		populateStudyTripStudent( 3, this.students.get( 2 ) );
		populateStudyTripStudent( 3, this.students.get( 3 ) );
		populateStudyTripStudent( 3, this.students.get( 4 ) );

		populateStudyTripStudent( 4, this.students.get( 3 ) );
		populateStudyTripStudent( 4, this.students.get( 4 ) );

		populateStudyTripStudent( 5, this.students.get( 2 ) );
		populateStudyTripStudent( 5, this.students.get( 4 ) );

		populateStudyTripStudent( 6, this.students.get( 0 ) );
		populateStudyTripStudent( 6, this.students.get( 1 ) );
		populateStudyTripStudent( 6, this.students.get( 3 ) );
		populateStudyTripStudent( 6, this.students.get( 4 ) );
	}

	private void populateStudyTripStudent( final long primaryId, final Student secondary )
	{
		DaoFactory.getInstance( ).getStudyTripStudentDao( ).update( primaryId, secondary );
	}
}