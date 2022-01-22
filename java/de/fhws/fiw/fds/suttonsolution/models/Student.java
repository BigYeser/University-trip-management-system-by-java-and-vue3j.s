package de.fhws.fiw.fds.suttonsolution.models;

import com.owlike.genson.annotation.JsonConverter;
import de.fhws.fiw.fds.sutton.server.api.converter.JsonServerLinkConverter;
import de.fhws.fiw.fds.sutton.server.api.converter.XmlServerLinkConverter;
import de.fhws.fiw.fds.sutton.server.models.AbstractModel;
import org.glassfish.jersey.linking.InjectLink;

import javax.ws.rs.core.Link;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Comparator;

public class Student extends AbstractModel
{
	protected String firstName;

	protected String lastName;

	protected String email;

	private String courseOfStudy;

	private int semesterOfStudy;

	private int matriculationNumber;

	@InjectLink(
			style = InjectLink.Style.ABSOLUTE,
			value = "/studytrips/${instance.primaryId}/students/${instance.id}",
			rel = "self",
			title = "self",
			type = "application/json",
			condition = "${instance.primaryId != 0}"
	)

	@XmlJavaTypeAdapter( XmlServerLinkConverter.class )
	private Link selfLinkOnSecond;

	@InjectLink(
			style = InjectLink.Style.ABSOLUTE,
			value = "/students/${instance.id}",
			rel = "self",
			title = "self",
			type = "application/json",
			condition = "${instance.primaryId == 0}"
	)
	private Link selfLinkPrimary;


	public Student( )
	{

	}

	public Student(
		final String firstName,
		final String lastName,
		final String email,
		final String courseOfStudy,
		final int semesterOfStudy,
		final int matriculationNumber )
	{
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.courseOfStudy = courseOfStudy;
		this.semesterOfStudy = semesterOfStudy;
		this.matriculationNumber = matriculationNumber;
	}

	public static Comparator<Student> getComparator( )
	{
		return Comparator.comparing( Student::getLastName ).thenComparing( Student::getFirstName );
	}

	public String getFirstName( )
	{
		return firstName;
	}

	public void setFirstName( final String firstName )
	{
		this.firstName = firstName;
	}

	public String getLastName( )
	{
		return lastName;
	}

	public void setLastName( final String lastName )
	{
		this.lastName = lastName;
	}

	public String getEmail( )
	{
		return email;
	}

	public void setEmail( final String email )
	{
		this.email = email;
	}

	public String getCourseOfStudy( )
	{
		return courseOfStudy;
	}

	public void setCourseOfStudy( final String courseOfStudy )
	{
		this.courseOfStudy = courseOfStudy;
	}

	public int getSemesterOfStudy( )
	{
		return semesterOfStudy;
	}

	public void setSemesterOfStudy( final int semesterOfStudy )
	{
		this.semesterOfStudy = semesterOfStudy;
	}

	public int getMatriculationNumber( )
	{
		return matriculationNumber;
	}

	public void setMatriculationNumber( final int matriculationNumber )
	{
		this.matriculationNumber = matriculationNumber;
	}

	@JsonConverter( JsonServerLinkConverter.class )
	public Link getSelfLinkOnSecond() {
		return selfLinkOnSecond;
	}

	public void setSelfLinkOnSecond(Link selfLinkOnSecond) {
		this.selfLinkOnSecond = selfLinkOnSecond;
	}

	@JsonConverter( JsonServerLinkConverter.class )
	public Link getSelfLinkPrimary() {
		return selfLinkPrimary;
	}

	public void setSelfLinkPrimary(Link selfLinkPrimary) {
		this.selfLinkPrimary = selfLinkPrimary;
	}
}