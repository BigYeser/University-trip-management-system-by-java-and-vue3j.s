package de.fhws.fiw.fds.suttonsolution.api.states.students;

import de.fhws.fiw.fds.sutton.server.api.states.AbstractState;
import de.fhws.fiw.fds.sutton.server.api.states.get.AbstractGetState;
import de.fhws.fiw.fds.sutton.server.database.results.SingleModelResult;
import de.fhws.fiw.fds.suttonsolution.api.hypermedia.rel_types.IStudentRelTypes;
import de.fhws.fiw.fds.suttonsolution.api.hypermedia.uris.IStudentUri;
import de.fhws.fiw.fds.suttonsolution.database.DaoFactory;
import de.fhws.fiw.fds.suttonsolution.models.Student;

public class GetSingleStudentState extends AbstractGetState<Student>
{
	public GetSingleStudentState( final Builder builder )
	{
		super( builder );
	}

	@Override protected void authorizeRequest( )
	{

	}

	@Override protected SingleModelResult<Student> loadModel( )
	{
		return DaoFactory.getInstance( ).getStudentDao( ).readById( this.requestedId );
	}

	@Override protected void defineTransitionLinks( )
	{

	}

	public static class Builder extends AbstractGetStateBuilder
	{
		@Override public AbstractState build( )
		{
			return new GetSingleStudentState( this );
		}
	}
}