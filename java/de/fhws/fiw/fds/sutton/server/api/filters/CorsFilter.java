package de.fhws.fiw.fds.sutton.server.api.filters;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.MultivaluedMap;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class CorsFilter implements ContainerResponseFilter
{
	@Override public void filter( final ContainerRequestContext containerRequestContext,
								  final ContainerResponseContext containerResponseContext ) throws IOException
	{
		final MultivaluedMap<String, Object> headers = containerResponseContext.getHeaders( );

		headers.add( "Access-Control-Allow-Origin", "*" );
		headers.add( "Access-Control-Allow-Headers", listOf( accessControlAllowHeaders( ) ) );
		headers.add( "Access-Control-Allow-Credentials", "true" );
		headers.add( "Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD" );
		headers.add( "Access-Control-Expose-Headers", listOf( accessControlExposeHeaders( ) ) );
	}

	protected Set<String> additionalAccessControlAllowHeaders( )
	{
		return Collections.emptySet( );
	}

	protected Set<String> additionalAccessControlExposeHeaders( )
	{
		return Collections.emptySet( );
	}

	private String listOf( final Set<String> set )
	{
		return set.stream( ).collect( Collectors.joining( ", " ) );
	}

	private Set<String> accessControlAllowHeaders( )
	{
		final Set<String> returnValue = new HashSet<>( );

		returnValue.addAll( Arrays.asList( "origin", "content-type", "accept", "authorization", "link", "location" ) );
		returnValue.addAll( additionalAccessControlAllowHeaders( ) );

		return returnValue;
	}

	private Set<String> accessControlExposeHeaders( )
	{
		final Set<String> returnValue = new HashSet<>( );

		returnValue.addAll( Arrays.asList( "Link", "Location", "Content-Disposition" ) );
		returnValue.addAll( additionalAccessControlExposeHeaders( ) );

		return returnValue;
	}
}