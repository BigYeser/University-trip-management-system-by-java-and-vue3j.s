package de.fhws.fiw.fds.suttonsolution.api.states.students;

import de.fhws.fiw.fds.sutton.server.api.queries.AbstractQuery;
import de.fhws.fiw.fds.sutton.server.api.states.AbstractState;
import de.fhws.fiw.fds.sutton.server.api.states.get.AbstractGetCollectionState;
import de.fhws.fiw.fds.sutton.server.database.DatabaseException;
import de.fhws.fiw.fds.sutton.server.database.results.CollectionModelResult;
import de.fhws.fiw.fds.suttonsolution.api.hypermedia.rel_types.IStudentRelTypes;
import de.fhws.fiw.fds.suttonsolution.api.hypermedia.uris.IStudentUri;
import de.fhws.fiw.fds.suttonsolution.database.DaoFactory;
import de.fhws.fiw.fds.suttonsolution.models.Student;

import javax.ws.rs.core.GenericEntity;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class GetCollectionStudentsState extends AbstractGetCollectionState<Student>
{
	public GetCollectionStudentsState( final Builder builder )
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

	}

	public static class AllStudents extends AbstractQuery<Student>
	{
		@Override protected CollectionModelResult<Student> doExecuteQuery( ) throws DatabaseException
		{
			final Collection<Student> studentsFromDb =
				DaoFactory.getInstance( ).getStudentDao( ).readByPredicate( all( ) ).getResult( );

			final List<Student> sortedStudents = new LinkedList<>( studentsFromDb );

			sortedStudents.sort( Student.getComparator() );

			return new CollectionModelResult<>( sortedStudents );
		}
	}

	public static class Builder extends AbstractGetCollectionStateBuilder<Student>
	{
		@Override public AbstractState build( )
		{
			return new GetCollectionStudentsState( this );
		}
	}
}