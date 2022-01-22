package de.fhws.fiw.fds.suttonsolution;

import de.fhws.fiw.fds.sutton.server.api.AbstractApplication;
import de.fhws.fiw.fds.suttonsolution.api.services.DispatcherService;
import de.fhws.fiw.fds.suttonsolution.api.services.StudentService;
import de.fhws.fiw.fds.suttonsolution.api.services.StudyTripService;
import de.fhws.fiw.fds.suttonsolution.api.services.UserService;
import de.fhws.fiw.fds.suttonsolution.database.util.InitializeDatabase;
import de.fhws.fiw.fds.suttonsolution.utils.date.LocalDateConverterProvider;

import javax.ws.rs.ApplicationPath;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath( "api" )
public class SuttonSolutionApplication extends AbstractApplication
{
	public SuttonSolutionApplication( )
	{
		super( );

		register( LocalDateConverterProvider.class );

		initializeDatabase( );
	}

	private void initializeDatabase( )
	{
		new InitializeDatabase( ).initialize( );
	}

	@Override protected Set<Class<?>> getServiceClasses( )
	{
		final Set<Class<?>> returnValue = new HashSet<>( );

		returnValue.add( UserService.class );
		returnValue.add( DispatcherService.class );
		returnValue.add( StudyTripService.class );
		returnValue.add( StudentService.class );

		return returnValue;
	}
}