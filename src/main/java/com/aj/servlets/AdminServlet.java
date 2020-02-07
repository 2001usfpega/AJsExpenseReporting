package com.aj.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aj.model.Expense;
import com.aj.model.User;
import com.aj.service.ExpServiceImpl;
import com.aj.service.UserServiceImpl;

/**
 * Servlet implementation class AdminServlet
 */
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest req, HttpServletResponse res)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("In doGet AdminServlet");
		List<Expense> expenses = new ArrayList<>();
		List<User> users = new ArrayList<>();
		String content = "";

		ExpServiceImpl expServ = new ExpServiceImpl();
		UserServiceImpl usrSvc = new UserServiceImpl();
		expenses = expServ.getAllExpenses();
		users = usrSvc.getAllUsers();
		System.out.println(expenses.size());

		res.setContentType("text/html");

		PrintWriter out = res.getWriter();

		for (Expense e : expenses) {
			String fname = null;
			String lname = null;
			for (User u : users) {
				if (e.getFk_e_id().equals(u.getU_id())) {
					fname = u.getLname();
					lname = u.getUname();
				}
			}
			content += "<tr><td>" + e.getFk_e_id() + "</td><td>" + fname + "</td><td>" + lname + "</td><td>"
					+ e.getType() + "</td><td>" + e.getAmount() + "</td><td>" + e.getSubmitted() + "</td><td>"
					+ e.getResolved() + "</td><td>" + e.getState() + "</td><td>" + e.getDesc() + "</td><td>"
					+ e.getExp_id() + "</td>" + "</tr>";
		}

		out.println("<html><body>	<div class=\"container\">\r\n" + "<div class=\"form-group\">\r\n"
				+ "				<table border=\"2\" class=\"table\" id=\"reimbtable\">\r\n"
				+ "					<tr>\r\n" + "<th>User Id</th>\r\n" + "						<th>First Name</th>\r\n"
				+ "<th>Last Name</th>\r\n" + "						<th>Type</th>\r\n"
				+ "						<th>Amount</th>\r\n" + "						<th>Submitted</th>\r\n"
				+ "						<th>Resolved</th>\r\n" + "						<th>Status</th>\r\n"
				+ "						<th>Description</th>\r\n" + "						<th>Ticket Id</th>\r\n"
				+ "</tr>" + content + "</table></div></div></body></html>");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest req, HttpServletResponse res)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, res);
	}

}
