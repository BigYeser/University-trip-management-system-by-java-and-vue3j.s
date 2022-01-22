package de.fhws.fiw.fds.suttonsolution.api.states.study_trips;

import de.fhws.fiw.fds.sutton.server.api.states.AbstractState;
import de.fhws.fiw.fds.sutton.server.api.states.post.AbstractPostState;
import de.fhws.fiw.fds.sutton.server.database.results.NoContentResult;
import de.fhws.fiw.fds.suttonsolution.database.DaoFactory;
import de.fhws.fiw.fds.suttonsolution.models.StudyTrip;

public class PostStudyTripState extends AbstractPostState<StudyTrip>
{
	public PostStudyTripState( final AbstractPostStateBuilder<StudyTrip> builder )
	{
		super( builder );
	}

	@Override protected void authorizeRequest( )
	{

	}

	@Override protected NoContentResult saveModel( )
	{
		return DaoFactory.getInstance( ).getStudyTripDao( ).create( this.modelToStore );
	}

	@Override protected void defineTransitionLinks( )
	{

	}

	public static class Builder extends AbstractPostStateBuilder<StudyTrip>
	{
		@Override public AbstractState build( )
		{
			return new PostStudyTripState( this );
		}
	}
}