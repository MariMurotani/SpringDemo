package demo.service;

import java.util.Date;
import java.util.List;

import javax.swing.SortOrder;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import demo.model.CMS;
import demo.model.CMSData;
import demo.model.PathList;
import demo.model.User;

@Service
public class CMSService extends ServiceBase{
	
	public CMSService() {
		super();
	}
	
	/**
	 * 新規にパスを追加
	 * */
	public String AddNewRecoad(String path, String name) {
		CMS cms = new CMS(path.trim(),name.trim());
		this.mongoOperation.save(cms);
		return cms.getId();
	}
	
	/**
	 * 重複しない全てのパスを取得
	 * */
	public List<PathList> GetAllPaths(){
		/*
		> db.CMS.aggregate(
		...   { $group   : { "_id"  : { "path" : "$path",
		...                             "name" : "$name" },
		...                  "count" : { "$sum" : 1 } } }
		... );
		{ "_id" : { "path" : "/images/201506/", "name" : "" }, "count" : 1 }
		{ "_id" : { "path" : "/images/201506/", "name" : "test.png" }, "count" : 2 }
		{ "_id" : { "path" : "/images/201507/", "name" : "test.png" }, "count" : 1 }
		{ "_id" : { "path" : "/images/201507/", "name" : "" }, "count" : 1 }
		 * */
		
		AggregationOperation match = Aggregation.match(Criteria.where("active").is(true));
	    AggregationOperation group = Aggregation.group("path","name");
	    AggregationOperation sort = Aggregation.sort(Sort.Direction.ASC,"path","name");
	    Aggregation aggregation = Aggregation.newAggregation(match, group,sort);
	    
	    //{ "aggregate" : "__collection__" , "pipeline" : [ { "$match" : { "active" : true}} , { "$group" : { "_id" : { "path" : "$path" , "name" : "$name"}}} , { "$sort" : { "_id.path" : 1 , "_id.name" : 1}}]}
	    System.out.println(aggregation.toString());
	    AggregationResults<PathList> groupResults = this.mongoOperation.aggregate(aggregation, CMS.class,PathList.class);
	    List pathRes = groupResults.getMappedResults();
	    return pathRes;
	}
	
	/**
	 * パスから全てのレコードを検索
	 * */
	public List<CMS> GetAllByPath(String path,String name){
		Criteria criteria = new Criteria().andOperator(
				Criteria.where("active").is(true),
                Criteria.where("path").is(path),
                Criteria.where("name").is(name));
        
		AggregationOperation match = Aggregation.match(criteria);
	    Aggregation aggregation = Aggregation.newAggregation(match);
	    System.out.println(aggregation.toString());
	    AggregationResults<CMS> groupResults = this.mongoOperation.aggregate(aggregation, CMS.class,CMS.class);
	    List pathRes = groupResults.getMappedResults();
	    return pathRes;
	}
	
	/**
	 * パス情報を更新する
	 * データがなければ作成あれば上書き
	 * */
	public void UpdateRecoad(String key,String ext,String mime,String data,Byte[] bdata){
		Query query = new Query(Criteria.where("_id").is(key));
		
		//	update CMS table
		if(0 < this.mongoOperation.count(query, CMS.class)){
			Update update = new Update();
			update.set("ext",ext);;
			update.set("content_type",mime);
			this.mongoOperation.updateFirst(query, update, CMS.class);
		}
		
		//	update CMS data
		if(0 < this.mongoOperation.count(query, CMSData.class)){
			Update update = new Update();
			if(data != null)
				update.set("txtdata",data);
			if(bdata != null)
				update.set("bindata",bdata);
			this.mongoOperation.updateFirst(query, update, CMSData.class);
		}else{
			CMSData cmsd = data.equals(null)?new CMSData(key,bdata):new CMSData(key, bdata);
			this.mongoOperation.save(cmsd);
		}
	}
}
