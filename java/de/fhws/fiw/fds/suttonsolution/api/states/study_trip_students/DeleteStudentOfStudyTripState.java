package de.fhws.fiw.fds.suttonsolution.api.states.study_trip_students;

import de.fhws.fiw.fds.sutton.server.api.states.AbstractState;
import de.fhws.fiw.fds.sutton.server.api.states.delete.AbstractDeleteRelationState;
import de.fhws.fiw.fds.sutton.server.database.results.NoContentResult;
import de.fhws.fiw.fds.sutton.server.database.results.SingleModelResult;
import de.fhws.fiw.fds.suttonsolution.api.hypermedia.rel_types.IStudyTripStudentRelTypes;
import de.fhws.fiw.fds.suttonsolution.api.hypermedia.uris.IStudyTripStudentUri;
import de.fhws.fiw.fds.suttonsolution.database.DaoFactory;
import de.fhws.fiw.fds.suttonsolution.models.Student;

public class DeleteStudentOfStudyTripState extends AbstractDeleteRelationState<Student>
{
	public DeleteStudentOfStudyTripState( final Builder builder )
	{
		super( builder );
	}

	@Override protected void authorizeRequest( )
	{

	}

	@Override protected SingleModelResult<Student> loadModel( )
	{
		return DaoFactory.getInstance( ).getStudyTripStudentDao( ).readById( this.primaryId, this.modelIdToDelete );
	}

	@Override protected NoContentResult deleteModel( )
	{
		return DaoFactory.getInstance( ).getStudyTripStudentDao( ).deleteRelation( this.primaryId, this.modelIdToDelete );
	}

	@Override protected void defineTransitionLinks( )
	{
		addLink(
			IStudyTripStudentUri.REL_PATH,
			IStudyTripStudentRelTypes.GET_ALL_LINKED_STUDENTS,
			getAcceptRequestHeader( ),
			this.primaryId );

		addLink(
				IStudyTripStudentUri.REL_PATH_ID,
				IStudyTripStudentRelTypes.GET_SINGLE_STUDENT,
				getAcceptRequestHeader( ),
				this.primaryId ,this.modelIdToDelete);
	}

	public static class Builder extends AbstractDeleteRelationStateBuilder
	{
		@Override public AbstractState build( )
		{
			return new DeleteStudentOfStudyTripState( this );
		}
	}
}