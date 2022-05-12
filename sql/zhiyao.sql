CREATE DATABASE zhiyao;

-- 连接到zhiyao

CREATE TABLE message_record (
	id BIGSERIAL PRIMARY KEY,
	connect_id VARCHAR(50) DEFAULT NULL,
	create_time INTEGER DEFAULT 0,
	msg_content TEXT DEFAULT NULL,
	deleted BOOLEAN DEFAULT FALSE
);
CREATE INDEX connect_id_index ON message_record(connect_id);
