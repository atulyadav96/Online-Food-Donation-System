package online.food.donation.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import online.food.donation.bean.BaseBean;
import online.food.donation.bean.UserBean;
import online.food.donation.model.UserModel;
import online.food.donation.utility.DataUtility;
import online.food.donation.utility.DataValidator;
import online.food.donation.utility.PropertyReader;
import online.food.donation.utility.ServletUtility;

@WebServlet(name = "MyProfileCtl" , urlPatterns = "/myprofile")
public class MyProfileCtl extends BaseCtl {
	
    public MyProfileCtl() {
        super();
    }

    @Override
  	protected boolean validate(HttpServletRequest request) {
  		boolean pass = true;

  		String login = request.getParameter("login");
  		String gender = request.getParameter("gender");

  		if (DataValidator.isNull(request.getParameter("firstName"))) {
  			request.setAttribute("firstName", PropertyReader.getValue("error.require", "First Name"));
  			pass = false;
  		} else if (!DataValidator.isName(request.getParameter("firstName"))) {
  			request.setAttribute("firstName", PropertyReader.getValue("error.name", "First Name"));
  			pass = false;
  		}
  		if (DataValidator.isNull(request.getParameter("lastName"))) {
  			request.setAttribute("lastName", PropertyReader.getValue("error.require", "Last Name"));
  			pass = false;
  		} else if (!DataValidator.isName(request.getParameter("lastName"))) {
  			request.setAttribute("lastName", PropertyReader.getValue("error.name", "Last Name"));
  			pass = false;
  		}

  		if (DataValidator.isNull(login)) {
  			request.setAttribute("login", PropertyReader.getValue("error.require", "Login Id"));
  			pass = false;
  		} else if (!DataValidator.isEmail(request.getParameter("login"))) {
  			request.setAttribute("login", PropertyReader.getValue("error.email", "Login"));
  			pass = false;
  		}
  		if (DataValidator.isNull(request.getParameter("confirmPassword"))) {
  			request.setAttribute("confirmPassword", PropertyReader.getValue("error.require", "Confirm Password"));
  			pass = false;
  		}
  		if ("-----Select-----".equalsIgnoreCase(request.getParameter("gender"))) {
  			request.setAttribute("gender", PropertyReader.getValue("error.require", "Gender"));
  			pass = false;
  		}

  		if (DataValidator.isNull(request.getParameter("password"))) {
  			request.setAttribute("password", PropertyReader.getValue("error.require", "Password"));
  			pass = false;

  		} else if (!DataValidator.isPassword(request.getParameter("password"))) {
  			request.setAttribute("password", PropertyReader.getValue("error.password", "Password"));
  			return false;
  		} else if (!DataValidator.isPassword(request.getParameter("password"))) {
  			request.setAttribute("password", PropertyReader.getValue("error.password", "Password"));
  			return false;
  		}

  		if (!request.getParameter("password").equals(request.getParameter("confirmPassword"))
  				&& !"".equals(request.getParameter("confirmPassword"))) {
  			/*
  			 * ServletUtility.setErrorMessage("Confirm Password did not match", request);
  			 */
  			request.setAttribute("confirmPassword",
  					PropertyReader.getValue("error.confirmPassword", "Confirm Password"));
  			pass = false;
  		}

  		if (DataValidator.isNull(request.getParameter("mobile"))) {
  			request.setAttribute("mobile", PropertyReader.getValue("error.require", "Mobile No"));
  			pass = false;
  		} else if (!DataValidator.isPhoneNo(request.getParameter("mobile"))) {
  			request.setAttribute("mobile", PropertyReader.getValue("error.invalid", "Mobile No"));
  			pass = false;
  		}
  		if (DataValidator.isNull(request.getParameter("uniqueid"))) {
  			request.setAttribute("uniqueid", PropertyReader.getValue("error.require", "Unique Id"));
  			pass = false;
  		}
  		return pass;
  	}

  	/**
  	 * Populates bean object from request parameters
  	 * 
  	 * @param request
  	 * @return
  	 */
  	@Override
  	protected BaseBean populateBean(HttpServletRequest request) {

  		UserBean bean = new UserBean();

  		bean.setId(DataUtility.getLong(request.getParameter("id")));

  		bean.setRoleId(2L);

  		bean.setRolename("User");

  		bean.setFirstName(DataUtility.getString(request.getParameter("firstName")));

  		bean.setLastName(DataUtility.getString(request.getParameter("lastName")));

  		bean.setLogin(DataUtility.getString(request.getParameter("login")));

  		bean.setPassword(DataUtility.getString(request.getParameter("password")));

  		bean.setConfirmPassword(DataUtility.getString(request.getParameter("confirmPassword")));

  		bean.setGender(DataUtility.getString(request.getParameter("gender")));

  		// bean.setDob(DataUtility.getDate(request.getParameter("dob")));

  		bean.setMobileNo(DataUtility.getString(request.getParameter("mobile")));

  		bean.setUnique_id(DataUtility.getString(request.getParameter("uniqueid")));

  		populateDTO(bean, request);

  		return bean;
  	}


  	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  		UserModel model = new UserModel();
  		long id = DataUtility.getLong(request.getParameter("id"));
  		if (id > 0) {
  			UserBean bean = null;
  			try {
  				bean = model.findByPK(id);
  			} catch (Exception e) {
  				e.printStackTrace();
  			}
  			ServletUtility.setBean(bean, request);
  		}
  		ServletUtility.forward(getView(), request, response);
  		
  	}

  	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  		UserModel model = new UserModel();
  		String op = DataUtility.getString(request.getParameter("operation"));
  		long id = DataUtility.getLong(request.getParameter("id"));
  		UserBean bean = new UserBean();
  		bean = (UserBean) populateBean(request);
  	
  		if (id>0) {
  			long i = model.Update(bean);
  			ServletUtility.setSuccessMessage("Data Updated Successfully", request);
  		}
  		ServletUtility.forward(getView(), request, response);
  	}

  	@Override
  	protected String getView() {
  		return OFDView.USER_VIEW;
  	}

}
