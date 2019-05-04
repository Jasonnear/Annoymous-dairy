drop procedure if exists init;$$$
create procedure init() 
begin
	declare topic_flag int;
	declare dairyType_flag int;
	
	select count(*) into topic_flag from topic;
	select count(*) into dairyType_flag from dairytype;
	
	if(topic_flag = 0)
		then
			INSERT INTO topic (id,topic_type) VALUES (0,"所有");
			INSERT INTO topic (id,topic_type) VALUES (1,"感情");
			INSERT INTO topic (id,topic_type) VALUES (2,"心情");
			INSERT INTO topic (id,topic_type) VALUES (3,"娱乐");
			INSERT INTO topic (id,topic_type) VALUES (4,"政治");
	end if;
	
	if(dairyType_flag = 0)
		then
			INSERT INTO dairytype (id,dairy_type) VALUES (0,"daily");
			INSERT INTO dairytype (id,dairy_type) VALUES (1,"voice");
			INSERT INTO dairytype (id,dairy_type) VALUES (2,"future_daily");
			INSERT INTO dairytype (id,dairy_type) VALUES (3,"future_voice");
	end if;
	
	
end;$$$

call init();$$$
drop procedure if exists init;$$$