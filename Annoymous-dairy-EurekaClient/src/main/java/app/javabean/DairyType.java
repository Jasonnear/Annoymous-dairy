package app.javabean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "dairytype")
public class DairyType implements Serializable{

	@Id
	private int id;
	
	/**
	 * 0:daily
	 * 1:voice
	 * 2:future_daily
	 * 3:future_voice
	 */
	@Column(length = 20,nullable = false)
	private String dairy_type;
	
	/**
	 * DairyType : Daily = 1:n
	 */
	@OneToMany(cascade = CascadeType.ALL , mappedBy = "dairytype_id")
	private List<Daily> daily_list = new ArrayList<Daily>();
	
	/**
	 * DairyType : Voice = 1:n
	 */
	@OneToMany(cascade = CascadeType.ALL , mappedBy = "dairytype_id")
	private List<Voice> voice_list = new ArrayList<Voice>();
	
	/**
	 * DairyType : Future_daily = 1:n
	 */
	@OneToMany(cascade = CascadeType.ALL , mappedBy = "dairytype_id")
	private List<Future_daily> future_daily_list = new ArrayList<Future_daily>();
	
	/**
	 * DairyType : Future_voice = 1:n
	 */
	@OneToMany(cascade = CascadeType.ALL , mappedBy = "dairytype_id")
	private List<Future_voice> future_voice_list = new ArrayList<Future_voice>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDairy_type() {
		return dairy_type;
	}

	public void setDairy_type(String dairy_type) {
		this.dairy_type = dairy_type;
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

	public List<Future_daily> getFuture_daily_list() {
		return future_daily_list;
	}

	public void setFuture_daily_list(List<Future_daily> future_daily_list) {
		this.future_daily_list = future_daily_list;
	}

	public List<Future_voice> getFuture_voice_list() {
		return future_voice_list;
	}

	public void setFuture_voice_list(List<Future_voice> future_voice_list) {
		this.future_voice_list = future_voice_list;
	}
}
