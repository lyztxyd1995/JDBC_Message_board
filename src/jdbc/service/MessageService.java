package jdbc.service;

import java.util.Date;
import java.util.List;

import jdbc.bean.Message;
import jdbc.bean.User;
import jdbc.dao.MessageDao;

public class MessageService {
	private MessageDao messageDao;
	public MessageService(){
		messageDao = new MessageDao();
	}
	public List<Message> getMessages(int page, int pagesize) {
		return messageDao.getMessages(page, pagesize);
	}
	
	public int countMessages() {
		return messageDao.countMessages();
	}
	
	public boolean addMessage(Message message) {
		message.setCreatetime(new Date());
		Long id = message.getUserid();
		return messageDao.save(message);
	}
	
	public List<Message> getMyMessages(User user, int page, int pagesize){
		return messageDao.getMyMessages(user, page, pagesize);
	}
	
	public boolean deleteMessage(Long id) {
		return messageDao.deleteMessage(id);
	}
	
	public Message queryMessage(Long id) {
		return messageDao.queryMessage(id);
	}
	
	public boolean updateMessage(Long id, String title, String content){
		return messageDao.updateMessage(id, title, content);
	}
}
