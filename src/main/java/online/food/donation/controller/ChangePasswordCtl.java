package online.food.donation.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import online.food.donation.bean.BaseBean;
import online.food.donation.bean.UserBean;
import online.food.donation.exception.ApplicationException;
import online.food.donation.exception.RecordNotFoundException;
import online.food.donation.model.UserModel;
import online.food.donation.utility.DataUtility;
import online.food.donation.utility.ServletUtility;

@WebServlet(name = "ChangePasswordCtl", urlPatterns = { "/ChangePasswordCtl" })
public class ChangePasswordCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
	public static final String OP_CHANGE_MY_PASSWORD = "Change My Password";
	public static final String OP_SAVE = "Save";

	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		UserBean bean = new UserBean();
		bean.setPassword(DataUtility.getString(request.getParameter("oldpwd")));
		populateDTO(bean, request);
		return bean;
	}

	public ChangePasswordCtl() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ServletUtility.forward(getView(), request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		String op = DataUtility.getString(request.getParameter("operation"));

		// get model
		UserModel model = new UserModel();

		UserBean bean = (UserBean) populateBean(request);

		UserBean UserBean = (UserBean) session.getAttribute("user");

		String newPassword = request.getParameter("newPassword");

		long id = UserBean.getId();
		System.out.println("ID----------" + id);

		if (OP_SAVE.equalsIgnoreCase(op)) {
				try {
					boolean flag;
					flag = model.changePassword(id, bean.getPassword(), newPassword);
					if (flag == true) {

						bean = model.findByLogin(UserBean.getLogin());
						session.setAttribute("user", bean);
						ServletUtility.setBean(bean, request);
						ServletUtility.setSuccessMessage("Password has been changed Successfully.", request);
						ServletUtility.forward(OFDView.CHANGE_PASSWORD_VIEW, request, response);
						return;

					}
				
				System.out.println("in do post");

				
			} catch (ApplicationException e) {
				e.printStackTrace();

			} catch (RecordNotFoundException e1) {
				ServletUtility.setErrorMessage("old password is invalid", request);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
                ServletUtility.forward(getView(), request, response);
	}

	@Override
	protected String getView() {
		return OFDView.CHANGE_PASSWORD_VIEW;
	}

}
