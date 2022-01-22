package de.fhws.fiw.fds.suttonsolution.api.hypermedia.uris;

import de.fhws.fiw.fds.suttonsolution.Start;

public interface IUserUri
{
	String PATH_ELEMENT = "users";
	String REL_PATH = Start.CONTEXT_PATH + "/api/" + PATH_ELEMENT;
	String REL_PATH_ID = REL_PATH + "/{id}";
}