package de.fhws.fiw.fds.suttonsolution.api.services;

import de.fhws.fiw.fds.sutton.server.api.services.AbstractService;
import de.fhws.fiw.fds.suttonsolution.api.states.users.GetCollectionUsersState;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path( "users" )
public class UserService extends AbstractService
{
	@GET
	@Produces( { MediaType.APPLICATION_JSON } )
	public Response getCollectionUsers( )
	{
		return new GetCollectionUsersState.Builder( )
			.setQuery( new GetCollectionUsersState.AllUsers( ) )
			.setUriInfo( this.uriInfo )
			.setRequest( this.request )
			.setHttpServletRequest( this.httpServletRequest )
			.setContext( this.context )
			.build( )
			.execute( );
	}
}