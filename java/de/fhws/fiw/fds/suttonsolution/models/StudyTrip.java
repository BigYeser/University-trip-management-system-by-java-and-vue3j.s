package de.fhws.fiw.fds.suttonsolution.models;

import com.owlike.genson.annotation.JsonConverter;
import de.fhws.fiw.fds.sutton.server.api.converter.JsonServerLinkConverter;
import de.fhws.fiw.fds.sutton.server.models.AbstractModel;
import de.fhws.fiw.fds.sutton.utils.JsonDateTimeConverter;
import org.glassfish.jersey.linking.InjectLink;

import javax.ws.rs.core.Link;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Comparator;

public class StudyTrip extends AbstractModel implements Serializable, Cloneable
{
	private String name;

	private LocalDate startDate;

	private LocalDate endDate;

	private String companyName;

	private String city;

	private String country;

	private boolean isNational;

	@InjectLink(
		style = InjectLink.Style.ABSOLUTE,
		value = "studytrips/${instance.id}/students",
		rel = "getStudentsOfStudyTrip",
		title = "students",
		type = "application/json"
	)
	private Link students;

	@InjectLink(
		style = InjectLink.Style.ABSOLUTE,
		value = "/studytrips/${instance.id}",
		rel = "self",
		title = "self",
		type = "application/json" )
	private Link selfLink;

	public StudyTrip( )
	{

	}

	public StudyTrip(
		final String name,
		final LocalDate startDate,
		final LocalDate endDate,
		final String companyName,
		final String city,
		final String country,
		final boolean isNational )
	{
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
		this.companyName = companyName;
		this.city = city;
		this.country = country;
		this.isNational = isNational;
	}

	public static Comparator<StudyTrip> getComparator( )
	{
		return Comparator.comparing( StudyTrip::getStartDate );
	}

	public String getName( )
	{
		return name;
	}

	public void setName( final String name )
	{
		this.name = name;
	}

	@JsonConverter( JsonDateTimeConverter.class )
	public LocalDate getStartDate( )
	{
		return startDate;
	}

	@JsonConverter( JsonDateTimeConverter.class )
	public void setStartDate( final LocalDate startDate )
	{
		this.startDate = startDate;
	}

	@JsonConverter( JsonDateTimeConverter.class )
	public LocalDate getEndDate( )
	{
		return endDate;
	}

	@JsonConverter( JsonDateTimeConverter.class )
	public void setEndDate( final LocalDate endDate )
	{
		this.endDate = endDate;
	}

	public String getCompanyName( )
	{
		return companyName;
	}

	public void setCompanyName( final String companyName )
	{
		this.companyName = companyName;
	}

	public String getCity( )
	{
		return city;
	}

	public void setCity( final String city )
	{
		this.city = city;
	}

	public String getCountry( )
	{
		return country;
	}

	public void setCountry( final String country )
	{
		this.country = country;
	}

	public boolean isNational( )
	{
		return isNational;
	}

	public void setNational( final boolean national )
	{
		isNational = national;
	}

	@JsonConverter( JsonServerLinkConverter.class )
	public Link getStudents( )
	{
		return students;
	}

	@JsonConverter( JsonServerLinkConverter.class )
	public void setStudents( final Link students )
	{
		this.students = students;
	}

	@JsonConverter( JsonServerLinkConverter.class )
	public Link getSelfLink( )
	{
		return selfLink;
	}

	public void setSelfLink( final Link selfLink )
	{
		this.selfLink = selfLink;
	}

	@Override public String toString( )
	{
		return "StudyTrip {" +
			"id:" + this.id +
			", name: '" + this.name + '\'' +
			", firstDate:" + this.startDate +
			", lastDate: " + this.endDate +
			", companyName: '" + this.companyName + '\'' +
			", city: '" + this.city + '\'' +
			", country: '" + this.country + '\'' +
			", isNational: '" + this.isNational + '\'' +
			'}';
	}

	@Override public Object clone( ) throws CloneNotSupportedException
	{
		return super.clone( );
	}
}