package online.food.donation.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import online.food.donation.bean.UserBean;
import online.food.donation.model.NGOModel;
import online.food.donation.utility.DataUtility;
import online.food.donation.utility.ServletUtility;

@WebServlet(name = "NgoListCtl", urlPatterns = { "/NgoListCtl" })
public class NgoListCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
       
    public NgoListCtl() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("in get");
		NGOModel model = new NGOModel();
		UserBean bean = null;
		long id = DataUtility.getLong(request.getParameter("id"));
		long id2 = DataUtility.getLong(request.getParameter("userid"));
		if (id>0) {
		if (id == id2) {
			System.out.println("ID:"+id);
			System.out.println("UserID :"+id2);
			model.delete(id);
			model.NGOdelete(id2);
			ServletUtility.setSuccessMessage("Data Deleted Successfully", request);
		}
		}
		List list = null;
		try {
			list = model.NGOlist();
			if (list == null || list.size() == 0) {
				ServletUtility.setErrorMessage("No Record Found", request);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		ServletUtility.setList(list, request);
		ServletUtility.forward(getView(), request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	@Override
	protected String getView() {
		return OFDView.NGO_LIST_VIEW;
	}

}
