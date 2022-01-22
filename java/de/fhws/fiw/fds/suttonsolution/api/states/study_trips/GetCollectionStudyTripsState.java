package de.fhws.fiw.fds.suttonsolution.api.states.study_trips;

import de.fhws.fiw.fds.sutton.server.api.queries.AbstractQuery;
import de.fhws.fiw.fds.sutton.server.api.states.AbstractState;
import de.fhws.fiw.fds.sutton.server.api.states.get.AbstractGetCollectionState;
import de.fhws.fiw.fds.sutton.server.database.DatabaseException;
import de.fhws.fiw.fds.sutton.server.database.results.CollectionModelResult;
import de.fhws.fiw.fds.suttonsolution.api.hypermedia.rel_types.IStudyTripRelTypes;
import de.fhws.fiw.fds.suttonsolution.api.hypermedia.uris.IStudyTripUri;
import de.fhws.fiw.fds.suttonsolution.database.DaoFactory;
import de.fhws.fiw.fds.suttonsolution.models.StudyTrip;
import org.apache.commons.lang.StringUtils;

import javax.ws.rs.core.GenericEntity;
import java.time.LocalDate;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;

public class GetCollectionStudyTripsState extends AbstractGetCollectionState<StudyTrip>
{
	public GetCollectionStudyTripsState( final Builder builder )
	{
		super( builder );
	}

	@Override protected void authorizeRequest( )
	{

	}

	@Override protected void defineHttpResponseBody( )
	{
		this.responseBuilder.entity( new GenericEntity<Collection<StudyTrip>>( this.result.getResult( ) ) { } );
	}

	@Override protected void defineTransitionLinks( )
	{

	}

	public static class ByAttributes extends AbstractQuery<StudyTrip>
	{
		protected final String name;

		protected final LocalDate intervalStart;

		protected final LocalDate intervalEnd;

		protected final String supervisorNames;

		protected final String city;

		protected final String country;

		protected final boolean isNational;

		public ByAttributes(
			final String name,
			final LocalDate intervalStart,
			final LocalDate intervalEnd,
			final String supervisorNames,
			final String city,
			final String country,
			final boolean isNational )
		{
			this.name = name;
			this.intervalStart = intervalStart;
			this.intervalEnd = intervalEnd;
			this.supervisorNames = supervisorNames;
			this.city = city;
			this.country = country;
			this.isNational = isNational;
		}

		@Override protected CollectionModelResult<StudyTrip> doExecuteQuery( ) throws DatabaseException
		{
			final Collection<StudyTrip> studyTripsFromDb =
				DaoFactory.getInstance( ).getStudyTripDao( ).readByPredicate( byAttributes( ) ).getResult( );

			final List<StudyTrip> sortedStudyTrips = new LinkedList<>( studyTripsFromDb );
			sortedStudyTrips.sort( StudyTrip.getComparator( ) );

			return new CollectionModelResult<>( sortedStudyTrips );
		}

		protected Predicate<StudyTrip> byAttributes( )
		{
			return this::matchName;
		}

		private boolean matchName( final StudyTrip studyTrip )
		{
			return StringUtils.isEmpty( this.name ) ||
				StringUtils.containsIgnoreCase( studyTrip.getName( ), this.name );
		}
	}

	public static class Builder extends AbstractGetCollectionStateBuilder<StudyTrip>
	{
		@Override public AbstractState build( )
		{
			return new GetCollectionStudyTripsState( this );
		}
	}
}