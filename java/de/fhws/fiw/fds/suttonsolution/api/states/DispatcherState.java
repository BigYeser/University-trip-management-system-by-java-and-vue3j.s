package de.fhws.fiw.fds.suttonsolution.api.states;

import de.fhws.fiw.fds.sutton.server.api.states.AbstractState;
import de.fhws.fiw.fds.sutton.server.api.states.get.AbstractGetDispatcherState;
import de.fhws.fiw.fds.suttonsolution.api.hypermedia.rel_types.*;
import de.fhws.fiw.fds.suttonsolution.api.hypermedia.uris.*;

import javax.ws.rs.core.MediaType;

public class DispatcherState extends AbstractGetDispatcherState
{
	public DispatcherState( final Builder builder )
	{
		super( builder );
	}

	@Override protected void defineTransitionLinks( )
	{
		addLink( IStudyTripUri.REL_PATH, IStudyTripRelTypes.GET_ALL_STUDY_TRIPS, MediaType.APPLICATION_JSON );
		addLink( IStudentUri.REL_PATH, IStudentRelTypes.GET_ALL_STUDENTS, MediaType.APPLICATION_JSON );
		addLink( IUserUri.REL_PATH, IUserRelTypes.GET_ALL_USERS, MediaType.APPLICATION_JSON );
	}

	public static class Builder extends AbstractDispatcherStateBuilder
	{
		@Override public AbstractState build( )
		{
			return new DispatcherState( this );
		}
	}
}