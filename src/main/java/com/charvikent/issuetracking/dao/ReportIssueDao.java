package com.charvikent.issuetracking.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.charvikent.issuetracking.model.KpStatus;
import com.charvikent.issuetracking.model.ReportIssue;
import com.charvikent.issuetracking.model.User;

@Repository
public class ReportIssueDao {

	@PersistenceContext
	private EntityManager em;
	@Autowired
	HttpSession session;

	public void saveReportIssue(ReportIssue reportIssue) {
		User objuserBean = (User) session.getAttribute("cacheUserBean");

		reportIssue.setAssignby(String.valueOf(objuserBean.getId()));
		reportIssue.setKstatus("2");
		em.persist(reportIssue);

	}

	/* @SuppressWarnings("unchecked")
public List<ReportIssue> getAllReportIssues()
 {
	 return (List<ReportIssue>) em.createQuery("select reportIssue from ReportIssue reportIssue").getResultList();
 }
	 */



	public List<ReportIssue> getIssuesAssignBy(String id) {
		List<ReportIssue> listissue=new ArrayList<>();

		try {
			@SuppressWarnings("unchecked")
			List <Object[]> rows=em.createQuery("select r.id , u.username, s.colour, p.priority,r.uploadfile,r.subject ,c.category,r.createdTime from ReportIssue r, Category c, Priority p, User u, Severity s  where r.assignto=u.id and p.id=r.priority and s.id=r.severity and c.id=r.category and r.kstatus<>'1'  and  r.assignby =:custName").setParameter("custName", id).getResultList();
			for(Object[] row: rows)
			{
				ReportIssue issue =new ReportIssue();
				int j = Integer.parseInt(String.valueOf(row[0]));
				Integer intobj=new Integer(j);
				issue.setId(intobj);
				issue.setAssignto((String) row[1]);
				issue.setSeverity((String) row[2]);
				issue.setPriority((String) row[3]);
				issue.setUploadfile((String) row[4]);
				issue.setSubject((String) row[5]);
				issue.setCategory((String) row[6]);
				issue.setCreatedTime((Date) row[7]);
				listissue.add(issue);

			}
		} catch (Exception e) {
			System.out.println("error here");
			e.printStackTrace();
		}

		return  listissue;

	}

	public Object getIssuesAssignTo(String id) {
		List<ReportIssue> listissue=new ArrayList<>();

		try {
			@SuppressWarnings("unchecked")
			List <Object[]> rows=em.createQuery("select r.id , u.username, s.colour, p.priority,r.uploadfile,r.subject ,r.createdTime,c.category  from ReportIssue r, Category c, Priority p, User u, Severity s   where r.assignto=u.id and p.id=r.priority and s.id=r.severity and c.id=r.category  and r.kstatus='2' and r.assignto =:custName").setParameter("custName", id).getResultList();
			for(Object[] row: rows)
			{
				System.out.println(row);
				ReportIssue issue =new ReportIssue();
				int j = Integer.parseInt(String.valueOf(row[0]));
				Integer intobj=new Integer(j);
				issue.setId(intobj);
				issue.setAssignby((String) row[1]);
				issue.setSeverity((String) row[2]);
				issue.setPriority((String) row[3]);
				issue.setUploadfile((String) row[4]);
				issue.setSubject((String) row[5]);
				issue.setCreatedTime((Date) row[6]);
				issue.setCategory((String) row[7]);


				listissue.add(issue);

			}
		} catch (Exception e) {
			System.out.println("error here");
			e.printStackTrace();
		}

		return  listissue;

	}


	public Object getIssuesAssignToResolved(String id) {
		List<ReportIssue> listissue=new ArrayList<>();

		try {
			@SuppressWarnings("unchecked")
			List <Object[]> rows=em.createQuery("select r.id , u.username, s.colour, p.priority,r.uploadfile,r.subject ,r.createdTime,c.category  from ReportIssue r, Category c, Priority p, User u, Severity s   where r.assignby=u.id and p.id=r.priority and s.id=r.severity and c.id=r.category  and r.kstatus='4' and  r.assignto =:custName").setParameter("custName", id).getResultList();
			for(Object[] row: rows)
			{
				ReportIssue issue =new ReportIssue();
				int j = Integer.parseInt(String.valueOf(row[0]));
				Integer intobj=new Integer(j);
				issue.setId(intobj);
				issue.setAssignby((String) row[1]);
				issue.setSeverity((String) row[2]);
				issue.setPriority((String) row[3]);
				issue.setUploadfile((String) row[4]);
				issue.setSubject((String) row[5]);
				issue.setCreatedTime((Date) row[6]);
				issue.setCategory((String) row[7]);


				listissue.add(issue);

			}
		} catch (Exception e) {
			System.out.println("error here");
			e.printStackTrace();
		}

		return  listissue;

	}








	public List<ReportIssue> getAllReportIssues() {
		List<ReportIssue> listissue=new ArrayList<>();

		try {
			@SuppressWarnings("unchecked")
			List<Object[]> rows = em.createQuery("select r.id, c.category ,s.severity,p.priority,"
					+ "u.username ,r.subject ,r.uploadfile,"
					+ "r.createdTime, r.updatedTime from ReportIssue r, Category c ,Severity s,Priority p,User u  where r.category=c.id and r.severity=s.id and r.priority=p.id and r.assignto=u.id")
			.getResultList();
			for (Object[] row : rows) {
				ReportIssue issue = new ReportIssue();

				issue.setId(Integer.parseInt(String.valueOf(row[0])));

				issue.setCategory((String) row[1]);
				issue.setSeverity((String) row[2]);
				issue.setPriority((String) row[3]);
				issue.setAssignto((String) row[4]);
				issue.setSubject((String) row[5]);
				issue.setUploadfile((String) row[6]);
				issue.setCreatedTime((Date) row[7]);
				issue.setUpdatedTime((Date) row[8]);
				listissue.add(issue);

			}
		} catch (Exception e) {
			System.out.println("error here");
			e.printStackTrace();
		}

		return  listissue;

	}


	@SuppressWarnings("unchecked")
	public Map<Integer, Integer>  getGapAndCount() {

		List<ReportIssue> listissuegap=new ArrayList<>();
		ReportIssue issuegap =null;

		List<Object[]> rows = 	em.createNativeQuery("SELECT DATEDIFF(CURDATE(),created_time ) as gap ,count(id)  from report_issue group by gap ").getResultList();

		Map<Integer, Integer> issueTimelines = new HashMap<Integer, Integer>();

		for (Object[] row : rows) {
			issuegap = new ReportIssue();
			//System.out.print(Integer.parseInt(String.valueOf(row[0])));
			//System.out.print(Integer.parseInt(String.valueOf(row[1])));

			issuegap.setGapdays(Integer.parseInt(String.valueOf(row[0])));
			issuegap.setGapcount(Integer.parseInt(String.valueOf(row[1])));
			listissuegap.add(issuegap);

			issueTimelines.put(Integer.parseInt(String.valueOf(row[0])), Integer.parseInt(String.valueOf(row[1])));
		}


		return issueTimelines;

	}

	@SuppressWarnings("unchecked")
	public Map<Integer, Integer>  getGapAndCountForClosed() {

		List<ReportIssue> listissuegap=new ArrayList<>();
		ReportIssue issuegap =null;

		//String custName=null;

		List<Object[]> rows = 	em.createNativeQuery(" SELECT DATEDIFF(CURDATE(),created_time ) as gap ,count(id)  from report_issue where kstatus =:custName  group by gap  ").setParameter("custName", "1").getResultList();

		Map<Integer, Integer> issueTimelines = new HashMap<Integer, Integer>();

		for (Object[] row : rows) {
			issuegap = new ReportIssue();
			System.out.print(Integer.parseInt(String.valueOf(row[0])));
			System.out.print(Integer.parseInt(String.valueOf(row[1])));

			issuegap.setGapdays(Integer.parseInt(String.valueOf(row[0])));
			issuegap.setGapcount(Integer.parseInt(String.valueOf(row[1])));
			listissuegap.add(issuegap);

			issueTimelines.put(Integer.parseInt(String.valueOf(row[0])), Integer.parseInt(String.valueOf(row[1])));
		}


		return issueTimelines;

	}






	@SuppressWarnings({ "unchecked", "rawtypes" })
	public  List<ReportIssue> getRecentlyModified(String id) {

		List<ReportIssue> listissue=new ArrayList<>();

		try {
			@SuppressWarnings("unchecked")
			List<Object[]> rows = em
			.createNativeQuery(" select   r.id , u.username, s.colour, p.priority,r.uploadfile,r.subject ,c.category,r.created_time from report_issue r, category c, priority p, kpusers u, severity s  where r.assignto=u.id and p.id=r.priority and s.id=r.severity and c.id=r.category and r.kstatus='1'  and DATEDIFF (CURDATE(),r.updated_time )<=30 and  r.assignby =:custName union (select   r.id , u.username, s.colour, p.priority,r.uploadfile,r.subject ,c.category,r.created_time from report_issue r, category c, priority p, kpusers u, severity s  where r.assignto=u.id and p.id=r.priority and s.id=r.severity and c.id=r.category and r.kstatus='1'  and DATEDIFF (CURDATE(),r.updated_time )<=30 and  r.assignto =:custName )").setParameter("custName", id)
			.getResultList();
			for (Object[] row : rows) {
				System.out.println(row);
				ReportIssue issue = new ReportIssue();

				issue.setId(Integer.parseInt(String.valueOf(row[0])));
				issue.setAssignto((String) row[1]);
				issue.setSeverity((String) row[2]);
				issue.setPriority((String) row[3]);
				issue.setUploadfile((String) row[4]);
				issue.setSubject((String) row[5]);

				issue.setCategory((String) row[6]);
				issue.setCreatedTime((Date) row[7]);
				listissue.add(issue);

			}
		} catch (Exception e) {
			System.out.println("error here");
			e.printStackTrace();
		}

		return  listissue;


	}

	public ReportIssue getReportIssueById(Integer id) {

		return em.find(ReportIssue.class, id);
	}

	public void updateIssue(ReportIssue issue) {
		
     System.out.println(issue);
     System.out.println("enter to edit issue dao block");
     
     ReportIssue editissue=getReportIssueById(issue.getId());
     
     editissue.setAssignto(issue.getAssignto());
     //editissue.setAssignby(issue.getAssignby());
     editissue.setCategory(issue.getCategory());
     editissue.setDescription(issue.getDescription());
     editissue.setPriority(issue.getPriority());
     editissue.setSeverity(issue.getSeverity());
     editissue.setSubject(issue.getSubject());
     editissue.setKstatus(issue.getKstatus());
    // editissue.setUploadfile(issue.getUploadfile());
     
     
		em.merge(editissue);


		em.flush();


	}
	
	
	
	@SuppressWarnings("unchecked")
	public List<KpStatus> getKpStatues() {
		return em.createQuery("SELECT kpstatus FROM KpStatus kpstatus").getResultList();
	}
	
	
	public  Map<String,Integer> getCountByStatusWise() {
		
		Map<String,Integer> statusCounts =new LinkedHashMap<String,Integer>();
		
		Integer opentotal=0;
		
		try {
			@SuppressWarnings("unchecked")
			List<Object[]> rows = em
			.createNativeQuery(" select ks.name,count(*)as count from report_issue r,kpstatus ks  where  r.kstatus=ks.id group by kstatus")
			.getResultList();
			for (Object[] row : rows) {
				opentotal=opentotal+Integer.parseInt(String.valueOf(row[1]));
				statusCounts.put((String)row[0], Integer.parseInt(String.valueOf(row[1])));
				
			}
			
			statusCounts.put("Open",opentotal);
		} catch (Exception e) {
			System.out.println("error here");
			e.printStackTrace();
		}
		return statusCounts;


	}

	
	


}







