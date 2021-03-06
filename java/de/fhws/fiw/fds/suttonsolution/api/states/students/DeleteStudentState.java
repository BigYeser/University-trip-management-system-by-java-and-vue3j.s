package de.fhws.fiw.fds.suttonsolution.api.states.students;

import de.fhws.fiw.fds.sutton.server.api.states.AbstractState;
import de.fhws.fiw.fds.sutton.server.api.states.delete.AbstractDeleteState;
import de.fhws.fiw.fds.sutton.server.database.results.NoContentResult;
import de.fhws.fiw.fds.sutton.server.database.results.SingleModelResult;
import de.fhws.fiw.fds.suttonsolution.api.hypermedia.rel_types.IStudentRelTypes;
import de.fhws.fiw.fds.suttonsolution.api.hypermedia.uris.IStudentUri;
import de.fhws.fiw.fds.suttonsolution.database.DaoFactory;
import de.fhws.fiw.fds.suttonsolution.models.Student;

public class DeleteStudentState extends AbstractDeleteState<Student>
{
	public DeleteStudentState( final Builder builder )
	{
		super( builder );
	}

	@Override protected void authorizeRequest( )
	{

	}

	@Override protected SingleModelResult<Student> loadModel( )
	{
		return DaoFactory.getInstance( ).getStudentDao( ).readById( this.modelIdToDelete );
	}

	@Override protected NoContentResult deleteModel( )
	{
		DaoFactory.getInstance( ).getStudyTripStudentDao( ).deleteRelationsFromPrimary( this.modelIdToDelete );

		return DaoFactory.getInstance( ).getStudentDao( ).delete( this.modelIdToDelete );
	}

	@Override protected void defineTransitionLinks( )
	{
		addLink( IStudentUri.REL_PATH, IStudentRelTypes.GET_ALL_STUDENTS, getAcceptRequestHeader( ) );
	}

	public static class Builder extends AbstractDeleteStateBuilder
	{
		@Override public AbstractState build( )
		{
			return new DeleteStudentState( this );
		}
	}
}