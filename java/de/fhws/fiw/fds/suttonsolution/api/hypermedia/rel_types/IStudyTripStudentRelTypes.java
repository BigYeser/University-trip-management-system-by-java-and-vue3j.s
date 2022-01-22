package de.fhws.fiw.fds.suttonsolution.api.hypermedia.rel_types;

public interface IStudyTripStudentRelTypes
{
	String SELF = "self";
	String GET_ALL_LINKED_STUDENTS = "getAllLinkedStudents";
	String GET_ALL_STUDENTS = "getAllLinkableStudents";
	String CREATE_LINK_FROM_STUDY_TRIP_TO_STUDENT = "linkStudentToStudyTrip";
	String DELETE_LINK_FROM_STUDY_TRIP_TO_STUDENT = "unlinkStudentFromStudyTrip";
	String GET_SINGLE_STUDENT = "getOneStudentOfStudyTrip";
}