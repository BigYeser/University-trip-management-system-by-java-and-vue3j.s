package de.fhws.fiw.fds.suttonsolution.api.states.study_trip_students;

import de.fhws.fiw.fds.sutton.server.api.states.AbstractState;
import de.fhws.fiw.fds.sutton.server.api.states.get.AbstractGetRelationState;
import de.fhws.fiw.fds.sutton.server.database.results.SingleModelResult;
import de.fhws.fiw.fds.suttonsolution.api.hypermedia.rel_types.IStudyTripStudentRelTypes;
import de.fhws.fiw.fds.suttonsolution.api.hypermedia.uris.IStudyTripStudentUri;
import de.fhws.fiw.fds.suttonsolution.database.DaoFactory;
import de.fhws.fiw.fds.suttonsolution.models.Student;

public class GetSingleStudentOfStudyTripState extends AbstractGetRelationState<Student>
{
	public GetSingleStudentOfStudyTripState( final Builder builder )
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
		if ( isStudentLinkedToThisStudyTrip( ) )
		{
			addLink(
				IStudyTripStudentUri.REL_PATH_ID,
				IStudyTripStudentRelTypes.DELETE_LINK_FROM_STUDY_TRIP_TO_STUDENT,
				getAcceptRequestHeader( ),
				this.primaryId,
				this.requestedId );
		}
		else
		{
			addLink(
				IStudyTripStudentUri.REL_PATH_ID,
				IStudyTripStudentRelTypes.CREATE_LINK_FROM_STUDY_TRIP_TO_STUDENT,
				getAcceptRequestHeader( ),
				this.primaryId, this.requestedId );
		}

		addLink(
			IStudyTripStudentUri.REL_PATH_SHOW_ONLY_LINKED,
			IStudyTripStudentRelTypes.GET_ALL_LINKED_STUDENTS,
			getAcceptRequestHeader( ),
			this.primaryId );

		addLink(
			IStudyTripStudentUri.REL_PATH_ID,
			IStudyTripStudentRelTypes.SELF,
			getAcceptRequestHeader( ),
			this.primaryId, this.requestedId );
	}

	private boolean isStudentLinkedToThisStudyTrip( )
	{
		return !DaoFactory.getInstance( )
			.getStudyTripStudentDao( )
			.readById( this.primaryId, this.requestedId )
			.isEmpty( );
	}

	public static class Builder extends AbstractGetRelationStateBuilder
	{
		@Override public AbstractState build( )
		{
			return new GetSingleStudentOfStudyTripState( this );
		}
	}
}