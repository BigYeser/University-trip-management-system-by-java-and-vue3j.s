package de.fhws.fiw.fds.suttonsolution.database.impl;

import de.fhws.fiw.fds.sutton.server.api.security.User;
import de.fhws.fiw.fds.sutton.server.database.inmemory.AbstractInMemoryStorage;
import de.fhws.fiw.fds.sutton.server.database.results.NoContentResult;
import de.fhws.fiw.fds.suttonsolution.database.DaoFactory;
import de.fhws.fiw.fds.suttonsolution.database.spi.IUserDao;

public class UserDaoInMemoryStorage
	extends AbstractInMemoryStorage<User>
	implements IUserDao
{
	@Override public void clearStorage( )
	{
		this.storage.clear( );
	}

	@Override public NoContentResult create( final User model )
	{
		return super.create( model );
	}
}