package de.fhws.fiw.fds.suttonsolution.api.services;

import de.fhws.fiw.fds.sutton.server.api.queries.AbstractQuery;
import de.fhws.fiw.fds.sutton.server.api.services.AbstractService;
import de.fhws.fiw.fds.suttonsolution.api.states.students.*;
import de.fhws.fiw.fds.suttonsolution.models.Student;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path( "students" )
public class StudentService extends AbstractService
{
	@GET
	@Produces( { MediaType.APPLICATION_JSON } )
	public Response getCollectionStudents( )
	{
		final AbstractQuery<Student> query = new GetCollectionStudentsState.AllStudents( );

		return new GetCollectionStudentsState.Builder( )
			.setQuery( query )
			.setUriInfo( this.uriInfo )
			.setRequest( this.request )
			.setHttpServletRequest( this.httpServletRequest )
			.setContext( this.context )
			.build( )
			.execute( );
	}

	@GET
	@Path( "{id: \\d+}" )
	@Produces( { MediaType.APPLICATION_JSON } )
	public Response getSingleStudent( @PathParam( "id" ) final long id )
	{
		return new GetSingleStudentState.Builder( )
			.setRequestedId( id )
			.setUriInfo( this.uriInfo )
			.setRequest( this.request )
			.setHttpServletRequest( this.httpServletRequest )
			.setContext( this.context )
			.build( )
			.execute( );
	}
}