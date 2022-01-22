package de.fhws.fiw.fds.suttonsolution.api.states.study_trip_students;

import de.fhws.fiw.fds.sutton.server.api.queries.AbstractRelationQuery;
import de.fhws.fiw.fds.sutton.server.api.states.AbstractState;
import de.fhws.fiw.fds.sutton.server.api.states.get.AbstractGetCollectionRelationState;
import de.fhws.fiw.fds.sutton.server.database.DatabaseException;
import de.fhws.fiw.fds.sutton.server.database.results.CollectionModelResult;
import de.fhws.fiw.fds.suttonsolution.api.hypermedia.rel_types.IStudyTripStudentRelTypes;
import de.fhws.fiw.fds.suttonsolution.api.hypermedia.uris.IStudyTripStudentUri;
import de.fhws.fiw.fds.suttonsolution.database.DaoFactory;
import de.fhws.fiw.fds.suttonsolution.database.spi.IStudyTripStudentDao;
import de.fhws.fiw.fds.suttonsolution.models.Student;

import javax.ws.rs.core.GenericEntity;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class GetCollectionStudentsOfStudyTripState extends AbstractGetCollectionRelationState<Student>
{
	public GetCollectionStudentsOfStudyTripState( final Builder builder )
	{
		super( builder );
	}

	@Override protected void authorizeRequest( )
	{

	}

	@Override protected void defineHttpResponseBody( )
	{
		this.responseBuilder.entity( new GenericEntity<Collection<Student>>( this.result.getResult( ) ) { } );
	}

	@Override protected void defineTransitionLinks( )
	{
		if ( this.query.isShowAll( ) )
		{
			addLink(
				IStudyTripStudentUri.REL_PATH_SHOW_ONLY_LINKED,
				IStudyTripStudentRelTypes.GET_ALL_LINKED_STUDENTS,
				getAcceptRequestHeader( ),
				this.primaryId );
		}
		else
		{
			addLink(
				IStudyTripStudentUri.REL_PATH_SHOW_ALL,
				IStudyTripStudentRelTypes.GET_ALL_STUDENTS,
				getAcceptRequestHeader( ),
				this.primaryId );
		}
	}

	public static class AllStudents extends AbstractRelationQuery<Student>
	{
		private final IStudyTripStudentDao storage;

		public AllStudents( final long primaryId, final boolean showAll )
		{
			super( primaryId, showAll );

			this.storage = DaoFactory.getInstance( ).getStudyTripStudentDao( );
		}

		@Override protected CollectionModelResult<Student> doExecuteQuery( ) throws DatabaseException
		{
			Collection<Student> studentsFromDb;

			if ( this.showAll )
			{
				studentsFromDb = this.storage.readAllByPredicate( this.primaryId, all( ) ).getResult( );
			}
			else
			{
				studentsFromDb = this.storage.readByPredicate( this.primaryId, all( ) ).getResult( );
			}

			final List<Student> sortedStudents = new LinkedList<>( studentsFromDb );

			sortedStudents.sort( Student.getComparator( ) );

			return new CollectionModelResult<>( sortedStudents );
		}
	}

	public static class Builder extends AbstractGetCollectionRelationStateBuilder<Student>
	{
		@Override public AbstractState build( )
		{
			return new GetCollectionStudentsOfStudyTripState( this );
		}
	}
}