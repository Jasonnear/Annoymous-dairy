package app.javabean;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="topic")
public class Topic {

	@Id
	private int id;
	
	/**
	 * 属于什么专题：
	 * 	0:所有
	 * 	1:感情
	 * 	2:心情
	 * 	3:娱乐
	 * 	4:政治
	 */
	@Column(length = 20,nullable = false,unique = true)
	private String topic_type;

	/**
	 * Topic : Daily = 1 : n
	 */
	@OneToMany(cascade = CascadeType.ALL , mappedBy = "topic_id")
	private List<Daily> daily_list = new ArrayList<Daily>();
	
	/**
	 * Topic : Voice = 1 : n
	 */
	@OneToMany(cascade = CascadeType.ALL , mappedBy = "topic_id")
	private List<Voice> voice_list = new ArrayList<Voice>();
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTopic_type() {
		return topic_type;
	}

	public void setTopic_type(String topic_type) {
		this.topic_type = topic_type;
	}

	public List<Daily> getDaily_list() {
		return daily_list;
	}

	public void setDaily_list(List<Daily> daily_list) {
		this.daily_list = daily_list;
	}

	public List<Voice> getVoice_list() {
		return voice_list;
	}

	public void setVoice_list(List<Voice> voice_list) {
		this.voice_list = voice_list;
	}
}
