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
import online.food.donation.utility.DataUtility;
import online.food.donation.utility.ServletUtility;


@WebServlet(name = "UpdateRequestFoodCtl", urlPatterns = { "/UpdateRequestFoodCtl" })
public class UpdateRequestFoodCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
       
    public UpdateRequestFoodCtl() {
        super();
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
   		
   		HttpSession session = request.getSession(false);
   		UserBean bean2 = (UserBean) session.getAttribute("user");
   		
   		bean.setSendername(bean2.getFirstName());
   		
   		bean.setSendercontactno(DataUtility.getLong(bean2.getMobileNo()));
   		
   		bean.setStatus("sentRequest");
   		populateDTO(bean, request);

   		return bean;
   	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestFoodModel model = new RequestFoodModel();
		long id = DataUtility.getLong(request.getParameter("id"));
		if (id > 0) {
			RequestFoodBean bean = null;
			try {
				bean = model.findByPk(id);
				System.out.println("In Do Get");
			} catch (Exception e) {
				e.printStackTrace();
			}
			ServletUtility.setBean(bean, request);
		}
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
				long pk = model.Update(bean);
				ServletUtility.setBean(bean, request);
				ServletUtility.setSuccessMessage("Requested Food send Successfully", request);
				ServletUtility.forward(getView(), request, response);
				return;
			} catch (Exception e) {
				e.printStackTrace();
			}
			ServletUtility.forward(getView(), request, response);
		}
	}

	@Override
	protected String getView() {
		return OFDView.UPDATE_REQUEST_FOOD_VIEW;
	}

}
