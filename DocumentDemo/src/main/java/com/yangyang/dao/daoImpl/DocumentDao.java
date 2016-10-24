package com.yangyang.dao.daoImpl;

import com.yangyang.dao.idao.IDocumentDao;
import com.yangyang.model.Document;
import org.springframework.stereotype.Repository;

@Repository
public class DocumentDao extends BaseDao<Document> implements IDocumentDao{

}
