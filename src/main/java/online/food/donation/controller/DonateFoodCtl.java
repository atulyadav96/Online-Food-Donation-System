package online.food.donation.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import online.food.donation.bean.BaseBean;
import online.food.donation.bean.DonateBean;
import online.food.donation.bean.UserBean;
import online.food.donation.exception.ApplicationException;
import online.food.donation.exception.DuplicateRecordException;
import online.food.donation.model.DonateModel;
import online.food.donation.model.NGOModel;
import online.food.donation.utility.DataUtility;
import online.food.donation.utility.DataValidator;
import online.food.donation.utility.PropertyReader;
import online.food.donation.utility.ServletUtility;


@WebServlet(name = "DonateFoodCtl", urlPatterns = { "/DonationCtl" })
public class DonateFoodCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
       
    public DonateFoodCtl() {
        super();
    }
    
    @Override
   	protected void preload(HttpServletRequest request) {
       	NGOModel model = new NGOModel();
   			try {
   					List ngolist = model.NGOList();
   					request.setAttribute("ngoName", ngolist);
   			} catch (Exception e) {
   				e.printStackTrace();
   			}
                   	
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

   		if ("-----Select-----".equalsIgnoreCase(request.getParameter("ngoid"))) {
			request.setAttribute("ngoid", PropertyReader.getValue("error.require", "NGO Name"));
			pass = false;
		}
   		return pass;
   	}
    
    @Override
   	protected BaseBean populateBean(HttpServletRequest request) {
   		DonateBean bean = new DonateBean();

   		bean.setId(DataUtility.getLong(request.getParameter("id")));

   		bean.setNgoid(DataUtility.getLong(request.getParameter("ngoid")));

   		bean.setCategory(DataUtility.getString(request.getParameter("category")));

   		bean.setFood(DataUtility.getString(request.getParameter("food")));

   		bean.setQuamtity(DataUtility.getLong(request.getParameter("quantity")));
   		
   		HttpSession session = request.getSession(false);
		UserBean existBean = (UserBean)session.getAttribute("user");
		long userId = existBean.getId();
		bean.setUserid(userId);
        bean.setDonarname(existBean.getFirstName());
   		populateDTO(bean, request);

   		return bean;
   	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletUtility.forward(getView(), request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Do Post");
		DonateModel model = new DonateModel();
		String op = DataUtility.getString(request.getParameter("operation"));
		long id = DataUtility.getLong(request.getParameter("id"));
		DonateBean bean = new DonateBean();
		bean = (DonateBean) populateBean(request);
		if (OP_RESET.equalsIgnoreCase(op)) {
			ServletUtility.redirect(OFDView.DONATION_CTL, request, response);
			return;
		}
		if (OP_SAVE.equalsIgnoreCase(op)) {
			try {
				bean = (DonateBean) populateBean(request);
				long pk = model.add(bean);
				ServletUtility.setBean(bean, request);
				ServletUtility.setSuccessMessage("Donation Saved Successfully", request);
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
		return OFDView.DONATION_VIEW;
	}

}
