package online.food.donation.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import online.food.donation.bean.UserBean;
import online.food.donation.model.DonateModel;
import online.food.donation.model.RequestFoodModel;
import online.food.donation.utility.DataUtility;
import online.food.donation.utility.ServletUtility;

@WebServlet(name = "DonateListCtl", urlPatterns = { "/DonationListCtl" })
public class DonateListCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
       
    public DonateListCtl() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("in get");
		DonateModel model = new DonateModel();
		UserBean bean = null;
		long id = DataUtility.getLong(request.getParameter("id"));

		if (id > 0) {
			model.delete(id);
			ServletUtility.setSuccessMessage("Data Deleted Successfully", request);
		}
		List list = null;
        HttpSession session = request.getSession(false);
    	UserBean bean2 = (UserBean) session.getAttribute("user");
    	long roleid = bean2.getRoleId();
    	if (roleid==2) {
    		   try {
    			     list =	model.DonateFoodList(bean2.getId());
    			     if (list == null || list.size() == 0) {
    						ServletUtility.setErrorMessage("No record found ", request);
    					}
    			     ServletUtility.setList(list, request);
    			} catch (Exception e) {
    				e.printStackTrace();
    			}
		}else{
			 try {
			     list =	model.DonateFoodlist();
			     if (list == null || list.size() == 0) {
						ServletUtility.setErrorMessage("No record found ", request);
					}
			     ServletUtility.setList(list, request);
			} catch (Exception e) {
				e.printStackTrace();
		}
		}
		ServletUtility.forward(getView(), request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	@Override
	protected String getView() {
		return OFDView.DONATION_LIST_VIEW;
	}

}
