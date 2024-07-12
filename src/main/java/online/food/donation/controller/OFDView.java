package online.food.donation.controller;

public interface OFDView {

	public String APP_CONTEXT = "/OnlineFoodDonation";

	public String LAYOUT_VIEW = "/BaseLayout.jsp";
	public String PAGE_FOLDER = "/jsp";

//	public String JAVA_DOC_VIEW = APP_CONTEXT + "/doc/index.html";

	public String ERROR_VIEW = PAGE_FOLDER + "/Error.jsp";

	public String REQUEST_DONATION_VIEW = PAGE_FOLDER + "/RequestDonationView.jsp";
	public String USER_LIST_VIEW = PAGE_FOLDER + "/UserListView.jsp";

	public String USER_VIEW = PAGE_FOLDER + "/UserView.jsp";

	public String NGO_LIST_VIEW = PAGE_FOLDER + "/NgoListView.jsp";

	public String USER_REGISTRATION_VIEW = PAGE_FOLDER + "/UserRegistrationView.jsp";
	public String NGO_REGISTRATION_VIEW = PAGE_FOLDER + "/NgoRegistrationView.jsp";

	public String LOGIN_VIEW = PAGE_FOLDER + "/LoginView.jsp";
	public String WELCOME_VIEW = PAGE_FOLDER + "/Welcome.jsp";
	public String MY_PROFILE_VIEW = PAGE_FOLDER + "/MyProfileView.jsp";
	public String FORGET_PASSWORD_VIEW = PAGE_FOLDER + "/ForgetPasswordView.jsp";

	public String ERROR_CTL = "/ctl/ErrorCtl";

	public String USER_LIST_CTL = APP_CONTEXT + "/UserListCtl";
	public String USER_CTL = APP_CONTEXT + "/UserCtl";
	public String NGO_LIST_CTL = APP_CONTEXT + "/NgoListCtl";

	public String REQUEST_DONATION_CTL = APP_CONTEXT + "/RequestDonationCtl";

	public String VIEW_DONATION_CTL = APP_CONTEXT + "/ViewDonationCtl";
	public String VIEW_DONATION_VIEW = PAGE_FOLDER + "/DonationView.jsp";

	public String DONATION_CTL = APP_CONTEXT + "/DonationCtl";
	public String DONATION_VIEW = PAGE_FOLDER + "/DonationFoodView.jsp";

	public String DONATION_LIST_CTL = APP_CONTEXT + "/DonationListCtl";
	public String DONATION_LIST_VIEW = PAGE_FOLDER + "/DonationFoodListView.jsp";

	public String REQUEST_FOOD_BY_NGO_CTL = APP_CONTEXT + "/RequestFoodByNgoCtl";
	public String REQUEST_FOOD_BY_NGO_VIEW = PAGE_FOLDER + "/RequestFoodByNgo.jsp";

	public String UPDATE_REQUEST_FOOD_CTL = APP_CONTEXT + "/UpdateRequestFoodCtl";
	public String UPDATE_REQUEST_FOOD_VIEW = PAGE_FOLDER + "/UpdateRequestFoodView.jsp";
	
	public String CHANGE_PASSWORD_CTL = APP_CONTEXT + "/ChangePasswordCtl";
	public String CHANGE_PASSWORD_VIEW = PAGE_FOLDER + "/ChangePasswordView.jsp";

	public String MYPROFILE_CTL = APP_CONTEXT + "/myprofile";

	public String MYPROFILE_VIEW = PAGE_FOLDER + "/MyprofileView.jsp";

	public String USER_REGISTRATION_CTL = APP_CONTEXT + "/UserRegistrationCtl";
	public String NGO_REGISTRATION_CTL = APP_CONTEXT + "/NgoRegistrationCtl";
	public String LOGIN_CTL = APP_CONTEXT + "/LoginCtl";
	public String WELCOME_CTL = APP_CONTEXT + "/WelcomeCtl";
	public String LOGOUT_CTL = APP_CONTEXT + "/LoginCtl";
	public String MY_PROFILE_CTL = APP_CONTEXT + "/ctl/MyProfileCtl";

}
