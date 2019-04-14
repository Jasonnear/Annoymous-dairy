package app.service.user;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import app.javabean.Daily;
import app.javabean.Record;
import app.javabean.Voice;
import app.repository.user.DailyRepository;
import app.repository.user.Future_daily_Repository;
import app.repository.user.Future_voice_Repository;
import app.repository.user.RecordRepository;
import app.repository.user.VoiceRepository;

@Service
public class DairyServiceDelete {

	@Resource(name = "dailyRepository")
	private DailyRepository dailyRepository;
	
	@Resource(name = "voiceRepository")
	private VoiceRepository voiceRepository;
	
	@Resource(name = "recordRepository")
	private RecordRepository recordRepository;
	
	@Resource(name = "future_daily_Repository")
	private Future_daily_Repository future_daily_Repository;
	
	@Resource(name = "future_voice_Repository")
	private Future_voice_Repository future_voice_Repository;
	
	/**
	 * 删除daily
	 * @param id daily_id
	 * @throws Exception
	 */
	public void delete_daily(String id) throws Exception{
		dailyRepository.delete(id);
		Daily daily = dailyRepository.findOne(id);
		if(daily != null){
			throw new Exception();
		}
	}

	/**
	 * 删除record_id
	 * @param id
	 * @throws Exception
	 */
	public void delete_record(String id) throws Exception{
		recordRepository.delete(id);
		Record record = recordRepository.findOne(id);
		if(record != null){
			throw new Exception();
		}	
	}
	
	/**
	 * 删除voice_id
	 * @param id
	 * @throws Exception
	 */
	public void delete_voice(String id) throws Exception{
		voiceRepository.delete(id);
		Voice voice = voiceRepository.findOne(id);
		if(voice != null){
			throw new Exception();
		}	
	}
}
