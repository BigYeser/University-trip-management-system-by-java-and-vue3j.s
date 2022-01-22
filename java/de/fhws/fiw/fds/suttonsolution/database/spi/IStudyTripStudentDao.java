package de.fhws.fiw.fds.suttonsolution.database.spi;

import de.fhws.fiw.fds.sutton.server.database.IDatabaseRelationAccessObject;
import de.fhws.fiw.fds.suttonsolution.database.IInMemoryStorage;
import de.fhws.fiw.fds.suttonsolution.models.Student;

public interface IStudyTripStudentDao extends IDatabaseRelationAccessObject<Student>, IInMemoryStorage
{

}