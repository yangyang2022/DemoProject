package com.yangyang.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "t_attach")
public class Attachment {
    private Integer id;
    private String oldName;
    private String newName;
    private String contentType;
    private Long size;
    private LocalDate createDate;

    private Message message;
    private Document document;
    private String type;

    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    //附件原有的名称
    @Column(name = "old_name")
    public String getOldName() {
        return oldName;
    }

    public void setOldName(String oldName) {
        this.oldName = oldName;
    }

    //附件的新名称 这个名称不会重复 可以使用uuid和时间来生成
    @Column(name = "new_name")
    public String getNewName() {
        return newName;
    }

    public void setNewName(String newName) {
        this.newName = newName;
    }

    //附件的文件类型
    @Column(name = "content_type")
    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    // 附件的大小,以字节为单位
    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    //附件创建时间
    @Column(name = "create_date")
    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    //附件属于哪一个消息
    @ManyToOne
    @JoinColumn(name = "msg_id")
    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    @ManyToOne
    @JoinColumn(name = "doc_id")
    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    //附件的后缀名
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
