package online.food.donation.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import online.food.donation.bean.RequestFoodBean;
import online.food.donation.bean.UserBean;
import online.food.donation.model.RequestFoodModel;
import online.food.donation.utility.DataUtility;
import online.food.donation.utility.ServletUtility;

@WebServlet(name = "RequestFoodByNgoCtl", urlPatterns = { "/RequestFoodByNgoCtl" })
public class RequestFoodByNgoCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
       
    public RequestFoodByNgoCtl() {
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
		try {
			list = model.Donationlist();
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
		return OFDView.REQUEST_FOOD_BY_NGO_VIEW;
	}

}
