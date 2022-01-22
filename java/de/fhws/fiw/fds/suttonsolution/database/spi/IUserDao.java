package de.fhws.fiw.fds.suttonsolution.database.spi;

import de.fhws.fiw.fds.sutton.server.api.security.User;
import de.fhws.fiw.fds.sutton.server.database.IDatabaseAccessObject;
import de.fhws.fiw.fds.suttonsolution.database.IInMemoryStorage;

public interface IUserDao extends IDatabaseAccessObject<User>, IInMemoryStorage
{

}