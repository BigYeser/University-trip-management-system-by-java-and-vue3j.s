package de.fhws.fiw.fds.suttonsolution.api.states.study_trip_students;

import de.fhws.fiw.fds.sutton.server.api.states.AbstractState;
import de.fhws.fiw.fds.sutton.server.api.states.put.AbstractPutRelationState;
import de.fhws.fiw.fds.sutton.server.database.results.NoContentResult;
import de.fhws.fiw.fds.sutton.server.database.results.SingleModelResult;
import de.fhws.fiw.fds.suttonsolution.api.hypermedia.rel_types.IStudyTripStudentRelTypes;
import de.fhws.fiw.fds.suttonsolution.api.hypermedia.uris.IStudyTripStudentUri;
import de.fhws.fiw.fds.suttonsolution.database.DaoFactory;
import de.fhws.fiw.fds.suttonsolution.models.Student;

public class PutStudentOfStudyTripState extends AbstractPutRelationState<Student>
{
	public PutStudentOfStudyTripState( final Builder builder )
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

	@Override protected NoContentResult updateModel( )
	{
		return DaoFactory.getInstance( ).getStudyTripStudentDao( ).update( this.primaryId, this.modelToUpdate );
	}

	@Override protected void defineTransitionLinks( )
	{
		addLink(
			IStudyTripStudentUri.REL_PATH_ID,
			IStudyTripStudentRelTypes.GET_SINGLE_STUDENT,
			getAcceptRequestHeader( ),
			this.primaryId,
			this.requestedId );

	}

	public static class Builder extends AbstractPutRelationStateBuilder<Student>
	{
		@Override public AbstractState build( )
		{
			return new PutStudentOfStudyTripState( this );
		}
	}
}