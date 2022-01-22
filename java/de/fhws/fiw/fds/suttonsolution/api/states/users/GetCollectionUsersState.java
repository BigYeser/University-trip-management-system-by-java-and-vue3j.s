package de.fhws.fiw.fds.suttonsolution.api.states.users;

import de.fhws.fiw.fds.sutton.server.api.queries.AbstractQuery;
import de.fhws.fiw.fds.sutton.server.api.security.User;
import de.fhws.fiw.fds.sutton.server.api.states.AbstractState;
import de.fhws.fiw.fds.sutton.server.api.states.get.AbstractGetCollectionState;
import de.fhws.fiw.fds.sutton.server.database.DatabaseException;
import de.fhws.fiw.fds.sutton.server.database.results.CollectionModelResult;
import de.fhws.fiw.fds.suttonsolution.database.DaoFactory;

import javax.ws.rs.core.GenericEntity;
import java.util.Collection;

public class GetCollectionUsersState extends AbstractGetCollectionState<User>
{
	public GetCollectionUsersState( final Builder builder )
	{
		super( builder );
	}

	@Override protected void authorizeRequest( )
	{

	}

	@Override protected void defineHttpResponseBody( )
	{
		this.responseBuilder.entity( new GenericEntity<Collection<User>>( this.result.getResult( ) ) { } );
	}

	@Override protected void defineTransitionLinks( )
	{

	}

	public static class AllUsers extends AbstractQuery<User>
	{
		@Override protected CollectionModelResult<User> doExecuteQuery( ) throws DatabaseException
		{
			return DaoFactory.getInstance( ).getUserDao( ).readByPredicate( all( ) );
		}
	}

	public static class Builder extends AbstractGetCollectionStateBuilder<User>
	{
		@Override public AbstractState build( )
		{
			return new GetCollectionUsersState( this );
		}
	}
}