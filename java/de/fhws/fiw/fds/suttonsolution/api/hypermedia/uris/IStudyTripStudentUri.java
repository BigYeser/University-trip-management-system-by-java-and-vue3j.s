package de.fhws.fiw.fds.suttonsolution.api.hypermedia.uris;

import de.fhws.fiw.fds.suttonsolution.Start;

public interface IStudyTripStudentUri
{
	String SHOW_ALL_PARAMETER = "showAll";

	String PATH_ELEMENT = "studytrips/{id}/students";
	String REL_PATH = Start.CONTEXT_PATH + "/api/" + PATH_ELEMENT;
	String REL_PATH_ID = REL_PATH + "/{id}";
	String REL_PATH_SHOW_ALL =
		Start.CONTEXT_PATH + "/api/" + PATH_ELEMENT + "?" + SHOW_ALL_PARAMETER + "=true";
	String REL_PATH_SHOW_ONLY_LINKED =
		Start.CONTEXT_PATH + "/api/" + PATH_ELEMENT + "?" + SHOW_ALL_PARAMETER + "=false";
}