package de.fhws.fiw.fds.suttonsolution.api.services;

import de.fhws.fiw.fds.sutton.server.api.queries.AbstractQuery;
import de.fhws.fiw.fds.sutton.server.api.services.AbstractService;
import de.fhws.fiw.fds.suttonsolution.api.states.study_trip_students.*;
import de.fhws.fiw.fds.suttonsolution.api.states.study_trips.*;
import de.fhws.fiw.fds.suttonsolution.models.Student;
import de.fhws.fiw.fds.suttonsolution.models.StudyTrip;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDate;

@Path( "studytrips" )
public class StudyTripService extends AbstractService
{
	@GET
	@Produces( { MediaType.APPLICATION_JSON } )
	public Response getCollectionStudyTrips(
		@DefaultValue( "" ) @QueryParam( "name" ) final String name,
		@DefaultValue( "" ) @QueryParam( "startDate" ) final LocalDate startDate,
		@DefaultValue( "" ) @QueryParam( "endDate" ) final LocalDate endDate,
		@DefaultValue( "" ) @QueryParam( "supervisorNames" ) final String supervisorNames,
		@DefaultValue( "" ) @QueryParam( "city" ) final String city,
		@DefaultValue( "" ) @QueryParam( "country" ) final String country,
		@DefaultValue( "false" ) @QueryParam( "isNational" ) final boolean isNational)
	{
		final AbstractQuery<StudyTrip> query = new GetCollectionStudyTripsState.ByAttributes(
			name,
			startDate,
			endDate,
			supervisorNames,
			city,
			country,
			isNational );

		return new GetCollectionStudyTripsState.Builder( )
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
	public Response getSingleStudyTrip( @PathParam( "id" ) final long id )
	{
		return new GetSingleStudyTripState.Builder( )
			.setRequestedId( id )
			.setUriInfo( this.uriInfo )
			.setRequest( this.request )
			.setHttpServletRequest( this.httpServletRequest )
			.setContext( this.context )
			.build( )
			.execute( );
	}

	@GET
	@Path( "{studyTripId: \\d+}/students" )
	@Produces( { MediaType.APPLICATION_JSON } )
	public Response getStudentsOfStudyTrip(
		@PathParam( "studyTripId" ) final long studyTripId,
		@DefaultValue( "false" ) @QueryParam( "showAll" ) final boolean showAll )
	{
		return new GetCollectionStudentsOfStudyTripState.Builder( )
			.setParentId( studyTripId )
			.setQuery( new GetCollectionStudentsOfStudyTripState.AllStudents( studyTripId, showAll ) )
			.setUriInfo( this.uriInfo )
			.setRequest( this.request )
			.setHttpServletRequest( this.httpServletRequest )
			.setContext( this.context )
			.build( )
			.execute( );
	}

	@GET
	@Path( "{studyTripId: \\d+}/students/{studentId: \\d+}" )
	@Produces( { MediaType.APPLICATION_JSON } )
	public Response getStudentByIdOfStudyTrip(
		@PathParam( "studyTripId" ) final long studyTripId,
		@PathParam( "studentId" ) final long studentId )
	{
		return new GetSingleStudentOfStudyTripState.Builder( )
			.setParentId( studyTripId )
			.setRequestedId( studentId )
			.setUriInfo( this.uriInfo )
			.setRequest( this.request )
			.setHttpServletRequest( this.httpServletRequest )
			.setContext( this.context )
			.build( )
			.execute( );
	}

	@PUT
	@Path( "{studyTripId: \\d+}/students/{studentId: \\d+}" )
	@Consumes( { MediaType.APPLICATION_JSON } )
	public Response updateStudentOfStudyTrip(
		@PathParam( "studyTripId" ) final long studyTripId,
		@PathParam( "studentId" ) final long studentId, final Student student )
	{
		return new PutStudentOfStudyTripState.Builder( )
			.setParentId( studyTripId )
			.setRequestedId( studentId )
			.setModelToUpdate( student )
			.setUriInfo( this.uriInfo )
			.setRequest( this.request )
			.setHttpServletRequest( this.httpServletRequest )
			.setContext( this.context )
			.build( )
			.execute( );
	}

	@DELETE
	@Path( "{studyTripId: \\d+}/students/{studentId: \\d+}" )
	public Response deleteStudentOfStudyTrip(
		@PathParam( "studyTripId" ) final long studyTripId,
		@PathParam( "studentId" ) final long studentId )
	{
		return new DeleteStudentOfStudyTripState.Builder( )
			.setParentId( studyTripId )
			.setRequestedId( studentId )
			.setUriInfo( this.uriInfo )
			.setRequest( this.request )
			.setHttpServletRequest( this.httpServletRequest )
			.setContext( this.context )
			.build( )
			.execute( );
	}
}