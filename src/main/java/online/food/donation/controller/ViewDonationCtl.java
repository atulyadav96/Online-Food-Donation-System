package online.food.donation.controller;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import online.food.donation.bean.UserBean;
import online.food.donation.model.NGOModel;
import online.food.donation.model.RequestFoodModel;
import online.food.donation.utility.DataUtility;
import online.food.donation.utility.ServletUtility;

@WebServlet(name = "ViewDonationCtl", urlPatterns = { "/ViewDonationCtl" })
public class ViewDonationCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
       
    public ViewDonationCtl() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("in get");
		RequestFoodModel model = new RequestFoodModel();
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
    	NGOModel  ngmodel = new NGOModel();
    	UserBean bean1 = (UserBean) session.getAttribute("user");
    	try {
			 list = ngmodel.NGONamelist(bean2.getId());
			Iterator it1 = list.iterator();
			while (it1.hasNext()) {
			bean1 = (UserBean) it1.next();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		String ngoname = bean1.getNgoname();
		System.out.println("NGONAME :"+ngoname);
    	if (roleid==3) {
    		   try {
    			     list =	model.DonationList(ngoname);
    			     if (list == null || list.size() == 0) {
    						ServletUtility.setErrorMessage("No record found ", request);
    					}
    			     ServletUtility.setList(list, request);
    			} catch (Exception e) {
    				e.printStackTrace();
    			}
		}else{
			 try {
			     list =	model.Donationlist();
			     if (list == null || list.size() == 0) {
						ServletUtility.setErrorMessage("No record found ", request);
					}
			     ServletUtility.setList(list, request);
			} catch (Exception e) {
				e.printStackTrace();
		}
		}
		ServletUtility.setList(list, request);
		ServletUtility.forward(getView(), request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	@Override
	protected String getView() {
		return OFDView.VIEW_DONATION_VIEW;
	}

}
