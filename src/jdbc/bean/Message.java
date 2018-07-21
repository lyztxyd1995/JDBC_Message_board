package jdbc.bean;

import java.util.Date;

public class Message {
	private long id;
	public Message(long id, long userid, String username, String title, String content, Date createtime) {
		super();
		this.id = id;
		this.userid = userid;
		this.username = username;
		this.title = title;
		this.content = content;
		this.createtime = createtime;
	}
	
	public Message(long userid, String username, String title, String content) {
		super();
		this.userid = userid;
		this.username = username;
		this.title = title;
		this.content = content;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getUserid() {
		return userid;
	}
	public void setUserid(long userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	private long userid;
	private String username;
	private String title;
	private String content;
	private Date createtime;
	
}
