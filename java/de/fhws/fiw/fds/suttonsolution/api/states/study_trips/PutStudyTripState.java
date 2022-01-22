package de.fhws.fiw.fds.suttonsolution.api.states.study_trips;

import de.fhws.fiw.fds.sutton.server.api.states.AbstractState;
import de.fhws.fiw.fds.sutton.server.api.states.put.AbstractPutState;
import de.fhws.fiw.fds.sutton.server.database.results.NoContentResult;
import de.fhws.fiw.fds.sutton.server.database.results.SingleModelResult;
import de.fhws.fiw.fds.suttonsolution.api.hypermedia.rel_types.IStudyTripRelTypes;
import de.fhws.fiw.fds.suttonsolution.api.hypermedia.uris.IStudyTripUri;
import de.fhws.fiw.fds.suttonsolution.database.DaoFactory;
import de.fhws.fiw.fds.suttonsolution.models.StudyTrip;

public class PutStudyTripState extends AbstractPutState<StudyTrip>
{
	public PutStudyTripState( final AbstractPutStateBuilder<StudyTrip> builder )
	{
		super( builder );
	}

	@Override protected void authorizeRequest( )
	{

	}

	@Override protected SingleModelResult<StudyTrip> loadModel( )
	{
		return DaoFactory.getInstance( ).getStudyTripDao( ).readById( this.modelToUpdate.getId( ) );
	}

	@Override protected NoContentResult updateModel( )
	{
		return DaoFactory.getInstance( ).getStudyTripDao( ).update( this.modelToUpdate );
	}

	@Override protected void defineTransitionLinks( )
	{
		addLink(
			IStudyTripUri.REL_PATH,
			IStudyTripRelTypes.GET_SINGLE_STUDY_TRIP,
			getAcceptRequestHeader( ),
			this.modelToUpdate.getId( )
		);
	}

	public static class Builder extends AbstractPutStateBuilder<StudyTrip>
	{
		@Override public AbstractState build( )
		{
			return new PutStudyTripState( this );
		}
	}
}