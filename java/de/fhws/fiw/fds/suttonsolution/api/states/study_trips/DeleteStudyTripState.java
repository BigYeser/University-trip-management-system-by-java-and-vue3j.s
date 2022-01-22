package de.fhws.fiw.fds.suttonsolution.api.states.study_trips;

import de.fhws.fiw.fds.sutton.server.api.states.AbstractState;
import de.fhws.fiw.fds.sutton.server.api.states.delete.AbstractDeleteState;
import de.fhws.fiw.fds.sutton.server.database.results.NoContentResult;
import de.fhws.fiw.fds.sutton.server.database.results.SingleModelResult;
import de.fhws.fiw.fds.suttonsolution.api.hypermedia.rel_types.IStudyTripRelTypes;
import de.fhws.fiw.fds.suttonsolution.api.hypermedia.uris.IStudyTripUri;
import de.fhws.fiw.fds.suttonsolution.database.DaoFactory;
import de.fhws.fiw.fds.suttonsolution.models.StudyTrip;

public class DeleteStudyTripState extends AbstractDeleteState<StudyTrip>
{
	public DeleteStudyTripState( final AbstractDeleteStateBuilder builder )
	{
		super( builder );
	}

	@Override protected void authorizeRequest( )
	{

	}

	@Override protected SingleModelResult<StudyTrip> loadModel( )
	{
		return DaoFactory.getInstance( ).getStudyTripDao( ).readById( this.modelIdToDelete );
	}

	@Override protected NoContentResult deleteModel( )
	{
		DaoFactory.getInstance( ).getStudyTripStudentDao( ).deleteRelationsFromPrimary( this.modelIdToDelete );

		return DaoFactory.getInstance( ).getStudyTripDao( ).delete( this.modelIdToDelete );
	}

	@Override protected void defineTransitionLinks( )
	{
		addLink( IStudyTripUri.REL_PATH, IStudyTripRelTypes.GET_ALL_STUDY_TRIPS, getAcceptRequestHeader( ) );
	}

	public static class Builder extends AbstractDeleteStateBuilder
	{
		@Override public AbstractState build( )
		{
			return new DeleteStudyTripState( this );
		}
	}
}