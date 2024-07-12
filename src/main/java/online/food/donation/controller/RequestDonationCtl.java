package online.food.donation.controller;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import online.food.donation.bean.BaseBean;
import online.food.donation.bean.RequestFoodBean;
import online.food.donation.bean.UserBean;
import online.food.donation.exception.ApplicationException;
import online.food.donation.exception.DuplicateRecordException;
import online.food.donation.model.NGOModel;
import online.food.donation.model.RequestFoodModel;
import online.food.donation.model.UserModel;
import online.food.donation.utility.DataUtility;
import online.food.donation.utility.DataValidator;
import online.food.donation.utility.PropertyReader;
import online.food.donation.utility.ServletUtility;

@WebServlet(name = "RequestDonationCtl", urlPatterns = { "/RequestDonationCtl" })
public class RequestDonationCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
       
    public RequestDonationCtl() {
        super();
    }
    
    @Override
	protected boolean validate(HttpServletRequest request) {

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("category"))) {
			request.setAttribute("category", PropertyReader.getValue("error.require", "Category"));
			pass = false;
		} 
		if (DataValidator.isNull(request.getParameter("food"))) {
			request.setAttribute("food", PropertyReader.getValue("error.require", "Food"));
			pass = false;
		} 

		if (DataValidator.isNull(request.getParameter("quantity"))) {
			request.setAttribute("quantity", PropertyReader.getValue("error.require", "Quantity"));
			pass = false;
		} 

		if (DataValidator.isNull(request.getParameter("address"))) {
			request.setAttribute("address", PropertyReader.getValue("error.require", "Address"));
			pass = false;
		}
		return pass;
	}
    
    @Override
	protected BaseBean populateBean(HttpServletRequest request) {
		RequestFoodBean bean = new RequestFoodBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));

		bean.setNgoName(DataUtility.getString(request.getParameter("ngoname")));

		bean.setCategory(DataUtility.getString(request.getParameter("category")));

		bean.setFoodname(DataUtility.getString(request.getParameter("food")));

		bean.setQuantity(DataUtility.getLong(request.getParameter("quantity")));

		bean.setAddress(DataUtility.getString(request.getParameter("address")));
		NGOModel  model = new NGOModel();
		HttpSession session = request.getSession(false);
		UserBean bean2 = (UserBean) session.getAttribute("user");
		try {
			List list = model.NGONamelist(bean2.getId());
			Iterator it = list.iterator();
			while (it.hasNext()) {
				bean2 = (UserBean) it.next();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("NGO:"+bean2.getNgoname());
		bean.setNgoName(bean2.getNgoname());
		bean.setStatus("Request");
		populateDTO(bean, request);

		return bean;
	}



	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		NGOModel  model = new NGOModel();
		RequestFoodBean bean = new RequestFoodBean();
		HttpSession session = request.getSession(false);
		UserBean bean2 = (UserBean) session.getAttribute("user");
		try {
			List list = model.NGONamelist(bean2.getId());
			Iterator it = list.iterator();
			while (it.hasNext()) {
				bean2 = (UserBean) it.next();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		String ngoname = bean2.getNgoname();
		bean.setNgoName(ngoname);
		ServletUtility.setBean(bean, request);
		ServletUtility.forward(getView(), request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Do Post");
		RequestFoodModel model = new RequestFoodModel();
		String op = DataUtility.getString(request.getParameter("operation"));
		long id = DataUtility.getLong(request.getParameter("id"));
		RequestFoodBean bean = new RequestFoodBean();
		bean = (RequestFoodBean) populateBean(request);
		if (OP_RESET.equalsIgnoreCase(op)) {
			ServletUtility.redirect(OFDView.REQUEST_DONATION_CTL, request, response);
			return;
		}
		if (OP_SAVE.equalsIgnoreCase(op)) {
			bean = (RequestFoodBean) populateBean(request);
			try {
				long pk = model.add(bean);
				ServletUtility.setBean(bean, request);
				ServletUtility.setSuccessMessage("Send Food Request Successfully", request);
				ServletUtility.forward(getView(), request, response);
				return;
			} catch (DuplicateRecordException e) {
				ServletUtility.setBean(bean, request);
				ServletUtility.setErrorMessage(e.getMessage(), request);
				ServletUtility.forward(getView(), request, response);

			} catch (ApplicationException e) {
				e.printStackTrace();
			} catch (Exception e) {

				e.printStackTrace();
			}
			ServletUtility.forward(getView(), request, response);
		}
	}

	@Override
	protected String getView() {
		return OFDView.REQUEST_DONATION_VIEW;
	}

}
